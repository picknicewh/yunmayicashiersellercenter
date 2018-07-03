package com.yun.ma.yi.app.module.report.statistics.inoutwork;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.InOutWorkInfoVos;
import com.yun.ma.yi.app.bean.Result;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/9/4
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutWorkPresenter  implements InOutWorkContract.Persenter{
    private InOutWorkContract.View view;
    private Context context;
    private Subscription mSubscription;
    public InOutWorkPresenter(Context context, InOutWorkContract.View view){
       this.context = context;
        this.view = view;
    }
    @Override
    public void subscribe() {
       getInOutWorkList();
    }

    @Override
    public void unSubscribe() {
     if (mSubscription!=null&& mSubscription.isUnsubscribed()){
         mSubscription.unsubscribe();
     }
    }

    @Override
    public void getInOutWorkList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id",view.getUserId());
        parameter.setParam("start_time",view.getStartTime());
        parameter.setParam("end_time",view.getEndTime());
        parameter.setParam("keyword",view.getKeyWord());
        mSubscription = ApiManager.getApiManager().
                getInOutWorkList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<InOutWorkInfoVos>>() {
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
                            public void requestSuccess(Result<InOutWorkInfoVos> data) {
                             if (data!=null){
                                 view.setInOutWorkVos(data.getData());
                             }
                            }
                        },context)
                );
    }
}
