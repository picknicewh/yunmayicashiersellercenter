package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/6/26
 * 名称：商品子集类目信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SecondCategoryInfo {

    /**
     * id : 100089
     * name : 休闲食品
     * parent_cid : 0
     */

    private int id;
    private String name;
    private int parent_cid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public int getParent_cid() {
        return parent_cid;
    }

    public void setParent_cid(int parent_cid) {
        this.parent_cid = parent_cid;
    }
}
