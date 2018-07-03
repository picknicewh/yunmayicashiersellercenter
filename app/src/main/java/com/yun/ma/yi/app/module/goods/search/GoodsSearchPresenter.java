package com.yun.ma.yi.app.module.goods.search;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopGoodsInfo;
import com.yun.ma.yi.app.utils.G;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：搜索商品数据处理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSearchPresenter implements GoodsSearchContract.Presenter {
    private Context context;
    private GoodsSearchContract.View view;
    private Subscription mSubscription;
    public GoodsSearchPresenter( Context context,GoodsSearchContract.View view){
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
    public void getGoodsDetailInfoList() {
        RequestParameter parameter = new RequestParameter();
        if (view!=null){
            parameter.setParam("uid", view.getUser_id());
            parameter.setParam("page",view.getPage());
            parameter.setParam("size",view.getSize());
            parameter.setParam("keyword",view.getKeyWord());
        }
        mSubscription = ApiManager.getApiManager()
                .getGoodsItems(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsInfo>>() {
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
                    public void requestSuccess(Result<GoodsInfo> data) {
                        GoodsInfo  goodsInfo =  data.getData();
                        view.setGoodsInfo(goodsInfo);
                    }
                },context));
    }

    @Override
    public void getShopGoodInfoByCatId() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("shop_id", view.getShopId());
        parameter.setParam("shop_cat_id",0);
        parameter.setParam("page_no", view.getPage());
        parameter.setParam("page_size", view.getSize());
        parameter.setParam("isStatus", 1);
        parameter.setParam("token", view.getToken());
        if (!G.isEmteny(view.getKeyWord())){
            parameter.setParam("keyword", view.getKeyWord());
        }
        mSubscription = ApiManager.getApiManager()
                .getShopGoodInfoByCatId(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ShopGoodsInfo>>() {
                    @Override
                    public void beforeRequest() {
                      //  view.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        view.showMessage(msg);
                      //  view.hideProgress();
                    }
                    @Override
                    public void requestComplete() {
                      // view.hideProgress();

                    }
                    @Override
                    public void requestSuccess(Result<ShopGoodsInfo> data) {
                        if (data.getData()!=null){
                            ShopGoodsInfo shopGoodsInfo = data.getData();
                            view.setShopGoodsInfo(shopGoodsInfo);
                        }
                    }
                },context));
    }
}
