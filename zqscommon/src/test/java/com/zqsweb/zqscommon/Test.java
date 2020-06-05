package com.zqsweb.zqscommon;

import com.zqsweb.zqscommon.utils.FastJsonUtils;

import rxhttp.wrapper.param.RxHttp;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 15:31
 *   @description
 */
public class Test {
    String root_url = "";
    @org.junit.Test
    public void test() {
        System.out.println("kaishi ");
        RxHttp.postForm("https://www.leodao888.com/api/circle/detail")
                .addHeader("token","7cc20df3a794ddd6d73cafdeb39f12effccfb498871eeeaab6a3a4d96bdfe5cf1")
                .asbean(ChildBean.class)
                .subscribe(bean->{
                    System.out.println("json:" );
                    System.out.println("json:" + FastJsonUtils.getBeanToJson(bean));
                    System.out.println(bean.getContent());
                    System.out.println(bean.getHead_pic());
                    System.out.println(bean.getUsername());
                    System.out.println(bean.getSex());
                });
    }

}
