package com.zqsweb.zqscommon.utils.fback;

import com.zqsweb.zqscommon.ui.JumpActivity;
import com.zqsweb.zqscommon.utils.AUtils;

import androidx.fragment.app.Fragment;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 17:48
 *   @description
 */
public class ZqsUtils {

    /*
    * 携带一个fragment跳转到activity中
    * */
    public static void toJump(Fragment fragment) {
        JumpActivity.JUMP_FRAGMENT = fragment;
        AUtils.toPath(AUtils.zqscommon_JumpActivity);
    }

}
