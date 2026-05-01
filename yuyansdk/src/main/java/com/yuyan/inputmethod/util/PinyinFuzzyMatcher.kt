package com.yuyan.inputmethod.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 拼音模糊音匹配器
 *
 * 在 Rime 引擎之上提供额外的模糊音匹配功能。
 * 利用 unicode_to_hanyu_pinyin.txt 构建拼音→汉字反向映射表，
 * 当用户输入拼音时，用模糊规则生成变体拼音并查询匹配的汉字。
 *
 * 独立于 Rime 引擎，可用于任何输入方案（全键、九键、双拼）。
 */
object PinyinFuzzyMatcher {

    // 拼音→汉字正向映射（含声调）
    // 例如: "yin1" -> ["因", "音", "阴", ...]
    private val pinyinToCharsWithTone = mutableMapOf<String, MutableList<String>>()

    // 拼音→汉字映射（无音调）
    // 例如: "yin" -> ["因", "音", "阴", "殷", ...]
    private val pinyinToChars = mutableMapOf<String, MutableList<String>>()

    private var initialized = false

    /**
     * 初始化：从资源文件加载拼音映射表
     * 应在 Application.onCreate 或首次使用时调用
     */
    fun init(context: Context) {
        if (initialized) return

        try {
            val inputStream = context.assets.open("pinyindb/unicode_to_hanyu_pinyin.txt")
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val l = line ?: continue
                // 格式: UNICODE [pinyin1,pinyin2,...]
                // 例: 4E2D [zhong1,zhong4]
                val hexMatch = Regex("^([0-9A-Fa-f]+)\\s+\\[(.+)\\]$").find(l.trim())
                if (hexMatch != null) {
                    val unicodeHex = hexMatch.groupValues[1]
                    val pinyinList = hexMatch.groupValues[2].split(",")

                    // 将 Unicode hex 转为实际字符
                    val charCode = unicodeHex.toInt(16)
                    val charStr = String(Character.toChars(charCode))

                    for (pinyinWithTone in pinyinList) {
                        val pinyinTrimmed = pinyinWithTone.trim()
                        if (pinyinTrimmed.isEmpty()) continue

                        // 带音调的映射
                        pinyinToCharsWithTone
                            .getOrPut(pinyinTrimmed) { mutableListOf() }
                            .add(charStr)

                        // 去掉音调数字（末尾的数字）
                        val pinyinNoTone = pinyinTrimmed.replace(Regex("[1-5]$"), "")
                        if (pinyinNoTone.isNotEmpty()) {
                            pinyinToChars
                                .getOrPut(pinyinNoTone) { mutableListOf() }
                                .add(charStr)
                        }
                    }
                }
            }
            reader.close()
            initialized = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 模糊音规则
     * 每个规则定义两个相互可替换的拼音后缀
     */
    data class FuzzyRule(val leftSuffix: String, val rightSuffix: String)

    private val fuzzyRules = listOf(
        FuzzyRule("in", "ing"),     // in ↔ ing（金斤今 → 京经惊）
        FuzzyRule("en", "eng"),     // en ↔ eng（门们闷 → 蒙盟猛）
        FuzzyRule("an", "ang"),     // an ↔ ang（班般板 → 帮榜膀）
        FuzzyRule("ian", "iang"),   // ian ↔ iang（先显现 → 乡香箱）
        FuzzyRule("u", "ü"),        // u ↔ ü（书术树 → 虚需许）
        FuzzyRule("l", "n"),        // l ↔ n（老劳乐 → 脑闹南）
        FuzzyRule("s", "sh"),        // s ↔ sh（三四岁 → 山沙设）
        FuzzyRule("z", "zh"),       // z ↔ zh（杂灾再 → 扎窄债）
        FuzzyRule("c", "ch"),       // c ↔ ch（擦猜才 → 差察拆）
        FuzzyRule("f", "h"),        // f ↔ h（发反饭 → 花华话）
        FuzzyRule("k", "g"),        // k ↔ g（看砍刊 → 干敢感）
        FuzzyRule("r", "l"),        // r ↔ l（日入然 → 立力里）
        FuzzyRule("ou", "iu"),      // ou ↔ iu（收手首 → 修休秀）
        FuzzyRule("ai", "ei"),      // ai ↔ ei（白百败 → 被背北）
        FuzzyRule("ao", "ou"),      // ao ↔ ou（好号豪 → 后厚侯）
        FuzzyRule("ui", "uei"),     // ui ↔ uei（回会灰 → 位为威）
        FuzzyRule("ia", "ua"),      // ia ↔ ua（下夏虾 → 跨夸挂）
    )

    /**
     * 为指定拼音生成所有模糊变体
     *
     * @param pinyin 原始拼音（无音调，如 "yin"）
     * @return 模糊变体列表（含原始拼音本身）
     */
    fun getFuzzyVariants(pinyin: String): List<String> {
        val result = mutableSetOf(pinyin)

        for (rule in fuzzyRules) {
            // 规则 A: 原始拼音以 leftSuffix 结尾 → 替换为 rightSuffix
            if (pinyin.endsWith(rule.leftSuffix)) {
                val prefix = pinyin.substring(0, pinyin.length - rule.leftSuffix.length)
                result.add(prefix + rule.rightSuffix)
            }
            // 规则 B: 原始拼音以 rightSuffix 结尾 → 替换为 leftSuffix
            if (pinyin.endsWith(rule.rightSuffix)) {
                val prefix = pinyin.substring(0, pinyin.length - rule.rightSuffix.length)
                result.add(prefix + rule.leftSuffix)
            }
        }

        return result.toList()
    }

    /**
     * 获取指定拼音（含变体）匹配的汉字列表
     *
     * @param pinyins 拼音列表（每个可以是单音节或多音节拼音，如 ["in"] 或 ["ni", "hao"]）
     * @return 模糊匹配的汉字
     */
    fun getFuzzyChars(pinyins: List<String>): List<String> {
        if (!initialized || pinyins.isEmpty()) return emptyList()

        val result = mutableSetOf<String>()

        for (pinyin in pinyins) {
            val variants = getFuzzyVariants(pinyin)
            for (variant in variants) {
                pinyinToChars[variant]?.let { result.addAll(it) }
            }
        }

        return result.toList()
    }

    /**
     * 从拼写字符串中提取拼音列表
     * 例如: "yinyue" → ["yin", "yue"]（需要分割）
     *        "ni'hao" → ["ni", "hao"]
     *
     * 注意：对于全键拼音，输入是连续的字母串（没有分隔符）。
     * Rime 引擎内部处理切分，我们这里只能做近似处理：
     * - 如果有单引号 ' 分隔符，按 ' 分割
     * - 如果没有，使用简单规则尝试切分
     */
    fun extractPinyins(composition: String): List<String> {
        // 过滤掉非 ASCII 字符（如已上屏的中文）
        val asciiPart = composition.filter { it.code <= 0xFF }

        // 有单引号分隔符
        if ("'" in asciiPart) {
            return asciiPart.split("'").filter { it.isNotBlank() }
        }

        // 无分隔符（连续字母）— 返回整个字符串
        // Rime 引擎内部会做音节分割，我们这里不做切割
        // 因为模糊音按后缀匹配，输入短拼音（如 "ren"）直接使用
        if (asciiPart.isNotBlank()) {
            return listOf(asciiPart)
        }

        return emptyList()
    }

    /**
     * 从候选词的 comment 中提取拼音
     * Rime 候选词有时会带 comment 显示拼音
     */
    fun getFuzzyCandidates(compositionPinyin: String): List<String> {
        val pinyins = extractPinyins(compositionPinyin)
        return getFuzzyChars(pinyins)
    }

    /**
     * 检查两个拼音是否模糊匹配
     */
    fun isFuzzyMatch(pinyinA: String, pinyinB: String): Boolean {
        return pinyinB in getFuzzyVariants(pinyinA)
    }

    /**
     * 获取模糊音规则的描述文本
     */
    fun getRulesSummary(): String {
        return fuzzyRules.joinToString("、") { "${it.leftSuffix}↔${it.rightSuffix}" }
    }
}
