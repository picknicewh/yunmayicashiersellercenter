package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/7/1.
 * 帮助  公告
 */

public class NoticeInfo implements Serializable {

    /**
     * id : 8
     * type : 2
     * title : 怎么开通支付宝和微信收款功能呢？
     * detail : 先把相关资料提交给我们，我们代你们来申请支付宝和微信支付功能 。
     * create_time : 1498899931
     * create_datetime : 2017-07-01 17:05:31
     * update_time : 1498899960
     * update_datetime : 2017-07-01 17:06:00
     */

    private int id;
    private int type;
    private String title;
    private String detail;
    private String create_datetime;
    private String update_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(String update_datetime) {
        this.update_datetime = update_datetime;
    }
}
