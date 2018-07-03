package com.yun.ma.yi.app.module.staff.role;

import android.content.Context;

import com.google.gson.Gson;
import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.RoleRuleInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：员工权限数据处理类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffRolePresenter implements StaffRoleContract.Presenter{
    private StaffRoleContract.View view;
    private StaffRoleContract.ViewEdit viewEdit;
    private Subscription mSubscription;
    private Context context;
    private Gson gson;
     public StaffRolePresenter(Context context,StaffRoleContract.View view){
         this.view =view;
         this.context = context;

     }
    public StaffRolePresenter(Context context,StaffRoleContract.ViewEdit viewEdit){
        this.viewEdit =viewEdit;
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
    public void addRole() {
        RequestParameter parameter = new RequestParameter();
        RoleInfo roleInfo = viewEdit.getData();
        if (G.isEmteny(roleInfo.getName())){
            G.showToast(context,"角色姓名不能为空！");
            return;
        }
        if (G.isEmteny(roleInfo.getRule_ids())){
            G.showToast(context,"角色权限不能为空！");
            return;
        }
        if (gson==null){
            gson = new Gson();
        }
        parameter.setParam("data", gson.toJson(roleInfo));
        mSubscription = ApiManager.getApiManager()
                .addRole(parameter.getMapParams())
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
    public void editRole() {
        RequestParameter parameter = new RequestParameter();
         RoleInfo roleInfo = viewEdit.getData();
        if (G.isEmteny(roleInfo.getName())){
            G.showToast(context,"角色姓名不能为空！");
            return;
        }
        if (G.isEmteny(roleInfo.getRule_ids())){
            G.showToast(context,"角色权限不能为空！");
            return;
        }
        if (gson==null){
            gson = new Gson();
        }
        parameter.setParam("data", gson.toJson(roleInfo));
        mSubscription = ApiManager.getApiManager()
                .editRole(parameter.getMapParams())
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
        parameter.setParam("user_id",  UserMessage.getInstance().getUId());
        mSubscription = ApiManager.getApiManager()
                .getRoleByUserId(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<RoleInfo>>>() {
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
                    public void requestSuccess(Result<List<RoleInfo>> data) {
                        if (data.getData()!=null){
                            List<RoleInfo> roleInfos = data.getData();
                            view.setRoleList(roleInfos);
                        }
                    }
                },context));
    }

    @Override
    public void getRuleByRole() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("user_id", UserMessage.getInstance().getUId());
        parameter.setParam("role_id",viewEdit.getRoleId());
        mSubscription = ApiManager.getApiManager()
                .getRuleByRole(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result< List<RoleRuleInfo>>>() {
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
                    public void requestSuccess(Result<List<RoleRuleInfo>> data) {
                        if (data.getData()!=null){
                           List<RoleRuleInfo> rolePermissionInfos = data.getData();
                            viewEdit.setRolePermissionInfoList(rolePermissionInfos);

                        }
                    }
                },context));
    }

    @Override
    public void getRuleList() {
        RequestParameter parameter = new RequestParameter();
        mSubscription = ApiManager.getApiManager()
                .getRuleList(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result< List<RoleRuleInfo>>>() {
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
                    public void requestSuccess(Result<List<RoleRuleInfo>> data) {
                        if (data.getData()!=null){
                            List<RoleRuleInfo> rolePermissionInfos = data.getData();
                            viewEdit.setRolePermissionInfoList(rolePermissionInfos);
                        }
                    }
                },context));
    }
}
