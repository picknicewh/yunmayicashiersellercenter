package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：库存盘点查询界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockStatisticSearchActivity extends BaseActivity {
    /**
     * 状态
     */
    @BindView(R.id.et_condition)
    EditText et_condition;
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
    @BindView(R.id.tv_status)
    TextView tvStatus;
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
     * 提交状态
     */
    private int status;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_stock_statistic_search;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_stock_inventory_list);
        //默认日期
        tvEndTime.setText(DateUtil.getFormatDate(new Date()));
        tvStartTime.setText(DateUtil.getFormatDateBetweenDays(new Date(), 7));
        startCalendar.setTime(DateUtil.getDateBetweenDays(new Date(), 7));
        initOptionsList();
    }
    @OnClick(R.id.ll_start_time)
    public void startTime() {
        DateUtil.getDatePickerView("开始时间", this, startCalendar, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startCalendar.setTime(date);
                String mDate = DateUtil.getFormatDate(date);
                tvStartTime.setText(mDate);
            }
        }).show();
    }

    @OnClick(R.id.ll_end_time)
    public void endTime() {
        DateUtil.getDatePickerView("结束时间", this, endCalendar, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                endCalendar.setTime(date);
                String mDate = DateUtil.getFormatDate(date);
                tvEndTime.setText(mDate);
            }
        }).show();
    }

    private void initOptionsList() {
        optionsList = new ArrayList<>();
        optionsList.add("全部");
        optionsList.add("未提交");
        optionsList.add("已提交");
    }

    @OnClick(R.id.tv_search)
    public void search() {
        Intent intent = new Intent(this, RecordStatisticListActivity.class);
        intent.putExtra("startTime", tvStartTime.getText().toString());
        intent.putExtra("endTime", tvEndTime.getText().toString());
        intent.putExtra("keyWord", et_condition.getText().toString());
        intent.putExtra("status",status);
        startActivity(intent);
    }

    @OnClick(R.id.ll_source)
    public void source() {
        DateUtil.getOptionPickerView("选择状态", optionsList, position, this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                position = options1;
                tvStatus.setText(optionsList.get(options1));
                switch (position){
                    case 0:status = 0;break;
                    case 1:status = 1;break;
                    case 2:status = 99;break;
                }
            }
        }).show();
    }

}
