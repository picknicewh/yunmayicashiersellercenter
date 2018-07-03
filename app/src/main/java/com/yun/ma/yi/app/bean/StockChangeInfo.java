package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/7/3
 * 名称：商品库存新增类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockChangeInfo {
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
    private int befroe_stock;
    private int change_stock;
    private int after_stock;

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

    public String getCategory_names() {
        return category_names;
    }

    public void setCategory_names(String category_names) {
        this.category_names = category_names;
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

    public void setPrice(double price) {
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


    public int getBefroe_stock() {
        return befroe_stock;
    }

    public void setBefroe_stock(int befroe_stock) {
        this.befroe_stock = befroe_stock;
    }

    public int getChange_stock() {
        return change_stock;
    }

    public void setChange_stock(int change_stock) {
        this.change_stock = change_stock;
    }

    public int getAfter_stock() {
        return after_stock;
    }

    public void setAfter_stock(int after_stock) {
        this.after_stock = after_stock;
    }
}
