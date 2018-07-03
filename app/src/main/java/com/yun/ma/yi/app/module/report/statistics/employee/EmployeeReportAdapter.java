package com.yun.ma.yi.app.module.report.statistics.employee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.EmployeeInfo;
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
public class EmployeeReportAdapter extends RecyclerView.Adapter<EmployeeReportAdapter.ViewHolder> {
    private List<EmployeeInfo.EmployeeInfoDetail> employeeInfoDetailList;
    public EmployeeReportAdapter(List<EmployeeInfo.EmployeeInfoDetail> employeeInfoDetailList){
      this.employeeInfoDetailList =employeeInfoDetailList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empolyee_report, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        EmployeeInfo.EmployeeInfoDetail employeeInfoDetail = employeeInfoDetailList.get(position);
        holder.tv_employee.setText(employeeInfoDetail.getName());
        holder.tv_goods_count.setText(String.valueOf(employeeInfoDetail.getQuantity()));
        holder.tv_goods_sale.setText(String.valueOf(employeeInfoDetail.getTrade_number()));
        holder.tv_total_income.setText("￥"+PriceTransfer.chageMoneyToString(employeeInfoDetail.getReceived_fee()));
        holder.tv_actual_income.setText("￥"+ PriceTransfer.chageMoneyToString(employeeInfoDetail.getCod()));
        holder.tv_zhifu_income.setText("￥"+ PriceTransfer.chageMoneyToString(employeeInfoDetail.getAlipay()));
        holder.tvw_wechat_income.setText("￥"+ PriceTransfer.chageMoneyToString(employeeInfoDetail.getWxpay()));
        holder.tv_cwechat_income.setText("￥"+ PriceTransfer.chageMoneyToString(employeeInfoDetail.getCod_wxpay()));
        holder.tv_czhifu_income.setText("￥"+ PriceTransfer.chageMoneyToString(employeeInfoDetail.getCod_alipay()));

    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_employee;
        TextView tv_goods_count;
        TextView tv_goods_sale;
        TextView tv_total_income;
        TextView tv_actual_income;
        TextView tv_czhifu_income;
        TextView tv_cwechat_income;
        TextView tv_zhifu_income;
        TextView tvw_wechat_income;
        ViewHolder(View itemView) {
            super(itemView);
            tv_employee = (TextView) itemView.findViewById(R.id.tv_employee);
            tv_goods_count = (TextView) itemView.findViewById(R.id.tv_goods_count);
            tv_goods_sale = (TextView) itemView.findViewById(R.id.tv_goods_sale);
            tv_total_income = (TextView) itemView.findViewById(R.id.tv_total_income);
            tv_actual_income = (TextView) itemView.findViewById(R.id.tv_actual_income);
            tv_zhifu_income = (TextView) itemView.findViewById(R.id.tv_zhifu_income);
            tv_czhifu_income = (TextView) itemView.findViewById(R.id.tv_czhifu_income);
            tv_cwechat_income = (TextView) itemView.findViewById(R.id.tv_cwechat_income);
            tvw_wechat_income = (TextView) itemView.findViewById(R.id.tv_wechat_income);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return employeeInfoDetailList.size();
    }

}
