package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/12/4
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsInfoDetail implements Serializable {

    /**
     * id : 10092103
     * app_id : 100000
     * cashier_id :
     * user_id : 100673
     * shop_id : 1000175
     * top_cat_id : 100096
     * mid_cat_id : 100100
     * leaf_cat_id : 100221
     * shop_cat_id : 1070
     * title : 脉动菠萝味600ml
     * sub_title :
     * market_price : 0
     * sell_price : 400
     * cost_price : 0
     * spec : 1*24/箱
     * unit : 瓶
     * limit_num : 0
     * stock_num : 9999
     * brand_id : 0
     * pic_url : http://i1.yunmayi.com/upload/2015/05/18/2aaf1fa422cb9c3e898d20c458a4bfbe.jpg
     * number :
     * bar_code : 6902538005851
     * sales_volume : 0
     * shop_sort_id : 1
     * item_status : 4
     * status : 99
     * created_at : 2016-12-01 10:30:51
     * updated_at : 2016-12-01 10:30:51
     * up_shelves_time : null
     * down_shelves_time : null
     * insert_type : 0
     * is_optimization : 0
     * is_public : 0
     * black : []
     * top_shop_cid : 1068
     * top_shop_cname : 酒水饮料
     * second_shop_cid : 1069
     * second_shop_cname : 饮料饮品
     * third_shop_cid : 1070
     * third_shop_cname : 综合饮料
     */

    private int id;
    private int app_id;
    private String cashier_id;
    private int user_id;
    private int shop_id;
    private int top_cat_id;
    private int mid_cat_id;
    private int leaf_cat_id;
    private String shop_cat_id;
    private String title;
    private String sub_title;
    private double market_price;
    private double sell_price;
    private double cost_price;
    private String spec;
    private String unit;
    private int limit_num;
    private int stock_num;
    private int brand_id;
    private String pic_url;
    private String number;
    private String bar_code;
    private int sales_volume;
    private int shop_sort_id;
    private int item_status;
    private int status;
    private String created_at;
    private String updated_at;
    private Object up_shelves_time;
    private Object down_shelves_time;
    private int insert_type;
    private int is_optimization;
    private int is_public;
    private int top_shop_cid;
    private String top_shop_cname;
    private int second_shop_cid;
    private String second_shop_cname;
    private int third_shop_cid;
    private String third_shop_cname;
   // private List black;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public String getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(String cashier_id) {
        this.cashier_id = cashier_id;
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

    public String getShop_cat_id() {
        return shop_cat_id;
    }

    public void setShop_cat_id(String shop_cat_id) {
        this.shop_cat_id = shop_cat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getLimit_num() {
        return limit_num;
    }

    public void setLimit_num(int limit_num) {
        this.limit_num = limit_num;
    }

    public int getStock_num() {
        return stock_num;
    }

    public void setStock_num(int stock_num) {
        this.stock_num = stock_num;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
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

    public int getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(int sales_volume) {
        this.sales_volume = sales_volume;
    }

    public int getShop_sort_id() {
        return shop_sort_id;
    }

    public void setShop_sort_id(int shop_sort_id) {
        this.shop_sort_id = shop_sort_id;
    }

    public int getItem_status() {
        return item_status;
    }

    public void setItem_status(int item_status) {
        this.item_status = item_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Object getUp_shelves_time() {
        return up_shelves_time;
    }

    public void setUp_shelves_time(Object up_shelves_time) {
        this.up_shelves_time = up_shelves_time;
    }

    public Object getDown_shelves_time() {
        return down_shelves_time;
    }

    public void setDown_shelves_time(Object down_shelves_time) {
        this.down_shelves_time = down_shelves_time;
    }

    public int getInsert_type() {
        return insert_type;
    }

    public void setInsert_type(int insert_type) {
        this.insert_type = insert_type;
    }

    public int getIs_optimization() {
        return is_optimization;
    }

    public void setIs_optimization(int is_optimization) {
        this.is_optimization = is_optimization;
    }

    public int getIs_public() {
        return is_public;
    }

    public void setIs_public(int is_public) {
        this.is_public = is_public;
    }

    public int getTop_shop_cid() {
        return top_shop_cid;
    }

    public void setTop_shop_cid(int top_shop_cid) {
        this.top_shop_cid = top_shop_cid;
    }

    public String getTop_shop_cname() {
        return top_shop_cname;
    }

    public void setTop_shop_cname(String top_shop_cname) {
        this.top_shop_cname = top_shop_cname;
    }

    public int getSecond_shop_cid() {
        return second_shop_cid;
    }

    public void setSecond_shop_cid(int second_shop_cid) {
        this.second_shop_cid = second_shop_cid;
    }

    public String getSecond_shop_cname() {
        return second_shop_cname;
    }

    public void setSecond_shop_cname(String second_shop_cname) {
        this.second_shop_cname = second_shop_cname;
    }

    public int getThird_shop_cid() {
        return third_shop_cid;
    }

    public void setThird_shop_cid(int third_shop_cid) {
        this.third_shop_cid = third_shop_cid;
    }

    public String getThird_shop_cname() {
        return third_shop_cname;
    }

    public void setThird_shop_cname(String third_shop_cname) {
        this.third_shop_cname = third_shop_cname;
    }
/*
    public List getBlack() {
        return black;
    }

    public void setBlack(List black) {
        this.black = black;
    }*/
}
