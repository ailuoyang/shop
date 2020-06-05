package com.zqsweb.zqscommon.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.utils.LogUtils

/*
 *   @author zhangqisheng
 *   @date 2020-05-29 13:22
 *   @description 
 */
open abstract class DataBindingActivity<DB : ViewDataBinding?> : BaseActivity() {

    private var mDataing: DB? = null
        get() = field

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataing?.let {
            mDataing = DataBindingUtil.inflate<DB>(layoutInflater, getAnnoLayoutId(), null, false)
        }
    }

    fun getAnnoLayoutId(): Int {
        if (javaClass.isAnnotationPresent(LayoutId::class.java)) {
            var clazz = javaClass.getAnnotation(LayoutId::class.java)
            LogUtils.v("获得fragment布局注解")
            clazz?.let {
                return it.value
            }
        }
        LogUtils.v("获得fragment布局注解失败,使用getLayoutId获得")
        throw IllegalArgumentException("请使用FragmentLayoutId注解" + this.javaClass.name)
    }

    fun getDB() = mDataing

}