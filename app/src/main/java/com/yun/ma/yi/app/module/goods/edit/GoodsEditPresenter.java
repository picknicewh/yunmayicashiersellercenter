package com.yun.ma.yi.app.module.goods.edit;

import android.app.Activity;
import android.content.Intent;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.ImageUtil;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.bean.EditGoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsItemCodeInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/26
 * 名称：添加商品数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsEditPresenter implements GoodsEditContract.Presenter{
    private Activity context;
    private GoodsEditContract.View view;
    private Subscription mSubscription;

    public GoodsEditPresenter(Activity context, GoodsEditContract.View view){
        this.context = context;
        this.view  =view;
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
    public void getSortList() {
        RequestParameter parameter = new RequestParameter();
        mSubscription = ApiManager.getApiManager()
                .getCategoryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<CategoryInfo>>>() {
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
                    public void requestSuccess(Result<List<CategoryInfo>> data) {
                        List<CategoryInfo> categoryInfos  = data.getData();
                        view.setSortList(categoryInfos);
                    }
                },context));
    }
    @Override
    public void getSecondSortList(int cid, final String sort) {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("cid",cid);
        mSubscription = ApiManager.getApiManager()
                 .getSecondCategoryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<SecondCategoryInfo>>>() {
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
                    public void requestSuccess(Result<List<SecondCategoryInfo>> data) {
                        List<SecondCategoryInfo> categoryInfos = data.getData();
                        if (sort.equals(Constants.GOODS_FIRST_SORT)){
                            view.setSecondSortList(categoryInfos);
                        }else if (sort.equals(Constants.GOODS_SECOND_SORT)){
                            view.setThirdSortList(categoryInfos);
                        }

                    }
                },context));
    }
    @Override
    public List<String> getCategoryTitle(List<CategoryInfo> categoryInfos){
        List<String> categoryTitle = new ArrayList<>();
        for (int i =  0 ;i <categoryInfos.size();i++){
            categoryTitle.add(categoryInfos.get(i).getTitle());
        }
        return categoryTitle;
    }
    @Override
    public List<String> getSecondCategoryTitle(List<SecondCategoryInfo> categoryInfos){
        List<String> categoryTitle = new ArrayList<>();
        for (int i =  0 ;i <categoryInfos.size();i++){
            categoryTitle.add(categoryInfos.get(i).getName());
        }
        return categoryTitle;
    }
    @Override
    public void getGoodInfoById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("id",view.getId());
        mSubscription = ApiManager.getApiManager()
                .getItemById(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<EditGoodsDetailInfo>>() {
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
                    public void requestSuccess(Result<EditGoodsDetailInfo> data) {
                        if (data.getData()!=null){
                            EditGoodsDetailInfo goodsDetailInfo = data.getData();
                            view.setItemInfo(goodsDetailInfo);
                        }
                    }
                },context));
    }


    private boolean isNecessaryEmpty(){
        if ( view.getPrice()==0){
            G.showToast(context,"商品售价不能为空！");
            return true;
        }if (G.isEmteny(view.getName())){
            G.showToast(context,"商品名称不能为空！");
            return true;
        }
        if (view.getTopCatId()<=0 ){
            G.showToast(context,"一级类目不能为空");
            return true;
        }
        return false;
    }
    @Override
    public void upLoadImage(String path) {
       if (isNecessaryEmpty()){
           return;
       }
        mSubscription = ImageUtil.upLoad(path,new BaseSubscriber<>(new RequestCallback<Result<String>>() {
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
                if (data.getStatus().equals("success") && data.getInfo().equals("OK")){
                    String  imageUrl = data.getData();
                    view.setImageUrl(imageUrl);
                    //照片上传成功之后添加
                    addItemInfo();
                }
            }
        },context));
    }
    @Override
    public void addItemInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id",view.getUid());
        parameter.setParam("sub_user_id", UserMessage.getInstance().getUser_id());
        parameter.setParam("top_cat_id",view.getTopCatId());
        parameter.setParam("mid_cat_id",view.getMidCatId());
        parameter.setParam("leaf_cat_id",view.getLeafCatId());
        parameter.setParam("title",view.getName());
        parameter.setParam("price",view.getPrice());
        parameter.setParam("cost",view.getCost());
        parameter.setParam("number",view.getNumber());
        parameter.setParam("stock",view.getStock());
        parameter.setParam("bar_code",view.getBarCode());
        parameter.setParam("stock_warning_high",view.getStockWaringHigh());
        parameter.setParam("stock_warning_low",view.getStockWaringLow());
        parameter.setParam("spec",view.getSpec());
        parameter.setParam("unit",view.getUnit());
        parameter.setParam("shelf_life",view.getShelfLife());
        parameter.setParam("is_weigh",view.getIsWeight());
        parameter.setParam("vip_price",view.getVipPrice());
        parameter.setParam("item_status",view.getItemStatus());

        if (!G.isEmteny(view.getImageUrl())){
            parameter.setParam("image_url",view.getImageUrl());
        }
        if (!G.isEmteny(view.getId())){
            parameter.setParam("id",view.getId());
        }
        if (isNecessaryEmpty()){
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .saveItemByInfo(parameter.getMapParams())
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
                        if (msg.equals("0")){
                            view.showMessage("编辑失败！");
                        }
                    }

                    @Override
                    public void requestComplete() {
                       view.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data.getInfo().equals("OK")&&data.getStatus().equals("success")){
                            if (G.isEmteny(view.getId())){
                                view.showMessage("添加成功");
                                if (view.getIsContinue()){
                                    view.cleanInfo();
                                }else {
                                    context.finish();
                                }
                            }else {
                                view.showMessage("修改成功");
                                Intent intent = new Intent();
                                context.setResult(Constants.RESULT_GOOODS_EDIT,intent);
                                context.finish();
                            }
                        }
                    }
                },context));
    }

    @Override
    public void deleteItemInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",UserMessage.getInstance().getUser_id());
        parameter.setParam("id",view.getId());
        ApiManager.getApiManager()
                .deleteItemByInfo(parameter.getMapParams())
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
                        int  code = Integer.parseInt(data.getData());
                         if (code>0){
                             G.showToast(context,"删除成功！");
                         }else if (code<0){
                             G.showToast(context,"删除失败！");
                         }
                        Intent intent = new Intent();
                        context.setResult(Constants.RESULT_GOOODS_DELETE,intent);
                        context.finish();
                    }
                },context));
    }

    @Override
    public void getGoodsInfoByCode() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("bar_code",view.getCode());
        ApiManager.getApiManager()
                .findAllGoods(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsItemCodeInfo>>>() {
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
                    public void requestSuccess(Result<List<GoodsItemCodeInfo>> data) {
                        if (data.getData()!=null){
                            List<GoodsItemCodeInfo> goodsItemCodeInfoList = data.getData();
                            view.setGoodsItemCodeInfoList(goodsItemCodeInfoList);
                        }
                    }
                },context));
    }
    @Override
    public void getScanGoodsInfo() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid",view.getUid());
        parameter.setParam("bar_code",view.getCode());
        ApiManager.getApiManager()
                .findCodeGoods(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsDetailInfo>>>() {
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
                    public void requestSuccess(Result<List<GoodsDetailInfo>> data) {
                        if (data.getData()!=null){
                            List<GoodsDetailInfo> goodsItemCodeInfoList = data.getData();
                            if (goodsItemCodeInfoList.size()>0){
                                view.setGoodsDetailInfo(goodsItemCodeInfoList.get(0));
                            }else {
                                view.showMessage("没找到该商品！");
                            }

                        }
                    }
                },context));
    }

    public List<String> getCategoryCodeList(String category_ids){
        List<String>  codeList = new ArrayList<>();
        if (category_ids.length()>1){
            category_ids = category_ids.substring(1,category_ids.length()-1);
        }
        String[] codes = category_ids.split(",");
        for (int i = 0;i<codes.length;i++){
            codeList.add(codes[i]);
        }
        return codeList;
    }
}
