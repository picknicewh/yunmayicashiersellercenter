package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/17
 * 名称：余额明细/积分明细类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoChangeInfo  implements Serializable{

    /**
     * id : 9
     * user_id : 18643
     * seller_id : 100673
     * card_number : 1598765432112345678765
     * trade_type : 1
     * trade_id : 0
     * product_ids :
     * before_card_money : 2460
     * after_card_money : 2700
     * change_card_money : 240
     * before_card_integral : 8000
     * after_card_integral : 16000
     * change_card_integral : 8000
     * remark :
     * create_id : 100673
     * create_name : maijia
     * status : 99
     * create_time : 1500279029
     * create_datetime : 2017-07-17 16:10:29
     */

    private int id;
    private int user_id;
    private int seller_id;
    private String card_number;
    private int trade_type;
    private String trade_id;
    private String product_ids;
    private double before_card_money;
    private double after_card_money;
    private double change_card_money;
    private int before_card_integral;
    private int after_card_integral;
    private int change_card_integral;
    private String remark;
    private int create_id;
    private String create_name;
    private int status;
    private int create_time;
    private String create_datetime;

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

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public int getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(int trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(String product_ids) {
        this.product_ids = product_ids;
    }

    public double getBefore_card_money() {
        return before_card_money;
    }

    public void setBefore_card_money(int before_card_money) {
        this.before_card_money = before_card_money;
    }

    public double getAfter_card_money() {
        return after_card_money;
    }

    public void setAfter_card_money(int after_card_money) {
        this.after_card_money = after_card_money;
    }

    public double getChange_card_money() {
        return change_card_money;
    }

    public void setChange_card_money(int change_card_money) {
        this.change_card_money = change_card_money;
    }

    public int getBefore_card_integral() {
        return before_card_integral;
    }

    public void setBefore_card_integral(int before_card_integral) {
        this.before_card_integral = before_card_integral;
    }

    public int getAfter_card_integral() {
        return after_card_integral;
    }

    public void setAfter_card_integral(int after_card_integral) {
        this.after_card_integral = after_card_integral;
    }

    public int getChange_card_integral() {
        return change_card_integral;
    }

    public void setChange_card_integral(int change_card_integral) {
        this.change_card_integral = change_card_integral;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
