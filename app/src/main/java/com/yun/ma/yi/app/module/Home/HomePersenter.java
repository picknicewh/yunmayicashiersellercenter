package com.yun.ma.yi.app.module.Home;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.CountTrade;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.Shop;
import com.yun.ma.yi.app.module.Home.HomeContract.IHomePresenter;
import com.yun.ma.yi.app.module.Home.HomeContract.IHomeView;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 首页
 */

public class HomePersenter implements IHomePresenter{

    private IHomeView homeView;
    private Subscription mSubscription;
    private Context context;

    public HomePersenter(IHomeView homeView, Context context){
        this.homeView = homeView;
        this.context = context;
        getCategoryList();
    }



    @Override
    public void subscribe() {
        countTrade();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void countTrade() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",homeView.getUid());
        parameter.setParam("time",homeView.getTime());
        mSubscription = ApiManager.getApiManager()
                .getCountTrade(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<CountTrade>>() {
                    @Override
                    public void beforeRequest() {

                    }

                    @Override
                    public void requestError(String msg) {
                        homeView.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {

                    }

                    @Override
                    public void requestSuccess(Result<CountTrade> data) {
                        if (data != null){
                            homeView.getCountTrade(data.getData());
                        }
                    }
                },context));
    }

    @Override
    public void getShopByUserId() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",homeView.getUid());
        mSubscription = ApiManager.getApiManager()
                .getShopByUserId(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<Shop>>() {
                    @Override
                    public void beforeRequest() {

                    }

                    @Override
                    public void requestError(String msg) {
                        homeView.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {

                    }

                    @Override
                    public void requestSuccess(Result<Shop> data) {
                        if (data != null){
                            homeView.getShopByUserId(data.getData());
                        }
                    }
                },context));
    }

    @Override
    public void getCategoryList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",  homeView.getUid());
        mSubscription = ApiManager.getApiManager()
                .getItemCategory(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsListInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        homeView.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        homeView.hideProgress();
                        homeView.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                        homeView.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<List<GoodsListInfo>> data) {
                        if (data.getData()!=null){
                            homeView.getCategoryList(data.getData());
                        }
                    }
                },context));
    }
}
