package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * Created by ys on 2017/6/16.
 * 修改店铺信息
 */

public class Shop  implements Serializable{

    /**
     * company : 买家测试
     * address : 测试
     * name : 糊涂斯基
     * mobile : 18358119525
     * number:571999998
     */

    private String company;
    private String address;
    private String name;
    private String mobile;
    private String number;
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
