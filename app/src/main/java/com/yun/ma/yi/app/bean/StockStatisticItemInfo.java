package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/7/4.
 */

public class StockStatisticItemInfo implements Serializable{
    /**
     * id : 1
     * user_id : 100673
     * type : 1
     * trade_id : 1006731499139728926
     * come_from : appupdate
     * status : 99
     * item_id : 1000001497336071
     * title : 移动测试添加
     * spec :
     * unit :
     * cost : 200
     * price : 2000
     * number :
     * bar_code : 2345678967889
     * before_stock : 0
     * change_stock : 6
     * after_stock : 6
     * create_time : 1499139728
     * create_datetime : 2017-07-04 11:42:08
     * remark :
     * update_time : 1499139728
     * update_datetime : 2017-07-04 11:42:08
     * image_url :
     */

    private int id;
    private int user_id;
    private int type;
    private String trade_id;
    private String come_from;
    private int status;
    private String item_id;
    private String title;
    private String spec;
    private String unit;
    private double cost;
    private double price;
    private String number;
    private String bar_code;
    private int before_stock;
    private int change_stock;
    private int after_stock;
    private double create_time;
    private String create_datetime;
    private String remark;
    private double update_time;
    private String update_datetime;
    private String image_url;
    private String user_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getCome_from() {
        return come_from;
    }

    public void setCome_from(String come_from) {
        this.come_from = come_from;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public int getBefore_stock() {
        return before_stock;
    }

    public void setBefore_stock(int before_stock) {
        this.before_stock = before_stock;
    }

    public int getChange_stock() {
        return change_stock;
    }

    public void setChange_stock(int change_stock) {
        this.change_stock = change_stock;
    }

    public int getAfter_stock() {
        return after_stock;
    }

    public void setAfter_stock(int after_stock) {
        this.after_stock = after_stock;
    }

    public double getCreate_time() {
        return create_time;
    }

    public void setCreate_time(double create_time) {
        this.create_time = create_time;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(double update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(String update_datetime) {
        this.update_datetime = update_datetime;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
