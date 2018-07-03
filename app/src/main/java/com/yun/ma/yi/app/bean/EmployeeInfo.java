package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/4
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EmployeeInfo {

    /**
     * totalProductNumber : 86
     * totalPrice : 83150
     * list : [{"quantity":86,"sumTotal":83150,"received_fee":83150,"cod":83150,"wxpay":0,"alipay":0,"name":"管理员"}]
     */

    private int totalProductNumber;
    private double totalPrice;
    private List<EmployeeInfoDetail> list;

    public int getTotalProductNumber() {
        return totalProductNumber;
    }

    public void setTotalProductNumber(int totalProductNumber) {
        this.totalProductNumber = totalProductNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<EmployeeInfoDetail> getList() {
        return list;
    }

    public void setList(List<EmployeeInfoDetail> list) {
        this.list = list;
    }

    public static class EmployeeInfoDetail {
        /**
         * quantity : 86
         * sumTotal : 83150
         * received_fee : 83150
         * cod : 83150
         * wxpay : 0
         * alipay : 0
         * name : 管理员
         */

        private int quantity;
        private double sumTotal;
        private double received_fee;
        private double cod;
        private double cod_wxpay;
        private double cod_alipay;
        private double wxpay;
        private double alipay;
        private String name;
        private int trade_number;
        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSumTotal() {
            return sumTotal;
        }

        public void setSumTotal(double sumTotal) {
            this.sumTotal = sumTotal;
        }

        public double getReceived_fee() {
            return received_fee;
        }

        public void setReceived_fee(double received_fee) {
            this.received_fee = received_fee;
        }

        public double getCod() {
            return cod;
        }

        public void setCod(double cod) {
            this.cod = cod;
        }

        public double getWxpay() {
            return wxpay;
        }

        public void setWxpay(double wxpay) {
            this.wxpay = wxpay;
        }

        public double getAlipay() {
            return alipay;
        }

        public void setAlipay(double alipay) {
            this.alipay = alipay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCod_wxpay() {
            return cod_wxpay;
        }

        public void setCod_wxpay(double cod_wxpay) {
            this.cod_wxpay = cod_wxpay;
        }

        public double getCod_alipay() {
            return cod_alipay;
        }

        public void setCod_alipay(double cod_alipay) {
            this.cod_alipay = cod_alipay;
        }

        public int getTrade_number() {
            return trade_number;
        }

        public void setTrade_number(int trade_number) {
            this.trade_number = trade_number;
        }
    }
}
