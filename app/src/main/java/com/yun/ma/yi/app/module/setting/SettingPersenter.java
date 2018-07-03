package com.yun.ma.yi.app.module.setting;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.StcokWarningInfo;
import com.yun.ma.yi.app.module.setting.SettingContract.INoticeiView;
import com.yun.ma.yi.app.module.setting.SettingContract.ISettingPresenter;
import com.yun.ma.yi.app.module.setting.SettingContract.IStockWarningView;
import com.yun.ma.yi.app.module.setting.SettingContract.IUpdatePasswordView;
import com.yunmayi.app.manage.R;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 设置
 */

public class SettingPersenter implements ISettingPresenter {

    private IUpdatePasswordView updatePasswordView;
    private Subscription mSubscription;
    private Context context;
    private IStockWarningView stockWarningView;
    private INoticeiView noticeiView;
    private boolean isInit = true;

    public SettingPersenter(IUpdatePasswordView updatePasswordView, Context context){
        this.updatePasswordView = updatePasswordView;
        this.context = context;
    }

    public SettingPersenter(IStockWarningView stockWarningView, Context context){
        this.stockWarningView = stockWarningView;
        this.context = context;
    }

    public SettingPersenter(INoticeiView noticeiView, Context context){
        this.noticeiView = noticeiView;
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
    public void updateUser() {
        //检查入参是否为空
        if (!SettingCheckText.check(updatePasswordView)){
            return;
        }
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",updatePasswordView.getUser().getUid());
        parameter.setParam("old_password",updatePasswordView.getUser().getNoPassword());
        parameter.setParam("new_password",updatePasswordView.getUser().getNewPassword());
        parameter.setParam("re_new_password",updatePasswordView.getUser().getPasswrod());
        mSubscription = ApiManager.getApiManager().updatePassword(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber(new RequestCallback<Result>() {
                            @Override
                            public void beforeRequest() {
                                updatePasswordView.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                updatePasswordView.showMessage(msg);
                                updatePasswordView.hideProgress();
                            }

                            @Override
                            public void requestComplete() {
                                updatePasswordView.hideProgress();
                            }

                            @Override
                            public void requestSuccess(Result data) {
                                if (data != null){
                                    updatePasswordView.showMessage(context.getResources().getString(R.string.save_success));
                                    updatePasswordView.updateUser();
                                }
                            }
                        },context)
                );
    }

    @Override
    public void getStockWarningData() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",stockWarningView.getUser_id());
        parameter.setParam("page",stockWarningView.getPage());
        parameter.setParam("size",stockWarningView.getSize());
        mSubscription = ApiManager.getApiManager().getStockWarningData(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber(new RequestCallback<Result<List<StcokWarningInfo>>>() {
                            @Override
                            public void beforeRequest() {
                                if (isInit){
                                    stockWarningView.showProgress();
                                }
                            }

                            @Override
                            public void requestError(String msg) {
                                stockWarningView.showMessage(msg);
                                if (isInit){
                                    stockWarningView.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestComplete() {
                                if (isInit){
                                    stockWarningView.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestSuccess(Result<List<StcokWarningInfo>> data) {
                                if (data != null){
                                    stockWarningView.getStockWarningData(data.getData());
                                }
                            }
                        },context)
                );
    }

    @Override
    public void getNoticeList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("type",noticeiView.gettType());
        parameter.setParam("page",noticeiView.getPage());
        parameter.setParam("size",noticeiView.getSize());
        mSubscription = ApiManager.getApiManager().getNoticeList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber(new RequestCallback<Result<List<NoticeInfo>>>() {
                            @Override
                            public void beforeRequest() {
                                if (isInit){
                                    noticeiView.showProgress();
                                }
                            }

                            @Override
                            public void requestError(String msg) {
                                noticeiView.showMessage(msg);
                                if (isInit){
                                    noticeiView.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestComplete() {
                                if (isInit){
                                    noticeiView.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestSuccess(Result<List<NoticeInfo>> data) {
                                if (data != null){
                                    noticeiView.getNoticeList(data.getData());
                                }
                            }
                        },context)
                );
    }
}
