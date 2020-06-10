package com.ledao.shop.one.cart

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ledao.shop.R
import com.ledao.shop.databinding.AdapterItemCartCartlistBinding
import com.ledao.shop.databinding.CartCartFragmentBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import com.zqsweb.zqscommon.utils.TUtils

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
        db.cartList.apply {
            layoutManager=LinearLayoutManager(context)
            var mCartAdapter=CartAdapter()
            var carts= mutableListOf<CartModel>()
            for (item in 0..100) {
                var cart:CartModel= CartModel()
                carts.add(cart)
            }
            mCartAdapter.setNewInstance(carts)
            adapter=mCartAdapter
        }
    }

    class CartAdapter: ZqsDBAdapter<CartModel, AdapterItemCartCartlistBinding>(R.layout.adapter_item_cart_cartlist){
        override fun convert(
            holder: ZqsDBViewHolder<AdapterItemCartCartlistBinding>,
            item: CartModel
        ) {
            super.convert(holder, item)
            holder.dataBinding?.model=item
            holder.dataBinding?.holder=Holder()
        }


        inner class Holder{
            /*
            * 删除
            * */
            fun clickDelete() {
                TUtils.show("删除")
            }

            /*
            * 收藏
            * */
            fun clickStar() {
                TUtils.show("收藏")
            }

            /*
            * 相似
            * */
            fun clickSimilar() {
                TUtils.show("相似")
            }

        }

    }


    class CartModel{

    }

}