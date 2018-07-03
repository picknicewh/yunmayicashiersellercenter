package com.yun.ma.yi.app.module.report.statistics.receivables;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.ReceivablesInfo;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 统计查询
 */

public class ReceivablesReportPresenter implements ReceivablesReportContract.Presenter {


    private ReceivablesReportContract.View view;
    private Subscription mSubscription;
    private Context context;

    public ReceivablesReportPresenter(ReceivablesReportContract.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void subscribe() {
        getReceivablesReportInfo();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getReceivablesReportInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("starttime",view.getStartTime());
        parameter.setParam("endtime",view.getEndTime());
        mSubscription = ApiManager.getApiManager().getReceivablesReportInfo(parameter.getMapParams())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<ReceivablesInfo>>>() {
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
                    public void requestSuccess(Result<List<ReceivablesInfo>> data) {
                        if (data != null){
                            view.getReceivablesReportInfo(data.getData());
                        }
                    }
                },context)
                );
    }
}
