package com.yun.ma.yi.app.module.goods.list;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopCatInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfo;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsListPresenter implements GoodsListContract.Presenter {
    private Context context;
    private GoodsListContract.View view;
    private Subscription mSubscription;
    public GoodsListPresenter(Context context,GoodsListContract.View view){
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
    public void getGoodSortInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", view.getUser_id());
        mSubscription = ApiManager.getApiManager()
                .getItemCategory(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsListInfo>>>() {
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
                    public void requestSuccess(Result<List<GoodsListInfo>> data) {
                        List<GoodsListInfo> goodsListInfos = data.getData();
                        view.setGoodsInfoList(goodsListInfos);
                    }
                },context));
    }

    @Override
    public void getGoodsDetailInfoList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", view.getUser_id());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        parameter.setParam("category_id",view.getCategoryId());
        mSubscription = ApiManager.getApiManager()
                .getGoodsItems(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsInfo>>() {
                    @Override
                    public void beforeRequest() {
                        if (view.getPage()==1){
                            view.showProgress();
                        }
                    }
                    @Override
                    public void requestError(String msg) {
                        if (view.getPage()==1){
                            view.showMessage(msg);
                        }
                    }
                    @Override
                    public void requestComplete() {
                        if (view.getPage()==1){
                            view.hideProgress();
                        }
                    }
                    @Override
                    public void requestSuccess(Result<GoodsInfo> data) {
                        GoodsInfo goodsInfo = data.getData();
                        List<GoodsDetailInfo> goodsDetailInfos = goodsInfo.getData();
                        view.setGoodsDetailInfoList(goodsDetailInfos);
                    }
                },context));
    }
    @Override
    public void getSearchGoodsList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", view.getUser_id());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        parameter.setParam("keyword",view.getKeyWord());
        mSubscription = ApiManager.getApiManager()
                .getGoodsItems(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsInfo>>() {
                    @Override
                    public void beforeRequest() {
                    }
                    @Override
                    public void requestError(String msg) {
                        view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {}
                    @Override
                    public void requestSuccess(Result<GoodsInfo> data) {
                        if (data.getData()!=null){
                            GoodsInfo  goodsInfo =  data.getData();
                            view.setGoodsInfo(goodsInfo);
                        }
                    }
                },context));
    }
    private boolean isFirst;
    @Override
    public void getShopCatList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("shopId", view.getShopId());
        parameter.setParam("token", view.getToken());
        mSubscription = ApiManager.getApiManager()
                .getShopCatList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<ShopCatInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        view.showProgress();
                        isFirst =true;
                    }
                    @Override
                    public void requestError(String msg) {
                        view.hideProgress();
                        view.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                   //     view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<List<ShopCatInfo>> data) {
                        if (data.getData()!=null){
                            List<ShopCatInfo> shopCatInfoList = data.getData();
                            view.setShopCatInfoList(shopCatInfoList);
                        }
                    }
                },context));
    }

    @Override
    public void getShopGoodInfoByCatId() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("shop_id", view.getShopId());
        parameter.setParam("shop_cat_id", view.getShopCatId());
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
                    public void beforeRequest() {}
                    @Override
                    public void requestError(String msg) {
                        view.showMessage(msg);
                        view.hideProgress();
                    }
                    @Override
                    public void requestComplete() {
                        if (isFirst){
                            isFirst=false;
                            view.hideProgress();
                        }

                    }
                    @Override
                    public void requestSuccess(Result<ShopGoodsInfo> data) {
                        if (data.getData()!=null){
                            ShopGoodsInfo shopGoodsInfo = data.getData();
                           view.setShopGoodsInfoDetailList(shopGoodsInfo.getList());
                        }
                    }
                },context));
    }

    @Override
    public void getOneKeyUploadCount() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("category_id", view.getCashierCategoryId());
        requestParameter.setParam("token", view.getToken());
        mSubscription = ApiManager.getApiProgressManager()
                .onKeyUploadCount(requestParameter.getMapParams())
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
                        view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data != null) {
                            String result = data.getData();
                            view.setOneKeyUploadCount(Integer.parseInt(result));
                        }
                    }
                }, context));
    }

    @Override
    public void oneKeyUploadShopGoods() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("shop_id", view.getShopId());
        requestParameter.setParam("category_id", view.getCashierCategoryId());
        requestParameter.setParam("token", view.getToken());
        requestParameter.setParam("page",view.getOneKeyPage());
        requestParameter.setParam("size",view.getOneKeySize());
        mSubscription = ApiManager.getApiProgressManager()
                .oneKeyUploadShopGoods(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {}
                    @Override
                    public void requestError(String msg) {
                      view.showMessage(msg);
                      view.onSuccessBack();
                    }
                    @Override
                    public void requestComplete() {
                      //  view.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data != null) {
                            String result = data.getData();
                            int page = view.getOneKeyPage()+1;
                            view.setOnKeyPage(page);
                        }
                    }
                }, context));
    }
}
