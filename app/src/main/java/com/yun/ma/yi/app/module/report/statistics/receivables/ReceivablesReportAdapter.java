package com.yun.ma.yi.app.module.report.statistics.receivables;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.ReceivablesInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 收款统计适配
 */

public class ReceivablesReportAdapter extends CommonRecyclerAdapter<ReceivablesInfo>{

    public ReceivablesReportAdapter(Context context, List<ReceivablesInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, ReceivablesInfo cashierInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.receivables_date)).setText(cashierInfo.getDate());
            ((TextView)holder.getView(R.id.total)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getTotal()));
            ((TextView)holder.getView(R.id.cash_sum)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getCod()));
            ((TextView)holder.getView(R.id.alipay_sum)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getAlipay()));
            ((TextView)holder.getView(R.id.wechat_sum)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getWxpay()));
            ((TextView)holder.getView(R.id.wechat_cash_count)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getWxCash()));
            ((TextView)holder.getView(R.id.zhifu_cash_count)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getAlipayCash()));
            ((TextView)holder.getView(R.id.member_pay_sum)).setText(" ¥ "+PriceTransfer.chageMoneyToString(cashierInfo.getSubUserPay()));
        }
    }
}
