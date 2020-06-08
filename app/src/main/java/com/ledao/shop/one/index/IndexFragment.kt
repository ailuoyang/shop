package com.ledao.shop.one.index

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.flexbox.*
import com.ledao.shop.R
import com.ledao.shop.databinding.AdapterItemIndexShopFlowBinding
import com.ledao.shop.databinding.IndexIndexFragmentBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import com.zqsweb.zqscommon.utils.AppUtils
import com.zqsweb.zqscommon.utils.GlideApp
import com.zqsweb.zqscommon.utils.LogUtils


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
            var bannerBeans:MutableList<BannerBean> = mutableListOf()
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
            var stagger= FlexboxLayoutManager(context)
            //stagger.alignContent=AlignContent.SPACE_AROUND
            stagger.alignItems=AlignItems.BASELINE
            stagger.flexDirection=FlexDirection.ROW
            stagger.flexWrap=FlexWrap.WRAP
            stagger.justifyContent=JustifyContent.SPACE_AROUND
            layoutManager=stagger
            var shops= mutableListOf<ShopBean>()
            for (item in 1..100) {
                var shop= ShopBean()
                shops.add(shop)
            }
            var shopAdapter=ShopAdapter()
            shopAdapter.setNewInstance(shops)
            adapter=shopAdapter
        }
        db.holder=Holder()
        db.model=Model()

    }

    inner class Holder {

        /*
        * 消息
        * */
        fun clickMessage() {

        }

        /*
        * 免费领
        * */
        fun click1() {

        }

        /*
        * 领券
        * */
        fun click2() {

        }

        /*
        * 团购
        * */
        fun click3() {

        }

        /*
        * 精选
        * */
        fun click4() {

        }

        /*
        * 爆款
        * */
        fun click5() {

        }
    }

    inner class Model {

    }

    /*
    * banner适配器
    * */
    inner class BannerAdapter(layoutId: Int) :BaseBannerAdapter<BannerBean, BannerAdapter.BannerViewHolder>() {

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
            holder?.bindData(data!!,position,pageSize)
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
    inner class ShopAdapter: ZqsDBAdapter<ShopBean, AdapterItemIndexShopFlowBinding>(R.layout.adapter_item_index_shop_flow) {
        override fun convert(
            holder: ZqsDBViewHolder<AdapterItemIndexShopFlowBinding>,
            item: ShopBean
        ) {
            super.convert(holder, item)
            var params: ViewGroup.LayoutParams = holder.itemView.getLayoutParams()
            if (params is FlexboxLayoutManager.LayoutParams) {
               //params.flexGrow=1f
                //params.flexBasisPercent=0.45f
               // if (getItemPosition(item) % 2 == 0) params.marginLeft=DisplayUtils.dip2px(context,15f).toInt()
            }

        }
    }

    class ShopBean{
        var title:String?="标题"
        var content:String?="内容"
        var imageUrl:String?=AppUtils.getRandomImage()
        var price:Float?=520f
        var payNum:Long?=439
    }

    class BannerBean {
        var url: String = AppUtils.getRandomImage()
    }

}