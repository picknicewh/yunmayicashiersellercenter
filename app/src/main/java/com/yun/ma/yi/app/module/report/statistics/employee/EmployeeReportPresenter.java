package com.yun.ma.yi.app.module.report.statistics.employee;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.EmployeeInfo;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/3
 * 名称：员工统计报表计数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EmployeeReportPresenter implements EmployeeReportContract.Presenter {
    private Context context;
    private EmployeeReportContract.View view;
    private Subscription mSubscription;
    public EmployeeReportPresenter(Context context, EmployeeReportContract.View view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void subscribe() {}
    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getEmployeeReport() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("startTime",view.getStartTime());
        parameter.setParam("endTime",view.getEndTime());
        mSubscription = ApiManager.getApiManager()
                .getEmployeeReport(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<EmployeeInfo>>() {
                            @Override
                            public void beforeRequest() {
                                view.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                view.hideProgress();
                                view.showMessage(msg);
                                view.setEmployeeInfo(null);
                            }
                            @Override
                            public void requestComplete() {
                                view.hideProgress();
                            }
                            @Override
                            public void requestSuccess(Result<EmployeeInfo> data) {
                                if (data.getData()!= null){
                                   view.setEmployeeInfo(data.getData());
                                }

                            }
                        },context)
                );
    }
}
