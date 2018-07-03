package com.yun.ma.yi.app.module.marketing.delivery;

import android.content.Context;

import com.google.gson.Gson;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.FullDeliveryInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/9/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FullDeliveryPresenter implements FullDeliveryContract.Presenter {
    private FullDeliveryContract.View view;
    private FullDeliveryContract.ViewEdit viewEdit;
    private Context context;
    private Subscription mSubscription;
    public FullDeliveryPresenter(FullDeliveryContract.View view, Context context){
        this.view =view;
        this.context =context;
    }
    public FullDeliveryPresenter(FullDeliveryContract.ViewEdit viewEdit, Context context){
        this.viewEdit =viewEdit;
        this.context =context;
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
      if (mSubscription!=null&& mSubscription.isUnsubscribed()){
          mSubscription.unsubscribe();
      }
    }
    @Override
    public void getFullDeliveryList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("userId",view.getUserId());
        parameter.setParam("activityEndTime",view.getActivityEndTime());
        parameter.setParam("activityStartTime",view.getActivityStartTime());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        mSubscription = ApiManager.getApiManager().
                getFullDeliveryList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<FullDeliveryInfo>>>() {
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
                    public void requestSuccess(Result<List<FullDeliveryInfo>> data) {
                        if (data!=null){
                            List<FullDeliveryInfo> fullDeliveryInfoList = data.getData();
                            view.setFullDeliveryInfoList(fullDeliveryInfoList);
                        }
                    }
                },context));
    }

    @Override
    public void deleteFullDeliveryInfoById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id",viewEdit.getId());
        mSubscription = ApiManager.getApiManager().
                delFullDeliveryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            viewEdit.editSuccess(data.getData());
                        }
                    }
                },context));
    }

    @Override
    public void editFullDeliveryInfo() {
        RequestParameter parameter = new RequestParameter();
        FullDeliveryInfo fullCutInfo = viewEdit.getData();
        if (G.isEmteny(fullCutInfo.getName()) ||
                fullCutInfo.getBuy_money()==0||fullCutInfo.getGive_product_number()==0){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (G.isEmteny(fullCutInfo.getAssign_product_ids())){
            G.showToast(context,"请选择指定商品范围！");
            return;
        }
        if (G.isEmteny(fullCutInfo.getGive_product_id())){
            G.showToast(context,"请选择赠送商品！");
            return;
        }
        if (fullCutInfo.getActivity_start_time().compareTo(fullCutInfo.getActivity_end_time())>0){
            G.showToast(context,"选择的活动开始时间不能大于活动的结束时间哦！");
            return;
        }
        parameter.setParam("data",new Gson().toJson(fullCutInfo));
        mSubscription = ApiManager.getApiManager().
                editFullDeliveryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }
                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            viewEdit.editSuccess(data.getData());
                            new ItemDao(context).deleteAll();
                        }
                    }
                },context));
    }

    @Override
    public void addFullDeliveryInfo() {
        RequestParameter parameter = new RequestParameter();
        FullDeliveryInfo fullCutInfo = viewEdit.getData();
        if (G.isEmteny(fullCutInfo.getName()) ||
                fullCutInfo.getBuy_money()==0||fullCutInfo.getGive_product_number()==0){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (G.isEmteny(fullCutInfo.getAssign_product_ids())){
            G.showToast(context,"请选择指定商品范围！");
            return;
        }
        if (fullCutInfo.getActivity_start_time().compareTo(fullCutInfo.getActivity_end_time())>0){
            G.showToast(context,"选择的活动开始时间不能大于活动的结束时间哦！");
            return;
        }
        parameter.setParam("data",new Gson().toJson(fullCutInfo));
        mSubscription = ApiManager.getApiManager().
                addFullDeliveryInfo(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<String>>() {
                    @Override
                    public void beforeRequest() {
                        viewEdit.showProgress();
                    }

                    @Override
                    public void requestError(String msg) {
                        viewEdit.hideProgress();
                        viewEdit.showMessage(msg);
                    }

                    @Override
                    public void requestComplete() {
                        viewEdit.hideProgress();
                    }

                    @Override
                    public void requestSuccess(Result<String> data) {
                        if (data!=null){
                            viewEdit.editSuccess(data.getData());
                        }
                    }
                },context));
    }


}
