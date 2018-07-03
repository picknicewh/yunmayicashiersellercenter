package com.yun.ma.yi.app.Jpush;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class TestActivity extends BaseActivity {

    /**
     * 推送的标题
     */
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /**
     * 推送的内容
     */
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if (bundle != null) {
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tvTitle.setText(title);
            tvContent.setText(content);
        }
    }
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleText("极光推送测试页");
    }
}
