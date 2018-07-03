package com.yun.ma.yi.app.module.shop.order;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.ShopOrderDetailInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间： 2017/11/21
 * 名称：店铺订单管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderDetailAdapter extends RecyclerView.Adapter<ShopOrderDetailAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<ShopOrderDetailInfo> shopOrderDetailInfoList;
    public ShopOrderDetailAdapter(Context context,List<ShopOrderDetailInfo> shopOrderDetailInfoList){
        this.context = context;
        this.shopOrderDetailInfoList =shopOrderDetailInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_order_goods_detail, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        int tradeTextColor = ContextCompat.getColor(context,R.color.goods_trade_text);
        int lastTextColor=  ContextCompat.getColor(context,R.color.red_btn);
        int defaultTextColor = ContextCompat.getColor(context,R.color.goods_text_color);
        boolean isLast = position==shopOrderDetailInfoList.size();
        holder.tv_goods_barcode.setTextColor(isLast?lastTextColor:tradeTextColor);
        holder.tv_goods_name.setTextColor(isLast?lastTextColor:defaultTextColor);
        holder.tv_goods_money.setTextColor(isLast?lastTextColor:defaultTextColor);
        holder.tv_goods_count.setTextColor(isLast?lastTextColor:defaultTextColor);
        holder.tv_goods_barcode.setVisibility(isLast?View.GONE:View.VISIBLE);

        if (!isLast){
            ShopOrderDetailInfo shopOrderDetailInfo = shopOrderDetailInfoList.get(position);
            holder.tv_goods_name.setText(shopOrderDetailInfo.getTitle());
            holder.tv_goods_barcode.setText(shopOrderDetailInfo.getBar_code());
            holder.tv_goods_money.setText(PriceTransfer.chageMoneyToString(shopOrderDetailInfo.getOrder_total_price()));
            holder.tv_goods_count.setText(String.valueOf(shopOrderDetailInfo.getQuantity()));
        }else {
            holder.tv_goods_name.setText("商品合计");
            int totalQuantity=0;
            double totalMoney=0;
            for (int i = 0 ;i< shopOrderDetailInfoList.size();i++){
                ShopOrderDetailInfo shopOrderDetailInfo = shopOrderDetailInfoList.get(i);
                totalQuantity += shopOrderDetailInfo.getQuantity();
                totalMoney += shopOrderDetailInfo.getOrder_total_price();
            }
            holder.tv_goods_count.setText(String.valueOf(totalQuantity));
            holder.tv_goods_money.setText(PriceTransfer.chageMoneyToString(totalMoney));
        }

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
        TextView tv_goods_name;
        TextView tv_goods_barcode;
        TextView tv_goods_count;
        TextView tv_goods_money;
        ViewHolder(View itemView) {
            super(itemView);
            tv_goods_name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_goods_barcode = (TextView) itemView.findViewById(R.id.tv_goods_barcode);
            tv_goods_count = (TextView) itemView.findViewById(R.id.tv_goods_count);
            tv_goods_money = (TextView) itemView.findViewById(R.id.tv_goods_money);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return shopOrderDetailInfoList.size()+1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
