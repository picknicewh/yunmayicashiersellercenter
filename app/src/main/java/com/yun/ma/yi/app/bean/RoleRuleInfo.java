package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/28
 * 名称：角色权限类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleRuleInfo  implements Serializable{
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
     * checked : 0
     * list : [{"id":5,"parent_id":2,"name":"开钱箱","url":"/cashier/open/","action":"open","level":2,"node":"/0/1/","sort_order":0,"is_high_risk":0,"is_delete":0,"create_time":"2017-07-28 14:45:55","update_time":"2017-07-28 14:45:59","checked":0}]
     */

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
    private String create_time;
    private String update_time;
    private int checked;
    private List<ChildRuleInfo> list;

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

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public List<ChildRuleInfo> getList() {
        return list;
    }

    public void setList(List<ChildRuleInfo> list) {
        this.list = list;
    }

    public static class ChildRuleInfo {
        /**
         * id : 5
         * parent_id : 2
         * name : 开钱箱
         * url : /cashier/open/
         * action : open
         * level : 2
         * node : /0/1/
         * sort_order : 0
         * is_high_risk : 0
         * is_delete : 0
         * create_time : 2017-07-28 14:45:55
         * update_time : 2017-07-28 14:45:59
         * checked : 0
         */

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
        private String create_time;
        private String update_time;
        private int checked;

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

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }
    }
}
