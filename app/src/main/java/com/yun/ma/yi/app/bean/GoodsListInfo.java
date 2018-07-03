package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：商品列表信息类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsListInfo {

    /**
     * count : 16
     * category_id : 0
     * category_name :
     * user_id : 100673
     */

    private int count;
    private int category_id;
    private String category_name;
    private int user_id;
    private boolean isCheckAll;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isCheckAll() {
        return isCheckAll;
    }

    public void setCheckAll(boolean checkAll) {
        isCheckAll = checkAll;
    }
}
