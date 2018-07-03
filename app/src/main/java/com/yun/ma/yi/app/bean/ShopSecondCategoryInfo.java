package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/12/4
 * 名称：蚂蚁小店商品二级/三级分类列表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopSecondCategoryInfo {

    /**
     * id : 1065
     * user_id : 100673
     * shop_id : 1000175
     * title : 香烟代购
     * pic_url :
     * parent_cid : 1064
     * system_cid : 100127
     * sort_id : 1
     * status : 99
     * created_at : 2016-10-08 14:32:20
     * updated_at : 2016-10-08 14:32:20
     */

    private int id;
    private int user_id;
    private int shop_id;
    private String title;
    private String pic_url;
    private int parent_cid;
    private int system_cid;
    private int sort_id;
    private int status;
    private String created_at;
    private String updated_at;

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

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getParent_cid() {
        return parent_cid;
    }

    public void setParent_cid(int parent_cid) {
        this.parent_cid = parent_cid;
    }

    public int getSystem_cid() {
        return system_cid;
    }

    public void setSystem_cid(int system_cid) {
        this.system_cid = system_cid;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
