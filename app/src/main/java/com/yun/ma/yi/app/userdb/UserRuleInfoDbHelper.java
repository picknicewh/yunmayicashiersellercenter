package com.yun.ma.yi.app.userdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yun.ma.yi.app.bean.UserInfo;
import com.yun.ma.yi.app.utils.G;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间： 2016/8/31
 * 名称：角色信息操作类（增删改查）
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UserRuleInfoDbHelper {

    private static final String dbname= "userRuleInfo";

    /**
     * 插入数据
     * @param db 数据库
     *@param id 权限角色
     *@param  parent_id 父if
     *@param url 地址
     *@param action 活动
     *@param name 权限名字
     *@param node 权限节点（0）父节点 1
     *@param level 权限等级
     *@param sort_order 权限筛选顺序
     *@param is_high_risk 是否高危险
     *@param  is_delete 是否可删除
     */
    public  static void insert(SQLiteDatabase db, int id, int parent_id, String name,String url,String action,String node,int level,int sort_order,int is_high_risk,int is_delete) {
        String sql = "insert into  "+dbname + "(id,parent_id,name,url,action,node,level,sort_order,is_high_risk,is_delete) values" +
                " (" + id + "," + parent_id + ",'"+name+"','"+url+"','" + action + "','" + node + "'," + level + "," + sort_order + "," + is_high_risk + "," + is_delete + ")";
        db.execSQL(sql);
    }
    /**
     * 插入权限列表
     * @param userInfo 用户信息
     * @param db 数据库
     */
    public static void insertAll(UserInfo userInfo,SQLiteDatabase db){

        if (!isEmpty(db)){}{
            delete(db);
        }
        //保存角色权限信息
        if (userInfo.getRule()!=null){
            for (int i = 0  ;i<userInfo.getRule().size();i++){
                UserInfo.Rule rule  = userInfo.getRule().get(i);
                insert(db,rule.getId(),rule.getParent_id(),rule.getName(),rule.getUrl(),
                    rule.getAction(),rule.getNode(),rule.getLevel(),rule.getSort_order(),
                    rule.getIs_high_risk(),rule.getIs_delete());
                G.log("xxxxxxxx"+rule.getName()+"====="+rule.getUrl());
        }
        }
    }


    /**
     * 查询所有数据数据,获取列表信息
     * @param  db 数据库
     */
    public static List<UserRuleInfo> getUserRuleInfoList(SQLiteDatabase db){
        List<UserRuleInfo> userRuleInfoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from  "+dbname,null);
        while (cursor.moveToNext()){
            UserRuleInfo userRuleInfo = new UserRuleInfo();
            userRuleInfo.setId( cursor.getInt(cursor.getColumnIndex("id")));
            userRuleInfo.setParent_id(cursor.getInt(cursor.getColumnIndex("parent_id")));
            userRuleInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
            userRuleInfo.setUrl(cursor.getString( cursor.getColumnIndex("url")));
            userRuleInfo.setAction(cursor.getString( cursor.getColumnIndex("action")));
            userRuleInfo.setNode( cursor.getString(cursor.getColumnIndex("node")));
            userRuleInfo.setSort_order(cursor.getInt(cursor.getColumnIndex("sort_order")));
            userRuleInfo.setIs_high_risk(cursor.getInt(cursor.getColumnIndex("is_high_risk")));
            userRuleInfo.setIs_delete(cursor.getInt(cursor.getColumnIndex("is_delete")));
            userRuleInfoList.add(userRuleInfo);
          //  G.log("xxxxxxxx"+userRuleInfo.getName()+"====="+userRuleInfo.getUrl());
        }
        cursor.close();
        return userRuleInfoList;
    }
    /**
     * 查询是否包含某个规则
     */
    public static boolean isContainRule(SQLiteDatabase db,String rule){
        List<UserRuleInfo> ruleInfoList = getUserRuleInfoList(db);
        for (int i = 0 ;i<ruleInfoList.size();i++){
            if (ruleInfoList.get(i).getUrl().equals(rule)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否数据是否为空
     * @param  db 数据库
     */
    public  static boolean  isEmpty(SQLiteDatabase db){
        Cursor cursor  =db.rawQuery("select * from "+dbname,null);
        while (cursor.moveToNext()){
            return false;
        }
        return true;
    }
    /**
     *删除通过id号删除单个系统消息
     * @param  db 数据库
     * @param id 唯一用户id号
     */
    public static void  deleteByTsId(SQLiteDatabase db,int id){
        String sql="delete from "+dbname+" where uid =" + id;
        db.execSQL(sql);
    }
    /**
     * 删除所有
     * @param db 数据库
     */
    public static void delete(SQLiteDatabase db){
        String sql="delete from  "+dbname;
        db.execSQL(sql);
        G.log(isEmpty(db)+"---是否删除数据库成功");
    }

}

