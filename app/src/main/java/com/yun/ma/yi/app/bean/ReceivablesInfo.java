package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/13.
 * 收款统计信息
 */

public class ReceivablesInfo implements Serializable{

    /**
     * cod : 900
     * alipay : 0
     * wxpay : 0
     * total：900
     * date : 2017-06-10
     */

    private double cod;
    private double alipay;
    private double wxpay;
    private double total;
    private String date;
    private double wxCash;
    private double alipayCash;
    private double subUserPay;
    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }

    public double getAlipay() {
        return alipay;
    }

    public void setAlipay(double alipay) {
        this.alipay = alipay;
    }

    public double getWxpay() {
        return wxpay;
    }

    public void setWxpay(double wxpay) {
        this.wxpay = wxpay;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWxCash() {
        return wxCash;
    }

    public void setWxCash(double wxCash) {
        this.wxCash = wxCash;
    }

    public double getAlipayCash() {
        return alipayCash;
    }

    public void setAlipayCash(double alipayCash) {
        this.alipayCash = alipayCash;
    }

    public double getSubUserPay() {
        return subUserPay;
    }

    public void setSubUserPay(double subUserPay) {
        this.subUserPay = subUserPay;
    }
}
