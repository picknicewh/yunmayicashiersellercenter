package com.yun.ma.yi.app.module.member.common;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.G;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员查询数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardSearchPresenter implements MemberCardSearchContract.Presenter {
    private Context context;
    private MemberCardSearchContract.View view;
    private Subscription mSubscription;

    public MemberCardSearchPresenter(Context context, MemberCardSearchContract.View view) {
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
    public void findMemberCard() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("shop_id", view.getShopId());
        parameter.setParam("keyword",view.getKeyWord());
        if (G.isEmteny(view.getKeyWord())){
            G.showToast(context,"请输入的会员卡号或者手机号");
            return;
        }
        mSubscription = ApiManager.getApiManager().
                findCard(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<MemberInfo>>() {
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
                    public void requestSuccess(Result<MemberInfo> data) {
                        if (data.getData()!=null){
                            MemberInfo memberInfo = data.getData();
                            view.setMemberInfo(memberInfo);
                        }else {
                            view.NoMemberInfo();
                        }
                    }
                }, context));
    }
}
