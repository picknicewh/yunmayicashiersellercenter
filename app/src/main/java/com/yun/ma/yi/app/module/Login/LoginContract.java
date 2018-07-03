package com.yun.ma.yi.app.module.Login;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.UserInfo;
import com.yun.ma.yi.app.bean.Version;

/**
 * Created by ys on 2017/5/31.
 * 登录页面
 */

public class LoginContract {

    interface ILoginView extends BaseView{
        /**获取用户名*/
        String getUserName();
        /**获取密码*/
        String getPassword();

        /**登录成功*/
        void loginSuccess(UserInfo userInfo);
        /**获取最新版本*/
        void getVersion(Version version);
    }

    interface ILoginPresenter extends BasePresenter{
        /**登录*/
        void login();
        /**子账号登录*/
        void childLogin();
        /**获取最新版本*/
        void getVersion();
    }

}
