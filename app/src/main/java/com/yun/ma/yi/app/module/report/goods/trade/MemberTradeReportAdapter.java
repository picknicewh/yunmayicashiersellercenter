package com.yun.ma.yi.app.module.report.goods.trade;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.MemberTradeInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.module.common.OnItemObjClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by wh on 2017/7/18.
 * 商品交易详情
 */

public class MemberTradeReportAdapter extends CommonRecyclerAdapter<MemberTradeInfo>{

    private static OnItemObjClickListener clickListener;

    public MemberTradeReportAdapter(Context context, List<MemberTradeInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, final MemberTradeInfo memberTradeInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.serial_number)).setText(memberTradeInfo.getId());
            ((TextView)holder.getView(R.id.paid_in_amount)).setText(PriceTransfer.chageMoneyToString(Double.valueOf(memberTradeInfo.getPayment())));
            ((TextView)holder.getView(R.id.goods_trade_create_time)).setText(memberTradeInfo.getCreate_date());
            holder.getView(R.id.serial_number).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null){
                        clickListener.onClick(view,memberTradeInfo);
                    }
                }
            });
        }
    }

    /**
     * 设置item 事件监听
     * @param clickListener
     */
    public void setClickListener(OnItemObjClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
