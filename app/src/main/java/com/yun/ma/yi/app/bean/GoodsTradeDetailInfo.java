package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/14.
 * 商品交易流水（商品信息）
 */

public class GoodsTradeDetailInfo implements Serializable{

    /**
     * total_fee : 400
     * quantity : 4
     * title : 康师傅酸菜牛肉面
     * bar_code : 6920152439005
     */
    private Double total_fee;
    private int quantity;
    private String title;
    private String bar_code;
     private int  is_weigh;
    private double weight;
    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getIs_weigh() {
        return is_weigh;
    }

    public void setIs_weigh(int is_weigh) {
        this.is_weigh = is_weigh;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
