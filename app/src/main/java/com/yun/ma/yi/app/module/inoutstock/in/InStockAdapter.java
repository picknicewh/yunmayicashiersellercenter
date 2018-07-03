package com.yun.ma.yi.app.module.inoutstock.in;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称:角色权限适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockAdapter extends CommonRecyclerAdapter<OrderInfo> {

    public InStockAdapter(Context context, List<OrderInfo> data, int layoutId) {
        super(context, data, layoutId);
    }
    @Override
    public void convert(CommonRecyclerHolder holder, OrderInfo orderInfo) {
        TextView tv_role = holder.getView(R.id.tv_code);
        tv_role.setText(orderInfo.getOrder_id());
    }
}
