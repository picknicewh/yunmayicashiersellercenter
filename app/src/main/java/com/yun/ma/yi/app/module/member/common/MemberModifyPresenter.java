package com.yun.ma.yi.app.module.member.common;

import android.content.Context;

import com.google.gson.Gson;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberRechargeInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.G;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：充值/赠积分/修改卡密码/挂失数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberModifyPresenter implements MemberModifyContract.Presenter {
    private Context context;
    private MemberModifyContract.View view;
    private Subscription mSubscription;
    private Gson gson;
    public MemberModifyPresenter(Context context, MemberModifyContract.View view) {
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
    /**
     * 挂失
     */
    @Override
    public void memberReportLost() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id", view.getId());
        parameter.setParam("value",view.getValue());
        parameter.setParam("seller_id",view.getSellerId());
        mSubscription = ApiManager.getApiManager().
                memberReportLost(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
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
                    public void requestSuccess(Result<String> data) {
                        String mResult = view.getResult();
                        if (data.getData()!=null){
                            int result = Integer.parseInt(data.getData());
                            if (result>0){
                                G.showToast(context,mResult+"成功");
                                view.backHome();
                            }else {
                                G.showToast(context,mResult+"失败");
                            }
                        }else {
                            G.showToast(context,mResult+"失败");
                        }
                    }
                }, context));
    }

    /**
     * 会员卡充值
     */
    @Override
    public void memberCardRecharge() {
        RequestParameter parameter = new RequestParameter();
        MemberRechargeInfo memberRechargeInfo = view.getRechargeData();
        if (memberRechargeInfo.getChange_card_integral()==0){
            G.showToast(context,"必填项不能为空！");
        }
        if (gson==null){
            gson = new Gson();
        }
        parameter.setParam("data",gson.toJson(memberRechargeInfo));
        mSubscription = ApiManager.getApiManager().
                memberCardRecharge(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
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
                    public void requestSuccess(Result<String> data) {
                        if (data.getData()!=null){
                            G.showToast(context,data.getData());
                            view.backHome();
                        }
                    }
                }, context));
    }
}
