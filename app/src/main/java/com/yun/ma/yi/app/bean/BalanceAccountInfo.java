package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/12/12
 * 名称：余额对账列表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountInfo {

    /**
     * total : 1
     * list : [{"id":137331,"user_id":100673,"shop_id":1000175,"trade_id":500000328,"in_out":2,"amount":1,"real_amount":1,"before_balance":0,"after_balance":-1,"type":"订单支出","description":"订单支出:0.01元，订单编号:500000328","created_at":"2017-12-08 18:59:28","updated_at":"2017-12-08 18:59:28"}]
     * hasNext : false
     */

    private int total;
    private boolean hasNext;
    private List<BalanceDetailInfo> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<BalanceDetailInfo> getList() {
        return list;
    }

    public void setList(List<BalanceDetailInfo> list) {
        this.list = list;
    }

    public static class  BalanceDetailInfo {
        /**
         * id : 137331
         * user_id : 100673
         * shop_id : 1000175
         * trade_id : 500000328
         * in_out : 2
         * amount : 1
         * real_amount : 1
         * before_balance : 0
         * after_balance : -1
         * type : 订单支出
         * description : 订单支出:0.01元，订单编号:500000328
         * created_at : 2017-12-08 18:59:28
         * updated_at : 2017-12-08 18:59:28
         */

        private int id;
        private int user_id;
        private int shop_id;
        private int trade_id;
        private int in_out;
        private double amount;
        private double real_amount;
        private double before_balance;
        private double after_balance;
        private String type;
        private String description;
        private String created_at;
        private String updated_at;

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

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(int trade_id) {
            this.trade_id = trade_id;
        }

        public int getIn_out() {
            return in_out;
        }

        public void setIn_out(int in_out) {
            this.in_out = in_out;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getReal_amount() {
            return real_amount;
        }

        public void setReal_amount(double real_amount) {
            this.real_amount = real_amount;
        }

        public double getBefore_balance() {
            return before_balance;
        }

        public void setBefore_balance(double before_balance) {
            this.before_balance = before_balance;
        }

        public double getAfter_balance() {
            return after_balance;
        }

        public void setAfter_balance(double after_balance) {
            this.after_balance = after_balance;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
