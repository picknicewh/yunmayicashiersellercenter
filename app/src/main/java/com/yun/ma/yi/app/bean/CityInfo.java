package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/11/24
 * 名称：城市信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CityInfo {

    /**
     * id : 1
     * number : 110100
     * name : 市辖区
     * parent_id : 110000
     */

    private int id;
    private int number;
    private String name;
    private int parent_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

}
