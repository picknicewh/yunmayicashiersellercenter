package com.yun.ma.yi.app.module.report.goods.profit;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsProfitInfo;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/6/23.
 */

public class GoodsProfitReportPresenter implements GoodsProfitReportContract.Presenter {

    private GoodsProfitReportContract.View view;
    private Subscription mSubscription;
    private Context context;

    public GoodsProfitReportPresenter(GoodsProfitReportContract.View view, Context context){
        this.view = view;
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
    public void getGoodsProfitReportInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("starttime",view.getStartTime());
        parameter.setParam("endtime",view.getEndTime());
        mSubscription = ApiManager.getApiManager().getGoodsProfitReportInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsProfitInfo>>() {
                            @Override
                            public void beforeRequest() {
                                view.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                view.showMessage(msg);
                                view.hideProgress();
                            }

                            @Override
                            public void requestComplete() {
                                view.hideProgress();
                            }

                            @Override
                            public void requestSuccess(Result<GoodsProfitInfo> data) {
                                if (data != null){
                                    view.getGoodsProfitReportInfo(data.getData());
                                }
                            }
                        },context)
                );
    }
}
