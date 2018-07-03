package com.yun.ma.yi.app.module.inoutstock.out;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/3
 * 名称：出库数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OutStockPresenter implements OutStockContract.Presenter{
    private Context context;
    private OutStockContract.View view;
    private OutStockContract.ViewDetail viewDetail;
    private Subscription mSubscription;

    public OutStockPresenter(Context context,OutStockContract.View view){
        this.context = context;
        this.view  =view;
    }
    public OutStockPresenter(Context context, OutStockContract.ViewDetail viewDetail){
        this.context = context;
        this.viewDetail  =viewDetail;
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
    public void conformOutStock() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id",view.getUserId());
        parameter.setParam("keyword",view.getKeyWord());
        mSubscription = ApiManager.getApiManager()
                .conformOutStock(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<OrderInfoDetail>>>() {
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
                            public void requestSuccess(Result<List<OrderInfoDetail>> data) {
                                if (data.getData()!= null){
                                    List<OrderInfoDetail> orderInfoDetailList = data.getData();
                                    view.setOrderInfoDetailList(orderInfoDetailList);
                                }
                            }
                        },context)
                );
    }

    @Override
    public void conformOutStockDetail() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("detail_id",viewDetail.getDetailId());
        parameter.setParam("quantity",viewDetail.getQuantity());
        parameter.setParam("return_money_way",viewDetail.getReturnWay());
        parameter.setParam("return_goods_reason",viewDetail.getReturnReason());
        parameter.setParam("buyer_remark",viewDetail.getBuyerRemark());
        mSubscription = ApiManager.getApiManager()
                .conformOutStockDetail(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ConformInStockInfo>>() {
                            @Override
                            public void beforeRequest() {
                                viewDetail.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                viewDetail.hideProgress();
                                viewDetail.showMessage(msg);
                            }
                            @Override
                            public void requestComplete() {
                                viewDetail.hideProgress();
                            }
                            @Override
                            public void requestSuccess(Result<ConformInStockInfo> data) {
                                if (data.getData()!= null) {
                                    ConformInStockInfo stockInfo  = data.getData();
                                    viewDetail.setConformInStockInfo(stockInfo);
                                    if (stockInfo.getData()==null && stockInfo.getData().size()==0){
                                        viewDetail.back();
                                    }
                                }
                            }
                        },context)
                );
    }

    @Override
    public void conformOutStockDetailById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("item_id",viewDetail.getItemId());
        parameter.setParam("detail_id",viewDetail.getDetailId());
        parameter.setParam("quantity",viewDetail.getQuantity());
        parameter.setParam("return_money_way",viewDetail.getReturnWay());
        parameter.setParam("return_goods_reason",viewDetail.getReturnReason());
        parameter.setParam("buyer_remark",viewDetail.getBuyerRemark());
        mSubscription = ApiManager.getApiManager()
                .conformOutStockDetailById(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ConformInStockInfo>>() {
                            @Override
                            public void beforeRequest() {
                                viewDetail.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                viewDetail.hideProgress();
                                viewDetail.showMessage(msg);
                            }
                            @Override
                            public void requestComplete() {
                                viewDetail.hideProgress();
                            }
                            @Override
                            public void requestSuccess(Result<ConformInStockInfo> data) {
                                if (data.getData()!= null) {
                                    ConformInStockInfo stockInfo  = data.getData();
                                    viewDetail.setConformInStockInfo(stockInfo);
                                    viewDetail.back();
                                }
                            }
                        },context)
                );
    }
}
