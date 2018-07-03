package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：订单详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OrderInfoDetail implements Serializable{
    /**
     * id : 13046933
     * order_id : 1707201739072657
     * seller_order_id : 170720173907235822586
     * buyer_id : 100673
     * buyer_number : 571999998
     * seller_id : 106381
     * seller_number : 5710035
     * product_id : 318996
     * product_master_id : 0
     * product_title : 测试凑单商品，勿下单测试凑单商品12
     * product_sub_title : 测试凑单商品，勿下单667
     * product_fst_cat_id : 1085
     * product_snd_cat_id : 1313
     * product_thd_cat_id : 1322
     * product_market_price : 68400
     * product_sell_price : 25929
     * product_cost_price : 28900
     * product_number : 555555555
     * product_bar_code : 69000000000
     * product_brand_id : 0
     * product_spec : 30个／箱
     * product_unit : 箱
     * product_image : /upload/2016/07/04/ca390cd2e092f7ed070b7ef7361fece4.jpg
     * quantity : 2
     * already_return :2
     * pay_total_sell_price : 51858
     * real_pay_total_sell_price : 51858
     * pay_total_cost_price : 57800
     * real_pay_total_cost_price : 57800
     * cat_discount_price : 0
     * state : 1
     * create_from : 0
     * pay_type : 1
     * mark : 江干2
     * out_of_stock_num : 0
     * abnormal_type_id : 0
     * abnormal_type :
     * abnormal_reason_id : 0
     * abnormal_reason :
     * abnormal_recorder_id : 0
     * abnormal_recorder_name :
     * is_warehouse : 0
     * create_time : 1500543546
     * finish_time : 0
     * abnormal_record_time : 0
     * create_datetime : 2017-07-20 17:39:06
     * finish_datetime : null
     * abnormal_record_datetime : null
     * agent_number : 57100000
     * remark :
     * abnormal_record_description :
     * deliver_type : platform_deliver
     */
    private int id;
    private String order_id;
    private String seller_order_id;
    private int buyer_id;
    private String buyer_number;
    private int seller_id;
    private String seller_number;
    private int product_id;
    private int product_master_id;
    private String product_title;
    private String product_sub_title;
    private int product_fst_cat_id;
    private int product_snd_cat_id;
    private int product_thd_cat_id;
    private int product_market_price;
    private double product_sell_price;
    private double product_cost_price;
    private String product_number;
    private String product_bar_code;
    private int product_brand_id;
    private String product_spec;
    private String product_unit;
    private String product_image;
    private int quantity;
    private int already_return;
    private int pay_total_sell_price;
    private int real_pay_total_sell_price;
    private int pay_total_cost_price;
    private int real_pay_total_cost_price;
    private int cat_discount_price;
    private int state;
    private int create_from;
    private int pay_type;
    private String mark;
    private int out_of_stock_num;
    private int abnormal_type_id;
    private String abnormal_type;
    private int abnormal_reason_id;
    private String abnormal_reason;
    private int abnormal_recorder_id;
    private String abnormal_recorder_name;
    private int is_warehouse;
    private int create_time;
    private int finish_time;
    private int abnormal_record_time;
    private String create_datetime;
    private String finish_datetime;
    private String abnormal_record_datetime;
    private String agent_number;
    private String remark;
    private String abnormal_record_description;
    private String deliver_type;
    private int is_enter;

    public int getAlready_return() {
        return already_return;
    }

    public void setAlready_return(int already_return) {
        this.already_return = already_return;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSeller_order_id() {
        return seller_order_id;
    }

    public void setSeller_order_id(String seller_order_id) {
        this.seller_order_id = seller_order_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_number() {
        return buyer_number;
    }

    public void setBuyer_number(String buyer_number) {
        this.buyer_number = buyer_number;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_number() {
        return seller_number;
    }

    public void setSeller_number(String seller_number) {
        this.seller_number = seller_number;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_master_id() {
        return product_master_id;
    }

    public void setProduct_master_id(int product_master_id) {
        this.product_master_id = product_master_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_sub_title() {
        return product_sub_title;
    }

    public void setProduct_sub_title(String product_sub_title) {
        this.product_sub_title = product_sub_title;
    }

    public int getProduct_fst_cat_id() {
        return product_fst_cat_id;
    }

    public void setProduct_fst_cat_id(int product_fst_cat_id) {
        this.product_fst_cat_id = product_fst_cat_id;
    }

    public int getProduct_snd_cat_id() {
        return product_snd_cat_id;
    }

    public void setProduct_snd_cat_id(int product_snd_cat_id) {
        this.product_snd_cat_id = product_snd_cat_id;
    }

    public int getProduct_thd_cat_id() {
        return product_thd_cat_id;
    }

    public void setProduct_thd_cat_id(int product_thd_cat_id) {
        this.product_thd_cat_id = product_thd_cat_id;
    }

    public int getProduct_market_price() {
        return product_market_price;
    }

    public void setProduct_market_price(int product_market_price) {
        this.product_market_price = product_market_price;
    }

    public double getProduct_sell_price() {
        return product_sell_price;
    }

    public void setProduct_sell_price(double product_sell_price) {
        this.product_sell_price = product_sell_price;
    }

    public double getProduct_cost_price() {
        return product_cost_price;
    }

    public void setProduct_cost_price(double product_cost_price) {
        this.product_cost_price = product_cost_price;
    }

    public String getProduct_number() {
        return product_number;
    }

    public void setProduct_number(String product_number) {
        this.product_number = product_number;
    }

    public String getProduct_bar_code() {
        return product_bar_code;
    }

    public void setProduct_bar_code(String product_bar_code) {
        this.product_bar_code = product_bar_code;
    }

    public int getProduct_brand_id() {
        return product_brand_id;
    }

    public void setProduct_brand_id(int product_brand_id) {
        this.product_brand_id = product_brand_id;
    }

    public String getProduct_spec() {
        return product_spec;
    }

    public void setProduct_spec(String product_spec) {
        this.product_spec = product_spec;
    }

    public String getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(String product_unit) {
        this.product_unit = product_unit;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPay_total_sell_price() {
        return pay_total_sell_price;
    }

    public void setPay_total_sell_price(int pay_total_sell_price) {
        this.pay_total_sell_price = pay_total_sell_price;
    }

    public int getReal_pay_total_sell_price() {
        return real_pay_total_sell_price;
    }

    public void setReal_pay_total_sell_price(int real_pay_total_sell_price) {
        this.real_pay_total_sell_price = real_pay_total_sell_price;
    }

    public int getPay_total_cost_price() {
        return pay_total_cost_price;
    }

    public void setPay_total_cost_price(int pay_total_cost_price) {
        this.pay_total_cost_price = pay_total_cost_price;
    }

    public int getReal_pay_total_cost_price() {
        return real_pay_total_cost_price;
    }

    public void setReal_pay_total_cost_price(int real_pay_total_cost_price) {
        this.real_pay_total_cost_price = real_pay_total_cost_price;
    }

    public int getCat_discount_price() {
        return cat_discount_price;
    }

    public void setCat_discount_price(int cat_discount_price) {
        this.cat_discount_price = cat_discount_price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCreate_from() {
        return create_from;
    }

    public void setCreate_from(int create_from) {
        this.create_from = create_from;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getOut_of_stock_num() {
        return out_of_stock_num;
    }

    public void setOut_of_stock_num(int out_of_stock_num) {
        this.out_of_stock_num = out_of_stock_num;
    }

    public int getAbnormal_type_id() {
        return abnormal_type_id;
    }

    public void setAbnormal_type_id(int abnormal_type_id) {
        this.abnormal_type_id = abnormal_type_id;
    }

    public String getAbnormal_type() {
        return abnormal_type;
    }

    public void setAbnormal_type(String abnormal_type) {
        this.abnormal_type = abnormal_type;
    }

    public int getAbnormal_reason_id() {
        return abnormal_reason_id;
    }

    public void setAbnormal_reason_id(int abnormal_reason_id) {
        this.abnormal_reason_id = abnormal_reason_id;
    }

    public String getAbnormal_reason() {
        return abnormal_reason;
    }

    public void setAbnormal_reason(String abnormal_reason) {
        this.abnormal_reason = abnormal_reason;
    }

    public int getAbnormal_recorder_id() {
        return abnormal_recorder_id;
    }

    public void setAbnormal_recorder_id(int abnormal_recorder_id) {
        this.abnormal_recorder_id = abnormal_recorder_id;
    }

    public String getAbnormal_recorder_name() {
        return abnormal_recorder_name;
    }

    public void setAbnormal_recorder_name(String abnormal_recorder_name) {
        this.abnormal_recorder_name = abnormal_recorder_name;
    }

    public int getIs_warehouse() {
        return is_warehouse;
    }

    public void setIs_warehouse(int is_warehouse) {
        this.is_warehouse = is_warehouse;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public int getAbnormal_record_time() {
        return abnormal_record_time;
    }

    public void setAbnormal_record_time(int abnormal_record_time) {
        this.abnormal_record_time = abnormal_record_time;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public String getFinish_datetime() {
        return finish_datetime;
    }

    public void setFinish_datetime(String finish_datetime) {
        this.finish_datetime = finish_datetime;
    }

    public String getAbnormal_record_datetime() {
        return abnormal_record_datetime;
    }

    public void setAbnormal_record_datetime(String abnormal_record_datetime) {
        this.abnormal_record_datetime = abnormal_record_datetime;
    }

    public String getAgent_number() {
        return agent_number;
    }

    public void setAgent_number(String agent_number) {
        this.agent_number = agent_number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAbnormal_record_description() {
        return abnormal_record_description;
    }

    public void setAbnormal_record_description(String abnormal_record_description) {
        this.abnormal_record_description = abnormal_record_description;
    }

    public String getDeliver_type() {
        return deliver_type;
    }

    public void setDeliver_type(String deliver_type) {
        this.deliver_type = deliver_type;
    }
    public int getIs_enter() {
        return is_enter;
    }

    public void setIs_enter(int is_enter) {
        this.is_enter = is_enter;
    }

}
