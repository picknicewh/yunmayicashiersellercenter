package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/20.
 * 商品销售报表详情
 */

public class GoodsSalesInfo implements Serializable{

    /**
     * id
     * total_quantity => 商品销售数
     * total_fee => 总销售额
     * title => 商品标题
     * bar_code => 条形码
     * category_name 分类名称
     * stock : 99999 库存
     * total_fee : 200
     * received_fee : 200
     * real_cost_fee : 100
     * profit : 100
     */

    private String id;
    private String title;
    private String bar_code;
    private double total_fee;
    private String total_quantity;
    private String category_name;
    private int stock;
    private double received_fee;
    private double real_cost_fee;
    private double profit;
    private double total_real_cost_fee;
    private int is_weigh;
    private double total_weight;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getReceived_fee() {
        return received_fee;
    }

    public void setReceived_fee(double received_fee) {
        this.received_fee = received_fee;
    }

    public double getReal_cost_fee() {
        return real_cost_fee;
    }

    public void setReal_cost_fee(double real_cost_fee) {
        this.real_cost_fee = real_cost_fee;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getTotal_real_cost_fee() {
        return total_real_cost_fee;
    }

    public void setTotal_real_cost_fee(double total_real_cost_fee) {
        this.total_real_cost_fee = total_real_cost_fee;
    }

    public int getIs_weigh() {
        return is_weigh;
    }

    public void setIs_weigh(int is_weigh) {
        this.is_weigh = is_weigh;
    }

    public double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(double total_weight) {
        this.total_weight = total_weight;
    }
}
