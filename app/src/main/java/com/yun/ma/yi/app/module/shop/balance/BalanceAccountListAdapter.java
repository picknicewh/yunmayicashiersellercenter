package com.yun.ma.yi.app.module.shop.balance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.BalanceAccountInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/12/12
 * 名称：余额对账列表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountListAdapter extends RecyclerView.Adapter<BalanceAccountListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<BalanceAccountInfo.BalanceDetailInfo> balanceDetailInfoList;

    public BalanceAccountListAdapter(List<BalanceAccountInfo.BalanceDetailInfo> balanceDetailInfoList) {
        this.balanceDetailInfoList = balanceDetailInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balance_account_detail, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        BalanceAccountInfo.BalanceDetailInfo balanceDetailInfo= balanceDetailInfoList.get(position);
        holder.tv_order_id.setText(String.valueOf(balanceDetailInfo.getTrade_id()));
        holder.tv_order_type.setText(balanceDetailInfo.getType());
        holder.tv_balance.setText(PriceTransfer.chageMoneyToString(balanceDetailInfo.getReal_amount()));
        holder.tv_before_balance.setText(PriceTransfer.chageMoneyToString(balanceDetailInfo.getBefore_balance()));
        holder.tv_after_balance.setText(PriceTransfer.chageMoneyToString(balanceDetailInfo.getAfter_balance()));
        holder.tv_time.setText(balanceDetailInfo.getCreated_at());
        holder.tv_describe.setText(balanceDetailInfo.getDescription());
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
        TextView tv_order_id;
        TextView tv_order_type;
        TextView tv_balance;
        TextView tv_before_balance;
        TextView tv_after_balance;
        TextView tv_time;
        TextView tv_describe;

        ViewHolder(View itemView) {
            super(itemView);
            tv_order_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_order_type = (TextView) itemView.findViewById(R.id.tv_order_type);
            tv_balance = (TextView) itemView.findViewById(R.id.tv_balance);
            tv_before_balance = (TextView) itemView.findViewById(R.id.tv_before_balance);
            tv_after_balance = (TextView) itemView.findViewById(R.id.tv_after_balance);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_describe = (TextView) itemView.findViewById(R.id.tv_describe);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return balanceDetailInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
