package com.zqsweb.zqscommon.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.components.ImmersionFragment;
import com.zqsweb.zqscommon.anno.LayoutId;
import com.zqsweb.zqscommon.utils.LogUtils;
import com.zqsweb.zqscommon.utils.fback.BackHandlerHelper;
import com.zqsweb.zqscommon.utils.fback.FragmentBackHandler;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
 *   @author zhangqisheng
 *   @date 2020-05-05 17:05
 *   @description
 */
public class BaseFragment extends ImmersionFragment implements FragmentBackHandler {

    private boolean mIsFirstLoad=true;

    protected final boolean isFirstLoad() {
        return mIsFirstLoad;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.v(this.getClass().getName() + "->onViewCreated");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.v(this.getClass().getName() + "->onCreateView");
        if (getView() == null) {
            return inflater.inflate(getAnnoLayoutId(), container, false);
        }
        return getView();
    }

    @LayoutRes
    protected int getAnnoLayoutId() {
        if (this.getClass().isAnnotationPresent(LayoutId.class)) {
            LayoutId anno = this.getClass().getAnnotation(LayoutId.class);
            if (anno != null) {
                LogUtils.v("获得fragment布局注解");
                return anno.value();
            }
        }
        LogUtils.v("获得fragment布局注解失败,使用getLayoutId获得");
        throw new IllegalArgumentException("请使用FragmentLayoutId注解" + this.getClass().getName());

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.v(this.getClass().getName() + "->onResume");
        if (mIsFirstLoad == true) {
            mIsFirstLoad = false;
            LogUtils.v(this.getClass().getName() + "第一次加载");
            onFirstLoad();
        }
    }

    /*
    * fragment第一次加载的时候回调用
    * */
    protected void onFirstLoad() {

    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.v(this.getClass().getName() + "->onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.v(this.getClass().getName() + "->onStart");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.v(this.getClass().getName() + "->onDestroyView");
    }

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }

    @Override
    public void initImmersionBar() {

    }
}
