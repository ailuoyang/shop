package com.ledao.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.SimpleOnTabSelectedListener
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ledao.shop.databinding.ActivityMainBinding
import com.ledao.shop.one.cart.CartFragment
import com.ledao.shop.one.index.IndexFragment
import com.ledao.shop.one.mulitype.MulitypeFragment
import com.ledao.shop.one.my.MyFragment
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingActivity
import java.util.*

@LayoutId(R.layout.activity_main)
class MainActivity : DataBindingActivity<ActivityMainBinding>() {
    val mMainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragments: MutableList<Fragment> = ArrayList()
        var indexFragment=IndexFragment()
        var mulitypeFragment=MulitypeFragment()
        var cartFragment= CartFragment()
        var myFragment=MyFragment()
        fragments.add(indexFragment)
        fragments.add(mulitypeFragment)
        fragments.add(cartFragment)
        fragments.add(myFragment)
        getDB().mainVp.setAdapter(object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }

            override fun getItemCount(): Int {
                return fragments.size
            }
        })
        getDB().mainVp.setUserInputEnabled(false)
        getDB().mainVp.setOffscreenPageLimit(fragments.size)
        var indexItem= BottomNavigationItem(R.mipmap.ic_index_select, "商城")
        indexItem.setInactiveIconResource(R.mipmap.ic_index)
        var typeItem= BottomNavigationItem(R.mipmap.ic_type_select, "分类")
        typeItem.setInactiveIconResource(R.mipmap.ic_type)
        var cartItem= BottomNavigationItem(R.mipmap.ic_shopping_cart_select, "购物车")
        cartItem.setInactiveIconResource(R.mipmap.ic_shopping_cart)
        var myItem= BottomNavigationItem(R.mipmap.ic_my_select, "我的")
        myItem.setInactiveIconResource(R.mipmap.ic_my)
        getDB().nav
            .setMode(BottomNavigationBar.MODE_FIXED)
            .setTabSelectedListener(object : SimpleOnTabSelectedListener() {
                override fun onTabSelected(position: Int) {
                    super.onTabSelected(position)
                    getDB().mainVp.setCurrentItem(position, false)
                }
            })
            .setActiveColor("#000000")
            .setInActiveColor("#666666")
            .addItem(indexItem)
            .addItem(typeItem)
            .addItem(cartItem)
            .addItem(myItem)
            .initialise()
    }
}
