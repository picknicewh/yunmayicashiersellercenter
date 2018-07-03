package com.yun.ma.yi.app.module.shop.balance;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.BalanceAccountInfo;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/12/12
 * 名称：余额对账数据处理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountPresenter implements BalanceAccountContract.Presenter {
    private Context context;
    private BalanceAccountContract.View view;
    private Subscription mSubscription;

    public BalanceAccountPresenter(BalanceAccountContract.View view, Context context) {
        this.context = context;
        this.view = view;
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
    public void getBalanceAccountList() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("user_id", view.getUid());
        requestParameter.setParam("token", view.getToken());
        requestParameter.setParam("page_no", view.getPage());
        requestParameter.setParam("page_size", view.getSize());
        requestParameter.setParam("start_time", view.getStartTime());
        requestParameter.setParam("end_time", view.getEndTime());
        mSubscription = ApiManager.getApiManager()
                .getBalanceDetailList(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<BalanceAccountInfo>>() {
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
                    public void requestSuccess(Result<BalanceAccountInfo> data) {
                        if (data != null) {
                            BalanceAccountInfo balanceAccountInfo = data.getData();
                            List<BalanceAccountInfo.BalanceDetailInfo> balanceDetailInfoList =balanceAccountInfo.getList();
                            view.setBalanceDetailList(balanceDetailInfoList);
                        }
                    }
                }, context));
    }
}
