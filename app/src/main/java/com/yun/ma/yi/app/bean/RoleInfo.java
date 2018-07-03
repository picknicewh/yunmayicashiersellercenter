package com.yun.ma.yi.app.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RoleInfo  implements Serializable{

    private String rule_ids;
    /**
     * id : 2
     * user_id : 100673
     * name : 配送员
     * is_delete : 0
     * create_time : 2017-07-27 17:54:47
     * update_time : 2017-07-27 17:54:47
     */

    private int id;
    private int user_id;
    private String name;
    private int is_delete;
    private String create_time;
    private String update_time;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getRule_ids() {
        return rule_ids;
    }

    public void setRule_ids(String rule_ids) {
        this.rule_ids = rule_ids;
    }


}
