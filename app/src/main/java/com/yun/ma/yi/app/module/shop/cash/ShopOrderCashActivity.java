package com.yun.ma.yi.app.module.shop.cash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ShopCashInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
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
 * 时间：  2017/11/20
 * 名称：蚂蚁小店提现申请
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderCashActivity extends BaseActivity implements OnItemClickListener, View.OnClickListener, ShopCashContract.View ,PullToRefreshBase.OnRefreshListener2<RecyclerView>, RadioGroup.OnCheckedChangeListener {
    /**
     * 开始时间
     */
    @BindView(R.id.tv_order_start_time)
    TextView tvOrderStartTime;
    /**
     * 结束时间
     */
    @BindView(R.id.tv_order_end_time)
    TextView tvOrderEndTime;

    /**
     * 提现列表
     */
    @BindView(R.id.rv_order)
    PullToRefreshRecyclerView prv;
    RecyclerView rvOrder;
    /**
     * 搜索条件
     */
    @BindView(R.id.rg_step)
    RadioGroup rgStep;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 下划线1
     */
    @BindView(R.id.line1)
    View line1;
    /**
     * 下划线2
     */
    @BindView(R.id.line2)
    View line2;
    /**
     * 下划线3
     */
    @BindView(R.id.line3)
    View line3;
    /**
     * 下划线4
     */
    @BindView(R.id.line4)
    View line4;

    /**
     * 适配器
     */
    private ShopOrderCashAdapter adapter;

    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();
    /**
     * 开始时间选择
     */
    private TimePickerView startTimePickerView;
    /**
     * 结束时间选择
     */
    private TimePickerView endTimePickerView;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 页码
     */
    private int page=1;
    /**
     * 页数
     */
    private int size = 10;
    /**
     * 是否还有更多数据
     */
    private boolean hasMoreData;
    /**
     * 数据处理类
     */
    private ShopCashPresenter presenter;
    /**
     * 数据列表
     */
    private List<ShopCashInfo.ShopCashDetailInfo> shopCashDetailInfoList;
    /**
     * 提现状态
     */
    private int status=0;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_order_cash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_order_cash);
        setSubTitleText("新增");
        setSubtitleClickListener(this);
        rgStep.setOnCheckedChangeListener(this);
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        rvOrder = prv.getRefreshableView();
        shopCashDetailInfoList = new ArrayList<>();
        adapter = new ShopOrderCashAdapter(shopCashDetailInfoList);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        initTime();
        presenter = new ShopCashPresenter(this,this);
        presenter.drawBalanceList();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        line1.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line2.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line3.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line4.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        // status:0全部 1待审核 2提现失败 99提现成功
        page=1;
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        switch (checkedId) {
            case R.id.rb_step1:
                status  =0;
                line1.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step2:
                status  = 1;
                line2.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step3:
                status  = 2;
                line3.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step4:
                status = 99;
                line4.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
        }
        presenter.drawBalanceList();
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, ShopOrderCashDetailActivity.class);
        intent.putExtra("shopCashDetailInfo",shopCashDetailInfoList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.subtitle_text) {
            Intent intent = new Intent(this, AddOrderCashActivity.class);
            intent.putExtra("shopId",getIntent().getIntExtra("shopId",0));
            intent.putExtra("balance",getIntent().getDoubleExtra("balance",0));
            startActivity(intent);
        }
    }

    /**
     * 初始化时间
     */
    private void initTime() {
        startDate = DateUtil.getNeedTime(0, 0, 0, -7);
        endDate = DateUtil.getNeedTime(23, 59, 59, 0);
        tvOrderEndTime .setText(DateUtil.getFormatDate(endDate));    //默认日期
        tvOrderStartTime.setText(DateUtil.getFormatDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);//默认日期选中的开始时间
        startTimePickerView = DateUtil.getDatePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getDatePickerView("结束时间", this, endCalendar, endListener);
    }
    @OnClick({R.id.tv_order_start_time, R.id.tv_order_end_time})
    public void time(View view) {
        switch (view.getId()) {
            case R.id.tv_order_start_time:
                startTimePickerView.show();
                break;
            case R.id.tv_order_end_time:
                endTimePickerView.show();
                break;
        }
    }

    private TimePickerView.OnTimeSelectListener startListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            startCalendar.setTime(date);
            startDate = date;
            String mDate = DateUtil.getFormatDate(date);
            tvOrderStartTime.setText(mDate);
            presenter.drawBalanceList();
        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = DateUtil.geteEndDate(date);
            String mDate = DateUtil.getFormatDate(date);
            tvOrderEndTime.setText(mDate);
            presenter.drawBalanceList();
        }
    };


    @Override
    public int getState() {
        return status;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getStartTime() {
        String startTime = DateUtil.getFormatTimeDate(startDate);
        return startTime;
    }

    @Override
    public String getEndTime() {
        String endTime = DateUtil.getFormatTimeDate(endDate);
        return endTime;
    }

    @Override
    public void setShopCashInfo(ShopCashInfo shopCashInfo) {
        if (page==1){
            shopCashDetailInfoList.clear();
            tvNodata.setVisibility(shopCashInfo.getList().size() == 0 ? View.VISIBLE : View.GONE);
        }
        hasMoreData =shopCashInfo.getList().size()==size;
        shopCashDetailInfoList.addAll(shopCashInfo.getList());
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page=1;
        prv.onRefreshComplete();
        presenter.drawBalanceList();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        prv.onRefreshComplete();
        presenter.drawBalanceList();
        if (!hasMoreData){
            showMessage("没有更多数据");
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
    }
}
