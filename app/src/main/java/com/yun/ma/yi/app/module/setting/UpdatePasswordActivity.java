package com.yun.ma.yi.app.module.setting;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.UserResponse;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.module.Login.LoginActivity;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.module.setting.SettingContract.ISettingPresenter;
import com.yun.ma.yi.app.module.setting.SettingContract.IUpdatePasswordView;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ys on 2017/6/15.
 * 修改密码
 */

public class UpdatePasswordActivity extends BaseActivity implements IUpdatePasswordView{
    @BindView(R.id.useranme)
    ItemTextView useranme;
    @BindView(R.id.old_password)
    ItemEditText2 oldPassword;
    @BindView(R.id.new_password)
    ItemEditText2 newPassword;
    @BindView(R.id.pasword)
    ItemEditText2 pasword;

    private ISettingPresenter settingPersenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.up_pas);
        settingPersenter = new SettingPersenter(this,this);
        useranme.initLabel(getString(R.string.username), "");
        useranme.initData(UserMessage.getInstance().getUsername());

    }

    @OnClick(R.id.save_password)
    public void onSavePassword(){
        settingPersenter.updateUser();
    }

    @Override
    public UserResponse getUser() {
        UserResponse user = new UserResponse();
        user.setUid( UserMessage.getInstance().getUId());
        user.setNewPassword(newPassword.getText());
        user.setNoPassword(oldPassword.getText());
        user.setPasswrod(pasword.getText());
        return user;
    }

    @Override
    public void updateUser() {
        finish();
        YunmayiApplication.destoryActivity("HomeActivity");
        startActivity(new Intent(this, LoginActivity.class));
    }
}
