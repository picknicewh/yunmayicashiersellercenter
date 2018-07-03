package com.yun.ma.yi.app.module.goods.sort.childsort;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者： wh
 * 时间：  2017/6/23
 * 名称：是否还有子分类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SortUtil {
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;
    private static SortUtil sortUtil;
    public SortUtil(Context context) {
        spf=context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        editor=spf.edit();
    }
    public  static SortUtil getInstance(Context context){
        if(null==sortUtil){
            sortUtil=new SortUtil(context.getApplicationContext());
        }
        return sortUtil;
    }
    /**
     * 设置用户是否没有分类
     * @param  hasSort
     */
    public void  setHasSort(boolean hasSort){
        editor.putBoolean("hasSort",hasSort);
        editor.commit();
    }
    public boolean getHasSort(){
        return spf.getBoolean("hasSort",true);
    }
}
