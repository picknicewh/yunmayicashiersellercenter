package com.yun.ma.yi.app.module.common;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;


/**
 * 作者： wh
 * 时间：  2017/6/19
 * 名称：更新进度条对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ProgressChangeDialog extends AlertDialog {
    /**
     * 进度条
     */
    private ProgressBar progressBar;
    public ProgressChangeDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_progress);
        setCancelable(false);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
    /**
     * 当前上传的
     * @param  progress 当前下载进度
     */
    public void setProgress(float progress){
        progressBar.setProgress((int)(progress*100));
        G.log("--xxxxx:progress" + progress);
        if (progress==1){
            dismiss();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
