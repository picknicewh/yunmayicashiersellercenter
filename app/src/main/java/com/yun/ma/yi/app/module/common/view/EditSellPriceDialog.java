package com.yun.ma.yi.app.module.common.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：编辑售价对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EditSellPriceDialog extends AlertDialog implements View.OnClickListener {
    /**
     * 原成本价
     */
    private TextView tvPrice;
    /**
     * 匹配之后成本价
     */
    private    TextView tvConformPrice;
    /**
     * 修改后的价格
     */
    private   EditText etEditPrice;
    /**
     * 取消
     */
    private TextView tvCancel;
    /**
     * 确认
     */
    private   TextView tvConform;
    private Activity context;

    public EditSellPriceDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_sell_price);
        setDialogWidth();
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvConformPrice = (TextView) findViewById(R.id.tv_conform_price);
        etEditPrice = (EditText) findViewById(R.id.et_edit_price);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvConform = (TextView) findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    /**
     * 设置商品的成本价
     * @param price
     */
    public void setPrice(double price){
        tvPrice.setText(PriceTransfer.chageMoneyToString(price));
    }
    /**
     * 设置配合后的成本价
     * @param conformPrice
     */
    public void setConformPrice(double conformPrice){
      tvConformPrice.setText(PriceTransfer.chageMoneyToString(conformPrice));
    }

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

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tv_cancel) {
            dismiss();
        } else if (viewId == R.id.tv_conform) {
            if (onSalePriceChangeListener!=null){
                String price = etEditPrice.getText().toString();
                double changePrice = G.isEmteny(price)?0:Double.parseDouble(price);
                onSalePriceChangeListener.onSaleChange(changePrice);
            }
            dismiss();
        }
    }

    /**
     *回调监听
     */
    public void setOnSalePriceChangeListener(OnSalePriceChangeListener onSalePriceChangeListener) {
        this.onSalePriceChangeListener = onSalePriceChangeListener;
    }

    private OnSalePriceChangeListener onSalePriceChangeListener;
    public interface  OnSalePriceChangeListener{
        void  onSaleChange(double price);
    }
}
