package com.zqsweb.zqscommon.interceptor;

import com.zqsweb.zqscommon.app.ZqsApp;
import com.zqsweb.zqscommon.utils.LogUtils;
import com.zqsweb.zqscommon.utils.NetWorkUtils;
import com.zqsweb.zqscommon.utils.TUtils;
import com.zqsweb.zqscommon.utils.TokenUtils;
import com.zqsweb.zqscommon.utils.ViewUtils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 10:15
 *   @description 拦截器
 */
public class ZqsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtils.v("进入拦截器");
        if (!NetWorkUtils.checkNetwork(ZqsApp.getApp())) {
            ViewUtils.runOnUiThread(()->{
                TUtils.show("网掉了(⊙﹏⊙)！");
            });
        }
        Request request = chain.request();
        //参数就要针对body做操作,我这里针对From表单做操作
        if (request.body() instanceof FormBody) {
            // 构造新的请求表单
            FormBody.Builder builder = new FormBody.Builder();

            FormBody body = (FormBody) request.body();
            //将以前的参数添加
            for (int i = 0; i < body.size(); i++) {
                builder.add(body.name(i), body.value(i));
            }

            Headers.Builder bheader = new Headers.Builder();
            Headers headers = request.headers();
            //是否添加Token
            String isAddToken="true";
            for (int i = 0; i < headers.size(); i++) {
                bheader.add(headers.name(i), headers.value(i));
                if ("addToken".equals(headers.name(i))) {
                    isAddToken = headers.value(i);
                }
            }
            if ("true".equals(isAddToken)) {
                bheader.add("token", TokenUtils.getToken());
            }
            //追加新的参数
            //builder.add("newKye", "newValue");
            request = request.newBuilder().headers(bheader.build()).post(builder.build()).build();//构造新的请求体
        }
        Response response = chain.proceed(request);
        /*
         * 不是debug状态,直接返回
         * */
        if (!LogUtils.isDebug) {
            return response;
        }
        LogUtils.v("进入拦截器2");
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        //注意 >>>>>>>>> okhttp3.4.1这里变成了 !HttpHeader.hasBody(response)
        //if (!HttpEngine.hasBody(response)) {
        if (!HttpHeaders.hasBody(response)) { //HttpHeader -> 改成了 HttpHeaders，看版本进行选择
            //END HTTP
        } else if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                return response;
            }

            if (false||contentLength != 0) {
                try {
                    String result = buffer.clone().readString(charset);
                    if (result.contains("<html")){
                        LogUtils.writeLogToFile(request.url().toString().replace(":", "_").replace("/", "_"), result);
                        TUtils.show("服务器数据格式严重错误,日志已写入文件:" + request.url().toString());

                    }
                    request.url().queryParameterNames();
                    Set<String> paramNames=request.url().queryParameterNames();
                    StringBuilder sb1 = new StringBuilder();
                    for (String name:paramNames){
                        sb1.append(name+"="+request.url().queryParameter(name)+"<-->");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    Headers headers = request.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        sb2.append(headers.name(i) + "=" + headers.value(i) + "<-->");
                    }
                    StringBuilder postParams = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        //将以前的参数添加
                        for (int i = 0; i < body.size(); i++) {
                            postParams.append(body.name(i) + "=" + body.value(i) + "<-->");
                        }
                    }

                    LogUtils.v("请求地址:" + request.url() + "\nget请求参数:" + sb1.toString() + "\npost请求参数:" + postParams.toString() + "\n请求头:" + sb2.toString() + "\n响应结果:" + result);
                } catch (Exception e) {
                    LogUtils.e("请求拦截器出现错误:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

}
