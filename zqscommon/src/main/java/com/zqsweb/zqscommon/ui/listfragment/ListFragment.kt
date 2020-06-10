package com.zqsweb.zqscommon.ui.listfragment

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.zqsweb.zqscommon.R
import com.zqsweb.zqscommon.base.BaseFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import kotlinx.android.synthetic.main.zqs_common_fragment_list.*
import rxhttp.wrapper.param.FormParam

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description 针对只有一个列表的页面的抽象
 **/
class ListFragment<Model, DB : ViewDataBinding>() : BaseFragment() {

    lateinit var mParams: () -> FormParam
    lateinit var mLoad: () -> Unit
    lateinit var mRefresh: () -> Unit
    lateinit var mListLayoutId: () -> Int
    lateinit var mConvert: (holder: ZqsDBViewHolder<DB>, item: Model) -> Unit

    fun convert(convert: (holder: ZqsDBViewHolder<DB>, item: Model) -> Unit) {
        mConvert = convert
    }

    /*
    * 请求参数
    * */
    fun params(params: () -> FormParam) {
        mParams = params
    }

    /*
    * 加载监听
    * */
    fun load(load: () -> Unit) {
        mLoad = load
    }

    /*
    * 刷新监听
    * */
    fun refresh(refresh: () -> Unit) {
        mRefresh = refresh
    }

    /*
    * list的布局id
    * */
    fun listLayoutId(layoutId: () -> Int) {
        mListLayoutId = layoutId
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
        zqs_list.apply {
            layoutManager = LinearLayoutManager(context)
            var mAdapter = ListAdapter(mListLayoutId())
            adapter = mAdapter
        }
    }

    override fun getAnnoLayoutId(): Int {
        return R.layout.zqs_common_fragment_list
    }

    inner class ListAdapter(layoutId: Int) : ZqsDBAdapter<Model, DB>(layoutId) {
        override fun convert(holder: ZqsDBViewHolder<DB>, item: Model) {
            super.convert(holder, item)
            mConvert(holder,item)
        }
    }

}
