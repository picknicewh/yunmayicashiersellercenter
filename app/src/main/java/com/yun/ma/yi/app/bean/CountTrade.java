package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/10.
 * 今日报表
 */

public class CountTrade implements Serializable{

    /**
     * todayIncome : 0
     * cashIncome : 0
     * aliIncome : 0
     * wxIncome : 0
     * cashCount : 0
     * aliCount : 0
     * wxCount : 0
     * itemCount : 0
     * totalCost : 0
     * profit : 0
     */

    private double todayIncome;
    private double cashIncome;
    private double aliIncome;
    private double wxIncome;
    private double aliCashIncome;
    private double wxCashIncome;
    private int cashCount;
    private int aliCount;
    private int wxCount;
    private int itemCount;
    private double totalCost;
    private double profit;

    public double getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(double todayIncome) {
        this.todayIncome = todayIncome;
    }

    public double getCashIncome() {
        return cashIncome;
    }

    public void setCashIncome(double cashIncome) {
        this.cashIncome = cashIncome;
    }

    public double getAliIncome() {
        return aliIncome;
    }

    public void setAliIncome(double aliIncome) {
        this.aliIncome = aliIncome;
    }

    public double getWxIncome() {
        return wxIncome;
    }

    public void setWxIncome(double wxIncome) {
        this.wxIncome = wxIncome;
    }

    public int getCashCount() {
        return cashCount;
    }

    public void setCashCount(int cashCount) {
        this.cashCount = cashCount;
    }

    public int getAliCount() {
        return aliCount;
    }

    public void setAliCount(int aliCount) {
        this.aliCount = aliCount;
    }

    public int getWxCount() {
        return wxCount;
    }

    public void setWxCount(int wxCount) {
        this.wxCount = wxCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getAliCashIncome() {
        return aliCashIncome;
    }

    public void setAliCashIncome(double aliCashIncome) {
        this.aliCashIncome = aliCashIncome;
    }

    public double getWxCashIncome() {
        return wxCashIncome;
    }

    public void setWxCashIncome(double wxCashIncome) {
        this.wxCashIncome = wxCashIncome;
    }
}
