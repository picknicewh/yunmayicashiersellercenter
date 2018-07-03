package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/23.
 * 销售收益报表
 */

public class GoodsProfitInfo implements Serializable{


    /**
     * count : 6
     * total_fee : 3750
     * cost_fee : 2400
     * profit : 1350
     * rate : 36%
     * return_num : 0
     * return_fee : 0
     */

    private int count;
    private Double total_fee;
    private Double cost_fee;
    private Double profit;
    private String rate;
    private int return_num;
    private Double return_fee;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public Double getCost_fee() {
        return cost_fee;
    }

    public void setCost_fee(Double cost_fee) {
        this.cost_fee = cost_fee;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getReturn_num() {
        return return_num;
    }

    public void setReturn_num(int return_num) {
        this.return_num = return_num;
    }

    public Double getReturn_fee() {
        return return_fee;
    }

    public void setReturn_fee(Double return_fee) {
        this.return_fee = return_fee;
    }
}
