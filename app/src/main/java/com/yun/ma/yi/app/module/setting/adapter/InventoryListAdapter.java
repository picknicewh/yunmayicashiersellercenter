package com.yun.ma.yi.app.module.setting.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.StcokWarningInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 库存预警商品
 */

public class InventoryListAdapter extends CommonRecyclerAdapter<StcokWarningInfo>{

    private Context context;

    public InventoryListAdapter(Context context, List<StcokWarningInfo> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
    }

    @Override
    public void convert(CommonRecyclerHolder holder, StcokWarningInfo stcokWarningInfo) {
        if (holder != null){
            ((TextView)holder.getView(R.id.goods_code)).setText(stcokWarningInfo.getBar_code());
            ((TextView)holder.getView(R.id.goods_name)).setText(stcokWarningInfo.getTitle());
            ((TextView)holder.getView(R.id.warning_number)).setText(String.valueOf(stcokWarningInfo.getStock_warning_low()));
            ((TextView)holder.getView(R.id.stock_number)).setText(String.valueOf(stcokWarningInfo.getStock()));
        }
    }
}
