package com.yun.ma.yi.app.module.report.goods.sales;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsSalesInfoBo;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 商品销售统计
 */

public class GoodsSalesReportPresenter implements GoodsSalesReportContract.IGoodsSalesViewPresenter {

    private GoodsSalesReportContract.View view ;
    private Subscription mSubscription;
    private Context context;
    private boolean isInit = true;

    public GoodsSalesReportPresenter(GoodsSalesReportContract.View view, Context context){
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
    public void getGoodsSalesReportInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("starttime",view.getStartTime());
        parameter.setParam("endtime",view.getEndTime());
        parameter.setParam("page_no",view.getPageNo());
        parameter.setParam("page_size",view.getPageSize());
        parameter.setParam("groupType",view.getGroupType());
        parameter.setParam("orderType",view.getOrderType());
        parameter.setParam("keyword",view.getKeyword());
        mSubscription = ApiManager.getApiManager().getGoodsSalesReportInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsSalesInfoBo>>() {
                            @Override
                            public void beforeRequest() {
                                if (isInit){
                                    view.showProgress();
                                }
                            }

                            @Override
                            public void requestError(String msg) {
                                view.showMessage(msg);
                                if (isInit){
                                    view.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestComplete() {
                                if (isInit){
                                    view.hideProgress();
                                    isInit = false;
                                }
                            }

                            @Override
                            public void requestSuccess(Result<GoodsSalesInfoBo> data) {
                                if (data != null){
                                    view.setGoodsSalesReportInfo(data.getData());
                                }
                            }
                        },context)
                );
    }
}
