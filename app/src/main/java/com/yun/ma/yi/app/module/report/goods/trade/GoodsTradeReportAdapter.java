package com.yun.ma.yi.app.module.report.goods.trade;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsTradeInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.module.common.OnItemObjClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 商品交易流水适配
 */

public class GoodsTradeReportAdapter extends CommonRecyclerAdapter<GoodsTradeInfo>{

    private static OnItemObjClickListener clickListener;

    public GoodsTradeReportAdapter(Context context, List<GoodsTradeInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, final GoodsTradeInfo goodsTradeInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.serial_number)).setText(goodsTradeInfo.getTrade_id());
            ((TextView)holder.getView(R.id.paid_in_amount)).setText(PriceTransfer.chageMoneyToString(goodsTradeInfo.getReceived_fee()));
            ((TextView)holder.getView(R.id.goods_trade_create_time)).setText(goodsTradeInfo.getPay_date());
            TextView textView = holder.getView(R.id.tv_number);
            if (!G.isEmteny(goodsTradeInfo.getSub_user_username())){
                textView.setText("工号："+goodsTradeInfo.getSub_user_username());
                textView.setVisibility(View.VISIBLE);
            }else {
                textView.setVisibility(View.GONE);
            }

            holder.getView(R.id.serial_number).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null){
                        clickListener.onClick(view,goodsTradeInfo);
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
