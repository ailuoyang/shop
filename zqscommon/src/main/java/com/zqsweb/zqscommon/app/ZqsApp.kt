package com.zqsweb.zqscommon.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.hjq.toast.style.ToastAliPayStyle
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.zqsweb.zqscommon.R
import com.zqsweb.zqscommon.base.BaseActivity
import com.zqsweb.zqscommon.interceptor.ZqsInterceptor
import com.zqsweb.zqscommon.utils.ForegroundCallbacks
import com.zqsweb.zqscommon.utils.LogUtils
import com.zqsweb.zqscommon.utils.MD5Utils
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.callback.IConverter
import rxhttp.wrapper.converter.FastJsonConverter
import rxhttp.wrapper.param.RxHttp
import java.io.File
import java.util.concurrent.TimeUnit

/*
 *   @author zhangqisheng
 *   @date 2020-05-18 16:08
 *   @description
 */
class ZqsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mApp = this
        initARouter()
        initToast()
        initRxHttp()
        initForegroundCallbackListener()
        LogUtils.init(this)
    }

    //初始化路由
    private fun initARouter() {
        if (LogUtils.isDebug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    //初始化Toast
    private fun initToast() {
        ToastUtils.init(this)
        ToastUtils.initStyle(ToastAliPayStyle(this))
    }

    //初始化前后台切换监听
    private fun initForegroundCallbackListener() {
        ForegroundCallbacks.init(this).addListener(object : ForegroundCallbacks.Listener {
            override fun onBecameForeground() {
                isForeground = true
                LogUtils.v("app切换到前台")
            }

            override fun onBecameBackground() {
                isForeground = false
                LogUtils.v("app切换到后台")
            }
        })
    }

    //RxHttp初始化
    private fun initRxHttp() {
        val cacheDir = File(externalCacheDir, MD5Utils.encodeMD5("rxhttp"))
        RxHttpPlugins.setCache(cacheDir, 10 * 1024 * 1024.toLong(), CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE, 60 * 1000.toLong())
        val okHttpClient = OkHttpClient.Builder()
                .writeTimeout(60000L, TimeUnit.MILLISECONDS)
                .connectTimeout(4000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(ZqsInterceptor())
                .connectionPool(ConnectionPool(35, 1
                        , TimeUnit.SECONDS)) //其他配置
                .build()
        RxHttp.init(okHttpClient)

        //使用fastjson解析器
        val converter: IConverter = FastJsonConverter.create()
        RxHttp.setConverter(converter)
    }


    companion object {

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
                override fun createRefreshHeader(
                    context: Context,
                    layout: RefreshLayout
                ): RefreshHeader {
                    layout.setEnableLoadMoreWhenContentNotFull(false)
                    layout.setEnableFooterFollowWhenNoMoreData(true)
                    layout.setPrimaryColorsId(
                        R.color.white,
                        R.color.zqs_flush_color
                    ) //全局设置主题颜色

                    //ClassicsHeader header=new ClassicsHeader(context);
                    //ClassicsHeader header=new ClassicsHeader(context);
                    return ClassicsHeader(context)
                }
            })

            SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
                override fun createRefreshFooter(
                    context: Context,
                    layout: RefreshLayout
                ): RefreshFooter {
                    //指定为经典Footer，默认是 BallPulseFooter
                    //指定为经典Footer，默认是 BallPulseFooter
                    layout.setPrimaryColorsId(R.color.white, R.color.zqs_flush_color)
                    layout.setEnableFooterFollowWhenNoMoreData(false)
                    return ClassicsFooter(context)
                }
            })
        }

        private var mApp: ZqsApp? = null

        //栈顶activity
        private var topActivity: BaseActivity? = null

        //是否在前台
        private var isForeground = false


        @JvmStatic
        fun getApp() :ZqsApp{
            return mApp!!
        }

        @JvmStatic
        fun getTopActivity(): BaseActivity {
            return topActivity!!
        }

        @JvmStatic
        fun setTopActivity(activity: BaseActivity) {
            topActivity=activity
        }

        @JvmStatic
        fun isForeground():Boolean {
            return isForeground
        }
    }
}