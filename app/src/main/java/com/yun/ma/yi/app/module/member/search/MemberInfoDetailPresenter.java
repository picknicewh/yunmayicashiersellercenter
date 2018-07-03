package com.yun.ma.yi.app.module.member.search;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员信息/余额明细/积分明细数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoDetailPresenter implements MemberInfoDetailContract.Presenter {
    private Context context;
    private MemberInfoDetailContract.View view;
    private MemberInfoDetailContract.InfoView infoView;
    private Subscription mSubscription;

    public MemberInfoDetailPresenter(Context context, MemberInfoDetailContract.View view) {
        this.context = context;
        this.view = view;
    }
    public MemberInfoDetailPresenter(Context context, MemberInfoDetailContract.InfoView infoView) {
        this.context = context;
        this.infoView = infoView;
    }
    @Override
    public void subscribe() {}
    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
    @Override
    public void getMoneyOrIntegralList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("seller_id", view.getSellerId());
        parameter.setParam("user_id",view.getUserId());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        parameter.setParam("type",view.getType());
        mSubscription = ApiManager.getApiManager().
                 getMoneyOrIntegralList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<MemberInfoChangeInfo>>>() {
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
                    public void requestSuccess(Result<List<MemberInfoChangeInfo>> data) {
                        if (data.getData()!=null){
                            List<MemberInfoChangeInfo> memberInfoChangeInfos = data.getData();
                            view.setMemberInfo(memberInfoChangeInfos);
                        }
                    }
                }, context));
    }


    @Override
    public void editMemberInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id", infoView.getId());
        parameter.setParam("seller_id", infoView.getSellerId());
        parameter.setParam("user_name", infoView.getUsername());
        parameter.setParam("user_sex", infoView.getUserSex());
        parameter.setParam("user_birthday", infoView.getUserBirthday());
        parameter.setParam("user_certify_id", infoView.getUserCertifyId());
        mSubscription = ApiManager.getApiManager().
                editCard(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        infoView.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        infoView.hideProgress();
                        infoView.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                        infoView.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data.getData()!=null){
                            infoView.showMessage(data.getData());
                            infoView.back();
                        }
                    }
                }, context));
    }
}
