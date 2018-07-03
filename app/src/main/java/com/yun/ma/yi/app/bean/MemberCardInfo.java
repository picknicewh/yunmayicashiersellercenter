package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/15
 * 名称：会员卡信息类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardInfo implements Serializable {
    /**
     * id : 20
     * seller_id : 100673
     * card_name : 白金卡
     * card_integral : 50000
     * discount_type : 1
     * discount_rate : 95
     * get_integral_by_money : 0
     * create_time : 1500253574
     * create_datetime : 2017-07-17 09:06:14
     * update_time : 1500253574
     * update_datetime : 2017-07-17 09:06:14
     * create_id : 100673
     * create_name : maijia
     */

    private int id;
    private int seller_id;
    private String card_name;
    private int card_integral;
    private int discount_type;
    private int discount_rate;
    private double get_integral_by_money;
    private int create_time;
    private String create_datetime;
    private int update_time;
    private String update_datetime;
    private int create_id;
    private String create_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public int getCard_integral() {
        return card_integral;
    }

    public void setCard_integral(int card_integral) {
        this.card_integral = card_integral;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public int getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(int discount_rate) {
        this.discount_rate = discount_rate;
    }

    public double getGet_integral_by_money() {
        return get_integral_by_money;
    }

    public void setGet_integral_by_money(double get_integral_by_money) {
        this.get_integral_by_money = get_integral_by_money;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(String update_datetime) {
        this.update_datetime = update_datetime;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }
}
