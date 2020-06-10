package com.ledao.shop.one.my

import androidx.lifecycle.ViewModelProvider
import com.ledao.shop.R
import com.ledao.shop.databinding.MyMyFragmentBinding
import com.ledao.shop.one.index.ui.ShopListFragment
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

    lateinit var mShopListFragment:ShopListFragment

    override fun onFirstLoad() {
        super.onFirstLoad()
        db.myList.apply {
            mShopListFragment= ShopListFragment()
            childFragmentManager.beginTransaction().add(id,mShopListFragment).commit()
        }
    }

    inner class Holder{

    }

    class MyModel{

    }

}