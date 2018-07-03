package com.yun.ma.yi.app.module.goods.sort.parentsort;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.bean.Result;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：一级商品分类数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSortPresenter implements GoodsSortContract.Presenter {
    private GoodsSortContract.View view;
    private Context context;
    private Subscription mSubscription;
    public GoodsSortPresenter( GoodsSortContract.View view,Context context){
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
      /*    int[] sort_images = new int[]{
                R.mipmap.wine_drink,
                R.mipmap.foods,
                R.mipmap.daily_wash,
                R.mipmap.grain_taste,
                R.mipmap.house_clean
           };
        String[] sort_name  = context.getResources().getStringArray(R.array.goods_sort_name);
        List<Map<String,Object>>  sortList = new ArrayList<>();
        for (int i = 0 ;i<sort_images.length;i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", sort_name[i]);
            item.put("image", sort_images[i]);
            sortList.add(item);
            }
            view.setSortList(sortList);*/
      RequestParameter parameter = new RequestParameter();
        mSubscription = ApiManager.getApiManager().getCategoryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<CategoryInfo>>>() {
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
                    public void requestSuccess(Result<List<CategoryInfo>> data) {
                        List<CategoryInfo> categoryInfos  = data.getData();
                        view.setSortList(categoryInfos);
                    }
                },context));
    }
}
