package com.yun.ma.yi.app.module.member.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：余额明细/积分明细适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoDetailAdapter extends RecyclerView.Adapter<MemberInfoDetailAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<MemberInfoChangeInfo> memberInfoChangeInfoList;
    private int source;
    public MemberInfoDetailAdapter(List<MemberInfoChangeInfo> memberInfoChangeInfoList, int source) {
       this.memberInfoChangeInfoList =memberInfoChangeInfoList;
        this.source = source;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balance_info_list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        MemberInfoChangeInfo memberInfoChangeInfo  = memberInfoChangeInfoList.get(position);
        //交易类型 1充值余额 2消费余额 3积分新增 4积分兑换
        if (source == Constants.BALANCE_HAPPEND_DETAIL) {
            holder.tv_recharge_money.setText(PriceTransfer.chageMoneyToString(memberInfoChangeInfo.getChange_card_money()));
            holder.tv_recharge_after_money.setText(PriceTransfer.chageMoneyToString(memberInfoChangeInfo.getAfter_card_money()));
        } else if (source == Constants.INTEGRAL_HAPPEND_DETAIL) {
            holder.tv_recharge_money.setText(String.valueOf(memberInfoChangeInfo.getChange_card_integral()));
            holder.tv_recharge_after_money.setText(String.valueOf(memberInfoChangeInfo.getAfter_card_integral()));
        }
        holder.tv_text.setText(TextUtils.getTradeType(memberInfoChangeInfo.getTrade_type()));
        holder.tv_recharge_time.setText(String.valueOf(memberInfoChangeInfo.getCreate_datetime()));
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
        TextView tv_text;
        TextView tv_recharge_after_money;
        TextView tv_recharge_money;
        TextView tv_recharge_time;
        ViewHolder(View itemView) {
            super(itemView);
            tv_recharge_money = (TextView) itemView.findViewById(R.id.tv_recharge_money);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            tv_recharge_after_money = (TextView) itemView.findViewById(R.id.tv_recharge_after_money);
            tv_recharge_time = (TextView) itemView.findViewById(R.id.tv_recharge_time);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return memberInfoChangeInfoList.size();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
