package com.yuyan.inputmethod.util

import com.yuyan.imemodule.prefs.AppPrefs
import com.yuyan.imemodule.prefs.behavior.FuzzyPinyinMode
import com.yuyan.inputmethod.core.Rime

/**
 * 模糊音工具类
 * 通过 Rime 的 setOption API 控制模糊音开关
 */
object FuzzyPinyinUtils {

    /**
     * 应用当前的模糊音设置到 Rime 引擎
     * 调用时机：切换输入方案或改变设置时
     */
    fun applyCurrentSetting() {
        val mode = AppPrefs.getInstance().input.fuzzyPinyinMode.getValue()
        applyMode(mode)
    }

    /**
     * 根据模式应用模糊音配置
     * Rime 支持通过 option 控制模糊音：
     * - fuzzy_en_eng: en/eng 不分
     * - fuzzy_in_ing: in/ing 不分
     * - fuzzy_an_ang: an/ang 不分
     * 更多规则详见 comprehensiveRules
     */
    private val simpleRules = setOf(
        "fuzzy_en_eng",
        "fuzzy_in_ing",
        "fuzzy_an_ang",
    )

    private val comprehensiveRules = setOf(
        "fuzzy_en_eng",
        "fuzzy_in_ing",
        "fuzzy_an_ang",
        "fuzzy_ian_iang",
        "fuzzy_u_ü",
        "fuzzy_l_n",
        "fuzzy_s_sh",
        "fuzzy_z_zh",
        "fuzzy_c_ch",
        "fuzzy_f_h",
        "fuzzy_k_g",
        "fuzzy_r_l",
        "fuzzy_ou_iu",
        "fuzzy_ai_ei",
        "fuzzy_ao_ou",
        "fuzzy_ui_uei",
        "fuzzy_ia_ua",
    )

    private fun applyMode(mode: FuzzyPinyinMode) {
        // 先关闭所有模糊音
        val allRules = simpleRules + comprehensiveRules
        for (rule in allRules) {
            Rime.setOption(rule, false)
        }

        when (mode) {
            FuzzyPinyinMode.Off -> { /* 已全部关闭 */ }
            FuzzyPinyinMode.Simple -> {
                for (rule in simpleRules) {
                    Rime.setOption(rule, true)
                }
            }
            FuzzyPinyinMode.Comprehensive -> {
                for (rule in comprehensiveRules) {
                    Rime.setOption(rule, true)
                }
            }
            FuzzyPinyinMode.Custom -> { /* 预留 */ }
        }
    }

    /**
     * 获取当前已启用的模糊音规则列表
     */
    fun getEnabledRules(): List<String> {
        return (simpleRules + comprehensiveRules).filter { rule ->
            val mode = AppPrefs.getInstance().input.fuzzyPinyinMode.getValue()
            when (mode) {
                FuzzyPinyinMode.Off -> false
                FuzzyPinyinMode.Simple -> rule in simpleRules
                FuzzyPinyinMode.Comprehensive -> true
                FuzzyPinyinMode.Custom -> false
            }
        }
    }

    /**
     * 模糊音是否已开启（非关闭状态）
     */
    fun isEnabled(): Boolean {
        return AppPrefs.getInstance().input.fuzzyPinyinMode.getValue() != FuzzyPinyinMode.Off
    }
}
