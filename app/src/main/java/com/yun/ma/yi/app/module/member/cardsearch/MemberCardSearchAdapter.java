package com.yun.ma.yi.app.module.member.cardsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
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
public class MemberCardSearchAdapter extends RecyclerView.Adapter<MemberCardSearchAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<MemberCardInfo> memberCardInfoList;
    public MemberCardSearchAdapter(List<MemberCardInfo> memberCardInfoList){
        this.memberCardInfoList = memberCardInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_search_list, null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        MemberCardInfo memberCardInfo = memberCardInfoList.get(position);
        holder.tv_discount_rate.setText(String.valueOf(memberCardInfo.getDiscount_rate()));
        holder.tv_card_name.setText(memberCardInfo.getCard_name());
        holder.tv_integral.setText(String.valueOf(memberCardInfo.getCard_integral()));
        switch (memberCardInfo.getDiscount_type()){
            case 0:
                holder.tv_discount_way.setText("会员价");
                break;
            case 1:
                holder.tv_discount_way.setText("折扣率");
                break;
            case 2:
                holder.tv_discount_way.setText("进货价");
                break;
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
        TextView tv_card_name;
        TextView tv_discount_way;
        TextView tv_discount_rate;
        TextView tv_integral;
        ViewHolder(View itemView) {
            super(itemView);
            tv_discount_rate = (TextView) itemView.findViewById(R.id.tv_discount_rate);
            tv_card_name = (TextView) itemView.findViewById(R.id.tv_card_name);
            tv_discount_way = (TextView) itemView.findViewById(R.id.tv_discount_way);
            tv_integral = (TextView) itemView.findViewById(R.id.tv_integral);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return memberCardInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
