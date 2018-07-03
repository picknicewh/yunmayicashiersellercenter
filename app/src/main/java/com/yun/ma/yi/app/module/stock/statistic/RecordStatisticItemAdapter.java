package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.StockStatisticItemInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：库存盘点记录适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticItemAdapter extends CommonRecyclerAdapter<StockStatisticItemInfo> {

    public RecordStatisticItemAdapter(Context context, List<StockStatisticItemInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, final StockStatisticItemInfo stockStatisticItemInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.tv_item_adjust_count)).setText("数量:" + stockStatisticItemInfo.getChange_stock());
            ((TextView)holder.getView(R.id.tv_item_after_adjust)).setText("调整后:"+ stockStatisticItemInfo.getAfter_stock());
            ((TextView)holder.getView(R.id.tv_item_date)).setText(stockStatisticItemInfo.getUpdate_datetime());
        }
    }
}
