package com.yun.ma.yi.app.module.inoutstock.in;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称: 收货入库/入库详情数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockPresenter implements InStockContract.Presenter {

    private InStockContract.View view;
    private InStockContract.ViewDetail viewDetail;
    private Subscription mSubscription;
    private Context context;

    public InStockPresenter(InStockContract.View view, Context context){
        this.view = view;
        this.context = context;
    }
    public InStockPresenter(InStockContract.ViewDetail viewDetail, Context context){
        this.viewDetail = viewDetail;
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
    public void getStockOrder() {
        RequestParameter parameter = new RequestParameter();
        if (viewDetail!=null){
            view = viewDetail;
        }
        parameter.setParam("user_id",view.getUserId());
        parameter.setParam("order_id",view.getOrderId());
        mSubscription = ApiManager.getApiManager()
                .getStockOrder(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<OrderInfo>>>() {
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
                            public void requestSuccess(Result<List<OrderInfo>> data) {
                                if (data.getData()!= null){
                                    List<OrderInfo> orderInfoList = data.getData();
                                    view.setOrderInfoList(orderInfoList);

                                }
                            }
                        },context)
                );
    }

    @Override
    public void getStockOrderDetail() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("order_id",viewDetail.getOrderId());
        mSubscription = ApiManager.getApiManager()
                .getStockOrderDetail(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<OrderInfoDetail>>>() {
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
                            public void requestSuccess(Result<List<OrderInfoDetail>> data) {
                                if (data.getData()!= null){
                                   List<OrderInfoDetail> orderInfoDetailList = data.getData();
                                    viewDetail.setOrderInfoDetail(orderInfoDetailList);
                                }
                            }
                        },context)
                );
    }

    @Override
    public void conformInStock() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id",viewDetail.getUserId());
        parameter.setParam("detail_id",viewDetail.getDetailId());
        parameter.setParam("quantity",viewDetail.getQuantity());
        mSubscription = ApiManager.getApiManager()
                .confromInStock(parameter.getMapParams())
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
                                if (data.getData()!= null){
                                   ConformInStockInfo conformInStockInfo = data.getData();
                                    viewDetail.setConformInStockInfo(conformInStockInfo);
                                }
                            }
                        },context)
                );
    }

    @Override
    public void changeStockById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id",viewDetail.getGoodsId());
        parameter.setParam("detail_id",viewDetail.getDetailId());
        parameter.setParam("quantity",viewDetail.getQuantity());
        mSubscription = ApiManager.getApiManager()
                .changeStockById(parameter.getMapParams())
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
                                if (data.getData()!= null){
                                    ConformInStockInfo conformInStockInfo = data.getData();
                                    viewDetail.setConformInStockInfo(conformInStockInfo);
                                }
                            }
                        },context)
                );
    }

    @Override
    public void conformAllInStock() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("order_id",viewDetail.getOrderId());
        parameter.setParam("user_id",viewDetail.getUserId());
        if (viewDetail.getData()==null){
            G.showToast(context,"入库信息不能为空！");
            return;
        }
        parameter.setParam("data",viewDetail.getData());
        mSubscription = ApiManager.getApiManager()
                .conformAllInStock(parameter.getMapParams())
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
                                if (data.getData()!= null){
                                    ConformInStockInfo conformInStockInfo = data.getData();
                                    viewDetail.setConformInStockInfo(conformInStockInfo);
                                }
                            }
                        },context)
                );
    }

}
