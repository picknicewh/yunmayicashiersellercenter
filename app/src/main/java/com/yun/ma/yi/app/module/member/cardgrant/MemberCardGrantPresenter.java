package com.yun.ma.yi.app.module.member.cardgrant;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.bean.MemberInfo;
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
public class MemberCardGrantPresenter implements MemberCardGrantContract.Presenter {
    private Context context;
    private MemberCardGrantContract.View view;
    private Subscription mSubscription;
    private MemberCardGrantContract.ViewEdit viewEdit;
    public MemberCardGrantPresenter(Context context, MemberCardGrantContract.View view) {
        this.context = context;
        this.view = view;
    }
    public MemberCardGrantPresenter(Context context,MemberCardGrantContract.ViewEdit viewEdit) {
        this.context = context;
        this.viewEdit = viewEdit;
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
        parameter.setParam("shop_id", view.getSellerId());
        parameter.setParam("keyword",view.getKeyWord());
        int  length = view.getKeyWord().length();
        if (length==11 || length==21){
          //  boolean isMobileNumber = TextUtils.isMobileNumber(view.getKeyWord());
         //   boolean isIncludeMobileNumber = TextUtils.isMobileNumber(view.getKeyWord().substring(0,11)) ;
            boolean isrightKeyword =length==11 || length==21;
            if (!isrightKeyword){
                G.showToast(context,"请输入正确的会员卡或者手机号");
                return;
            }
        }else {
            G.showToast(context,"请输入正确的会员卡或者手机号");
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
                            view.setHasCard(true);
                        }else {
                            view.setHasCard(false);
                        }
                    }
                }, context));
    }
    @Override
    public void sendSms() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("dynamic", view.getDynamic());
        parameter.setParam("mobile",view.getMobile());
        if (G.isEmteny(view.getDynamic())|| G.isEmteny(view.getMobile())){
            G.showToast(context,"必填项不能为空！");
        }
        mSubscription = ApiManager.getApiManager().
                sendSms(parameter.getMapParams())
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
                           String result = data.getData();
                           G.showToast(context,result);
                       }
                    }
                }, context));
    }

    @Override
    public void addCard() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("seller_id", viewEdit.getSellerId());
        parameter.setParam("user_mobile",viewEdit.getMobile());
        parameter.setParam("user_name",viewEdit.getUserName());
        parameter.setParam("user_birthday", viewEdit.getBirthday());
        parameter.setParam("user_certify_id",viewEdit.getCertifyId());
        parameter.setParam("card_name",viewEdit.getCardName());
        parameter.setParam("user_sex",viewEdit.getSex());
        parameter.setParam("expire_datetime",viewEdit.getExpireDateTime());
        if (G.isEmteny(viewEdit.getMobile()) || G.isEmteny(viewEdit.getUserName())
                || G.isEmteny(viewEdit.getBirthday())||G.isEmteny(viewEdit.getCertifyId())
                ||G.isEmteny(viewEdit.getCardName())){
            G.showToast(context,"必填项不能为空！");
        }
        mSubscription = ApiManager.getApiManager().
                addCard(parameter.getMapParams())
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
                        if (data.getData()!=null){
                            String result = data.getData();
                            G.showToast(context,result);
                            viewEdit.backHome();
                        }
                    }
                }, context));
    }

    @Override
    public void memberGradeList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("seller_id", viewEdit.getSellerId());
        mSubscription = ApiManager.getApiManager().
                memberGradeList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<MemberCardInfo>>>() {
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
                    public void requestSuccess(Result<List<MemberCardInfo>> data) {
                        if (data.getData()!=null){
                            List<MemberCardInfo> memberCardInfos = data.getData();
                            viewEdit.setMemberInfo(memberCardInfos);

                        }else {
                            viewEdit.NoMemberInfo();
                        }
                    }
                }, context));
    }
}
