package com.zqsweb.zqscommon.utils;

import com.hjq.toast.ToastUtils;

/*
 *   @author zhangqisheng
 *   @date 2020-05-06 9:21
 *   @description Toast工具类
 */
public class TUtils {

    public static void show(String msg) {
        ToastUtils.show(msg);
    }

    public static void showLog(String msg) {
        if (LogUtils.isDebug) {
            ToastUtils.show(msg);
        }
    }

}
