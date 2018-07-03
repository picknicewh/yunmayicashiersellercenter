package com.yun.ma.yi.app.module.marketing.choose;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.StockSearchInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
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
public class GoodsChoosePresenter implements GoodsChooseContract.Presenter {
    private Context context;
    private GoodsChooseContract.View view;
    private GoodsChooseContract.ViewSearch viewSearch;
    private GoodsChooseContract.ViewSingle viewSingle;
    private GoodsChooseContract.ViewSingleEdit viewSingleEdit;
    private Subscription mSubscription;

    public GoodsChoosePresenter(Context context, GoodsChooseContract.View view) {
        this.context = context;
        this.view = view;
        getGoodSortInfo();
    }

    public GoodsChoosePresenter(Context context,GoodsChooseContract.ViewSearch ViewSearch){
        this.viewSearch = ViewSearch;
        this.context =context;
    }
    public GoodsChoosePresenter(Context context,GoodsChooseContract.ViewSingle viewSingle){
        this.viewSingle = viewSingle;
        this.context =context;
    }
    public GoodsChoosePresenter(Context context,GoodsChooseContract.ViewSingleEdit viewSingleEdit){
        this.viewSingleEdit = viewSingleEdit;
        this.context =context;
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
                }, context));
    }

    @Override
    public void getGoodItemsByIds() {
        RequestParameter requestParameter  = new RequestParameter();
        if (viewSingleEdit!=null){
            requestParameter.setParam("userId", viewSingleEdit.getUserId());
            requestParameter.setParam("ids",viewSingleEdit.getIds());
        }else if (viewSingle!=null){
            requestParameter.setParam("userId", viewSingle.getUserId());
            requestParameter.setParam("ids",viewSingle.getIds());
        }

        mSubscription = ApiManager.getApiManager()
                .getGoodsItemsByIds(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsDetailInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        if (viewSingle!=null){
                            viewSingle.showProgress();
                        }else if (viewSingleEdit!=null){
                            viewSingleEdit.showProgress();
                        }


                    }
                    @Override
                    public void requestError(String msg) {
                        if (viewSingle!=null){
                            viewSingle.hideProgress();
                            viewSingle.showMessage(msg);
                        }else if (viewSingleEdit!=null){
                            viewSingleEdit.hideProgress();
                            viewSingleEdit.showMessage(msg);
                        }
                    }
                    @Override
                    public void requestComplete() {
                        if (viewSingle!=null){
                            viewSingle.hideProgress();
                        }else if (viewSingleEdit!=null){
                            viewSingleEdit.hideProgress();
                        }
                    }
                    @Override
                    public void requestSuccess(Result<List<GoodsDetailInfo>> data) {
                        if (data.getData()!=null){
                            List<GoodsDetailInfo> goodsDetailInfos = data.getData();
                            if (viewSingle!=null){
                                viewSingle.setGoodsDetailInfoList(goodsDetailInfos);
                            }else if (viewSingleEdit!=null){
                                viewSingleEdit.setGoodsDetailInfoList(goodsDetailInfos);
                            }
                        }
                    }
                },context));
    }
    @Override
    public void getGoodsDetailInfoList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", view.getUser_id());
        parameter.setParam("page", view.getPage());
        parameter.setParam("size", view.getSize());
        parameter.setParam("category_id", view.getCategoryId());
        mSubscription = ApiManager.getApiManager()
                .getGoodsItems(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<GoodsInfo>>() {
                    @Override
                    public void beforeRequest() {
                        if (view.getPage() == 1) {
                            view.showProgress();
                        }
                    }

                    @Override
                    public void requestError(String msg) {
                        if (view.getPage() == 1) {
                            view.showMessage(msg);
                            view.hideProgress();
                        }

                    }

                    @Override
                    public void requestComplete() {
                        if (view.getPage() == 1) {
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void requestSuccess(Result<GoodsInfo> data) {
                        GoodsInfo goodsInfo = data.getData();
                        List<GoodsDetailInfo> goodsDetailInfos = goodsInfo.getData();
                        view.setGoodsDetailInfoList(goodsDetailInfos);
                    }
                }, context));
    }


    @Override
    public void getSearchGoodsList() {
        RequestParameter parameter = new RequestParameter();
        if (view!=null){
            parameter.setParam("uid", view.getUser_id());
            parameter.setParam("page", view.getPage());
            parameter.setParam("size", view.getSize());
            parameter.setParam("keyword", view.getKeyWord());
        }else if (viewSearch!=null){
            parameter.setParam("uid", viewSearch.getUserId());
            parameter.setParam("page", viewSearch.getPage());
            parameter.setParam("size", viewSearch.getSize());
            parameter.setParam("keyword", viewSearch.getKeyWord());
        }
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
                        if (view!=null){
                            view.showMessage(msg);
                        }else if (viewSearch!=null){
                            viewSearch.showMessage(msg);
                        }
                    }
                    @Override
                    public void requestComplete() {
                    }
                    @Override
                    public void requestSuccess(Result<GoodsInfo> data) {
                        if (data.getData() != null) {
                            GoodsInfo goodsInfo = data.getData();
                            List<GoodsDetailInfo> goodsDetailInfos = goodsInfo.getData();
                            if (view!=null){
                                view.setGoodsDetailInfoList(goodsDetailInfos);
                            }else if (viewSearch!=null){
                                viewSearch.setGoodsDetailInfoList(goodsDetailInfos);
                            }

                        }
                    }
                }, context));
    }
    @Override
    public void getGoodList() {
        RequestParameter requestParameter  = new RequestParameter();
        requestParameter.setParam("uid",  UserMessage.getInstance().getUId());
        requestParameter.setParam("category_id",viewSingle.getCategoryId());
        if (G.isEmteny(viewSingle.getKeyword())&&viewSingle.getCategoryId()==-1){
            return;
        }
        requestParameter.setParam("keyword",viewSingle.getKeyword());
        requestParameter.setParam("page",viewSingle.getPage());
        requestParameter.setParam("size",viewSingle.getSize());
        mSubscription = ApiManager.getApiManager()
                .searchStock(requestParameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<StockSearchInfo>>() {
                    @Override
                    public void beforeRequest() {
                         viewSingle.showProgress();
                    }
                    @Override
                    public void requestError(String msg) {
                         viewSingle.hideProgress();
                         viewSingle.showMessage(msg);
                        G.log("xxxxxxxxxxx"+msg);
                    }
                    @Override
                    public void requestComplete() {
                           viewSingle.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<StockSearchInfo> data) {
                        if (data.getData()!=null){
                            StockSearchInfo stockSearchInfo = data.getData();
                            viewSingle.setGoodInfoList(stockSearchInfo);
                        }else {
                            if (viewSingle.getPage()==1){
                                viewSingle.setNoData();
                            }

                        }
                    }
                },context));
    }

}
