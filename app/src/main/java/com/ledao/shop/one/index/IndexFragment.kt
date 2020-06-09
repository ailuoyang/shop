package com.ledao.shop.one.index

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.gyf.immersionbar.ImmersionBar
import com.ledao.shop.R
import com.ledao.shop.databinding.AdapterItemIndexShopFlowBinding
import com.ledao.shop.databinding.IndexIndexFragmentBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import com.zqsweb.zqscommon.utils.*


/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description
 **/

@LayoutId(R.layout.index_index_fragment)
class IndexFragment : DataBindingFragment<IndexIndexFragmentBinding>() {
    val mViewModel by lazy {
        ViewModelProvider(this).get(IndexViewModel::class.java)
    }

    override fun onFirstLoad() {
        super.onFirstLoad()
        db.indexBannerVp.apply {
            var bannerBeans: MutableList<BannerBean> = mutableListOf()
            bannerBeans.add(BannerBean())
            bannerBeans.add(BannerBean())
            bannerBeans.add(BannerBean())
            bannerBeans.add(BannerBean())
            bannerBeans.add(BannerBean())
            bannerBeans.add(BannerBean())
            var adapter = BannerAdapter(R.layout.adapter_item_index_banner)
            setAdapter(adapter)
            setAutoPlay(true)
            setIndicatorVisibility(View.GONE)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    LogUtils.v("index_banner_滑动到了${position}")

                }
            })
            create(bannerBeans)
        }
        db.indexList.apply {
            var stagger = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            stagger.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            layoutManager = stagger
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
            var shopAdapter = ShopAdapter()
            shopAdapter.setHasStableIds(true)
            shopAdapter.setNewInstance(shops)
            setItemViewCacheSize(100)
            adapter = shopAdapter
        }
        db.indexFlush.apply {
            setOnRefreshListener{
                TUtils.show("上啦刷新")
                finishRefresh(1500)
            }

            setOnLoadMoreListener {
                TUtils.show("下啦刷新")
                finishLoadMore(1500)
            }
        }
        db.holder = Holder()
        db.model = Model()

    }

    inner class Holder {

        fun clickSearch() {
            TUtils.show("搜索")
        }

        /*
        * 消息
        * */
        fun clickMessage() {
            TUtils.show("消息")
        }

        /*
        * 免费领
        * */
        fun click1() {
            TUtils.show("免费领")
        }

        /*
        * 领券
        * */
        fun click2() {
            TUtils.show("领券")
        }

        /*
        * 团购
        * */
        fun click3() {
            TUtils.show("团购")
        }

        /*
        * 精选
        * */
        fun click4() {
            TUtils.show("精选")
        }

        /*
        * 爆款
        * */
        fun click5() {
            TUtils.show("爆款")
        }
    }

    class Model {
        var imageUrl1=AppUtils.getRandomImage()
        var imageUrl2=AppUtils.getRandomImage()
    }

    /*
    * banner适配器
    * */
    class BannerAdapter(layoutId: Int) :
        BaseBannerAdapter<BannerBean, BannerAdapter.BannerViewHolder>() {

        var mLayoutId: Int = layoutId

        override fun getLayoutId(viewType: Int): Int {
            return mLayoutId
        }

        override fun createViewHolder(itemView: View?, viewType: Int): BannerViewHolder {
            return BannerViewHolder(itemView!!)
        }

        override fun onBind(
            holder: BannerViewHolder?,
            data: BannerBean?,
            position: Int,
            pageSize: Int
        ) {
            holder?.bindData(data!!, position, pageSize)
        }

        inner class BannerViewHolder(itemView: View) : BaseViewHolder<BannerBean>(itemView) {
            override fun bindData(data: BannerBean, position: Int, pageSize: Int) {
                GlideApp.with(itemView).load(data.url).into(findView(R.id.banner_image))
            }
        }
    }

    /*
    * 商品列表适配器
    * */
    class ShopAdapter :
        ZqsDBAdapter<ShopBean, AdapterItemIndexShopFlowBinding>(R.layout.adapter_item_index_shop_flow) {

        override fun convert(
            holder: ZqsDBViewHolder<AdapterItemIndexShopFlowBinding>,
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

    class BannerBean {
        var url: String = AppUtils.getRandomImage()
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
            .keyboardEnable(false).statusBarDarkFont(true).navigationBarDarkIcon(false).init()
    }
}