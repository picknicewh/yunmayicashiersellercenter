package com.yun.ma.yi.app.module.shop.goods;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.ImageUtil;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/11/37
 * 名称： 店铺管理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsEditPresenter implements ShopGoodsEditContract.Presenter {
    private Context context;
    private ShopGoodsEditContract.View view;
    private ShopGoodsEditContract.ViewAdd viewAdd;
    private Subscription mSubscription;

    public ShopGoodsEditPresenter(ShopGoodsEditContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public ShopGoodsEditPresenter(ShopGoodsEditContract.ViewAdd viewAdd, Context context) {
        this.viewAdd = viewAdd;
        this.context = context;
        getCategoryList();
    }

    @Override
    public void subscribe() {

    }
    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void upLoadImage(String path) {
        mSubscription = ImageUtil.upLoad(path, new BaseSubscriber<>(new RequestCallback<Result<String>>() {
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
                if (data.getStatus().equals("success") && data.getInfo().equals("OK")) {
                    String imageUrl = data.getData();
                    view.setImageUrl(imageUrl);
                    //照片上传成功之后添加
                    editShopGoodInfo();
                }
            }
        }, context));
    }
    @Override
    public void getCashierItemList() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("keyword", viewAdd.getKeyWord());
        requestParameter.setParam("page_no", viewAdd.getPage());
        requestParameter.setParam("page_size", viewAdd.getSize());
        requestParameter.setParam("category_id",viewAdd.getShopCatId());
        requestParameter.setParam("token", UserMessage.getInstance().getToken());
        mSubscription = ApiManager.getApiManager()
                .getCashierItemList(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsDetailInfo>>>() {
                    @Override
                    public void beforeRequest() {
                     //   viewAdd.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                        viewAdd.hideProgress();
                        viewAdd.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        //viewAdd.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<List<GoodsDetailInfo>> data) {
                        if (data != null) {
                            List<GoodsDetailInfo> goodsDetailInfoList = data.getData();
                            viewAdd.setGoodsDetailInfoList(goodsDetailInfoList);

                        }
                    }
                }, context));
    }

    @Override
    public void addCashierItem() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("shop_id", viewAdd.getShopId());
        requestParameter.setParam("ids", viewAdd.getGoodsIds());
        requestParameter.setParam("token", UserMessage.getInstance().getToken());
        mSubscription = ApiManager.getApiManager()
                .addCashierItem(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewAdd.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        viewAdd.hideProgress();
                        viewAdd.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        viewAdd.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data != null) {
                            String result = data.getData();
                            viewAdd.showMessage(result);
                            viewAdd.setSuccessBack();
                        }
                    }
                }, context));
    }
    @Override
    public void getCategoryList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",  viewAdd.getUid());
        parameter.setParam("isPutWeigh",1);
        mSubscription = ApiManager.getApiManager()
                .getItemCategory(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsListInfo>>>() {
                    @Override
                    public void beforeRequest() {
                 //       viewAdd.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                  //      viewAdd.hideProgress();
                        viewAdd.showMessage(msg);
                    }
                    @Override
                    public void requestComplete() {
                       // viewAdd.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<List<GoodsListInfo>> data) {
                        if (data.getData()!=null){
                            viewAdd.setCategoryList(data.getData());
                        }
                    }
                },context));
    }
    @Override
    public void getShopInfoById() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("item_id", view.getItemId());
        requestParameter.setParam("token", view.getToken());
        mSubscription = ApiManager.getApiManager()
                .getShopInfoById(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<ShopGoodsInfoDetail>>() {
                    @Override
                    public void beforeRequest() {
                        view.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        view.hideProgress();
                    }

                    @Override
                    public void requestComplete() {
                        view.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<ShopGoodsInfoDetail> data) {
                        if (data != null) {
                            ShopGoodsInfoDetail shopGoodsInfoDetail = data.getData();
                            view.setShopGoodsInfoDetail(shopGoodsInfoDetail);
                        }
                    }
                }, context));
    }

    @Override
    public void editShopGoodInfo() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("item_id", view.getItemId());
        requestParameter.setParam("shop_cat_id", view.getShopCatId());
        requestParameter.setParam("pic_url", view.getPicUrl());
        requestParameter.setParam("title", view.getGoodsTitle());
        requestParameter.setParam("sub_title", view.getSubTitle());
        requestParameter.setParam("sell_price", view.getSellPrice());
        requestParameter.setParam("market_price", view.getMarketPrice());
        requestParameter.setParam("cost_price", view.getCostPrice());
        requestParameter.setParam("stock_num", view.getStockNum());
        requestParameter.setParam("bar_code", view.getBarCode());
        requestParameter.setParam("limit_num", view.getLimitNum());
        requestParameter.setParam("shop_sort_id", view.getShopSortId());
        requestParameter.setParam("token", view.getToken());
        requestParameter.setParam("item_status",view.getItemStatus());
        if (G.isEmteny(view.getGoodsTitle())) {
            view.showMessage("标题不能为空!");
            return;
        }

        mSubscription = ApiManager.getApiManager()
                .editShopGoodInfo(requestParameter.getMapParams())
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
                        if (data != null) {
                            String result = data.getData();
                            if (result.equals("OK")) {
                                view.showMessage("编辑成功！");
                            }
                            view.setSuccessBack(Constants.RESULT_GOOODS_EDIT);
                        }
                    }
                }, context));
    }

    @Override
    public void deleteShopGoodInfo() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setParam("item_id", view.getItemId());
        requestParameter.setParam("token", view.getToken());
        mSubscription = ApiManager.getApiManager()
                .deletShopGoodById(requestParameter.getMapParams())
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
                    }

                    @Override
                    public void requestComplete() {
                        view.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data != null) {
                            String result = data.getInfo();
                            if (result.equals("OK")) {
                                view.showMessage("删除成功");
                                view.setSuccessBack(Constants.RESULT_GOOODS_DELETE);
                            }
                        }
                    }
                }, context));
    }
}
