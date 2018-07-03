package com.yun.ma.yi.app.module.report.goods.trade;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsTradeDetailInfoBo;
import com.yun.ma.yi.app.bean.GoodsTradeInfoBo;
import com.yun.ma.yi.app.bean.MemberTradeInfo;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ys on 2017/5/31.
 * 商品交易流水报表 详情
 */

public class GoodsTradeReportPresenter implements GoodsTradeReportContract.Presenter{

    private GoodsTradeReportContract.View view;
    private GoodsTradeReportContract.ReportView reportView;
    private Subscription mSubscription;
    private Context context;
    private boolean isInit = true;

    public GoodsTradeReportPresenter(GoodsTradeReportContract.View view, Context context){
        this.view = view;
        this.context = context;
    }

    public GoodsTradeReportPresenter(GoodsTradeReportContract.ReportView reportView, Context context){
        this.reportView = reportView;
        this.context = context;
    }
    @Override
    public void subscribe() {}
    @Override
    public void unSubscribe() {
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    /**获取商品交易流水列表*/
    @Override
    public void getGoodsTradeReportInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("starttime",view.getStartTime());
        parameter.setParam("endtime",view.getEndTime());
        parameter.setParam("page_no",view.getPageNo());
        parameter.setParam("page_size",view.getPageSize());
        parameter.setParam("create_id",view.getCreateId());
        parameter.setParam("source",view.getSource());
        mSubscription = ApiManager.getApiManager().getGoodsTradeReportInfo(parameter.getMapParams())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsTradeInfoBo>>() {
                    @Override
                    public void beforeRequest() {
                        if (isInit){
                            view.showProgress();
                        }
                    }

                    @Override
                    public void requestError(String msg) {
                        view.showMessage(msg);
                        if (isInit){
                            view.hideProgress();
                            isInit = false;
                        }
                    }

                    @Override
                    public void requestComplete() {
                        if (isInit){
                            view.hideProgress();
                            isInit = false;
                        }
                    }

                    @Override
                    public void requestSuccess(Result<GoodsTradeInfoBo> data) {
                        if (data != null){
                            view.getGoodsTradeReportInfo(data.getData());
                        }
                    }
                },context)
                );
    }

    /**获取商品交易流水详情*/
    @Override
    public void getGoodsTradeDetailInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("trade_id",reportView.getTradeId());
        mSubscription = ApiManager.getApiManager().getGoodsTradeReportDetail(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsTradeDetailInfoBo>>() {
                            @Override
                            public void beforeRequest() {
                                reportView.showProgress();
                            }

                            @Override
                            public void requestError(String msg) {
                                reportView.hideProgress();
                                reportView.showMessage(msg);
                            }

                            @Override
                            public void requestComplete() {
                                reportView.hideProgress();
                            }

                            @Override
                            public void requestSuccess(Result<GoodsTradeDetailInfoBo> data) {
                                if (data != null){
                                    reportView.getGoodsTradeDetailInfo(data.getData());
                                }
                            }
                        },context)
                );
    }

    @Override
    public void getTradeList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("buyer_id",view.getBuyerId());
        parameter.setParam("size",view.getPageSize());
        parameter.setParam("page",view.getPageNo());
        parameter.setParam("seller_id",view.getUid());
        mSubscription = ApiManager.getApiManager().
                getTradeList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<MemberTradeInfo>>>() {
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
                    public void requestSuccess(Result<List<MemberTradeInfo>> data) {
                       if (data.getData()!=null){
                           List<MemberTradeInfo>  memberTradeInfoList = data.getData();
                           view.setMemberTradeInfoList(memberTradeInfoList);
                       }
                    }
                },context));
    }
}
