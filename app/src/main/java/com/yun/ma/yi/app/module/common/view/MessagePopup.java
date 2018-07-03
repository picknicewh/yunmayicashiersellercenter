package com.yun.ma.yi.app.module.common.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/8/22
 * 名称：确认弹框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MessagePopup extends PopupWindow {
    private Context context;
    private View contentView;
    private TextView tvMessage;
    public MessagePopup(Context context) {
        this.context = context;
        init();
    }
    private void initView() {
        contentView = LayoutInflater.from(context).inflate(R.layout.popup_message, null);
        tvMessage=(TextView)  contentView.findViewById(R.id.tv_message);
    }

    public void setMessage(String message) {
       tvMessage.setText(message);
    }

    public void init() {
        initView();
        //设置SignPopupWindow的View
        setContentView(contentView);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SignPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SignPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        //设置SignPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}

