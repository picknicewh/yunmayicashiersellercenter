package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/11/27
 * 名称：蚂蚁小店店铺订单详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderDetailInfo  implements Serializable{
    /**
     * id : 1500051120
     * trade_id : 500025093
     * buyer_id : 100673
     * buyer_rate : 0
     * seller_id : 100673
     * seller_rate : 0
     * shop_id : 1000175
     * item_id : 10092063
     * sku_id : 10103419
     * top_cat_id : 0
     * mid_cat_id : 0
     * leaf_cat_id : 0
     * title : 加多宝凉茶（罐装）310ml
     * price : 380
     * cost_price : 0
     * quantity : 6
     * pic_url : http://i1.yunmayi.com/upload/2016/03/04/bcb102a4605a9eaa7f365e7de5217927.jpg
     * number :
     * bar_code : 4891599338898
     * status : 2
     * order_total_price : 2280
     * order_pay_price : 2280
     * order_real_price : 2280
     * discount_price : 0
     * created_at : 2016-10-11 09:32:16
     * updated_at : 2016-10-11 09:32:16
     * consign_time : null
     * end_time : null
     */

    private int id;
    private int trade_id;
    private int buyer_id;
    private int buyer_rate;
    private int seller_id;
    private int seller_rate;
    private int shop_id;
    private int item_id;
    private int sku_id;
    private int top_cat_id;
    private int mid_cat_id;
    private int leaf_cat_id;
    private String title;
    private int price;
    private int cost_price;
    private int quantity;
    private String pic_url;
    private String number;
    private String bar_code;
    private int status;
    private double order_total_price;
    private double order_pay_price;
    private double order_real_price;
    private double discount_price;
    private String created_at;
    private String updated_at;
    private Object consign_time;
    private Object end_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
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

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public int getTop_cat_id() {
        return top_cat_id;
    }

    public void setTop_cat_id(int top_cat_id) {
        this.top_cat_id = top_cat_id;
    }

    public int getMid_cat_id() {
        return mid_cat_id;
    }

    public void setMid_cat_id(int mid_cat_id) {
        this.mid_cat_id = mid_cat_id;
    }

    public int getLeaf_cat_id() {
        return leaf_cat_id;
    }

    public void setLeaf_cat_id(int leaf_cat_id) {
        this.leaf_cat_id = leaf_cat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost_price() {
        return cost_price;
    }

    public void setCost_price(int cost_price) {
        this.cost_price = cost_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
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
}
