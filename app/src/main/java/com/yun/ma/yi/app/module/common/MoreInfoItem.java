package com.yun.ma.yi.app.module.common;

import android.app.Activity;

/**
 * Created by ys on 2017/6/2.
 * 目录
 */

public class MoreInfoItem {

    private int imageId;
    private String Text;
    private Class<Activity> cla;

    public MoreInfoItem(int imageId,String Text){
        this.imageId = imageId;
        this.Text = Text;
    }
    public MoreInfoItem(int imageId,String Text,Class activity){
        this.imageId = imageId;
        this.Text = Text;
        this.cla = activity;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Class<Activity> getCla() {
        return cla;
    }

    public void setCla(Class<Activity> cla) {
        this.cla = cla;
    }
}
