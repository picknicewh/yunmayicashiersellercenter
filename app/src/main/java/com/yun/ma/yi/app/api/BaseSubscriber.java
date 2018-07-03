package com.yun.ma.yi.app.api;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.utils.NetworkUtil;

import rx.Subscriber;

/**
 * Created by ys on 2017/6/5.
 * 封装Subscriber 处理 onError
 */

public class BaseSubscriber<T> extends Subscriber<T>{

    private RequestCallback<T> mRequestCallback;
    private Context context;
    private String SUCCESS_CODE = "success";
    private String ERROR_CODE = "fail";

    public BaseSubscriber(RequestCallback<T> requestCallback,Context context) {
        mRequestCallback = requestCallback;
        this.context = context;

    }


    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (mRequestCallback != null) {
            mRequestCallback.beforeRequest();
        }
        if (!NetworkUtil.isNetworkAvailable(context)) {
            mRequestCallback.requestError("无网络，请检查网络！");
            onCompleted();
        }
    }

    @Override
    public void onCompleted() {
        if (mRequestCallback != null) {
            mRequestCallback.requestComplete();
        }
    }
    @Override
    public void onError(Throwable e) {
        /**处理错误信息*/
        if (mRequestCallback != null) {
            ExceptionEngine.ResponeThrowable responeThrowable = ExceptionEngine.handleException(e);
            String message = responeThrowable.message;
            mRequestCallback.requestError(message);
        }
    }

    @Override
    public void onNext(T t) {
        if (mRequestCallback == null)
            return;
        if(t instanceof Result) {
            Result result = (Result)t;
//            LinkedTreeMap linkedTreeMap = (LinkedTreeMap)result.getData();
//            String data =gson.toJson(linkedTreeMap);
//            result.setData(data);
            //判断是否返回错误信息
            if (SUCCESS_CODE.equals(result.getStatus())){
                mRequestCallback.requestSuccess(t);
            }else if(ERROR_CODE.equals(result.getStatus())){
                mRequestCallback.requestError(result.getInfo());
            }
        }
    }
}
