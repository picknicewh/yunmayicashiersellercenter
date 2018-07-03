package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/9/4
 * 名称：满送信息类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FullDeliveryInfo  implements Serializable{

    /**
     * id : 1
     * name : 测试满就送
     * is_apply_member : 0
     * is_apply_entity : 0
     * is_apply_mini_shop : 0
     * buy_money : 200
     * assign_product_ids : 1,2,3
     * give_product_id : 110
     * give_product_number : 1
     * is_support_cash_pay : 0
     * is_support_net_pay : 0
     * activity_start_time : 1504108800
     * activity_end_time : 1504108800
     * shop_id : 0
     * add_user_id : 100673
     * add_user_name : maijia
     * edit_user_id : 100673
     * edit_user_name : maijia
     * create_time : 1504108800
     * update_time : 1504165916
     * is_delete : 0
     * status : 0
     * is_sync : 0
     */

    private int id;
    private String name;
    private int is_apply_member;
    private int is_apply_entity;
    private int is_apply_mini_shop;
    private double buy_money;
    private String assign_product_ids;
    private String give_product_id;
    private int give_product_number;
    private int is_support_cash_pay;
    private int is_support_net_pay;
    private String activity_start_time;
    private String activity_end_time;
    private int shop_id;
    private int add_user_id;
    private String add_user_name;
    private int edit_user_id;
    private String edit_user_name;
    private int create_time;
    private int update_time;
    private int is_delete;
    private int status;
    private int is_sync;
   private String assign_product_list;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_apply_member() {
        return is_apply_member;
    }

    public void setIs_apply_member(int is_apply_member) {
        this.is_apply_member = is_apply_member;
    }

    public int getIs_apply_entity() {
        return is_apply_entity;
    }

    public void setIs_apply_entity(int is_apply_entity) {
        this.is_apply_entity = is_apply_entity;
    }

    public int getIs_apply_mini_shop() {
        return is_apply_mini_shop;
    }

    public void setIs_apply_mini_shop(int is_apply_mini_shop) {
        this.is_apply_mini_shop = is_apply_mini_shop;
    }

    public double getBuy_money() {
        return buy_money;
    }

    public void setBuy_money(double buy_money) {
        this.buy_money = buy_money;
    }

    public String getAssign_product_ids() {
        return assign_product_ids;
    }

    public void setAssign_product_ids(String assign_product_ids) {
        this.assign_product_ids = assign_product_ids;
    }

    public String getGive_product_id() {
        return give_product_id;
    }

    public void setGive_product_id(String give_product_id) {
        this.give_product_id = give_product_id;
    }

    public int getGive_product_number() {
        return give_product_number;
    }

    public void setGive_product_number(int give_product_number) {
        this.give_product_number = give_product_number;
    }

    public int getIs_support_cash_pay() {
        return is_support_cash_pay;
    }

    public void setIs_support_cash_pay(int is_support_cash_pay) {
        this.is_support_cash_pay = is_support_cash_pay;
    }

    public int getIs_support_net_pay() {
        return is_support_net_pay;
    }

    public void setIs_support_net_pay(int is_support_net_pay) {
        this.is_support_net_pay = is_support_net_pay;
    }

    public String getActivity_start_time() {
        return activity_start_time;
    }

    public void setActivity_start_time(String activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public String getActivity_end_time() {
        return activity_end_time;
    }

    public void setActivity_end_time(String activity_end_time) {
        this.activity_end_time = activity_end_time;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getAdd_user_id() {
        return add_user_id;
    }

    public void setAdd_user_id(int add_user_id) {
        this.add_user_id = add_user_id;
    }

    public String getAdd_user_name() {
        return add_user_name;
    }

    public void setAdd_user_name(String add_user_name) {
        this.add_user_name = add_user_name;
    }

    public int getEdit_user_id() {
        return edit_user_id;
    }

    public void setEdit_user_id(int edit_user_id) {
        this.edit_user_id = edit_user_id;
    }

    public String getEdit_user_name() {
        return edit_user_name;
    }

    public void setEdit_user_name(String edit_user_name) {
        this.edit_user_name = edit_user_name;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_sync() {
        return is_sync;
    }

    public void setIs_sync(int is_sync) {
        this.is_sync = is_sync;
    }

    public String getAssign_product_list() {
        return assign_product_list;
    }

    public void setAssign_product_list(String assign_product_list) {
        this.assign_product_list = assign_product_list;
    }
}
