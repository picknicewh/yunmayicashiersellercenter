package com.yun.ma.yi.app.userdb;

/**
 * 作者： wh
 * 时间：  2017/7/31
 * 名称：用户角色权限信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UserRuleInfo {
    /**
    * id : 2
    * parent_id : 0
    * name : 收银机1
    * url : cashier1
    * action : add1
    * level : 1
    * node : /0/
    * sort_order : 0
    * is_high_risk : 0
    * is_delete : 0
    * create_time : 2017-07-27 10:51:09
    * update_time : 2017-07-27 10:58:55
    * */
    private int id;
    private int parent_id;
    private String name;
    private String url;
    private String action;
    private int level;
    private String node;
    private int sort_order;
    private int is_high_risk;
    private int is_delete;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getIs_high_risk() {
        return is_high_risk;
    }

    public void setIs_high_risk(int is_high_risk) {
        this.is_high_risk = is_high_risk;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }
}
