package com.yun.ma.yi.app.module.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：进度条确认对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ProgressDialog extends android.app.AlertDialog {

    private ImageView ivLoading;
    private Activity context;

    public ProgressDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_change);
        setDialogWidth();
        ivLoading = (ImageView)findViewById(R.id.iv_loading_image);
        startAnimation();
    }


    public void startAnimation(){
        ivLoading.startAnimation(AnimationUtils.loadAnimation(context, R.anim.progress_anim));
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    /**
     * 设置屏幕的宽度
     */
    private void setDialogWidth(){
        G.initDisplaySize(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (G.size.W* 0.65);
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager. LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }
}
