package com.zqsweb.zqscommon.net.parse;

import com.zqsweb.zqscommon.net.pojo.RespBean;
import com.zqsweb.zqscommon.utils.StringUtils;
import com.zqsweb.zqscommon.utils.TUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 15:13
 *   @description 传入一个List<T>泛型bean
 *   适用于这种类型的数据
 * {
    "status": 1,
    "msg": "查询成功",
    "data": [
        {
            "uid": 1,
            "money": "1000.00",
            "username": "dsgsgfsdssdf",
            "sex": "",
            "head_pic": "gssdgsdg"
        }
    ]
}
 */
@Parser(name = "beanList")
public class ZqsBeanListParse<T> extends AbstractParser<List<T>> {
    //状态为1时是否展示成功Toast
    private boolean mSuccessIsShowToast = false;

    public ZqsBeanListParse() {

    }

    public ZqsBeanListParse(boolean isShowToast) {
        this.mSuccessIsShowToast = isShowToast;
    }

    public ZqsBeanListParse(Class<T> clazz){
        super(clazz);
    }

    public ZqsBeanListParse(Class<T> clazz,boolean successIsShowToast){
        super(clazz);
        this.mSuccessIsShowToast = successIsShowToast;
    }

    @Override
    public List<T> onParse(@NotNull Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(RespBean.class, List.class, mType); //获取泛型类型
        RespBean<List<T>> data = convert(response, type);
        List<T> list = data.getData(); //获取data字段
        if (data.getStatus() != 1 || list == null) {
            //code不等于1，说明数据不正确，抛出异常
            if (data != null && !StringUtils.isEmpty(data.getMsg())) {
                TUtils.show(data.getMsg());
            }
            throw new ParseException(String.valueOf(data.getStatus()), data.getMsg(), response);
        } else if (mSuccessIsShowToast && data.getStatus() == 1) {
            TUtils.show(data.getMsg());
        }
        return list;
    }
}
