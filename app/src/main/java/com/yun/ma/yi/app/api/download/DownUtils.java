package com.yun.ma.yi.app.api.download;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.yun.ma.yi.app.api.Config;
import com.yun.ma.yi.app.bean.Version;
import com.yun.ma.yi.app.utils.FilePath;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ys on 2017/6/26.
 */

public class DownUtils {

    public static void retrofitDownload(final Context context, final Version version){
        //监听下载进度
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressNumberFormat("%1d KB/%2d KB");
        dialog.setTitle(context.getResources().getString(R.string.down));
        dialog.setMessage(context.getResources().getString(R.string.down_loading));
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.SERVER_ROOT);
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        DownloadApi retrofit = retrofitBuilder
                .client(builder.build())
                .build().create(DownloadApi.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress",String.format("%d%% done\n",(100 * bytesRead) / contentLength));
                Log.e("done","--->" + String.valueOf(done));
                dialog.setMax((int) (contentLength/1024));
                dialog.setProgress((int) (bytesRead/1024));
                if(done){
                    dialog.dismiss();
                }
            }
        });

        Call<ResponseBody> call = retrofit.retrofitDownload(version.getDownload_url());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream is = response.body().byteStream();
                    String  savePath = FilePath.getApkFileLoadPath(context);
                    savePath = savePath + File.separator + context.getResources().getString(R.string.app_name) + ".apk";
                    File  saveFile = new File(savePath);
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();
                    installAPK(context,savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.showToast(context,"下载失败！");
            }
        });
    }
    /**
     * 安装APK工具类
     * @param context       上下文
     * @param filePath      文件路径
     */
    public static void installAPK(Context context, String filePath) {
        if (context == null || G.isEmteny(filePath)) {
            return;
        }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            File apkFile = new File(filePath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(context, "com.yunmayi.app.manage.provider", apkFile);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
    }
}
