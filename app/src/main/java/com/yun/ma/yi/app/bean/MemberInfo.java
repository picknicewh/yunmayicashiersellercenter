package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfo  implements  Serializable{

    /**
     * id : 1
     * seller_id : 100673
     * user_id : 18643
     * user_name : Mrs
     * user_mobile : 15987654321
     * user_sex : 1
     * user_birthday : 9-12
     * user_certify_id : 876543567890876543
     * card_number : 1598765432112345678765
     * card_password :
     * card_name : 白金
     * card_money : 0
     * card_consume_integral : 0
     * card_integral : 0
     * get_integral_by_money : 0
     * create_id : 0
     * create_name :
     * modify_id : 0
     * modify_name :
     * status : 99
     * create_time : 0
     * create_datetime : 2017-07-13 15:23:23
     * update_time : 0
     * update_datetime : 2017-07-13 15:23:28
     */

    private int id;
    private int seller_id;
    private int user_id;
    private String user_name;
    private String user_mobile;
    private int user_sex;
    private String user_birthday;
    private String user_certify_id;
    private String card_number;
    private String card_password;
    private String card_name;
    private double card_money;
    private int card_consume_integral;
    private int card_integral;
    private int get_integral_by_money;
    private int create_id;
    private String create_name;
    private int modify_id;
    private String modify_name;
    private int status;
    private int create_time;
    private String create_datetime;
    private int update_time;
    private String update_datetime;
    private int count;
    private int discount_type;
    private int discount_rate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_certify_id() {
        return user_certify_id;
    }

    public void setUser_certify_id(String user_certify_id) {
        this.user_certify_id = user_certify_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_password() {
        return card_password;
    }

    public void setCard_password(String card_password) {
        this.card_password = card_password;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public double getCard_money() {
        return card_money;
    }

    public void setCard_money(double card_money) {
        this.card_money = card_money;
    }

    public int getCard_consume_integral() {
        return card_consume_integral;
    }

    public void setCard_consume_integral(int card_consume_integral) {
        this.card_consume_integral = card_consume_integral;
    }

    public int getCard_integral() {
        return card_integral;
    }

    public void setCard_integral(int card_integral) {
        this.card_integral = card_integral;
    }

    public int getGet_integral_by_money() {
        return get_integral_by_money;
    }

    public void setGet_integral_by_money(int get_integral_by_money) {
        this.get_integral_by_money = get_integral_by_money;
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

    public int getModify_id() {
        return modify_id;
    }

    public void setModify_id(int modify_id) {
        this.modify_id = modify_id;
    }

    public String getModify_name() {
        return modify_name;
    }

    public void setModify_name(String modify_name) {
        this.modify_name = modify_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(String update_datetime) {
        this.update_datetime = update_datetime;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public int getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(int discount_rate) {
        this.discount_rate = discount_rate;
    }
}
