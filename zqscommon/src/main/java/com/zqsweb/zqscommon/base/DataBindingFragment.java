package com.zqsweb.zqscommon.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/*
 *   @author zhangqisheng
 *   @date 2020-05-09 11:40
 *   @description
 */
public abstract class DataBindingFragment<DB extends ViewDataBinding> extends BaseFragment {

    private DB mBinding;

    protected DB getDB(){
        return mBinding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, getAnnoLayoutId(), null, false);
        }
        return mBinding.getRoot();
    }

    /*
    * 设置点击返回view
    * */
    public void setBackView(View view) {
        view.setOnClickListener(v -> getActivity().finish());
    }

}
