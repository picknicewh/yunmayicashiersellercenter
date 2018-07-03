package com.yun.ma.yi.app.module.common.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：确认编辑对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ConformEditDialog extends android.app.AlertDialog implements View.OnClickListener{

    /**
     * 取消
     */
    private  Button btCancel;
    /**
     * 确认
     */
    private   Button btConform;
    private Activity context;
    /**
     * 编辑框
     */
    private ClearEditText editText;
    /**
     * 确认回调
     */
    private OnConformCallBack onConformCallBack;
    public ConformEditDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conform_edit);
        setDialogWidth();
        editText =(ClearEditText)findViewById(R.id.edit);
        btCancel = (Button)findViewById(R.id.bt_cancel);
        btConform = (Button)findViewById(R.id.bt_conform);
        btCancel.setOnClickListener(this);
        btConform.setOnClickListener(this);
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
        int viewId= v.getId();
        if (viewId==R.id.bt_cancel){
            dismiss();
        }else if (viewId==R.id.bt_conform){
            if (onConformCallBack!=null){
                String reason = editText.getText().toString();
                onConformCallBack.onCallBack(reason);
            }
            dismiss();
        }
    }

    public void setOnConformCallBack(OnConformCallBack onConformCallBack) {
        this.onConformCallBack = onConformCallBack;
    }

    public interface OnConformCallBack{
        void onCallBack(String reason);
    }

}
