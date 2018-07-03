package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/28.
 * 库存预警
 */

public class StcokWarningInfo implements Serializable{

    /**
     * id : 1000001497336071
     * title : 移动测试添加
     * bar_code : 2345678967889
     * stock : 0
     * stock_warning_low : 0
     */

    private String id;
    private String title;
    private String bar_code;
    private int stock;
    private int stock_warning_low;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_warning_low() {
        return stock_warning_low;
    }

    public void setStock_warning_low(int stock_warning_low) {
        this.stock_warning_low = stock_warning_low;
    }
}
