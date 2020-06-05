package com.zqsweb.zqscommon.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

object UIutils {
    /*
    * 给文本添加富文本
    * */
    fun getRichTextSP(all: String, replaceValue: String, color: String): SpannableString {
        var ss = SpannableString(all)
        var index: Int = all.indexOf(replaceValue)
        ss.setSpan(ForegroundColorSpan(Color.parseColor(color)), index, index + replaceValue.length - 1, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
        return ss
    }
}