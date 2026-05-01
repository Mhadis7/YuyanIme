package com.yuyan.imemodule.prefs.behavior

import com.yuyan.imemodule.view.preference.ManagedPreference

/**
 * 模糊音模式设置
 * 支持常见的模糊音对
 */
enum class FuzzyPinyinMode(val label: String) {
    Off("关闭"),
    Simple("基础模糊音"),
    Comprehensive("全面模糊音"),
    Custom("自定义");

    companion object : ManagedPreference.StringLikeCodec<FuzzyPinyinMode> {
        override fun decode(raw: String): FuzzyPinyinMode? =
            entries.find { it.name == raw }
    }
}
