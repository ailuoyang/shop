package com.ledao.shop.one.cart

import androidx.lifecycle.ViewModelProvider
import com.ledao.shop.R
import com.ledao.shop.databinding.CartCartFragmentBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/
@LayoutId(R.layout.cart_cart_fragment)
class CartFragment : DataBindingFragment<CartCartFragmentBinding>() {

    val mViewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onFirstLoad() {
        super.onFirstLoad()

    }

}