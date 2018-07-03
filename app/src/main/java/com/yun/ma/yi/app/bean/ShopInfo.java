package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/11/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopInfo  implements Serializable{

    /**
     * id : 1000660
     * app_id : 100000
     * user_id : 105305
     * title : 测试修改
     * description :
     * notice :
     * logo_url :
     * delivery_range : 2000
     * delivery_fee : 2147483647
     * minimum_amount : 2147483647
     * shop_status : 1
     * rate_count : 0
     * com_score : 0.000
     * item_score : 0.000
     * service_score : 0.000
     * delivery_score : 0.000
     * day_open_time : 07:00
     * day_close_time : 22:30
     * night_open_time :
     * night_close_time :
     * support_cod : 2
     * support_online : 1
     * prov_id : 330000
     * city_id : 330100
     * district_id : 330105
     * prov_name : 浙江省
     * city_name : 杭州市
     * district_name : 西湖区
     * address : 浙大紫荆港
     * longitude : 120153576
     * latitude : 30287459
     * name : 微风
     * mobile : 18626888192
     * phone :
     * email :
     * balance : 0
     * status : 99
     * max_return_cash : 0
     * type_str : 超市
     * sort_id : 0
     * created_at : 2016-09-30 18:20:40
     * updated_at : 2016-11-04 15:08:26
     * order_count : 0
     * useful_count : 0
     * fix_useful_count : 0
     * expire_time : 0000-00-00 00:00:00
     */

    private int id;
    private int app_id;
    private int user_id;
    private String title;
    private String description;
    private String notice;
    private String logo_url;
    private int delivery_range;
    private double delivery_fee;
    private double minimum_amount;
    private int shop_status;
    private int rate_count;
    private String com_score;
    private String item_score;
    private String service_score;
    private String delivery_score;
    private String day_open_time;
    private String day_close_time;
    private String night_open_time;
    private String night_close_time;
    private int support_cod;
    private int support_online;
    private int prov_id;
    private int city_id;
    private int district_id;
    private String prov_name;
    private String city_name;
    private String district_name;
    private String address;
    private double longitude;
    private double latitude;
    private String name;
    private String mobile;
    private String phone;
    private String email;
    private double balance;
    private int status;
    private int max_return_cash;
    private String type_str;
    private int sort_id;
    private String created_at;
    private String updated_at;
    private int order_count;
    private int useful_count;
    private int fix_useful_count;
    private String expire_time;
    private int   isHaveCashier;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public int getDelivery_range() {
        return delivery_range;
    }

    public void setDelivery_range(int delivery_range) {
        this.delivery_range = delivery_range;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public double getMinimum_amount() {
        return minimum_amount;
    }

    public void setMinimum_amount(double minimum_amount) {
        this.minimum_amount = minimum_amount;
    }

    public int getShop_status() {
        return shop_status;
    }

    public void setShop_status(int shop_status) {
        this.shop_status = shop_status;
    }

    public int getRate_count() {
        return rate_count;
    }

    public void setRate_count(int rate_count) {
        this.rate_count = rate_count;
    }

    public String getCom_score() {
        return com_score;
    }

    public void setCom_score(String com_score) {
        this.com_score = com_score;
    }

    public String getItem_score() {
        return item_score;
    }

    public void setItem_score(String item_score) {
        this.item_score = item_score;
    }

    public String getService_score() {
        return service_score;
    }

    public void setService_score(String service_score) {
        this.service_score = service_score;
    }

    public String getDelivery_score() {
        return delivery_score;
    }

    public void setDelivery_score(String delivery_score) {
        this.delivery_score = delivery_score;
    }

    public String getDay_open_time() {
        return day_open_time;
    }

    public void setDay_open_time(String day_open_time) {
        this.day_open_time = day_open_time;
    }

    public String getDay_close_time() {
        return day_close_time;
    }

    public void setDay_close_time(String day_close_time) {
        this.day_close_time = day_close_time;
    }

    public String getNight_open_time() {
        return night_open_time;
    }

    public void setNight_open_time(String night_open_time) {
        this.night_open_time = night_open_time;
    }

    public String getNight_close_time() {
        return night_close_time;
    }

    public void setNight_close_time(String night_close_time) {
        this.night_close_time = night_close_time;
    }

    public int getSupport_cod() {
        return support_cod;
    }

    public void setSupport_cod(int support_cod) {
        this.support_cod = support_cod;
    }

    public int getSupport_online() {
        return support_online;
    }

    public void setSupport_online(int support_online) {
        this.support_online = support_online;
    }

    public int getProv_id() {
        return prov_id;
    }

    public void setProv_id(int prov_id) {
        this.prov_id = prov_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMax_return_cash() {
        return max_return_cash;
    }

    public void setMax_return_cash(int max_return_cash) {
        this.max_return_cash = max_return_cash;
    }

    public String getType_str() {
        return type_str;
    }

    public void setType_str(String type_str) {
        this.type_str = type_str;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getUseful_count() {
        return useful_count;
    }

    public void setUseful_count(int useful_count) {
        this.useful_count = useful_count;
    }

    public int getFix_useful_count() {
        return fix_useful_count;
    }

    public void setFix_useful_count(int fix_useful_count) {
        this.fix_useful_count = fix_useful_count;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public int getIsHaveCashier() {
        return isHaveCashier;
    }

    public void setIsHaveCashier(int isHaveCashier) {
        this.isHaveCashier = isHaveCashier;
    }
}
