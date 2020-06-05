package com.zqsweb.zqscommon.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/*
 *   @author zhangqisheng
 *   @date 2020-05-08 13:44
 *   @description 公共的databindingadapter
 */
public class DataBindingAdapter {

    @BindingAdapter({"bindImageUrl"})
    public static void bindImageUrl(ImageView imageView,String url) {
        GlideApp.with(imageView).load(url).into(imageView);
    }


}
