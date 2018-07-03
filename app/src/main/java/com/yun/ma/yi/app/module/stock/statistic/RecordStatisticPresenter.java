package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.bean.StockStatisticItemInfo;
import com.yun.ma.yi.app.userdb.UserMessage;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/4
 * 名称：库存盘点查询界面数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticPresenter implements RecordStatisticContract.Presenter{
    private Context context;
    private RecordStatisticContract.View view;
    private Subscription mSubscription;
    private RecordStatisticContract.RecordStatisticItemView recordStatisticItemView;
    private RecordStatisticContract.RecordStatisticItemDetailView recordStatisticItemDetailView;

    public RecordStatisticPresenter(Context context, RecordStatisticContract.View view){
        this.context = context;
        this.view = view;
    }

    public RecordStatisticPresenter(Context context, RecordStatisticContract.RecordStatisticItemView recordStatisticItemView){
        this.context = context;
        this.recordStatisticItemView = recordStatisticItemView;
    }

    public RecordStatisticPresenter(Context context, RecordStatisticContract.RecordStatisticItemDetailView recordStatisticItemDetailView){
        this.context = context;
        this.recordStatisticItemDetailView = recordStatisticItemDetailView;
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
    public void statisticSearchStock() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",   UserMessage.getInstance().getUId());
        parameter.setParam("starttime",view.getStartTime());
        parameter.setParam("endtime",view.getEndTime());
        parameter.setParam("keyword",view.getKeyword());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        parameter.setParam("status",view.getStatus());
        mSubscription = ApiManager.getApiManager().
             statisticSearchStock(parameter.getMapParams())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<StockStatisticInfo>>>() {
                 @Override
                 public void beforeRequest() {

                 }

                 @Override
                 public void requestError(String msg) {
                   view.showMessage(msg);
                 }

                 @Override
                 public void requestComplete() {

                 }
                 @Override
                 public void requestSuccess(Result<List<StockStatisticInfo>> data) {
                   if (data.getData()!=null){
                       List<StockStatisticInfo> stockStatisticInfos = data.getData();
                       view.setStockStatisticInfoList(stockStatisticInfos);
                   }
                 }
             },context));
    }

    @Override
    public void stockChangeForItemId() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", recordStatisticItemView.getUid());
        parameter.setParam("starttime",recordStatisticItemView.getStartTime());
        parameter.setParam("endtime",recordStatisticItemView.getEndTime());
        parameter.setParam("item_id",recordStatisticItemView.getItemId());
        parameter.setParam("page",recordStatisticItemView.getPage());
        parameter.setParam("size",recordStatisticItemView.getSize());
        mSubscription = ApiManager.getApiManager().
                stockChangeForItemId(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<StockStatisticItemInfo>>>() {
                    @Override
                    public void beforeRequest() {

                    }

                    @Override
                    public void requestError(String msg) {
                        recordStatisticItemView.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {

                    }
                    @Override
                    public void requestSuccess(Result<List<StockStatisticItemInfo>> data) {
                        if (data.getData()!=null){
                            recordStatisticItemView.stockChangeForItemId(data.getData());
                        }
                    }
                },context));
    }

    @Override
    public void stockGetById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id",recordStatisticItemDetailView.getId());
        mSubscription = ApiManager.getApiManager().
                stockGetById(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<StockStatisticItemInfo>>() {
                    @Override
                    public void beforeRequest() {
                        recordStatisticItemDetailView.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        recordStatisticItemDetailView.showMessage(msg);
                        recordStatisticItemDetailView.hideProgress();
                    }

                    @Override
                    public void requestComplete() {
                        recordStatisticItemDetailView.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<StockStatisticItemInfo> data) {
                        if (data.getData()!=null){
                            recordStatisticItemDetailView.stockGetById(data.getData());
                        }
                    }
                },context));
    }
}
