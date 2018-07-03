package com.yun.ma.yi.app.module.report.goods.trade;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsTradeDetailInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.StringUtils;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 账单详情里面的商品
 */

public class GoodsTradeDetailAdapter extends CommonRecyclerAdapter<GoodsTradeDetailInfo>{

    private Context context;

    public GoodsTradeDetailAdapter(Context context, List<GoodsTradeDetailInfo> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
    }
    @Override
    public void convert(CommonRecyclerHolder holder, GoodsTradeDetailInfo goodsTradeDetailInfo) {
        if (holder != null){
            if (StringUtils.isEmpty(goodsTradeDetailInfo.getBar_code())){
                holder.getView(R.id.goods_code).setVisibility(View.GONE);
                ((TextView)holder.getView(R.id.goods_name)).setTextColor(context.getResources().getColor(R.color.red_btn));
                ((TextView)holder.getView(R.id.goods_sum)).setTextColor(context.getResources().getColor(R.color.red_btn));
                ((TextView) holder.getView(R.id.goods_total)).setTextColor(context.getResources().getColor(R.color.red_btn));
            }else{
                ((TextView)holder.getView(R.id.goods_code)).setText(goodsTradeDetailInfo.getBar_code());
            }
            ((TextView)holder.getView(R.id.goods_name)).setText(goodsTradeDetailInfo.getTitle());
             if (goodsTradeDetailInfo.getIs_weigh()==1){
                 ((TextView)holder.getView(R.id.goods_sum)).setText("0/"+goodsTradeDetailInfo.getWeight());
             }else {
                 ((TextView)holder.getView(R.id.goods_sum)).setText(goodsTradeDetailInfo.getQuantity()+"/0");
             }
            ((TextView)holder.getView(R.id.goods_total)).setText(" ¥ "+PriceTransfer.chageMoneyToString(goodsTradeDetailInfo.getTotal_fee()));
        }
    }

}
