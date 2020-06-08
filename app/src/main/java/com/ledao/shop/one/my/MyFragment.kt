package com.ledao.shop.one.my

import androidx.lifecycle.ViewModelProvider
import com.ledao.shop.R
import com.ledao.shop.databinding.MyMyFragmentBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/
@LayoutId(R.layout.my_my_fragment)
class MyFragment : DataBindingFragment<MyMyFragmentBinding>() {

    val mViewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }


    override fun onFirstLoad() {
        super.onFirstLoad()

    }
}