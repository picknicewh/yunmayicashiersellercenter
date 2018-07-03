package com.yun.ma.yi.app.module.stock.add;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.StockSearchInfo;
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
public class AddStatisticPresenter implements AddStatisticContract.Presenter{
    private Context context;
    private AddStatisticContract.View view;
    private Subscription mSubscription;
    public AddStatisticPresenter(AddStatisticContract.View view, Context context){
         this.view =view;
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
    public void searchStock() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("uid", UserMessage.getInstance().getUId());
        requestParameter.setParam("category_id",view.getCategoryId());
        requestParameter.setParam("keyword",view.getKeyword());
        requestParameter.setParam("page",view.getPage());
        requestParameter.setParam("size",view.getSize());
        mSubscription = ApiManager.getApiManager()
                .searchStock(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<StockSearchInfo>>() {
                    @Override
                    public void beforeRequest() {
                    }
                    @Override
                    public void requestError(String msg) {
                       view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                     //   view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<StockSearchInfo> data) {
                        if (data.getData()!=null){
                            StockSearchInfo stockSearchInfo = data.getData();
                            view.setStockSearchInfo(stockSearchInfo);
                        }else {
                            if (view.getPage()==1){
                                view.setNoData();
                            }
                        }
                    }
                },context));
    }
    @Override
    public void insertStock() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("info",view.getInfo());
        requestParameter.setParam("user_id",  UserMessage.getInstance().getUser_id());
        requestParameter.setParam("come_from","appupdate");
        requestParameter.setParam("type",1);
        if (view.getInfo()==null){
            view.showMessage("你还没添加任何新增库存信息哦！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .submitNewStock(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {}
                    @Override
                    public void requestError(String msg) {
                        view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data.getData()!=null){
                            int result = Integer.parseInt(data.getData());
                            if (result==1){
                                G.showToast(context,"添加成功");
                                view.back();
                            }else {
                                G.showToast(context,"添加失败");
                            }
                        }
                    }
                },context));
    }
}
