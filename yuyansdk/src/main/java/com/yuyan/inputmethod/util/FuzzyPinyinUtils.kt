package com.yuyan.inputmethod.util

import com.yuyan.imemodule.prefs.AppPrefs
import com.yuyan.imemodule.prefs.behavior.FuzzyPinyinMode
import com.yuyan.inputmethod.core.Rime

/**
 * 模糊音工具类
 * 通过 Rime 的 setOption API 控制模糊音开关
 *
 * 对应 pinyin.schema.yaml 中的 option:
 * - fuzzy_basic:    基础模糊音（in↔ing, en↔eng, an↔ang）
 * - fuzzy_advanced: 全面模糊音（所有声母韵母模糊规则）
 *
 * 关闭 = 两个 option 都关
 * 简单 = 只开启 fuzzy_basic
 * 全面 = 开启 fuzzy_basic + fuzzy_advanced
 */
object FuzzyPinyinUtils {

    private const val OPT_BASIC = "fuzzy_basic"
    private const val OPT_ADVANCED = "fuzzy_advanced"

    /**
     * 应用当前的模糊音设置到 Rime 引擎
     * 调用时机：切换输入方案或改变设置时
     */
    fun applyCurrentSetting() {
        val mode = AppPrefs.getInstance().input.fuzzyPinyinMode.getValue()
        applyMode(mode)
    }

    private fun applyMode(mode: FuzzyPinyinMode) {
        when (mode) {
            FuzzyPinyinMode.Off -> {
                Rime.setOption(OPT_BASIC, false)
                Rime.setOption(OPT_ADVANCED, false)
            }
            FuzzyPinyinMode.Simple -> {
                Rime.setOption(OPT_BASIC, true)
                Rime.setOption(OPT_ADVANCED, false)
            }
            FuzzyPinyinMode.Comprehensive -> {
                Rime.setOption(OPT_BASIC, true)
                Rime.setOption(OPT_ADVANCED, true)
            }
            FuzzyPinyinMode.Custom -> {
                // 预留：自定义规则
                Rime.setOption(OPT_BASIC, false)
                Rime.setOption(OPT_ADVANCED, false)
            }
        }
    }

    /**
     * 获取当前已启用的模糊音规则描述
     */
    fun getEnabledRules(): List<String> {
        val mode = AppPrefs.getInstance().input.fuzzyPinyinMode.getValue()
        return when (mode) {
            FuzzyPinyinMode.Off -> emptyList()
            FuzzyPinyinMode.Simple -> listOf("基础模糊音（in/ing, en/eng, an/ang）")
            FuzzyPinyinMode.Comprehensive -> listOf(
                "全面模糊音（含声母、韵母全部规则）"
            )
            FuzzyPinyinMode.Custom -> emptyList()
        }
    }

    /**
     * 模糊音是否已开启（非关闭状态）
     */
    fun isEnabled(): Boolean {
        return AppPrefs.getInstance().input.fuzzyPinyinMode.getValue() != FuzzyPinyinMode.Off
    }
}
