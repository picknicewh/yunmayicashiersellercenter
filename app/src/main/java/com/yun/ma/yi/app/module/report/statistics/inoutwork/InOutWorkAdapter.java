package com.yun.ma.yi.app.module.report.statistics.inoutwork;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.InOutWorkInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称:员工报表适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutWorkAdapter extends RecyclerView.Adapter<InOutWorkAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private  List<InOutWorkInfo> inOutWorkInfoList;
    public InOutWorkAdapter(List<InOutWorkInfo> inOutWorkInfoList){
        this.inOutWorkInfoList = inOutWorkInfoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inout_work_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        InOutWorkInfo inOutWorkInfo = inOutWorkInfoList.get(position);
        if (inOutWorkInfo.getSub_user_id()==0){
            holder.tvStaffInfo.setText("店主");
        }else {
            holder.tvStaffInfo.setText(inOutWorkInfo.getSub_name()+"(工号："+inOutWorkInfo.getSub_username()+")");
        }
        String endTime = DateUtil.getChineseDate(inOutWorkInfo.getKnock_off_time());
        String startTime = DateUtil.getChineseDate(inOutWorkInfo.getSign_in_time());
      //  G.log("xxxxxxxxxxxxxxxxx"+"position="+position+"------》"+endTime+"==="+inOutWorkInfo.getKnock_off_datetime());
       // G.log("xxxxxxxxxxxxxxxxx"+"position="+position+"------》"+startTime+"==="+inOutWorkInfo.getSign_in_datetime());
        holder.tvStartTime.setText(startTime+"至");
        holder.tvEndTime.setText(endTime);
        holder.tvRemark.setText(inOutWorkInfo.getVerification_result());
        holder.tvIncome.setText("￥"+PriceTransfer.chageMoneyToString(inOutWorkInfo.getTotal_cash()));
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
        TextView tvStaffInfo;
        TextView tvIncome;
        TextView tvRemark;
        TextView tvStartTime;
        TextView tvEndTime;
        ViewHolder(View itemView) {
            super(itemView);
            tvStaffInfo = (TextView) itemView.findViewById(R.id.tv_staff_info);
            tvIncome = (TextView) itemView.findViewById(R.id.tv_income);
            tvRemark = (TextView) itemView.findViewById(R.id.tv_remark);
            tvStartTime = (TextView) itemView.findViewById(R.id.tv_start_time);
            tvEndTime = (TextView) itemView.findViewById(R.id.tv_end_time);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return inOutWorkInfoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
