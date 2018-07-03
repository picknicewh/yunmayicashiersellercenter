package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/7/15
 * 名称：会员充值类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberRechargeInfo {
    private int seller_id;
    private int  user_id;
    private String card_number;
    private int trade_type;
    private int change_card_money;
    private int change_card_integral;
    private int create_id;
    private String create_name;
    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public int getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(int trade_type) {
        this.trade_type = trade_type;
    }

    public int getChange_card_money() {
        return change_card_money;
    }

    public void setChange_card_money(int change_card_money) {
        this.change_card_money = change_card_money;
    }

    public int getChange_card_integral() {
        return change_card_integral;
    }

    public void setChange_card_integral(int change_card_integral) {
        this.change_card_integral = change_card_integral;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }
}
