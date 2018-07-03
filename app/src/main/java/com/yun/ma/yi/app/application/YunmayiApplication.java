package com.yun.ma.yi.app.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Shop;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.userdb.UserRuleInfoDb;
import com.yun.ma.yi.app.userdb.UserRuleInfoDbHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

import static android.content.ContentValues.TAG;

/**
 * Created by ys on 2017/5/31.
 */

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          YunmayiApplication extends Application {
    public static Map<String, Activity> destoryMap  = new HashMap<>();/***保存一些 activity*/
    private static YunmayiApplication INSTANCE;
    /**
     * 收银机店铺信息
     */
    private static Shop shop;
    /**
     * 分类信息
     */
    private static List<GoodsListInfo> categoryInfos;
    /**
     * actvity集合
     */
    private static LinkedList<Activity> activityLinkedList ;

    public static YunmayiApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        activityLinkedList = new LinkedList<>();
        registerActivityStaus();
        db = UserRuleInfoDb.getInstance().getWritableDatabase();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

}

    // 获取ApplicationContext
    public static Context getContext() {
        return INSTANCE;
    }

    /**
     * 添加到销毁队列
     * @param activity 要销毁的activity
     */
    public static void addDestoryActivity(Activity activity,String activityName) {
        destoryMap.put(activityName, activity);
    }

     private static SQLiteDatabase db;

     public static boolean isCanOperation(String rule){
         boolean isManager= UserMessage.getInstance().getParent_id()==0;
         return  isManager|| UserRuleInfoDbHelper.isContainRule(db,rule);
     }
    /**
     *销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet= destoryMap.keySet();
        for (String key:keySet){
            if (key.equals(activityName)){
                destoryMap.get(key).finish();
            }
        }
    }
    /**
     * 获取收银机店铺信息
     */
    public static Shop getShop() {
        return shop;
    }
    /**
     * 设置收银机店铺信息
     */
    public static void setShop(Shop shop) {
        YunmayiApplication.shop = shop;
    }

    /**
     * 获取分类列表
     */
    public static List<GoodsListInfo> getCategoryInfos() {
        return categoryInfos;
    }
    /**
     * 设置分类列表
     */
    public static void setCategoryInfos(List<GoodsListInfo> categoryInfos) {
        YunmayiApplication.categoryInfos = categoryInfos;
    }
    /**
     * 注册页面状态
     */
    private void registerActivityStaus(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d(TAG, "onActivityCreated: " + activity.getLocalClassName());
                activityLinkedList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted: " + activity.getLocalClassName());
            }
            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed: " + activity.getLocalClassName());
            }
            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused: " + activity.getLocalClassName());
            }
            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped: " + activity.getLocalClassName());
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d(TAG, "onActivityDestroyed: " + activity.getLocalClassName());
                activityLinkedList.remove(activity);
            }
        });
    }
    /**
     * 退出app
     */
    public static void exitApp() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
    }
}
