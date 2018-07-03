package com.yun.ma.yi.app.base;

import android.os.Bundle;

import com.yun.ma.yi.app.Jpush.JPushBaseActivity;
import com.yun.ma.yi.app.module.common.LoadingDialog;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.StatusBarUtil;
import com.yun.ma.yi.app.utils.ToastUtils;
import com.yunmayi.app.manage.R;

import butterknife.ButterKnife;

/**
 * 作者： wh
 * 时间：  2018/4/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract  class BaseSimpleActivity extends JPushBaseActivity implements BaseView  {
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            ButterKnife.bind(this);
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(this);
            }
            initView();
        }
    }
    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    protected abstract int getContentViewLayoutID();

    protected abstract  void  initView();
    /**
     * 界面初始化前期准备
     */
    protected void beforeInit() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.red_btn));
    }

    /**
     * 弹出消息
     */
    @Override
    public void toast(String msg) {
        ToastUtils.makeText(this,msg);
    }

    /**
     * 显示加载动画
     */
    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    /**
     * 隐藏加载动画
     */
    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        G.showToast(this,message);
    }
}
