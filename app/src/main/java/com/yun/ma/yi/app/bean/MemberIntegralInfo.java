package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/10
 * 名称：连锁积分详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberIntegralInfo {

    /**
     * count : 10
     * total_change : 100
     * list : [{"id":167,"user_id":16746545,"chain_seller_id":0,"card_number":"151671890021506398645","trade_type":1,"product_ids":"","before_card_money":100,"after_card_money":200,"change_card_money":100,"before_card_integral":100,"after_card_integral":200,"change_card_integral":100,"remark":"","create_id":100673,"create_name":"maijia","status":99,"create_time":1506398691,"create_datetime":"2017-09-01 00:00:00"}]
     */

    private int count;
    private int total_change;
    private List<IntegralInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_change() {
        return total_change;
    }

    public void setTotal_change(int total_change) {
        this.total_change = total_change;
    }

    public List<IntegralInfo> getList() {
        return list;
    }

    public void setList(List<IntegralInfo> list) {
        this.list = list;
    }

    public static class IntegralInfo {
        /**
         * id : 167
         * user_id : 16746545
         * chain_seller_id : 0
         * card_number : 151671890021506398645
         * trade_type : 1
         * product_ids :
         * before_card_money : 100
         * after_card_money : 200
         * change_card_money : 100
         * before_card_integral : 100
         * after_card_integral : 200
         * change_card_integral : 100
         * remark :
         * create_id : 100673
         * create_name : maijia
         * status : 99
         * create_time : 1506398691
         * create_datetime : 2017-09-01 00:00:00
         * seller_id : 0
         * trade_id : 201711081736386781
         */

        private int id;
        private int user_id;
        private int chain_seller_id;
        private String card_number;
        private int trade_type;
        private String product_ids;
        private int before_card_money;
        private int after_card_money;
        private int change_card_money;
        private int before_card_integral;
        private int after_card_integral;
        private int change_card_integral;
        private String remark;
        private int create_id;
        private String create_name;
        private int status;
        private int create_time;
        private String create_datetime;


        private int seller_id;
        private String trade_id;

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

        public int getChain_seller_id() {
            return chain_seller_id;
        }

        public void setChain_seller_id(int chain_seller_id) {
            this.chain_seller_id = chain_seller_id;
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

        public String getProduct_ids() {
            return product_ids;
        }

        public void setProduct_ids(String product_ids) {
            this.product_ids = product_ids;
        }

        public int getBefore_card_money() {
            return before_card_money;
        }

        public void setBefore_card_money(int before_card_money) {
            this.before_card_money = before_card_money;
        }

        public int getAfter_card_money() {
            return after_card_money;
        }

        public void setAfter_card_money(int after_card_money) {
            this.after_card_money = after_card_money;
        }

        public int getChange_card_money() {
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

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
        }
    }
}


