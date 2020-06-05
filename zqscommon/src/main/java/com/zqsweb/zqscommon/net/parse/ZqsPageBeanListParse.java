package com.zqsweb.zqscommon.net.parse;

import com.zqsweb.zqscommon.net.pojo.PageList;
import com.zqsweb.zqscommon.net.pojo.RespBean;
import com.zqsweb.zqscommon.utils.StringUtils;
import com.zqsweb.zqscommon.utils.TUtils;

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
 *   @date 2020-05-21 16:53
 *   @description 分页解析器
 *
 * {
    "status": 1,
    "msg": "查询成功",
    "data": {
        "total": 1,
        "per_page": 15,
        "current_page": 1,
        "last_page": 1,
        "data": [
            {
                "content": "sdfsgsdgsdg",
                "sex": "",
                "add_time": 1234567890,
                "username": "dsgsgfsdssdf",
                "head_pic": "gssdgsdg"
            }
        ]
    }
}
 */
@Parser(name = "pageList")
public class ZqsPageBeanListParse<T> extends AbstractParser<PageList<T>> {

    boolean mSuccessIsShowToast = false;

    public ZqsPageBeanListParse() {

    }

    public ZqsPageBeanListParse(boolean isShowToast) {
        this.mSuccessIsShowToast = isShowToast;
    }
    public ZqsPageBeanListParse(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public PageList<T> onParse(@NotNull Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(RespBean.class, PageList.class, mType); //获取泛型类型
        RespBean<PageList<T>> data = convert(response, type);
        if (data.getStatus() != 1) {
            //code不等于1，说明数据不正确，抛出异常
            if (data != null && !StringUtils.isEmpty(data.getMsg())) {
                TUtils.show(data.getMsg());
            }
            throw new ParseException(String.valueOf(data.getStatus()), data.getMsg(), response);
        } else if (mSuccessIsShowToast && data.getStatus() == 1) {
            TUtils.show(data.getMsg());
        }
        return data.getData();
    }
}
