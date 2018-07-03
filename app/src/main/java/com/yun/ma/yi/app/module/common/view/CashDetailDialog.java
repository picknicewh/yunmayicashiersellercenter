package com.yun.ma.yi.app.module.common.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.CountTrade;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/8/22
 * 名称：现金选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CashDetailDialog extends PopupWindow {
    private TextView tvCashIncome;
    private TextView tvAliCashIncome;
    private TextView tvWxCashIncome;
    private Context context;
    private View contentView;

    public CashDetailDialog(Context context) {
        this.context = context;
        init();
    }

    private void initView() {
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_cash_detail, null);
        tvCashIncome = (TextView) contentView.findViewById(R.id.tv_cashIncome);
        tvAliCashIncome = (TextView) contentView.findViewById(R.id.tv_aliCashIncome);
        tvWxCashIncome = (TextView) contentView.findViewById(R.id.tv_wxCashIncome);

    }

    public void setTradeInfo(CountTrade countTrade) {
        tvCashIncome.setText("￥" + PriceTransfer.chageMoneyToString(countTrade.getCashIncome()));
        tvAliCashIncome.setText("￥" + PriceTransfer.chageMoneyToString(countTrade.getAliCashIncome()));
        tvWxCashIncome.setText("￥" + PriceTransfer.chageMoneyToString(countTrade.getWxCashIncome()));
    }
    public void init() {
        initView();
        //设置SignPopupWindow的View
        setContentView(contentView);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SignPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SignPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        //设置SignPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

}

