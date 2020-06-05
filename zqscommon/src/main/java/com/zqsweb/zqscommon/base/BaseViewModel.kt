package com.zqsweb.zqscommon.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.zqsweb.zqscommon.app.ZqsApp

/**
@author zhangqisheng
@date 2020-06-2020/6/5 11:37
@description 包含一些公用的应用变量
 **/
open class BaseViewModel: AndroidViewModel(ZqsApp.getApp()) {

}