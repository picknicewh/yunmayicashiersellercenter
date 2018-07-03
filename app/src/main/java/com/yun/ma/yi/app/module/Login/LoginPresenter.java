package com.yun.ma.yi.app.module.Login;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.UserInfo;
import com.yun.ma.yi.app.bean.Version;
import com.yun.ma.yi.app.module.Login.LoginContract.ILoginView;
import com.yun.ma.yi.app.utils.G;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 登录
 */

public class LoginPresenter implements LoginContract.ILoginPresenter {

    private ILoginView loginView;
    private Subscription mSubscription;
    private Context context;

    public LoginPresenter(ILoginView loginView, Context context){
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void login() {
        //检查入参是否为空
        if (!LoginCheckText.check(loginView)){
            return;
        }
        if (G.isEmteny(loginView.getUserName()) || G.isEmteny(loginView.getPassword())){
            G.showToast(context,"用户名或者密码不能为空！");
            return;
        }
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("username",loginView.getUserName());
        parameter.setParam("password",loginView.getPassword());
        mSubscription = ApiManager.getApiManager().login(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<UserInfo>>() {
                            @Override
                            public void beforeRequest() {
                                loginView.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                loginView.hideProgress();
                                loginView.showMessage(msg);
                            }

                            @Override
                            public void requestComplete() {
                                loginView.hideProgress();
                            }

                            @Override
                            public void requestSuccess(Result<UserInfo> data) {
                                if (data != null){
                                    loginView.loginSuccess(data.getData());
                                }
                            }
                        },context)
                );
    }

    @Override
    public void childLogin() {
        //检查入参是否为空
        if (!LoginCheckText.check(loginView)){
            return;
        }
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("username",loginView.getUserName());
        parameter.setParam("password",loginView.getPassword());
        if (G.isEmteny(loginView.getUserName()) || G.isEmteny(loginView.getPassword())){
            G.showToast(context,"用户名或者密码不能为空！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                 .childLogin(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<UserInfo>>() {
                    @Override
                    public void beforeRequest() {
                        loginView.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        loginView.hideProgress();
                        loginView.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        loginView.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<UserInfo> data) {
                        G.log("xxxxxx"+data.getData());
                        if (data.getData() != null){
                            loginView.loginSuccess(data.getData());
                        }
                    }
                },context)
        );
    }

    @Override
    public void getVersion() {
        RequestParameter parameter = new RequestParameter();
        mSubscription = ApiManager.getApiManager().getVersion(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<Version>>() {
                    @Override
                    public void beforeRequest() {

                    }

                    @Override
                    public void requestError(String msg) {
                        loginView.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {

                    }

                    @Override
                    public void requestSuccess(Result<Version> data) {
                        if (data != null){
                            loginView.getVersion(data.getData());
                        }
                    }
                },context));
    }
}
