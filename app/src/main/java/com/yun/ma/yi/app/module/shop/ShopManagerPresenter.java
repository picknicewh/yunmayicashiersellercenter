package com.yun.ma.yi.app.module.shop;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopInfo;
import com.yun.ma.yi.app.userdb.UserMessage;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/11/37
 * 名称： 店铺管理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopManagerPresenter implements ShopManagerContract.Presenter{
    private Context context;
    private ShopManagerContract.View view;
    private Subscription mSubscription;
    public ShopManagerPresenter(ShopManagerContract.View view, Context context){
         this.view =view;
         this.context = context;
         getToken();
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
    public void getShopInfo() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("user_id",  UserMessage.getInstance().getUId());
        mSubscription = ApiManager.getApiManager()
                .getShopInfoByUserId(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ShopInfo>>() {
                    @Override
                    public void beforeRequest() {
                            view.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                          view.hideProgress();
                     //    view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                           view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<ShopInfo> data) {
                        if (data!=null){
                            ShopInfo shopInfo = data.getData();
                            view.setShopInfo(shopInfo);
                        }
                    }
                },context));
    }

    @Override
    public void getToken() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("username", view.getUserName());
        requestParameter.setParam("password", view.getPassword());
        mSubscription = ApiManager.getApiManager()
                .getToken(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        view.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        view.hideProgress();
                    }
                    @Override
                    public void requestComplete() {
                        view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            String token = data.getData();
                            UserMessage.getInstance().setToken(token);
                        }
                    }
                },context));
    }
}
