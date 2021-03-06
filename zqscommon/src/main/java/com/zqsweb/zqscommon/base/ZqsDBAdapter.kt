package com.zqsweb.zqscommon.base

import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/
open class ZqsDBAdapter<T,DB:ViewDataBinding>(layoutId:Int): BaseQuickAdapter<T, ZqsDBViewHolder<DB>>(layoutId) {

    public override fun convert(holder: ZqsDBViewHolder<DB>, item: T) {
        holder.bind(item,getItemPosition(item))
        //用于监听recyclerview是否复用
        //LogUtils.v("adapter_listener", "${this.javaClass.name}:${getItemPosition(item)}")
    }
}