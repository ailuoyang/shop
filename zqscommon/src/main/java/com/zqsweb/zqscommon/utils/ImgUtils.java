package com.zqsweb.zqscommon.utils;

import com.zqsweb.zqscommon.app.ZqsApp;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.tool.image.DownloadPictureUtil;

/*
 *   @author zhangqisheng
 *   @date 2020-05-25 17:39
 *   @description
 */
public class ImgUtils {

    public void see(String url) {
        List<String> images = new ArrayList<>();
        images.add(url);
        see(images,0);
    }

    public void see(List<String> images,int startIndex) {
        ImagePreview
                .getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext(ZqsApp.getApp())
                //设置是否展示下载按钮
                .setShowDownButton(true)
                // 设置从第几张开始看（索引从0开始）
                .setIndex(startIndex)
                .setImageList(images)
                .start();
    }

    /**
     * 保存图片
     * @param url
     */
    public void saveImage(String url) {
        DownloadPictureUtil.downloadPicture(ZqsApp.getApp(), url);
    }

    public void saveImage(String url,String path) {

    }


}
