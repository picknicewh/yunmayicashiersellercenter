package com.yun.ma.yi.app.module.common.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/10/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ConformDeleteDialog extends android.app.AlertDialog implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView tvContent;
    /**
     * 取消
     */
    private Button btCancel;
    /**
     * 确认
     */
    private Button btConform;
    private Activity context;

    public ConformDeleteDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conform_delete);
        setDialogWidth();
        tvContent = (TextView) findViewById(R.id.tv_content);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        btConform = (Button) findViewById(R.id.bt_conform);
        btCancel.setOnClickListener(this);
        btConform.setOnClickListener(this);
    }
    private int position=0;

    /**
     * 设置屏幕的宽度
     */
    private void setDialogWidth() {
        G.initDisplaySize(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (G.size.W * 0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }
    public void setContent(String content,int position){
        tvContent.setText(content+"?");
        this.position = position;
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bt_cancel) {
            dismiss();
        } else if (viewId == R.id.bt_conform) {
            if (deleteCallBack!=null){
                deleteCallBack.callBack(position);
            }
            dismiss();
        }
    }
    private DeleteCallBack deleteCallBack;

    public void setDeleteCallBack(DeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }



    public interface DeleteCallBack{
        void callBack(int position);
    }
}
