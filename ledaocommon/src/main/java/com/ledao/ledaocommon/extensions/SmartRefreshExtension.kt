package com.ledao.ledaocommon.extensions

import com.scwang.smart.refresh.layout.SmartRefreshLayout

class PageUtils {
    companion object {
        private val mPageInfos: MutableMap<String, PageInfo> = mutableMapOf()

        /**
         * 对分页信息进行操作
         * option:'+' or '-'
         */
        fun SmartRefreshLayout.optionPage(hashCode: String, option: Char) {
            var pageinfo = mPageInfos.get(hashCode)?.let {
                var pageInfo = it
                when (option) {
                    '+' -> pageInfo.currentPage?.plus(1)
                    '-' -> pageInfo.currentPage?.minus(1)
                }
                pageInfo.refreshTime = (System.currentTimeMillis() / 1000).toString()
                pageInfo
            }
            mPageInfos.set(hashCode, pageinfo!!)
        }

        /*
        * 获得当前的页数
        * */
        fun SmartRefreshLayout.getPage(hashCode: String): Int {
            return mPageInfos.get(hashCode)?.currentPage!!
        }

        /*
        * 获得刷新时间
        * */
        fun SmartRefreshLayout.getRefreshTime(hashCode: String): String {
            return mPageInfos.get(hashCode)?.refreshTime!!
        }
    }

    class PageInfo{
        //默认是1
        var currentPage: Int?=1
        //应用上线前的刷新时间
        var refreshTime:String?="1591327951"
    }
}
