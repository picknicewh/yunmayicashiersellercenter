package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：商品信息详情类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsDetailInfo {
     /**
     * id : 1000001494833888086
     * user_id : 100673
     * title : with
     * category_id : 100189
     * category_ids : ,100108,100110,100189,
     * category_name : 腌制品
     * price : 6500
     * cost : 6000
     * image_url :
     * number : 123456789
     * bar_code : 692015404226
     * stock : 80
     * stock_warning_high : 15
     * stock_warning_low : 5
     * spec : 1*24
     * unit : 盒
     * total_sales : 100
     * shelf_life : 10
     * item_status : 1
     * is_weigh : 0
     * is_delete : 0
     * create_date : 2017-05-15 15:38:08
     * update_date : 2017-05-15 15:38:08
     */
    private String id;
    private int user_id;
    private String title;
    private int category_id;
    private String category_ids;
    private String category_names;
    private String category_name;
    private double price;
    private double cost;
    private String image_url;
    private String number;
    private String bar_code;
    private int stock;
    private int stock_warning_high;
    private int stock_warning_low;
    private String spec;
    private String unit;
    private int total_sales;
    private int shelf_life;
    private int item_status;
    private int is_weigh;
    private int is_delete;
    private String create_date;
    private String update_date;
    private double vip_price;
    private double unit_sell_price;
    private boolean isCheck;
    private int current_category_id;
    public double getUnit_sell_price() {
        return unit_sell_price;
    }

    public void setUnit_sell_price(double unit_sell_price) {
        this.unit_sell_price = unit_sell_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(String category_ids) {
        this.category_ids = category_ids;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_warning_high() {
        return stock_warning_high;
    }

    public void setStock_warning_high(int stock_warning_high) {
        this.stock_warning_high = stock_warning_high;
    }

    public int getStock_warning_low() {
        return stock_warning_low;
    }

    public void setStock_warning_low(int stock_warning_low) {
        this.stock_warning_low = stock_warning_low;
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

    public int getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }

    public int getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(int shelf_life) {
        this.shelf_life = shelf_life;
    }

    public int getItem_status() {
        return item_status;
    }

    public void setItem_status(int item_status) {
        this.item_status = item_status;
    }

    public int getIs_weigh() {
        return is_weigh;
    }

    public void setIs_weigh(int is_weigh) {
        this.is_weigh = is_weigh;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
    public String getCategory_names() {
        return category_names;
    }

    public void setCategory_names(String category_names) {
        this.category_names = category_names;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVip_price() {
        return vip_price;
    }

    public void setVip_price(double vip_price) {
        this.vip_price = vip_price;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getCurrent_category_id() {
        return current_category_id;
    }

    public void setCurrent_category_id(int current_category_id) {
        this.current_category_id = current_category_id;
    }
}
