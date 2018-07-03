package com.yun.ma.yi.app.module.stock.search;

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
public class StockSearchPresenter  implements StockSearchContract.Presenter{
    private Context context;
    private StockSearchContract.View view;
    private Subscription mSubscription;
    public StockSearchPresenter(StockSearchContract.View view,Context context){
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
        requestParameter.setParam("uid",  UserMessage.getInstance().getUId());
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
                   //     view.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                      //  view.hideProgress();
                       view.showMessage(msg);
                        G.log("xxxxxxxxxxx"+msg);
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
                       //     G.showToast(context,"没有查询到任何信息哦！");
                        }
                    }
                },context));
    }
}
