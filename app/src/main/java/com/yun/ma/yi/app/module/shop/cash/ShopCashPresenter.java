package com.yun.ma.yi.app.module.shop.cash;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopCashInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

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
public class ShopCashPresenter implements ShopCashContract.Presenter{
    private Context context;
    private ShopCashContract.View view;
    private ShopCashContract.ViewEdit viewEdit;
    private Subscription mSubscription;
    public ShopCashPresenter(ShopCashContract.View view, Context context){
         this.view =view;
        this.context = context;
    }
    public ShopCashPresenter(ShopCashContract.ViewEdit view, Context context){
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
    public void drawBalanceList() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("user_id",  UserMessage.getInstance().getUser_id());
        requestParameter.setParam("status",view.getState());
        requestParameter.setParam("page_no",view.getPage());
        requestParameter.setParam("page_size",view.getSize());
        requestParameter.setParam("start_time",view.getStartTime());
        requestParameter.setParam("end_time",view.getEndTime());
        mSubscription = ApiManager.getApiManager()
                .drawBlanceList(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ShopCashInfo>>() {
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
                    public void requestSuccess(Result<ShopCashInfo> data) {
                        if (data!=null){
                            ShopCashInfo shopCashInfo = data.getData();
                            view.setShopCashInfo(shopCashInfo);
                        }
                    }
                },context));
    }

    @Override
    public void withdrawBalance() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("shop_id",  viewEdit.getShopId());
        requestParameter.setParam("account_type",viewEdit.getAccountType());
        requestParameter.setParam("account_name",viewEdit.getAccountName());
        requestParameter.setParam("account_no",viewEdit.getAccountNoo());
        requestParameter.setParam("amount",viewEdit.getAmount());
        requestParameter.setParam("user_id", UserMessage.getInstance().getUId());
        if (viewEdit.getAmount()<200){
            viewEdit.showMessage("提现金额不得少于200元！");
            return;
        }
        if (G.isEmteny(viewEdit.getAccountName())){
            viewEdit.showMessage("卡户人不能为空！");
            return;
        }
        if (G.isEmteny(viewEdit.getAccountNoo())){
            viewEdit.showMessage("卡户账号不能为空！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .withdrawBalance(requestParameter.getMapParams())
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
                        viewEdit.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            String shopCashInfo = data.getData();
                            if (shopCashInfo.equals("1")){
                                viewEdit.showMessage("提现成功！");
                                viewEdit.setSuccessBack();
                            }

                        }
                    }
                },context));
    }
}
