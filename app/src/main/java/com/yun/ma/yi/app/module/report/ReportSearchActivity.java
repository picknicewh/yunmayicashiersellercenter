package com.yun.ma.yi.app.module.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.marketing.bargain.BargainGoodsActivity;
import com.yun.ma.yi.app.module.marketing.cut.FullCutActivity;
import com.yun.ma.yi.app.module.marketing.delivery.FullDeliveryActivity;
import com.yun.ma.yi.app.module.report.goods.profit.GoodsProfitReportActivity;
import com.yun.ma.yi.app.module.report.goods.sales.GoodsSalesReportActivity;
import com.yun.ma.yi.app.module.report.goods.trade.GoodsTradeReportActivity;
import com.yun.ma.yi.app.module.report.integral.IntegralReportActivity;
import com.yun.ma.yi.app.module.report.statistics.employee.EmployeeReportActivity;
import com.yun.ma.yi.app.module.report.statistics.inoutwork.InOutWorkListActivity;
import com.yun.ma.yi.app.module.report.statistics.receivables.ReceivablesReportActivity;
import com.yun.ma.yi.app.module.staff.info.StaffInfoEditContract;
import com.yun.ma.yi.app.module.staff.info.StaffInfoEditPresenter;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ys on 2017/6/12.
 * 报表检索条件
 */

public class ReportSearchActivity extends BaseActivity implements StaffInfoEditContract.View {
    /**
     * 选择来源
     */
    @BindView(R.id.source_text)
    TextView sourceText;
    /**
     * 选择开始时间
     */
    @BindView(R.id.start_time_text)
    TextView startTimeText;
    /**
     * 选择结束时间
     */
    @BindView(R.id.end_time_text)
    TextView endTimeText;
    /**
     * 选择类型
     */
    @BindView(R.id.type_text)
    TextView typeText;
    /**
     * 下划线
     */
    @BindView(R.id.type_view)
    View typeView;
    /**
     * 选择类型
     */
    @BindView(R.id.ll_total_type)
    LinearLayout ll_total_type;
    /**
     * 选择类型
     */
    @BindView(R.id.ll_source)
    LinearLayout llSource;
    /**
     * 查询员工条件
     */
    @BindView(R.id.et_condition)
    ClearEditText etCondition;
    /**
     * 员工上下班条件
     */
    @BindView(R.id.ll_condition)
    LinearLayout llCondition;
    /**
     * 员工的标题
     */
    @BindView(R.id.staff_title)
    TextView staffTitle;
    /**
     * 员工内容
     */
    @BindView(R.id.staff_text)
    TextView staffText;
    /**
     * 员工选择
     */
    @BindView(R.id.ll_staff)
    LinearLayout llStaff;
    @BindView(R.id.line_member)
    View lineMember;

    /**
     * 记录当前选中来源的位置
     */
    private int sourcePosition;
    /**
     * 记录当前选中统计的位置
     */
    private int typePosition;
    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();
    /**
     * 来源列表
     */
    private List<String> sourceList;
    /**
     * 类型列表
     */
    private List<String> typeList;
    /**
     * 标记从哪个页面进入此页面
     */
    private String function;
    /**
     * 来源选项
     */
    private OptionsPickerView sourceOptionPickerView;
    /**
     * 开始时间选择
     */
    private TimePickerView startTimePickerView;
    /**
     * 结束时间选择
     */
    private TimePickerView endTimePickerView;
    /**
     * 类型选项
     */
    private OptionsPickerView typeOptionPickerView;
    /**
     * 员工姓名选项
     */
    private OptionsPickerView staffOptionPickerView;
    /**
     * 记录当前选中员工的位置
     */
    private int staffPosition = 0;

    /**
     * 获取制定员工交易流水报表，要获取员工的信息列表
     */
    private StaffInfoEditPresenter presenter;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new StaffInfoEditPresenter(this, this);
        function = getIntent().getStringExtra(Constants.FUNCTION);
        switch (function) {
            case Constants.RECEIVABLES:
                setTitleTextId(R.string.receivables_report);
                break;
            case Constants.EMPLOYEE:
                setTitleTextId(R.string.employee_report);
                llSource.setVisibility(View.GONE);
                break;
            case Constants.GOODS_TRADE:
                setTitleTextId(R.string.goods_trade_report);
                llStaff.setVisibility(View.VISIBLE);
                lineMember.setVisibility(View.VISIBLE);
                presenter.getSubUserList();
                break;
            case Constants.GOODS_SALES:
                setTitleTextId(R.string.goods_sales_report);
                typeView.setVisibility(View.VISIBLE);
                ll_total_type.setVisibility(View.VISIBLE);
                break;
            case Constants.GOODS_PROFIT:
                setTitleTextId(R.string.goods_profit_report);
                break;
            case Constants.STAFF_INOUTWORK:
                setTitleTextId(R.string.staff_in_out_work);
                llCondition.setVisibility(View.VISIBLE);
                llSource.setVisibility(View.GONE);
                break;
            case Constants.MARKING_FULL_CUT:
                setTitleTextId(R.string.full_cut_search);
                llSource.setVisibility(View.GONE);
                break;
            case Constants.MARKING_FULL_DELIVERY:
                setTitleTextId(R.string.full_delivery_search);
                llSource.setVisibility(View.GONE);
                break;
            case Constants.MARKING_BARGAIB_GOODS:
                setTitleTextId(R.string.bargain_goods_search);
                llSource.setVisibility(View.GONE);
                break;
            case Constants.MEMBER_INTEGRAL_REPORT:
                llSource.setVisibility(View.GONE);
                setTitleTextId(R.string.member_integral_report);
                break;

        }
      initOptionData();

    }


    /**
     * 初始化所有的选项
     */
    private void initOptionData() {
        sourceText.setText(getString(R.string.all));
        typeText.setText(getString(R.string.goods_statistics));
        startDate = DateUtil.getNeedTime(0, 0, 0, -7);
        endDate = DateUtil.getNeedTime(23, 59, 59, 0);
        endTimeText.setText(DateUtil.getFormatDate(endDate));    //默认日期
        startTimeText.setText(DateUtil.getFormatDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);//默认日期选中的开始时间
        staffName = new ArrayList<>();
        subUserInfoList = new ArrayList<>();
        sourceList = new ArrayList<>();
        sourceList.add(getString(R.string.all));
        sourceList.add(getString(R.string.physical_stores));
        sourceList.add(getString(R.string.ant_shop));
        typeList = new ArrayList<>();
        typeList.add(getString(R.string.goods_statistics));
        typeList.add(getString(R.string.category_statistics));
        sourceOptionPickerView = DateUtil.getOptionPickerView("选择来源", sourceList, sourcePosition, this, sourceListener);
        startTimePickerView = DateUtil.getDatePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getDatePickerView("结束时间", this, endCalendar, endListener);
        typeOptionPickerView = DateUtil.getOptionPickerView("选择类型", typeList, typePosition, this, typeListener);
    }


    @OnClick(R.id.ll_source)
    public void onSourceLayout() {
        sourceOptionPickerView.show();
    }

    @OnClick(R.id.ll_start_time)
    public void onStartTimeLayout() {
        startTimePickerView.show();
    }

    @OnClick(R.id.ll_end_time)
    public void onEndTimeLayout() {
        endTimePickerView.show();
    }

    @OnClick(R.id.ll_total_type)
    public void onTotalTypeLayout() {
        typeOptionPickerView.show();
    }

    @OnClick(R.id.ll_staff)
    public void onStaffLayout() {
        staffOptionPickerView.show();
    }
    @OnClick(R.id.seach)
    public void onSearch() {
        String startTime = DateUtil.getFormatTimeDate(startDate);
        String endTime = DateUtil.getFormatTimeDate(endDate);
        String source = sourceText.getText().toString();
        String type = typeText.getText().toString();
        Intent intent = new Intent();
        switch (function) {
            case Constants.RECEIVABLES:
                intent.setClass(this, ReceivablesReportActivity.class);
                break;
            case Constants.EMPLOYEE:
                intent.setClass(this, EmployeeReportActivity.class);
                intent.putExtra("source", Constants.REPORT_TRADE_DETAIL);
                break;
            case Constants.GOODS_TRADE:
                intent.setClass(this, GoodsTradeReportActivity.class);
                String create_id = staffPosition==0?"all":String.valueOf(subUserInfoList.get(staffPosition-1).getId());
                intent.putExtra("create_id",create_id);
                intent.putExtra("form", Constants.REPORT_TRADE_DETAIL);
                break;
            case Constants.GOODS_SALES:
                intent.setClass(this, GoodsSalesReportActivity.class);
                intent.putExtra("groupType", type);
                break;
            case Constants.GOODS_PROFIT:
                intent.setClass(this, GoodsProfitReportActivity.class);
                break;
            case Constants.STAFF_INOUTWORK:
                intent.setClass(this, InOutWorkListActivity.class);
                intent.putExtra("keyword", etCondition.getText().toString());
                break;
            case Constants.MARKING_FULL_CUT:
                intent.setClass(this, FullCutActivity.class);
                intent.putExtra(Constants.FUNCTION, Constants.MARKING_FULL_CUT);
                break;
            case Constants.MARKING_FULL_DELIVERY:
                intent.setClass(this, FullDeliveryActivity.class);
                intent.putExtra(Constants.FUNCTION, Constants.MARKING_FULL_DELIVERY);
                break;
            case Constants.MARKING_BARGAIB_GOODS:
                intent.setClass(this, BargainGoodsActivity.class);
                intent.putExtra(Constants.FUNCTION, Constants.MARKING_BARGAIB_GOODS);
                break;
            case Constants.MEMBER_INTEGRAL_REPORT:
                intent.setClass(this, IntegralReportActivity.class);
                intent.putExtra(Constants.FUNCTION, Constants.MEMBER_INTEGRAL_REPORT);
                break;
        }
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        intent.putExtra("source", source);
        startActivity(intent);
    }

    private OptionsPickerView.OnOptionsSelectListener sourceListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            sourcePosition = options1;
            sourceText.setText(sourceList.get(options1));
        }
    };
    private TimePickerView.OnTimeSelectListener startListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            startCalendar.setTime(date);
            startDate = date;
            String mDate = DateUtil.getFormatDate(date);
            startTimeText.setText(mDate);
        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = DateUtil.geteEndDate(date);
            String mDate = DateUtil.getFormatDate(date);
            endTimeText.setText(mDate);
        }
    };
    private OptionsPickerView.OnOptionsSelectListener typeListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            typePosition = options1;
            typeText.setText(typeList.get(options1));
        }
    };
    private OptionsPickerView.OnOptionsSelectListener staffListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            staffPosition = options1;
            staffText.setText(staffName.get(staffPosition));
            //    typeText.setText(typeList.get(options1));
        }
    };


    private List<String> staffName;
    private List<SubUserInfo> subUserInfoList;
    @Override
    public void setSubUserList(List<SubUserInfo> subUserList) {
        subUserInfoList.clear();
        subUserInfoList.addAll(subUserList);
        staffName.clear();
        staffName.add("全部");
        for (int i = 0; i < subUserList.size(); i++) {
            staffName.add(subUserList.get(i).getName());
        }
        staffOptionPickerView = DateUtil.getOptionPickerView("选择员工", staffName, staffPosition, this, staffListener);
    }

}
