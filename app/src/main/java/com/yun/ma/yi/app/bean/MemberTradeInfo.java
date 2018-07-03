package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/18
 * 名称：会员交易明细
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberTradeInfo implements Serializable{

    /**
     * id : 2
     * seller_id : 100673
     * seller_memo : 测试2
     * buyer_id : 100673
     * pay_type : 2
     * pay_platform : wxpay
     * pay_platform_no :
     * origin_fee : 500
     * change_fee : 150
     * total_fee : 350
     * payment : 350
     * received_fee : 350
     * cost_fee : 200
     * real_cost_fee : 200
     * discount_fee : 0
     * real_discount_fee : 0
     * state : 99
     * create_date : 2017-05-17 10:47:42
     * pay_date : 2017-05-17 10:47:45
     * finish_date : 2017-05-17 10:47:48
     * update_date : 2017-05-17 10:47:52
     */

    private String id;
    private int seller_id;
    private String seller_memo;
    private int buyer_id;
    private int pay_type;
    private String pay_platform;
    private String pay_platform_no;
    private int origin_fee;
    private int change_fee;
    private double total_fee;
    private int payment;
    private double received_fee;
    private double cost_fee;
    private double real_cost_fee;
    private double discount_fee;
    private double real_discount_fee;
    private int state;
    private String create_date;
    private String pay_date;
    private String finish_date;
    private String update_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_memo() {
        return seller_memo;
    }

    public void setSeller_memo(String seller_memo) {
        this.seller_memo = seller_memo;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(String pay_platform) {
        this.pay_platform = pay_platform;
    }

    public String getPay_platform_no() {
        return pay_platform_no;
    }

    public void setPay_platform_no(String pay_platform_no) {
        this.pay_platform_no = pay_platform_no;
    }

    public int getOrigin_fee() {
        return origin_fee;
    }

    public void setOrigin_fee(int origin_fee) {
        this.origin_fee = origin_fee;
    }

    public int getChange_fee() {
        return change_fee;
    }

    public void setChange_fee(int change_fee) {
        this.change_fee = change_fee;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public double getReceived_fee() {
        return received_fee;
    }

    public void setReceived_fee(int received_fee) {
        this.received_fee = received_fee;
    }

    public double getCost_fee() {
        return cost_fee;
    }

    public void setCost_fee(int cost_fee) {
        this.cost_fee = cost_fee;
    }

    public double getReal_cost_fee() {
        return real_cost_fee;
    }

    public void setReal_cost_fee(int real_cost_fee) {
        this.real_cost_fee = real_cost_fee;
    }

    public double getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(int discount_fee) {
        this.discount_fee = discount_fee;
    }

    public double getReal_discount_fee() {
        return real_discount_fee;
    }

    public void setReal_discount_fee(int real_discount_fee) {
        this.real_discount_fee = real_discount_fee;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
