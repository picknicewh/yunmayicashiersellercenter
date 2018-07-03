package com.yun.ma.yi.app.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.Jpush.JPushBaseActivity;
import com.yun.ma.yi.app.module.common.LoadingDialog;
import com.yun.ma.yi.app.utils.StatusBarUtil;
import com.yun.ma.yi.app.utils.ToastUtils;
import com.yunmayi.app.manage.R;

import butterknife.ButterKnife;

/**
 * Activity基类，所有Activity应该继承此类
 * Author: ys
 * Date: 2017-04-07  14:33
 */
public abstract class BaseActivity extends JPushBaseActivity implements BaseView {

    private ImageButton titleBack;
    private TextView titleText;
    private TextView subtitleText;

    private FrameLayout body;
    private LinearLayout titleLayout;
    private LoadingDialog loadingDialog;
    private boolean isNotHide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         beforeInit();
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (!isNotHide){
            super.setContentView(R.layout.activity_title);
            body = (FrameLayout) findViewById(R.id.body);
            titleText = (TextView) findViewById(R.id.title_text);
            subtitleText   = (TextView) findViewById(R.id.subtitle_text);
            titleLayout = (LinearLayout) findViewById(R.id.title_layout);
            body.requestFocus();
            getLayoutInflater().inflate(layoutResID, body, true);
            //返回结束当前页面
            titleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setBack();
                }
            });
        }else{
            super.setContentView(R.layout.activity_title_null);
            body = (FrameLayout) findViewById(R.id.body);
            body.requestFocus();
            getLayoutInflater().inflate(layoutResID, body, true);
        }
        ButterKnife.bind(this);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
    }

    /**
     * 添加返回键事件
     * 默认finish
     */
    public void setBack(){
        finish();
    }
    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

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

    /**
     * 设置标题
     * @param subtitleText string
     */
    public void setSubTitleText(String subtitleText){
        this.subtitleText.setText(subtitleText);
    }

    public  void setSubtitleClickListener(View.OnClickListener onClickListener){
        if (onClickListener!=null){
            subtitleText.setOnClickListener(onClickListener);
        }
    }
    public  void setBackClickListener(View.OnClickListener onClickListener){
        if (onClickListener!=null){
            titleLayout.setOnClickListener(onClickListener);
        }
    }
    /**
     * 设置标题
     * @param titleText string
     */
    public void setTitleText(String titleText){
        this.titleText.setText(titleText);
    }
    /**
     * 设置标题
     * @param textId int资源
     */
    public void setTitleTextId(int textId){
        this.titleText.setText(getResources().getText(textId));
    }

    /**
     * 设置隐藏头部
     */
    public void hideTitleLayout(boolean isNotHide){
        this.isNotHide = isNotHide;
    }

    /**
     * 弹出信息
     */
    @Override
    public void showMessage(String message) {
        ToastUtils.makeText(this, message);
    }

}
