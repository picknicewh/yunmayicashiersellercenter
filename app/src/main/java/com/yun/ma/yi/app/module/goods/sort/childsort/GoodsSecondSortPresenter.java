package com.yun.ma.yi.app.module.goods.sort.childsort;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：二级和三级级商品分类数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSecondSortPresenter implements GoodsSecondSortContract.Presenter {
    private GoodsSecondSortContract.View view;
    private Context context;
    private Subscription mSubscription;
    public GoodsSecondSortPresenter(GoodsSecondSortContract.View view, Context context){
        this.view = view;
        this.context =context;
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
    public void   getSortList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("cid",view.getParentId());
        mSubscription = ApiManager.getApiManager().getSecondCategoryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<SecondCategoryInfo>>>() {
                    @Override
                    public void beforeRequest() {
                        view.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                      view.showMessage(msg);
                        view.hideProgress();
                    }

                    @Override
                    public void requestComplete() {
                      view.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<List<SecondCategoryInfo>> data) {
                        List<SecondCategoryInfo> categoryInfos = data.getData();
                        view.setSortList(categoryInfos);
                    }
                },context));
       /* String[] sort_name=null;
        if (view.getSortLevel().equals(Constants.GOODS_SECOND_SORT)){
            sort_name  = context.getResources().getStringArray(R.array.goods_second_sort_name);

        }else if (view.getSortLevel().equals(Constants.GOODS_THIRD_SORT)){
            sort_name  = context.getResources().getStringArray(R.array.goods_third_sort_name);
        }
        List<String>  sortList = new ArrayList<>();
        for (int i = 0 ;i<sort_name.length;i++) {
           sortList.add(sort_name[i]);
        }*/
   //     view.setSortList(sortList);
    }
}
