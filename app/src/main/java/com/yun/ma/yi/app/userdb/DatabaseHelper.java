package com.yun.ma.yi.app.userdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yun.ma.yi.app.application.YunmayiApplication;

import java.sql.SQLException;

/**
 * Created by smile on 2017/3/8.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context,"temp", null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (DatabaseHelper.instance == null) {
            YunmayiApplication application = (YunmayiApplication) context.getApplicationContext();
            DatabaseHelper.instance = new DatabaseHelper(application);
        }
        return DatabaseHelper.instance;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        database.beginTransaction();
        try {
            TableUtils.createTable(connectionSource, Item.class);
            database.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                try {
                    TableUtils.createTable(connectionSource, Item.class);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

}
