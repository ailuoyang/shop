package com.zqsweb.zqscommon.utils;

/*
 *   @author zhangqisheng
 *   @date 2020-05-12 16:02
 *   @description
 */
public class TokenUtils {

    public static String mToken="7cc20df3a794ddd6d73cafdeb39f12effccfb498871eeeaab6a3a4d96bdfe5cf1";

    public static String getToken() {
        if (StringUtils.isEmpty(mToken)) {
            mToken = getSpToken();
        }
        return mToken;
    }

    private static String getSpToken() {
        return SPUtils.getInstance("token")
                .getString("token");
    }

    public static void setToken(String token) {
        SPUtils.getInstance("token")
                .put("token", token);
        mToken = token;
    }



}
