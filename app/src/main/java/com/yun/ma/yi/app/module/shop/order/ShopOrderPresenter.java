package com.yun.ma.yi.app.module.shop.order;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopOrderInfo;
import com.yun.ma.yi.app.userdb.UserMessage;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：库存查询页数据处理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderPresenter implements ShopOrderContract.Presenter{
    private Context context;
    private ShopOrderContract.View view;
    private ShopOrderContract.ViewEdit viewEdit;
    private Subscription mSubscription;
    public ShopOrderPresenter(ShopOrderContract.View view, Context context){
         this.view =view;
        this.context = context;
    }
    public ShopOrderPresenter(ShopOrderContract.ViewEdit view, Context context){
        this.viewEdit =view;
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
    public void getSellerOrderBySellerId() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("uid",  view.getUid());
        requestParameter.setParam("order_state",view.getOrderState());
        requestParameter.setParam("page",view.getPage());
        requestParameter.setParam("size",view.getSize());
        requestParameter.setParam("startTime",view.getStartTime());
        requestParameter.setParam("endTime",view.getEndTime());
        requestParameter.setParam("token", view.getToken());
        mSubscription = ApiManager.getApiManager()
                .getSellerOrderBySellerId(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<ShopOrderInfo>>>() {
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
                    public void requestSuccess(Result<List<ShopOrderInfo>> data) {
                        if (data!=null){
                            List<ShopOrderInfo> shopOrderInfoList = data.getData();
                            view.setShopOrderInfoList(shopOrderInfoList);
                        }
                    }
                },context));
    }

    @Override
    public void editOrderState() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("uid",  viewEdit.getUid());
        requestParameter.setParam("tid",viewEdit.getOrderId());
        requestParameter.setParam("reason",viewEdit.getReason());
        requestParameter.setParam("stateInfo",viewEdit.getStateInfo());
        requestParameter.setParam("token", UserMessage.getInstance().getToken());
        mSubscription = ApiManager.getApiManager()
                .editOrderState(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewEdit.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        viewEdit.hideProgress();
                        if (msg.equals("OK")){
                            viewEdit.showMessage("操作失败");
                        }else {
                            viewEdit.showMessage(msg);
                        }
                    }
                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            String result = data.getData();
                            if (result.equals("1") ){
                                if (viewEdit.getStateInfo().equals("accept")){
                                    viewEdit.showMessage("接单成功");
                                }else if (viewEdit.getStateInfo().equals("reject")){
                                    viewEdit.showMessage("拒绝接单成功");
                                }else {
                                    viewEdit.showMessage("确认配送成功");
                                }
                                viewEdit.setSuccessBack();
                            }
                        }
                    }
                },context));
    }
}
