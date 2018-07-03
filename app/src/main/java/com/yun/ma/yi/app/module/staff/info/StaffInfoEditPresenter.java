package com.yun.ma.yi.app.module.staff.info;

import android.content.Context;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：新增/修改员工数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffInfoEditPresenter implements StaffInfoEditContract.Presenter{
    private StaffInfoEditContract.View view;
    private StaffInfoEditContract.ViewEdit viewEdit;
    private Subscription mSubscription;
    private Context context;
     public StaffInfoEditPresenter( Context context,StaffInfoEditContract.ViewEdit viewEdit){
         this.viewEdit =viewEdit;
         this.context = context;
     }
    public StaffInfoEditPresenter( Context context,StaffInfoEditContract.View view){
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
    public void addSubUser() {
        RequestParameter parameter = new RequestParameter();
       parameter.setParam("user_id", UserMessage.getInstance().getUser_id());
        parameter.setParam("username", viewEdit.getUserName());
        parameter.setParam("password", viewEdit.getPassword());
        parameter.setParam("name", viewEdit.getName());
        parameter.setParam("mobile", viewEdit.getMobile());
        parameter.setParam("role_id", viewEdit.getRoleId());
        if (G.isEmteny(viewEdit.getMobile())||G.isEmteny(viewEdit.getPassword())
                ||G.isEmteny(viewEdit.getUserName())||G.isEmteny(viewEdit.getName())){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (viewEdit.getRoleId()==-1){
            G.showToast(context,"员工权限不能为空！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .addSubUser(parameter.getMapParams())
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
                        if (data.getData()!=null){
                            viewEdit.showMessage(data.getData());
                            if (data.getData().contains("成功")){
                                viewEdit.back();
                            }
                        }
                    }
                },context));
    }
    @Override
    public void getSubUserList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id", UserMessage.getInstance().getUId());
        mSubscription = ApiManager.getApiManager()
                .getSubUserList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<SubUserInfo>>>() {
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
                    public void requestSuccess(Result<List<SubUserInfo>> data) {
                        if (data.getData()!=null){
                            List<SubUserInfo> userInfoList  =data.getData();
                            view.setSubUserList(userInfoList);

                        }
                    }
                },context));
    }

    @Override
    public void editSubUser() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("sub_user_id", viewEdit.getSubUserId());
        parameter.setParam("password", viewEdit.getPassword());
        parameter.setParam("name", viewEdit.getName());
        parameter.setParam("mobile", viewEdit.getMobile());
        parameter.setParam("role_id", viewEdit.getRoleId());
        if (G.isEmteny(viewEdit.getMobile())
                ||G.isEmteny(viewEdit.getName())){
            G.showToast(context,"必填项不能为空！");
            return;
        }
        if (viewEdit.getRoleId()==-1){
            G.showToast(context,"员工权限不能为空！");
            return;
        }
        mSubscription = ApiManager.getApiManager()
                .editSubUser(parameter.getMapParams())
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
                        if (data.getData()!=null){
                            viewEdit.showMessage(data.getData());
                            if (data.getData().contains("成功")){
                                viewEdit.back();
                            }
                        }
                    }
                },context));
    }

    @Override
    public void delSubUser() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("sub_user_id", viewEdit.getSubUserId());
        mSubscription = ApiManager.getApiManager()
                .delSubUser(parameter.getMapParams())
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
                        if (data.getData()!=null){
                            viewEdit.showMessage(data.getData());
                            if (data.getData().contains("成功")){
                                viewEdit.back();
                            }
                        }
                    }
                },context));
    }
    @Override
    public void getRoleList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id", UserMessage.getInstance().getUId());
        mSubscription = ApiManager.getApiManager()
                .getRoleByUserId(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<RoleInfo>>>() {
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
                    public void requestSuccess(Result<List<RoleInfo>> data) {
                        if (data.getData()!=null){
                            List<RoleInfo> roleInfos = data.getData();
                            viewEdit.setRoleList(roleInfos);
                        }
                    }
                },context));
    }
}
