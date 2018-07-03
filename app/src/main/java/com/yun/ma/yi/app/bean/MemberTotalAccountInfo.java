package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员汇总类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberTotalAccountInfo {

    /**
     * userTotal : 0
     * cardCount : 0
     * moneyTotal : 0
     * integralTotal : 0
     */
    private int userTotal;
    private int cardCount;
    private double moneyTotal;
    private int integralTotal;

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public double getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(double moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public int getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(int integralTotal) {
        this.integralTotal = integralTotal;
    }
}
