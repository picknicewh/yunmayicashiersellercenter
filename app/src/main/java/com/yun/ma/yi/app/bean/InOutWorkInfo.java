package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/9/4
 * 名称：员工上下班信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class












InOutWorkInfo  implements Serializable{

    /**
     * id : 1
     * user_id : 100673
     * sub_user_id : 0
     * sub_name :
     * sub_username : maijia
     * mobile :
     * sign_in_time : 1504488813
     * sign_in_datetime : 2017-09-04 09:33:33
     * knock_off_time : 1504491236
     * knock_off_datetime : 2017-09-04 10:13:56
     * total_cash : 50000
     * abnormal_money : 0
     * before_reserve_money : 50000
     * abnormal_desc :
     * after_reserve_money : 50000
     * verification_result : 实收现金和统计现金无误差，可预留备用金：500.00元，并将实收金额：500.00元打包，并写上打包人和日期时间。
     * cashier_order_cash : 5000000
     * pack_cash : 50000
     */

    private int id;
    private int user_id;
    private int sub_user_id;
    private String sub_name;
    private String sub_username;
    private String mobile;
    private long sign_in_time;
    private String sign_in_datetime;
    private long knock_off_time;
    private String knock_off_datetime;
    private double total_cash;
    private double abnormal_money;
    private double before_reserve_money;
    private String abnormal_desc;
    private double after_reserve_money;
    private String verification_result;
    private double cashier_order_cash;
    private double pack_cash;
    private List<String> roles;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSub_user_id() {
        return sub_user_id;
    }

    public void setSub_user_id(int sub_user_id) {
        this.sub_user_id = sub_user_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_username() {
        return sub_username;
    }

    public void setSub_username(String sub_username) {
        this.sub_username = sub_username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getSign_in_time() {
        return sign_in_time;
    }

    public void setSign_in_time(long sign_in_time) {
        this.sign_in_time = sign_in_time;
    }

    public String getSign_in_datetime() {
        return sign_in_datetime;
    }

    public void setSign_in_datetime(String sign_in_datetime) {
        this.sign_in_datetime = sign_in_datetime;
    }

    public long getKnock_off_time() {
        return knock_off_time;
    }

    public void setKnock_off_time(long knock_off_time) {
        this.knock_off_time = knock_off_time;
    }

    public String getKnock_off_datetime() {
        return knock_off_datetime;
    }

    public void setKnock_off_datetime(String knock_off_datetime) {
        this.knock_off_datetime = knock_off_datetime;
    }

    public double getTotal_cash() {
        return total_cash;
    }

    public void setTotal_cash(double total_cash) {
        this.total_cash = total_cash;
    }

    public double getAbnormal_money() {
        return abnormal_money;
    }

    public void setAbnormal_money(double abnormal_money) {
        this.abnormal_money = abnormal_money;
    }

    public double getBefore_reserve_money() {
        return before_reserve_money;
    }

    public void setBefore_reserve_money(double before_reserve_money) {
        this.before_reserve_money = before_reserve_money;
    }

    public String getAbnormal_desc() {
        return abnormal_desc;
    }

    public void setAbnormal_desc(String abnormal_desc) {
        this.abnormal_desc = abnormal_desc;
    }

    public double getAfter_reserve_money() {
        return after_reserve_money;
    }

    public void setAfter_reserve_money(double after_reserve_money) {
        this.after_reserve_money = after_reserve_money;
    }

    public String getVerification_result() {
        return verification_result;
    }

    public void setVerification_result(String verification_result) {
        this.verification_result = verification_result;
    }

    public double getCashier_order_cash() {
        return cashier_order_cash;
    }

    public void setCashier_order_cash(double cashier_order_cash) {
        this.cashier_order_cash = cashier_order_cash;
    }

    public double getPack_cash() {
        return pack_cash;
    }

    public void setPack_cash(double pack_cash) {
        this.pack_cash = pack_cash;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
