package com.yun.ma.yi.app.module.inoutstock.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InOutStockSearchActivity extends BaseActivity implements TimePickerView.OnTimeSelectListener, OptionsPickerView.OnOptionsSelectListener {
    /**
     * 开始时间
     */
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    /**
     * 结束时间
     */
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    /**
     * 查询方式
     */
    @BindView(R.id.tv_search_way)
    TextView tvSearchWay;
    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();
    /**
     * 选项列表
     */
    private List<String> optionsList;
    /**
     * 记录当前选中选项的位置
     */
    private int position;
    /**
     * 提交查询方式
     */
    private int searchWay;
    /**
     * 起始时间选择
     */
    private TimePickerView timePickerViewStart;
    /**
     * 结束时间选择
     */
    private TimePickerView timePickerViewEnd;
    /**
     * 选项选择
     */
    private OptionsPickerView optionsPickerView;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_out_stock_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.inout_stock_search);
        initOptionsList();
        //默认日期
        tvEndTime.setText(DateUtil.getFormatDate(new Date()));
        tvStartTime.setText(DateUtil.getFormatDateBetweenDays(new Date(), 7));
        startCalendar.setTime(DateUtil.getDateBetweenDays(new Date(), 7));
        timePickerViewStart =  DateUtil.getDatePickerView("开始时间", this, startCalendar,this);
        timePickerViewEnd=    DateUtil.getDatePickerView("结束时间", this, endCalendar,onTimeSelectListener);
        optionsPickerView =    DateUtil.getOptionPickerView("选择状态", optionsList, position, this,this);
    }
    @OnClick(R.id.ll_start_time)
    public void startTime() {
       timePickerViewStart.show();
    }

    @OnClick(R.id.ll_end_time)
    public void endTime() {
        timePickerViewEnd.show();

    }

    private void initOptionsList() {
        optionsList = new ArrayList<>();
        optionsList.add("按入库查询");
        optionsList.add("按出库查询");
    }

    @OnClick(R.id.tv_search)
    public void search() {
        Intent intent = new Intent(this, InoutStockDetailActivity.class);
        intent.putExtra("startTime", tvStartTime.getText().toString());
        intent.putExtra("endTime", tvEndTime.getText().toString());
        intent.putExtra("keyWord", tvSearchWay.getText().toString());
        intent.putExtra("type",searchWay);
        startActivity(intent);
    }
    @OnClick(R.id.ll_search_way)
    public void source() {
        optionsPickerView.show();
    }

    @Override
    public void onTimeSelect(Date date, View v) {
        startCalendar.setTime(date);
        String mDate = DateUtil.getFormatDate(date);
        tvStartTime.setText(mDate);
    }
    private TimePickerView.OnTimeSelectListener onTimeSelectListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            String mDate = DateUtil.getFormatDate(date);
            tvEndTime.setText(mDate);
        }
    };

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        position = options1;
        tvSearchWay.setText(optionsList.get(options1));
        if (options1==0){
            //2(收货入库)
            searchWay=2;
        }else if (options1==1){
            //4(退货出库)
            searchWay= 4;
        }
    }

}
