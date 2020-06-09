package com.zqsweb.zqscommon.base

import android.view.View
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/


/*
 *   @author zhangqisheng
 *   @date 2020-05-08 11:28
 *   @description
 */
open class ZqsDBViewHolder<DB : ViewDataBinding>(view: View) :BaseDataBindingHolder<DB>(view) {

    fun <T> bind(t: T,position:Int) {

    }

}
