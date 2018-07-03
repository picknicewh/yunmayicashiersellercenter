package com.yun.ma.yi.app.userdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yun.ma.yi.app.application.YunmayiApplication;

/**
 * ================================================
 * 作    者：wh
 * 时    间：2016/7/26
 * 描    述：角色权限数据库
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class UserRuleInfoDb extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db"; //数据库名称
    private static final int version = 1; //数据库版本
    private static final String TABLENAME = "userRuleInfo";//表名
    private static UserRuleInfoDb userRuleInfoDb;
    public static  UserRuleInfoDb getInstance(){
        if (userRuleInfoDb==null){
            userRuleInfoDb = new UserRuleInfoDb(YunmayiApplication.getInstance().getApplicationContext());
        }
        return userRuleInfoDb;
    }
    public UserRuleInfoDb(Context context) {
        super(context, DB_NAME, null, 102);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table " +TABLENAME+
                "(id integer primary key autoincrement," +
                "parent_id integer not null,"+
                "name varchar(50) not null," +
                "url varchar(50) not null," +
                "action varchar(50) not null," +
                "node varchar(50) not null," +
                "level integer,"+
                "sort_order integer,"+
                "is_high_risk integer,"+
                "is_delete integer)";
        db.execSQL(sql);
    }

    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "drop table if exists " + TABLENAME;
        db.execSQL(sql);
        this.onCreate(db);
    }
}
