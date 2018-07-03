package com.yun.ma.yi.app.module.member.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员中公共的表格适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CommonTabAdapter extends RecyclerView.Adapter<CommonTabAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<MemberInfo> memberInfoList;
    private int type;

    public CommonTabAdapter(List<MemberInfo> memberInfoList,int type){
        this.memberInfoList = memberInfoList;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_tab_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (type){
            case Constants.TYPR_MEMBER_LIST:
                MemberInfo memberInfo = memberInfoList.get(position);
                holder.tv_text1.setText(memberInfo.getUser_mobile());
                holder.tv_text2.setText(memberInfo.getCard_name());
                holder.tv_text3.setText(PriceTransfer.chageMoneyToString((memberInfo.getCard_money())));
                holder.tv_text4.setText(String.valueOf(memberInfo.getCard_integral()));
                break;
        }
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
        TextView tv_text1;
        TextView tv_text2;
        TextView tv_text3;
        TextView tv_text4;

        ViewHolder(View itemView) {
            super(itemView);
            tv_text1 = (TextView) itemView.findViewById(R.id.tv_text1);
            tv_text2 = (TextView) itemView.findViewById(R.id.tv_text2);
            tv_text3 = (TextView) itemView.findViewById(R.id.tv_text3);
            tv_text4 = (TextView) itemView.findViewById(R.id.tv_text4);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        switch (type) {
            case Constants.TYPR_MEMBER_LIST:
                count = memberInfoList.size();
                break;
        }
        return count;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
