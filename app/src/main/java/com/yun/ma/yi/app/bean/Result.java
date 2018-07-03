package com.yun.ma.yi.app.bean;


/**
 * Created by ys on 2017/5/24.
 * 公共返回值
 */
public class Result<T>
{
    private String status;
    private String info;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}