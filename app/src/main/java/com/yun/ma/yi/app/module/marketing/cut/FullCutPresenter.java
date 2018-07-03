package com.yun.ma.yi.app.module.marketing.cut;

import android.content.Context;

import com.google.gson.Gson;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.FullCutInfo;
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
public class FullCutPresenter implements FullCutContract.Presenter {
    private FullCutContract.View view;
    private FullCutContract.ViewEdit viewEdit;
    private Context context;
    private Subscription mSubscription;
    public FullCutPresenter(FullCutContract.View view, Context context){
        this.view =view;
        this.context =context;
    }
    public FullCutPresenter(FullCutContract.ViewEdit viewEdit, Context context){
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
    public void getFullCutList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("userId",view.getUserId());
        parameter.setParam("activityEndTime",view.getActivityEndTime());
        parameter.setParam("activityStartTime",view.getActivityStartTime());
        parameter.setParam("page",view.getPage());
        parameter.setParam("size",view.getSize());
        mSubscription = ApiManager.getApiManager().
                getFullCutList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<FullCutInfo>>>() {
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
                    public void requestSuccess(Result<List<FullCutInfo>> data) {
                       if (data!=null){
                           List<FullCutInfo> fullCutInfos = data.getData();
                           view.setFullCutInfoList(fullCutInfos);
                       }
                    }
                },context));
    }

    @Override
    public void deleteFullInfoById() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("id",viewEdit.getId());
        mSubscription = ApiManager.getApiManager().
                delFullCutInfo(parameter.getMapParams())
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
    public void editFullCutInfo() {
        RequestParameter parameter = new RequestParameter();
        FullCutInfo fullCutInfo = viewEdit.getData();
        if (G.isEmteny(fullCutInfo.getName()) ||
                fullCutInfo.getBuy_money()==0||fullCutInfo.getMinus_money()==0){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (fullCutInfo.getIs_upper_limit()==1&&fullCutInfo.getUpper_limit_money()==0){
            G.showToast(context,"封顶金额不能为空！");
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
                editFullCutInfo(parameter.getMapParams())
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
    public void addFullCutInfo() {
        RequestParameter parameter = new RequestParameter();
        FullCutInfo fullCutInfo = viewEdit.getData();
        if (G.isEmteny(fullCutInfo.getName())|| fullCutInfo.getBuy_money()==0||fullCutInfo.getMinus_money()==0){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (fullCutInfo.getIs_upper_limit()==1&&fullCutInfo.getUpper_limit_money()==0){
            G.showToast(context,"封顶金额不能为空！");
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
                 addFullCutInfo(parameter.getMapParams())
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
}
