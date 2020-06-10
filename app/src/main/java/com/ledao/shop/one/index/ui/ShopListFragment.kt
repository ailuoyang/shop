package com.ledao.shop.one.index.ui

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ledao.shop.R
import com.ledao.shop.databinding.AdapterItemShopFlowBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.BaseFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import com.zqsweb.zqscommon.utils.AppUtils
import com.zqsweb.zqscommon.utils.RandomUtils
import com.zqsweb.zqscommon.utils.TUtils
import kotlinx.android.synthetic.main.all_fragment_shop_list.*
import rxhttp.wrapper.param.FormParam

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description 商品列表
 **/
@LayoutId(R.layout.all_fragment_shop_list)
class ShopListFragment() : BaseFragment() {

    lateinit var mParams: () -> FormParam
    lateinit var mLoad: () -> Unit
    lateinit var mRefresh: () -> Unit
    var mShopAdapter:ShopAdapter=ShopAdapter()
    constructor(params: () -> FormParam, load: () -> Unit = {}, refresh: () -> Unit = {}) : this() {
        mParams = params
        mLoad = load
        mRefresh = refresh
    }

    /*
    * 刷新
    * */
    fun refresh() {
        mShopAdapter.setNewInstance(getShops())
    }

    /*
    * 加载
    * */
    fun load() {
        mShopAdapter.addData(getShops())
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
        list.apply {
            var stagger = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            stagger.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            layoutManager = stagger
            mShopAdapter.setNewInstance(getShops())
            setItemViewCacheSize(100)
            adapter = mShopAdapter
        }
    }

    fun getShops(): MutableList<ShopBean> {
        var shops = mutableListOf<ShopBean>()
        for (item in 1..50) {
            var shop = ShopBean()
            shop.title = "标题$item"
            shop.content = "内容$item"
            var ran: Int = RandomUtils.randomInt(1, 20)
            repeat(ran) {
                shop.content += "内容"
            }
            shop.imageUrl = AppUtils.getRandomCartoonImage()
            shop.payNum = 2349
            shop.price = 520f
            shops.add(shop)
        }
        return shops
    }

    /*
    * 商品列表适配器
    * */
    class ShopAdapter :
        ZqsDBAdapter<ShopBean, AdapterItemShopFlowBinding>(R.layout.adapter_item_shop_flow) {

        override fun convert(
            holder: ZqsDBViewHolder<AdapterItemShopFlowBinding>,
            item: ShopBean
        ) {
            super.convert(holder, item)
            holder.dataBinding?.model = item
            holder.dataBinding?.root?.setOnClickListener {
                TUtils.show("点击商品列表:" + getItemPosition(item))
            }
        }
    }

    class ShopBean {
        var title: String? = "标题"
        var content: String? = "内容"
        var imageUrl: String =
            "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture"
        var price: Float? = 520f
        var payNum: Long? = 439
    }

}