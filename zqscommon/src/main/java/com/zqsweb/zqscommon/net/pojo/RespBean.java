package com.zqsweb.zqscommon.net.pojo;

/*
 *   @author zhangqisheng
 *   @date 2020-05-21 10:51
 *   @description 所有响应bean的父类
 */
public class RespBean<T> {
    private String msg;
    private int status;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
