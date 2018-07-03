package com.yun.ma.yi.app.module.common;

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
 * 时间：  2017/8/25
 * 名称：确认价格(进价<售价)
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ConformPriceDialog  extends android.support.v7.app.AlertDialog implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView tvContent;
    /**
     * 取消
     */
    private  Button btCancel;
    /**
     * 确认
     */
    private   Button btConform;
    private Activity context;
    public ConformPriceDialog(Activity context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conform);
        setDialogWidth();
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText("商品销售价小于进价你确定要添加吗？");
        btCancel = (Button)findViewById(R.id.bt_cancel);
        btConform = (Button)findViewById(R.id.bt_conform);
        btCancel.setOnClickListener(this);
        btConform.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_conform){
            if (onConformClickListener!=null){
                onConformClickListener.onConform();
            }
        }
        dismiss();
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
    private OnConformClickListener onConformClickListener;

    public void setOnConformClickListener(OnConformClickListener onConformClickListener) {
        this.onConformClickListener = onConformClickListener;
    }

    public interface  OnConformClickListener{
       void   onConform();
    }
}
