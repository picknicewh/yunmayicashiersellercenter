package com.yun.ma.yi.app.module.shop.order;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.ShopOrderInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：店铺订单管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderManagerAdapter extends RecyclerView.Adapter<ShopOrderManagerAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<ShopOrderInfo> orderInfoList;
    public ShopOrderManagerAdapter(List<ShopOrderInfo> orderInfoList){
        this.orderInfoList = orderInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_order_manager, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        ShopOrderInfo orderInfo = orderInfoList.get(position);
        holder.tv_cost.setText(PriceTransfer.chageMoneyToString(orderInfo.getOrder_total_price()));
        holder.tv_order_id.setText(orderInfo.getId());
        holder.tv_name.setText(orderInfo.getConsignee_name()+"/"+orderInfo.getConsignee_mobile());
        holder.tv_time.setText(orderInfo.getPay_time());
        holder.tv_status.setText(TextUtils.getShopOrderByStatus(orderInfo.getStatus()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (onItemClickListener!=null){
                   onItemClickListener.onClick(v,position);
               }
           }
       });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_cost;
        TextView tv_status;
        TextView tv_order_id;
        TextView tv_time;
        ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_cost = (TextView) itemView.findViewById(R.id.tv_cost);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_order_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return orderInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
