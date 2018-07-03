package com.yun.ma.yi.app.module.setting;

import android.os.Bundle;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.utils.PackageUtil;
import com.yun.ma.yi.app.utils.Utils;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * Created by ys on 2017/6/22.
 * 关于系统
 */

public class AboutInfoActivity extends BaseActivity {
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
         Utils.init(this);
        setTitleTextId(R.string.about);
        tvVersionCode.setText("当前的版本为"+ PackageUtil.getVersionName());
    }


}
