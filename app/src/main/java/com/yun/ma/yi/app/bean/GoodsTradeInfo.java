package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/14.
 * 商品交易流水
 */

public class GoodsTradeInfo implements Serializable{

    /**
     * trade_id : 1
     * species : 2
     * total_quantity : 14
     * real_cost_fee : 600
     * create_date : 2017-06-10 10:45:09
     * seller_id : 100673
     * total_fee : 900
     * received_fee : 900
     * pay_type : 1
     * pay_platform : cod
     * state : 1
     */

    private String trade_id;
    private int species;
    private String total_quantity;
    private Double real_cost_fee;
    private String create_date;
    private String pay_date;
    private int seller_id;
    private Double total_fee;
    private Double received_fee;
    private int pay_type;
    private String pay_platform;
    private int state;
    private String sub_user_username;
    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        this.species = species;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Double getReal_cost_fee() {
        return real_cost_fee;
    }

    public void setReal_cost_fee(Double real_cost_fee) {
        this.real_cost_fee = real_cost_fee;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public Double getReceived_fee() {
        return received_fee;
    }

    public void setReceived_fee(Double received_fee) {
        this.received_fee = received_fee;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSub_user_username() {
        return sub_user_username;
    }

    public void setSub_user_username(String sub_user_username) {
        this.sub_user_username = sub_user_username;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }
}
