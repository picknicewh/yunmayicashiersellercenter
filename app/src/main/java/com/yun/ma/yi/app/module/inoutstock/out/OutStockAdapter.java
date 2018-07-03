package com.yun.ma.yi.app.module.inoutstock.out;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OutStockAdapter extends RecyclerView.Adapter<OutStockAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<OrderInfoDetail> orderInfoDetailList;
    public OutStockAdapter(List<OrderInfoDetail> orderInfoDetailList) {
        this.orderInfoDetailList = orderInfoDetailList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_out_stock_goods, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        final OrderInfoDetail orderInfoDetail  = orderInfoDetailList.get(position);
        if (orderInfoDetail.getAlready_return()>0) {
            holder.tv_status.setText("已退货");
            holder.tv_status.setBackgroundResource(R.drawable.round_grey_btn);
        }  else{
            holder.tv_status.setText("未退货");
            holder.tv_status.setBackgroundResource(R.drawable.round_red_btn);
        }
        holder.tv_goods_name.setText("商品名称："+orderInfoDetail.getProduct_title());
        holder.tv_order_code.setText("条形码："+orderInfoDetail.getProduct_bar_code());
        holder.tv_order_time.setText("下单时间："+orderInfoDetail.getCreate_datetime());
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
        TextView tv_order_time;
        TextView tv_goods_name;
        TextView tv_order_code;
        TextView tv_status;
        ViewHolder(View itemView) {
            super(itemView);
            tv_order_time = (TextView) itemView.findViewById(R.id.tv_order_time);
            tv_goods_name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_order_code = (TextView) itemView.findViewById(R.id.tv_order_code);
            tv_status=  (TextView) itemView.findViewById(R.id.tv_status);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return orderInfoDetailList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
