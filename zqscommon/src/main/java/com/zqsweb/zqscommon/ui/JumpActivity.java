package com.zqsweb.zqscommon.ui;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zqsweb.zqscommon.R;
import com.zqsweb.zqscommon.base.BaseActivity;
import com.zqsweb.zqscommon.utils.AUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 11:36
 *   @description
 */
@Route(path = AUtils.zqscommon_JumpActivity)
public class JumpActivity extends BaseActivity {

    public static Fragment JUMP_FRAGMENT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zqs_common_activity_jump);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_con, JUMP_FRAGMENT)
                .commit();
    }

}
