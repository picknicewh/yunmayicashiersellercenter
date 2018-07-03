package com.yun.ma.yi.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ys on 2017/6/10.
 * 获取当前时间和星期
 */

public class SystemTime {
    /**获取时间 和 星期*/
    public static String getDateTime(){
        long time=System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String time1 = format.format(date);
        format = new SimpleDateFormat("EEEE");
        String time2 = format.format(date);
        return time1+"  "+time2;
    }
    /**获取根据日期时间 和 星期*/
    public static String getDateTimeByDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String time1 = format.format(date);
        format = new SimpleDateFormat("EEEE");
        String time2 = format.format(date);
        return time1+"  "+time2;
    }
    /**获取系统当前时间*/
    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
       return simpleDateFormat.format(date);
    }
    /**获取系统当前时间*/
    public static String getDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    /**获取一周期的时间*/
    public static String lastWeek(){
        Date date = new Date();
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(date))-6;

        if(day<1){
            month-=1;
            if(month==0){
                year-=1;month=12;
            }
            if(month==4||month==6||month==9||month==11){
                day=30+day;
            }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
            {
                day=31+day;
            }else if(month==2){
                if(year%400==0||(year %4==0&&year%100!=0))day=29+day;
                else day=28+day;
            }
        }
        String y = year+"";String m ="";String d ="";
        if(month<10) m = "0"+month;
        else m=month+"";
        if(day<10) d = "0"+day;
        else d = day+"";

        return y+"-"+m+"-"+d;
    }
}
