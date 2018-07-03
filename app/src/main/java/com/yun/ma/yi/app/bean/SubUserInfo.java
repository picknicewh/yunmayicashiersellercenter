package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：子账号信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SubUserInfo implements Serializable {

    /**
     * id : 1
     * user_id : 100673
     * username : 001
     * password : f3f050cad600fb0bf858811e7000ae88
     * salt : fnoAFIUVZ0
     * name : 陈琳
     * mobile : 13735484322
     * is_enabled : 1
     * is_delete : 0
     * create_time : 2017-07-27 15:22:22
     * update_time : 2017-07-27 15:22:22
     */

    private int id;
    private int user_id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String mobile;
    private int is_enabled;
    private int is_delete;
    private String create_time;
    private String update_time;
    private int role_id;
    private String role_name;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
