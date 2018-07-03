package com.yun.ma.yi.app.module.inoutstock.search;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.InOutSearchInfo;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/3
 * 名称： 退货入库数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutStockPresenter implements InOutStockContract.Presenter{
    private Context context;
    private InOutStockContract.View view;
    private Subscription mSubscription;
    public InOutStockPresenter(Context context, InOutStockContract.View view){
        this.context = context;
        this.view  =view;
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
    public void searchEnterOutStock() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id",view.getUserId());
        parameter.setParam("start_time",view.getStartTime());
        parameter.setParam("end_time",view.getEndTime());
        parameter.setParam("type",view.getType());
        mSubscription = ApiManager.getApiManager()
                .searchEnterOutStock(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<InOutSearchInfo>>() {
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
                            public void requestSuccess(Result<InOutSearchInfo> data) {
                                if (data.getData()!= null){
                                    InOutSearchInfo inOutSearchInfo = data.getData();
                                    view.setInOutSearchInfo(inOutSearchInfo);
                                }
                            }
                        },context)
                );
    }
}
