package com.yun.ma.yi.app.module.shop.cash;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.ShopCashInfo;
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
public class ShopOrderCashAdapter extends RecyclerView.Adapter<ShopOrderCashAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<ShopCashInfo.ShopCashDetailInfo> shopCashDetailInfoList;

    public ShopOrderCashAdapter(List<ShopCashInfo.ShopCashDetailInfo> shopCashDetailInfoList) {
        this.shopCashDetailInfoList = shopCashDetailInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_order_cash, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ShopCashInfo.ShopCashDetailInfo shopCashDetailInfo = shopCashDetailInfoList.get(position);
        holder.tv_actual_money.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getAccount()));
        holder.tv_status.setText(TextUtils.getOrderCashStatus(shopCashDetailInfo.getStatus()));
        holder.tv_time.setText(shopCashDetailInfo.getCreated_at());
        holder.tv_order_money.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getAmount()));
        holder.tv_server_charge.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getFormalities()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v, position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order_money;
        TextView tv_status;
        TextView tv_server_charge;
        TextView tv_actual_money;
        TextView tv_time;

        ViewHolder(View itemView) {
            super(itemView);
            tv_order_money = (TextView) itemView.findViewById(R.id.tv_order_money);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_server_charge = (TextView) itemView.findViewById(R.id.tv_server_charge);
            tv_actual_money = (TextView) itemView.findViewById(R.id.tv_actual_money);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return shopCashDetailInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
