package com.zqsweb.zqscommon.net.parse;

import com.bumptech.glide.Glide;
import com.zqsweb.zqscommon.net.pojo.RespBean;
import com.zqsweb.zqscommon.utils.GlideApp;
import com.zqsweb.zqscommon.utils.StringUtils;
import com.zqsweb.zqscommon.utils.TUtils;
import com.zqsweb.zqscommon.utils.ZqsGlide;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 10:45
 *   @description 传入一个泛型bean
 */
@Parser(name = "bean")
public class ZqsBeanParse<T> extends AbstractParser<T> {

    //状态为1时是否展示成功Toast
    private boolean mSuccessIsShowToast = false;

    public ZqsBeanParse() {
    }
    public ZqsBeanParse(boolean isShowToast) {
        this.mSuccessIsShowToast = isShowToast;
    }
    public ZqsBeanParse(Class<T> clazz){
        super(clazz);
    }

    @Override
    public T onParse(@NotNull Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(RespBean.class, mType); //获取泛型类型
        RespBean<T> data = convert(response, type);
        T t = data.getData(); //获取data字段
        if (t == null && mType == String.class) {
            /*
             * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
             * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
             * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
             */
            t = (T) data.getMsg();
        }
        if (data.getStatus() != 1 || t == null) {
            //code不等于0，说明数据不正确，抛出异常
            if (data != null && !StringUtils.isEmpty(data.getMsg())) {
                TUtils.show(data.getMsg());
            }
            throw new ParseException(String.valueOf(data.getStatus()), data.getMsg(), response);
        } else if (mSuccessIsShowToast && data.getStatus() == 1) {
            TUtils.show(data.getMsg());
        }
        return t;
    }
}
