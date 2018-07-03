package com.yun.ma.yi.app.api.util;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;

import java.io.File;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/26
 * 名称：上传图片
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ImageUtil {

    public static Subscription  upLoad(String path, BaseSubscriber subscriber){
        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("image",file.getName(),imageBody);
        Subscription subscription = ApiManager.getApiManager()
                .uploadFile(imageBodyPart,getRequestBody("app_id"),
                        getRequestBody("timestamp"),getRequestBody("sign"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);

        return subscription;
    }
    private static RequestBody getRequestBody(String key){
        String value="";
        RequestParameter parameter = new RequestParameter();
        Map<String,Object> params = parameter.getMapParams();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries){
            if (entry.getKey().equals(key)){
                value = String.valueOf(entry.getValue());
                break;
            }
        }
        return RequestBody.create(MediaType.parse("text/plain"),value);
    }
}
