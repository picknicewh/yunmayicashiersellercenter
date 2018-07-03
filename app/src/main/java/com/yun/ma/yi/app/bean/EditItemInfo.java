package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/4
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EditItemInfo {
    private int aid;
    private int bid;
    private int cid;
    private List<SecondCategoryInfo> mid_category_list;
    private List<SecondCategoryInfo>  leaf_category_list;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<SecondCategoryInfo> getMid_category_list() {
        return mid_category_list;
    }

    public void setMid_category_list(List<SecondCategoryInfo> mid_category_list) {
        this.mid_category_list = mid_category_list;
    }

    public List<SecondCategoryInfo> getLeaf_category_list() {
        return leaf_category_list;
    }

    public void setLeaf_category_list(List<SecondCategoryInfo> leaf_category_list) {
        this.leaf_category_list = leaf_category_list;
    }
}
