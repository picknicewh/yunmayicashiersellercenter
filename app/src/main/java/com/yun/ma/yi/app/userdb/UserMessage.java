package com.yun.ma.yi.app.userdb;

import android.content.Context;
import android.content.SharedPreferences;

import com.yun.ma.yi.app.application.YunmayiApplication;

/**
 * ================================================
 * 作    者：wh
 * 时    间：2016/7/19
 * 描    述：用户信息存储类
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class UserMessage {
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;
    private static UserMessage userInfo;

    public UserMessage(Context context) {
        spf = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = spf.edit();
    }

    public synchronized static UserMessage getInstance() {
        if (null == userInfo) {
            userInfo = new UserMessage(YunmayiApplication.getInstance().getApplicationContext());
        }
        return userInfo;
    }

    /**
     * 当前用户查询用户信息id
     * 如果父账号的id=0则当前id = user_id
     * 如果父账号的id！=0则当前id = parent_id
     */
    public int getUId() {
        return spf.getInt("uid", 1);
    }

    /**
     * 设置当前用户查询用户信息id
     *
     * @param uid 用户信息id
     */
    public void setUId(int uid) {
        editor.putInt("uid", uid);
        editor.commit();
    }

    /**
     * 当前用户的父id
     */
    public int getParent_id() {
        return spf.getInt("parent_id", 1);
    }

    /**
     * 设置当前用户的父id
     *
     * @param parent_id 父id
     *                  如果是管理员账号parent_id=0
     */
    public void setParent_id(int parent_id) {
        editor.putInt("parent_id", parent_id);
        editor.commit();
    }

    /**
     * 当前用户id
     * 可能是子账号的子id
     * 也可能是管理员的真实id
     */
    public int getUser_id() {
        return spf.getInt("user_id", 1);
    }

    /**
     * 设置用户id
     *
     * @param user_id 用户id
     */
    public void setUser_id(int user_id) {
        editor.putInt("user_id", user_id);
        editor.commit();
    }

    /**
     * 用户的姓名（登陆名）
     */
    public String getUsername() {
        return spf.getString("username", null);
    }

    /**
     * 设置用户的名字
     *
     * @param username 用户的名字
     */
    public void setUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }

    public String getSalt() {
        return spf.getString("salt", null);
    }

    public void setSalt(String salt) {
        editor.putString("salt", salt);
        editor.commit();
    }

    /**
     * 微信支付id
     */
    public String getWx_merchant_id() {
        return spf.getString("wx_merchant_id", null);
    }

    /**
     * 设置微信支付id
     *
     * @param wx_merchant_id 微信支付id
     */
    public void setWx_merchant_id(String wx_merchant_id) {
        editor.putString("wx_merchant_id", wx_merchant_id);
        editor.commit();

    }

    public String getAlipay_merchant_id() {
        return spf.getString("alipay_merchant_id", null);
    }

    /**
     * 设置支付宝支付id
     *
     * @param alipay_merchant_id 支付宝支付id
     */
    public void setAlipay_merchant_id(String alipay_merchant_id) {

        editor.putString("alipay_merchant_id", alipay_merchant_id);
        editor.commit();
    }

    public int getUnique_number() {
        return spf.getInt("unique_number", -1);

    }

    public void setUnique_number(int unique_number) {
        editor.putInt("unique_number", unique_number);
        editor.commit();
    }

    public int isChain() {
        return spf.getInt("is_chain", 0);
    }

    public void setIsChain(int is_chain) {
        editor.putInt("is_chain", is_chain);
        editor.commit();
    }

    public String getAuth_code() {
        return spf.getString("auth_code", null);

    }

    public void setAuth_code(String auth_code) {
        editor.putString("auth_code", auth_code);
        editor.commit();
    }

    /**
     * 设置用户的密码（登陆的密码）
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    /**
     * 用户的密码（登陆的密码）
     */
    public String getPassword() {
        return spf.getString("password", null);
    }

    /**
     * 蚂蚁小店的token
     * @param token token 值
    */
    public void setToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {
        return spf.getString("token", null);
    }

    /**
     * 收银机批发的小店名称
     * @param shopName 小店名称
     */
    public void setShopName(String shopName) {
        editor.putString("shopName", shopName);
        editor.commit();
    }

    public String getShopName() {
        return spf.getString("shopName", null);
    }
    /**
     * 收银机批发的小店电话
     * @param mobile 小店电话
     */
    public void setMobile(String mobile) {
        editor.putString("mobile", mobile);
        editor.commit();
    }

    public String getMobile() {
        return spf.getString("mobile", null);
    }

    /**
     * 收银机批发的小店地址
     * @param shopAddress 小店地址
     */
    public void setShopAddress(String shopAddress) {
        editor.putString("shopAddress", shopAddress);
        editor.commit();
    }

    public String getShopAddress() {
        return spf.getString("shopAddress", null);
    }

    /**
     * 收银机批发的小店地址--》省
     * @param provName 小店地址--》省
     */
    public void setProvName(String provName) {
        editor.putString("provName", provName);
        editor.commit();
    }

    public String getProvName() {
        return spf.getString("provName", null);
    }

    /**
     * 收银机批发的小店地址--》区
     * @param  areaName 小店地址--》区
     */
    public void setAreaName(String areaName) {
        editor.putString("areaName", areaName);
        editor.commit();
    }

    public String getAreaName() {
        return spf.getString("areaName", null);
    }

    public String getCityName() {
        return spf.getString("cityName", null);
    }
    /**
     * 收银机批发的小店地址--》市
     * @param  cityName 小店地址--》市
     */
    public void setCityName(String cityName) {
        editor.putString("cityName", cityName);
        editor.commit();
    }

    public int getAreaId() {
        return spf.getInt("areaId", 0);
    }

    /**
     * 收银机批发的小店地址--》区id
     * @param  areaId 小店地址--》区id
     */
    public void setAreaId(int areaId) {
        editor.putInt("areaId", areaId);
        editor.commit();
    }

    public int geCityId() {
        return spf.getInt("cityId", 0);
    }

    /**
     * 收银机批发的小店地址--》市id
     * @param  cityId 小店地址--》市id
     */
    public void setCityId(int cityId) {
        editor.putInt("cityId", cityId);
        editor.commit();
    }

    public int getProvId() {
        return spf.getInt("provId", 0);
    }
    /**
     * 收银机批发的小店地址--》省id
     * @param  provId 小店地址--》省id
     */
    public void setProvId(int provId) {
        editor.putInt("provId", provId);
        editor.commit();
    }
}
