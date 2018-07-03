package com.yun.ma.yi.app.module.report.integral;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.MemberIntegralInfo;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称： 连锁积分适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class IntegralReportAdapter extends RecyclerView.Adapter<IntegralReportAdapter.ViewHolder>{

    private List<MemberIntegralInfo.IntegralInfo> integralInfoList;
    public IntegralReportAdapter(List<MemberIntegralInfo.IntegralInfo> integralInfoList){
        this.integralInfoList =integralInfoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integral_report,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MemberIntegralInfo.IntegralInfo integralInfo = integralInfoList.get(position);
        holder.tv_create_time.setText(integralInfo.getCreate_datetime());
        holder.tv_integral.setText(String.valueOf(integralInfo.getChange_card_integral()));
        holder.tv_trade_number.setText(integralInfo.getTrade_id());
    }
    class  ViewHolder  extends RecyclerView.ViewHolder{
        TextView tv_trade_number ;
        TextView tv_integral;
        TextView tv_create_time ;
        ViewHolder(View itemView) {
            super(itemView);
            tv_trade_number = (TextView)itemView.findViewById(R.id.tv_trade_number);
            tv_integral = (TextView)itemView.findViewById(R.id.tv_integral);
            tv_create_time = (TextView)itemView.findViewById(R.id.tv_create_time);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return integralInfoList.size();
    }
}
