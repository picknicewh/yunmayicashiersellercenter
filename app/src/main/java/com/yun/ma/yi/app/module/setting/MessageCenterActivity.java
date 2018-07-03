package com.yun.ma.yi.app.module.setting;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * Created by ys on 2017/6/28.
 * 消息中心
 */

public class MessageCenterActivity extends BaseActivity {


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_message_center;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.message_center);
    }

    @OnClick(R.id.inventory_warning)
    public void onInventoryWarning(){
        startActivity(new Intent(this,InventoryWarningActivity.class));
    }

    @OnClick(R.id.notice)
    public void onNotice(){
        Intent intent = new Intent(this,NoticeCenterActivity.class);
        intent.putExtra("type",2);
        startActivity(intent);
    }

}
