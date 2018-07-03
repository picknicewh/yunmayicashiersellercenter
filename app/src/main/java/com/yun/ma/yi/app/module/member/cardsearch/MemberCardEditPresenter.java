package com.yun.ma.yi.app.module.member.cardsearch;

import android.app.Activity;

import com.google.gson.Gson;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.G;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：新增/编辑会员卡数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardEditPresenter implements MemberCardEditContract.Presenter {
    private Activity context;
    private MemberCardEditContract.View view;
    private Subscription mSubscription;
    public MemberCardEditPresenter(Activity context, MemberCardEditContract.View view) {
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
    public void addMemberGrade() {
        RequestParameter parameter = new RequestParameter();
        MemberCardInfo cardInfo = view.getData();
        if (cardInfo.getDiscount_rate()==0 ||cardInfo.getCard_integral()==0
                ||G.isEmteny(cardInfo.getCard_name())||cardInfo.getGet_integral_by_money()==0){
            G.showToast(context, "必填项不能为空！");
            return;
        }
        if (gson==null){
            gson = new Gson();
        }
        parameter.setParam("data",gson.toJson(cardInfo));
        mSubscription = ApiManager.getApiManager().
                 addMemberGrade(parameter.getMapParams())
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
                            context.setResult(Activity.RESULT_OK);
                            view.finish();
                        }
                    }
                }, context));
    }
   private Gson gson;
    @Override
    public void editMemberGrade() {
        RequestParameter parameter = new RequestParameter();
        MemberCardInfo cardInfo  = view.getData();
        if (cardInfo.getDiscount_rate()==0 ||cardInfo.getCard_integral()==0
                ||G.isEmteny(cardInfo.getCard_name())||cardInfo.getGet_integral_by_money()==0){
            G.showToast(context, "必填项不能为空！");
            return;
        }
        if (gson==null) {
            gson = new Gson();
        }
        parameter.setParam("data", gson.toJson(cardInfo));
        mSubscription = ApiManager.getApiManager().
                editMemberGrade(parameter.getMapParams())
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
                            context.setResult(Activity.RESULT_OK);
                            view.finish();
                        }
                    }
                }, context));
    }
    @Override
    public void deleteMemberGrade() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id", view.getId());
        mSubscription = ApiManager.getApiManager().
                 deleteMemberGrade(parameter.getMapParams())
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
                            context.setResult(Activity.RESULT_OK);
                            view.finish();
                        }
                    }
                }, context));
    }
}
