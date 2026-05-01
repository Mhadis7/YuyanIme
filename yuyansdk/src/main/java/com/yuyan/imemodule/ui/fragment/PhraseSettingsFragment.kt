package com.yuyan.imemodule.ui.fragment

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceScreen
import com.yuyan.imemodule.R
import com.yuyan.imemodule.database.DataBaseKT
import com.yuyan.imemodule.database.entry.Phrase
import com.yuyan.imemodule.libs.pinyin4j.PinyinHelper
import com.yuyan.imemodule.ui.fragment.base.CsPreferenceFragment
import com.yuyan.imemodule.utils.addPreference
import com.yuyan.imemodule.utils.importErrorDialog
import com.yuyan.imemodule.utils.queryFileName
import com.yuyan.inputmethod.util.LX17PinYinUtils
import com.yuyan.inputmethod.util.T9PinYinUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 词库/常用语设置页面
 * 支持：查看词组列表、批量导入、清空词库
 */
class PhraseSettingsFragment : CsPreferenceFragment() {

    private lateinit var importLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        importLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri == null) return@registerForActivityResult
            importPhrasesFromFile(uri)
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceScreen = preferenceManager.createPreferenceScreen(requireContext()).apply {
            addPreference(R.string.phrases_manage_title) {
                // 跳转到在键盘中显示的常用语管理（点击后直接从现有常用语面板查看）
                // 打开文件选择器导入
            }

            addPreference(R.string.phrases_import_title, R.string.phrases_import_summary) {
                importLauncher.launch("text/*")
            }

            addPreference(R.string.phrases_clear_title, R.string.phrases_clear_summary) {
                val ctx = requireContext()
                AlertDialog.Builder(ctx)
                    .setIconAttribute(android.R.attr.alertDialogIcon)
                    .setTitle(R.string.phrases_clear_title)
                    .setMessage(R.string.phrases_clear_confirm)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        lifecycleScope.launch {
                            withContext(Dispatchers.IO) {
                                DataBaseKT.instance.phraseDao().deleteAll()
                            }
                        }
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }

            val phrases = DataBaseKT.instance.phraseDao().getAll()
            if (phrases.isNotEmpty()) {
                val category = androidx.preference.PreferenceCategory(context).apply {
                    isIconSpaceReserved = false
                    title = context.getString(R.string.phrases_current_list, phrases.size.toString())
                }
                addPreference(category)
                for (phrase in phrases) {
                    val pref = androidx.preference.Preference(context).apply {
                        isIconSpaceReserved = false
                        isSingleLineTitle = false
                        title = phrase.content
                        summary = phrase.qwerty
                        setOnPreferenceClickListener {
                            AlertDialog.Builder(context)
                                .setTitle(phrase.content)
                                .setMessage("快捷码：${phrase.qwerty}\nT9：${phrase.t9}\n乱序17：${phrase.lx17}")
                                .setPositiveButton(android.R.string.ok, null)
                                .setNegativeButton(R.string.delete) { _, _ ->
                                    lifecycleScope.launch {
                                        withContext(Dispatchers.IO) {
                                            DataBaseKT.instance.phraseDao().deleteByContent(phrase.content)
                                        }
                                        // 重新加载
                                        requireActivity().supportFragmentManager
                                            .beginTransaction()
                                            .detach(this@PhraseSettingsFragment)
                                            .attach(this@PhraseSettingsFragment)
                                            .commit()
                                    }
                                }
                                .show()
                            true
                        }
                    }
                    category.addPreference(pref)
                }
            }
        }
    }

    private fun importPhrasesFromFile(uri: Uri) {
        val ctx = requireContext()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val inputStream = ctx.contentResolver.openInputStream(uri) ?: return@withContext
                    val text = inputStream.bufferedReader().use { it.readText() }
                    inputStream.close()

                    var imported = 0
                    var skipped = 0

                    for (line in text.lines()) {
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith("//")) continue

                        // 支持格式：
                        // 1. "词语" — 自动生成快捷码
                        // 2. "词语 快捷码" — 指定快捷码
                        val parts = trimmed.split(Regex("[\\s,，]+"), limit = 2)
                        val content = parts[0].trim()
                        if (content.isEmpty()) continue

                        // 检查是否已存在
                        val existing = try {
                            DataBaseKT.instance.phraseDao().queryByContent(content)
                        } catch (e: Exception) { null }

                        if (existing != null) {
                            skipped++
                            continue
                        }

                        val qwerty = if (parts.size > 1) {
                            parts[1].trim().take(4)
                        } else {
                            PinyinHelper.getPinYinHeadChar(content).take(4)
                        }

                        if (qwerty.isNotBlank()) {
                            val t9 = qwerty.map { T9PinYinUtils.pinyin2T9Key(it) }.joinToString("")
                            val lx17 = qwerty.map { LX17PinYinUtils.pinyin2Lx17Key(it) }.joinToString("")
                            val phrase = Phrase(
                                content = content,
                                t9 = t9,
                                qwerty = qwerty,
                                lx17 = lx17
                            )
                            DataBaseKT.instance.phraseDao().insert(phrase)
                            imported++
                        } else {
                            skipped++
                        }
                    }

                    withContext(Dispatchers.Main) {
                        AlertDialog.Builder(ctx)
                            .setTitle(R.string.phrases_import_result)
                            .setMessage(ctx.getString(R.string.phrases_import_summary_result, imported.toString(), skipped.toString()))
                            .setPositiveButton(android.R.string.ok) { _, _ ->
                                // 重新加载页面
                                requireActivity().supportFragmentManager
                                    .beginTransaction()
                                    .detach(this@PhraseSettingsFragment)
                                    .attach(this@PhraseSettingsFragment)
                                    .commit()
                            }
                            .show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        ctx.importErrorDialog(e)
                    }
                }
            }
        }
    }
}
