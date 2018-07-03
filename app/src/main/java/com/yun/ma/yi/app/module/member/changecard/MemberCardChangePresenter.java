package com.yun.ma.yi.app.module.member.changecard;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

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
public class MemberCardChangePresenter implements MemberCardChangeContract.Presenter {
    private Context context;
    private MemberCardChangeContract.View view;
    private Subscription mSubscription;

    public MemberCardChangePresenter(Context context, MemberCardChangeContract.View view) {
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
    public void memberGradeList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("seller_id", view.getSellerId());
        mSubscription = ApiManager.getApiManager().
                memberGradeList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<MemberCardInfo>>>() {
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
                    public void requestSuccess(Result<List<MemberCardInfo>> data) {
                        if (data.getData()!=null){
                            List<MemberCardInfo> memberCardInfos = data.getData();
                            view.setMemberInfo(memberCardInfos);
                        }else {
                             G.showToast(context, "没有会员信息！");
                        }
                    }
                }, context));
    }
    @Override
    public void editMemberInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id", view.getId());
        parameter.setParam("seller_id", view.getSellerId());
        parameter.setParam("user_name", view.getUsername());
        parameter.setParam("user_sex", view.getUserSex());
        parameter.setParam("user_birthday", view.getUserBirthday());
        parameter.setParam("user_certify_id", view.getUserCertifyId());
        parameter.setParam("card_name",view.getCardName());
        parameter.setParam("card_number",view.getCardNumber());
        if (G.isEmteny(view.getCardNumber())){
            G.showToast(context,"卡号不能为空！");
            return;
        }
        if (view.getCardNumber().length()!=15){
            G.showToast(context,"卡号的长度设置为15！");
            return;
        }
        mSubscription = ApiManager.getApiManager().
                editCard(parameter.getMapParams())
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
                            view.showMessage(data.getData());
                            view.back();
                        }
                    }
                }, context));
    }
}
