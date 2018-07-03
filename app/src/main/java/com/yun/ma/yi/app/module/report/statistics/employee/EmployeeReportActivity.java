package com.yun.ma.yi.app.module.report.statistics.employee;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.EmployeeInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/8/3
 * 名称： 员工业绩报表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EmployeeReportActivity extends BaseActivity implements EmployeeReportContract.View {
    /**
     * 总销售商品数量
     */
    @BindView(R.id.count)
    TextView count;
    /**
     * 总收入
     */
    @BindView(R.id.tv_total_sales)
    TextView tvTotalSales;
    /**
     * 员工列表
     */
    @BindView(R.id.rv_employee)
    RecyclerView rvEmployee;
    /**
     * 没有数据时显示
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private EmployeeReportAdapter adapter;
    /**
     * 数据处理类
     */
    private EmployeeReportPresenter presenter;
    /**
     * 员工信息详情
     */
    private List<EmployeeInfo.EmployeeInfoDetail> employeeInfoDetailList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_employee_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.employee_statistic_report);
        employeeInfoDetailList = new ArrayList<>();
        adapter = new EmployeeReportAdapter(employeeInfoDetailList);
        rvEmployee.setLayoutManager(new LinearLayoutManager(this));
        rvEmployee.setAdapter(adapter);
        presenter = new EmployeeReportPresenter(this, this);
        presenter.getEmployeeReport();
    }

    @Override
    public String getStartTime() {
        return getIntent().getStringExtra("startTime");
    }

    @Override
    public String getEndTime() {
        return getIntent().getStringExtra("endTime");
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        if (employeeInfo!=null){
            tvTotalSales.setText("￥" + PriceTransfer.chageMoneyToString(employeeInfo.getTotalPrice()));
            count.setText(String.valueOf(employeeInfo.getTotalProductNumber()));
            employeeInfoDetailList.clear();
            employeeInfoDetailList.addAll(employeeInfo.getList());
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }else {
            tvTotalSales.setText("￥0" );
            count.setText("0");
            tvNodata.setVisibility(View.VISIBLE);
            rvEmployee.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
