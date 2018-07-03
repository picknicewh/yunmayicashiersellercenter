package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/27
 * 名称：蚂蚁小店店铺订单信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderInfo  implements Serializable{
    /**
     * id : 500025093
     * app_id : 100000
     * buyer_id : 100673
     * buyer_rate : 0
     * buyer_can_rate : 1
     * buyer_remark :
     * buyer_close_reason :
     * seller_id : 100673
     * seller_rate : 0
     * seller_can_rate : 1
     * seller_remark :
     * seller_close_reason :
     * shop_id : 1000175
     * shop_name : 测试店铺，勿下单
     * pay_type : cod
     * pay_platform :
     * pay_platform_no :
     * order_total_price : 2280
     * order_pay_price : 2280
     * order_real_price : 2280
     * order_discount_price : 0
     * delivery_type : auto_delivery
     * network_no :
     * express_type : 0
     * express_no :
     * freight_fee : 0
     * trade_from : 5
     * status : 2
     * refund_status : 1
     * created_at : 2016-10-11 09:32:16
     * updated_at : 2016-10-11 09:32:16
     * pay_time : 2016-10-11 09:32:16
     * receive_time : null
     * consign_time : null
     * end_time : null
     * consignee_prov_id : 330000
     * consignee_city_id : 330100
     * consignee_district_id : 330105
     * consignee_prov_name : 浙江省
     * consignee_city_name : 杭州市
     * consignee_district_name : 拱墅区
     * consignee_name : 测试名字
     * consignee_mobile : 13758188170
     * consignee_phone :
     * consignee_email :
     * consignee_address : 祥园路35号
     * consignee_address_lng : 120.10921
     * consignee_address_lat : 30.335009
     * seller_name : 测试2
     * seller_mobile : 13162929357
     * seller_phone :
     * seller_email :
     * item_name : 加多宝凉茶（罐装）310ml
     * item_quantity : 6
     * order_num : 1
     * orders : [{"id":1500051120,"trade_id":500025093,"buyer_id":100673,"buyer_rate":0,"seller_id":100673,"seller_rate":0,"shop_id":1000175,"item_id":10092063,"sku_id":10103419,"top_cat_id":0,"mid_cat_id":0,"leaf_cat_id":0,"title":"加多宝凉茶（罐装）310ml","price":380,"cost_price":0,"quantity":6,"pic_url":"http://i1.yunmayi.com/upload/2016/03/04/bcb102a4605a9eaa7f365e7de5217927.jpg","number":"","bar_code":"4891599338898","status":2,"order_total_price":2280,"order_pay_price":2280,"order_real_price":2280,"discount_price":0,"created_at":"2016-10-11 09:32:16","updated_at":"2016-10-11 09:32:16","consign_time":null,"end_time":null}]
     */

    private String id;
    private int app_id;
    private int buyer_id;
    private int buyer_rate;
    private int buyer_can_rate;
    private String buyer_remark;
    private String buyer_close_reason;
    private int seller_id;
    private int seller_rate;
    private int seller_can_rate;
    private String seller_remark;
    private String seller_close_reason;
    private int shop_id;
    private String shop_name;
    private String pay_type;
    private String pay_platform;
    private String pay_platform_no;
    private double order_total_price;
    private double order_pay_price;
    private double order_real_price;
    private double order_discount_price;
    private String delivery_type;
    private String network_no;
    private int express_type;
    private String express_no;
    private double freight_fee;
    private int trade_from;
    private int status;
    private int refund_status;
    private String created_at;
    private String updated_at;
    private String pay_time;
    private Object receive_time;
    private Object consign_time;
    private Object end_time;
    private int consignee_prov_id;
    private int consignee_city_id;
    private int consignee_district_id;
    private String consignee_prov_name;
    private String consignee_city_name;
    private String consignee_district_name;
    private String consignee_name;
    private String consignee_mobile;
    private String consignee_phone;
    private String consignee_email;
    private String consignee_address;
    private String consignee_address_lng;
    private String consignee_address_lat;
    private String seller_name;
    private String seller_mobile;
    private String seller_phone;
    private String seller_email;
    private String item_name;
    private int item_quantity;
    private int order_num;
    private List<ShopOrderDetailInfo> orders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getBuyer_rate() {
        return buyer_rate;
    }

    public void setBuyer_rate(int buyer_rate) {
        this.buyer_rate = buyer_rate;
    }

    public int getBuyer_can_rate() {
        return buyer_can_rate;
    }

    public void setBuyer_can_rate(int buyer_can_rate) {
        this.buyer_can_rate = buyer_can_rate;
    }

    public String getBuyer_remark() {
        return buyer_remark;
    }

    public void setBuyer_remark(String buyer_remark) {
        this.buyer_remark = buyer_remark;
    }

    public String getBuyer_close_reason() {
        return buyer_close_reason;
    }

    public void setBuyer_close_reason(String buyer_close_reason) {
        this.buyer_close_reason = buyer_close_reason;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getSeller_rate() {
        return seller_rate;
    }

    public void setSeller_rate(int seller_rate) {
        this.seller_rate = seller_rate;
    }

    public int getSeller_can_rate() {
        return seller_can_rate;
    }

    public void setSeller_can_rate(int seller_can_rate) {
        this.seller_can_rate = seller_can_rate;
    }

    public String getSeller_remark() {
        return seller_remark;
    }

    public void setSeller_remark(String seller_remark) {
        this.seller_remark = seller_remark;
    }

    public String getSeller_close_reason() {
        return seller_close_reason;
    }

    public void setSeller_close_reason(String seller_close_reason) {
        this.seller_close_reason = seller_close_reason;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
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

    public double getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(double order_total_price) {
        this.order_total_price = order_total_price;
    }

    public double getOrder_pay_price() {
        return order_pay_price;
    }

    public void setOrder_pay_price(double order_pay_price) {
        this.order_pay_price = order_pay_price;
    }

    public double getOrder_real_price() {
        return order_real_price;
    }

    public void setOrder_real_price(double order_real_price) {
        this.order_real_price = order_real_price;
    }

    public double getOrder_discount_price() {
        return order_discount_price;
    }

    public void setOrder_discount_price(double order_discount_price) {
        this.order_discount_price = order_discount_price;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getNetwork_no() {
        return network_no;
    }

    public void setNetwork_no(String network_no) {
        this.network_no = network_no;
    }

    public int getExpress_type() {
        return express_type;
    }

    public void setExpress_type(int express_type) {
        this.express_type = express_type;
    }

    public String getExpress_no() {
        return express_no;
    }

    public void setExpress_no(String express_no) {
        this.express_no = express_no;
    }

    public double getFreight_fee() {
        return freight_fee;
    }

    public void setFreight_fee(double freight_fee) {
        this.freight_fee = freight_fee;
    }

    public int getTrade_from() {
        return trade_from;
    }

    public void setTrade_from(int trade_from) {
        this.trade_from = trade_from;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(int refund_status) {
        this.refund_status = refund_status;
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

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public Object getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(Object receive_time) {
        this.receive_time = receive_time;
    }

    public Object getConsign_time() {
        return consign_time;
    }

    public void setConsign_time(Object consign_time) {
        this.consign_time = consign_time;
    }

    public Object getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Object end_time) {
        this.end_time = end_time;
    }

    public int getConsignee_prov_id() {
        return consignee_prov_id;
    }

    public void setConsignee_prov_id(int consignee_prov_id) {
        this.consignee_prov_id = consignee_prov_id;
    }

    public int getConsignee_city_id() {
        return consignee_city_id;
    }

    public void setConsignee_city_id(int consignee_city_id) {
        this.consignee_city_id = consignee_city_id;
    }

    public int getConsignee_district_id() {
        return consignee_district_id;
    }

    public void setConsignee_district_id(int consignee_district_id) {
        this.consignee_district_id = consignee_district_id;
    }

    public String getConsignee_prov_name() {
        return consignee_prov_name;
    }

    public void setConsignee_prov_name(String consignee_prov_name) {
        this.consignee_prov_name = consignee_prov_name;
    }

    public String getConsignee_city_name() {
        return consignee_city_name;
    }

    public void setConsignee_city_name(String consignee_city_name) {
        this.consignee_city_name = consignee_city_name;
    }

    public String getConsignee_district_name() {
        return consignee_district_name;
    }

    public void setConsignee_district_name(String consignee_district_name) {
        this.consignee_district_name = consignee_district_name;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getConsignee_mobile() {
        return consignee_mobile;
    }

    public void setConsignee_mobile(String consignee_mobile) {
        this.consignee_mobile = consignee_mobile;
    }

    public String getConsignee_phone() {
        return consignee_phone;
    }

    public void setConsignee_phone(String consignee_phone) {
        this.consignee_phone = consignee_phone;
    }

    public String getConsignee_email() {
        return consignee_email;
    }

    public void setConsignee_email(String consignee_email) {
        this.consignee_email = consignee_email;
    }

    public String getConsignee_address() {
        return consignee_address;
    }

    public void setConsignee_address(String consignee_address) {
        this.consignee_address = consignee_address;
    }

    public String getConsignee_address_lng() {
        return consignee_address_lng;
    }

    public void setConsignee_address_lng(String consignee_address_lng) {
        this.consignee_address_lng = consignee_address_lng;
    }

    public String getConsignee_address_lat() {
        return consignee_address_lat;
    }

    public void setConsignee_address_lat(String consignee_address_lat) {
        this.consignee_address_lat = consignee_address_lat;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getSeller_mobile() {
        return seller_mobile;
    }

    public void setSeller_mobile(String seller_mobile) {
        this.seller_mobile = seller_mobile;
    }

    public String getSeller_phone() {
        return seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        this.seller_phone = seller_phone;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public List<ShopOrderDetailInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<ShopOrderDetailInfo> orders) {
        this.orders = orders;
    }



}
