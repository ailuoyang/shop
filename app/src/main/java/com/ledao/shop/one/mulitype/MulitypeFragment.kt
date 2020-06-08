package com.ledao.shop.one.mulitype

import androidx.lifecycle.ViewModelProvider
import com.ledao.shop.R
import com.ledao.shop.databinding.MulitypeMulitypeFragmentBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/

@LayoutId(R.layout.mulitype_mulitype_fragment)
class MulitypeFragment : DataBindingFragment<MulitypeMulitypeFragmentBinding>() {

    val mViewModel by lazy {
        ViewModelProvider(this).get(MulitypeViewModel::class.java)
    }

    override fun onFirstLoad() {
        super.onFirstLoad()

    }
}