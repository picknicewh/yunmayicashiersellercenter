package com.yun.ma.yi.app.module.common;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.utils.CommonUtil;
import com.yun.ma.yi.app.utils.FileUtils;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PermissionsChecker;
import com.yunmayi.app.manage.R;

import java.io.File;

/**
 * 作者： wh
 * 时间：  2017/6/23
 * 名称：图片选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class PictureChooseDialog extends AlertDialog implements View.OnClickListener {

    private Activity context;
    private TextView tv_album;
    private TextView tv_take_picture;
    private TextView tv_cance1;
    private  String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public PictureChooseDialog(Activity context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picture_choose);
        setCancelable(false);//设置外部不可点击
        //动态申请权限
        PermissionsChecker checker = PermissionsChecker.getInstance(context);
        if (checker.lacksPermissions(PERMISSIONS)) {
            checker.getPerMissions(PERMISSIONS);
            return;
        }
        initView();
    }
    private void initView(){
        setDialogWidth();
        tv_album = (TextView)findViewById(R.id.tv_album);
        tv_take_picture =(TextView)findViewById(R.id.tv_take_picture);
        tv_cance1 = (TextView)findViewById(R.id.tv_cance1);
        tv_cance1.setOnClickListener(this);
        tv_take_picture.setOnClickListener(this);
        tv_album.setOnClickListener(this);
    }
    /**
     * 设置屏幕的宽度
     */
   private void setDialogWidth(){
       G.initDisplaySize(context);
       WindowManager.LayoutParams params = getWindow().getAttributes();
       params.width = (int) (G.size.W* 0.8);
       params.height=WindowManager.LayoutParams.WRAP_CONTENT;
       params.flags = WindowManager. LayoutParams.FLAG_DIM_BEHIND;
       getWindow().setAttributes(params);
   }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.tv_album:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                context.startActivityForResult(intent, Constants.RESULT_IMAG);
                break;
            case R.id.tv_take_picture:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (CommonUtil.isCameraCanUse()){
                    if (Build.VERSION.SDK_INT>=24){
                         imagePath = FileUtils.getUploadPhotoFile(context);
                         File outputFile = new File(imagePath);
                         Uri contentUri = FileProvider.getUriForFile(context, "com.yunmayi.app.manage.provider", outputFile);
                         camera.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    }
                    context.startActivityForResult(camera, Constants.RESULT_CAMERA);
                }
                break;
            case R.id.tv_cance1:
                break;
        }
        dismiss();
    }
    private String imagePath;
    /**
     * 获取imagePath
     */
    public  String getImagePath(){
        return  imagePath;
    }
}
