package com.zqsweb.zqscommon.utils.databindingutils

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.zqsweb.zqscommon.utils.GlideApp
import java.text.SimpleDateFormat
import java.util.*

/*
 *   @author zhangqisheng
 *   @date 2020-05-29 10:37
 *   @description 
 */

object DataBindingCommonUtils {
    /**
     * 设置imageview的图片资源
     */
    //@BindingAdapter("zqs_bind:src")
    fun setImageSrc(iv: ImageView, url: String) {
        GlideApp.with(iv).load(url).into(iv)
    }

    /**
     * 设置textview时间文本格式
     * pair:first->时间戳
     * pair:second->时间格式
     */
    //@BindingAdapter("zqs_bind:data")
    fun setTimeFormat(tv: TextView, pair: Pair<Long, String>) {
        var dataFormat = SimpleDateFormat(pair.second)
        tv.text = dataFormat.format(Date(pair.first * 1000))
    }


}

