package com.yun.ma.yi.app.bean;

/**
 * Created by ys on 2017/6/16.
 * 修改密码
 */

public class UserResponse {
    private int uid;
    private String passwrod;
    private String noPassword;
    private String newPassword;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public String getNoPassword() {
        return noPassword;
    }

    public void setNoPassword(String noPassword) {
        this.noPassword = noPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
