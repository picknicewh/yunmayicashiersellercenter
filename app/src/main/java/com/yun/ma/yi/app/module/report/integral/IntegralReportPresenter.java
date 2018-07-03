package com.yun.ma.yi.app.module.report.integral;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberIntegralInfo;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/11/10
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class IntegralReportPresenter implements IntegralReportContract.Persenter {
    private IntegralReportContract.View view;
    private Context context ;
    private Subscription mSubscription;
    public IntegralReportPresenter(IntegralReportContract.View view, Context context){
        this.view = view;
        this.context = context;
    }
    @Override
    public void subscribe() {
        getIntegralReportInfo();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getIntegralReportInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("chain_seller_id", view.getUid());
        parameter.setParam("start_time", view.getStartTime());
        parameter.setParam("end_time", view.getEndTime());
        parameter.setParam("page_no", view.getPageNo());
        parameter.setParam("page_size", view.getPageSize());
        mSubscription = ApiManager.getApiManager()
                .getIntegralReport(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<MemberIntegralInfo>>() {
                    @Override
                    public void beforeRequest() {
                        view.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        view.hideProgress();
                        view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                        view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<MemberIntegralInfo> data) {
                        if (data.getData()!=null){
                            MemberIntegralInfo memberIntegralInfo = data.getData();
                            view.setIntegralInfo(memberIntegralInfo);
                        }
                    }
                },context));
    }
}
