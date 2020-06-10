package com.ledao.shop.one.mulitype

import android.graphics.Color
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.ledao.shop.R
import com.ledao.shop.databinding.AdapterItemMulitypeTypesBinding
import com.ledao.shop.databinding.MulitypeMulitypeFragmentBinding
import com.zqsweb.zqscommon.anno.LayoutId
import com.zqsweb.zqscommon.base.DataBindingFragment
import com.zqsweb.zqscommon.base.ZqsDBAdapter
import com.zqsweb.zqscommon.base.ZqsDBViewHolder
import com.zqsweb.zqscommon.utils.AppUtils

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
    var mAdapter:MuliTypeAdapter=MuliTypeAdapter(R.layout.adapter_item_mulitype_types)
    //当前选中的边
    var mCurrentSelect=0

    override fun onFirstLoad() {
        super.onFirstLoad()

        db.mulitypeList.apply {
            //var flex=FlexboxLayoutManager(context)
            var flex=GridLayoutManager(context,3)
            layoutManager=flex
            var types= mutableListOf<MuliTypeModel>()
            for (item in 0..30) {
                var m = MuliTypeModel()
                m.imageUrl=AppUtils.getRandomImage()
                m.name="标题"
                types.add(m)
            }
            mAdapter.setNewInstance(types)
            adapter=mAdapter
        }

        db.mulitypeSiderBar.apply {

            for (index in 0..(this.childCount-1)) {
                getChildAt(index).isClickable=true
                getChildAt(index).setOnClickListener {
                    it.setBackgroundColor(resources.getColor(R.color.zqs_common_view_default_bg))
                    getChildAt(mCurrentSelect).setBackgroundColor(Color.parseColor("#ffffffff"))
                    mCurrentSelect=indexOfChild(it)

                    var types= mutableListOf<MuliTypeModel>()
                    for (item in 0..30) {
                        var m = MuliTypeModel()
                        m.imageUrl=AppUtils.getRandomImage()
                        m.name="标题"
                        types.add(m)
                    }
                    mAdapter.setNewInstance(types)

                    it as TextView
                    db.mulitypeTitle.text=it.text
                }
            }

        }

    }

    class MuliTypeAdapter(layoutId: Int): ZqsDBAdapter<MuliTypeModel, AdapterItemMulitypeTypesBinding>(layoutId){
        override fun convert(
            holder: ZqsDBViewHolder<AdapterItemMulitypeTypesBinding>,
            item: MuliTypeModel
        ) {
            super.convert(holder, item)
            holder.dataBinding?.model=item
        }
    }


    class MuliTypeModel{
        var imageUrl:String?=""
        var name:String?=""
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this).statusBarColorTransformEnable(false)
            .keyboardEnable(false).statusBarDarkFont(true).navigationBarDarkIcon(false).init()
    }
}