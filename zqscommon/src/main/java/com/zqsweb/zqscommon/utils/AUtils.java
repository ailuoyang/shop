package com.zqsweb.zqscommon.utils;

import com.alibaba.android.arouter.launcher.ARouter;

/*
 *   @author zhangqisheng
 *   @date 2020-05-05 17:18
 *   @description ARouter工具类
 */
public class AUtils {

    public final static String zqscommon_JumpActivity = "/zqscommon/JumpActivity";
    public static void toPath(String path) {
        ARouter.getInstance().build(path).navigation();
    }

}
