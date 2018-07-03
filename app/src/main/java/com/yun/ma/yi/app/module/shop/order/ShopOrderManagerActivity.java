package com.yun.ma.yi.app.module.shop.order;

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
import com.yun.ma.yi.app.bean.ShopOrderInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
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
 * 名称：店铺订单管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderManagerActivity extends BaseActivity implements OnItemClickListener, ShopOrderContract.View, PullToRefreshBase.OnRefreshListener2<RecyclerView>, RadioGroup.OnCheckedChangeListener {
    /**
     * 订单列表
     */
    @BindView(R.id.rv_order)
    PullToRefreshRecyclerView prv;
    RecyclerView rvOrder;
    /**
     * 起始时间
     */
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    /**
     * 结束时间
     */
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    @BindView(R.id.rg_step)
    RadioGroup rgStep;
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
     * 下划线5
     */
    @BindView(R.id.line5)
    View line5;
    /**
     * 订单列表适配器
     */
    private ShopOrderManagerAdapter adapter;

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
     * 数据处理
     */
    private ShopOrderPresenter presenter;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 页数
     */
    private int size = 10;
    /**
     * 是否还有更多数据
     */
    private boolean hasMoreData;
    private List<ShopOrderInfo> shopOrderInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_order_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_order_manager);
        shopOrderInfoList = new ArrayList<>();
        adapter = new ShopOrderManagerAdapter(shopOrderInfoList);
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        rvOrder = prv.getRefreshableView();
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        rgStep.setOnCheckedChangeListener(this);
        initTime();
        presenter = new ShopOrderPresenter(this, this);
        presenter.getSellerOrderBySellerId();

    }

    /**
     * 初始化时间
     */
    private void initTime() {
        startDate = DateUtil.getNeedTime(0, 0, 0, -7);
        endDate = DateUtil.getNeedTime(23, 59, 59, 0);
        tvEndTime.setText(DateUtil.getFormatDate(endDate));    //默认日期
        tvStartTime.setText(DateUtil.getFormatDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);//默认日期选中的开始时间
        startTimePickerView = DateUtil.getDatePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getDatePickerView("结束时间", this, endCalendar, endListener);
    }

    private TimePickerView.OnTimeSelectListener startListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            startCalendar.setTime(date);
            startDate = date;
            String mDate = DateUtil.getFormatDate(date);
            tvStartTime.setText(mDate);
            presenter.getSellerOrderBySellerId();
        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = DateUtil.geteEndDate(date);
            String mDate = DateUtil.getFormatDate(date);
            tvEndTime.setText(mDate);
            presenter.getSellerOrderBySellerId();
        }
    };

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, ShopOrderDetailActivity.class);
        intent.putExtra("shopOrderInfo", shopOrderInfoList.get(position));
        startActivityForResult(intent, 1);
    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time})
    public void time(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                startTimePickerView.show();
                break;
            case R.id.tv_end_time:
                endTimePickerView.show();
                break;
        }
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getOrderState() {

        return orderStatus;
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
    public String getToken() {
        return UserMessage.getInstance().getToken();
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
    public void setShopOrderInfoList(List<ShopOrderInfo> shopOrderInfoList) {
        if (page == 1) {
            this.shopOrderInfoList.clear();
            tvNodata.setVisibility(shopOrderInfoList.size() == 0 ? View.VISIBLE : View.GONE);
        }

        hasMoreData = shopOrderInfoList.size() == size;
        this.shopOrderInfoList.addAll(shopOrderInfoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page=1;
        prv.onRefreshComplete();
        presenter.getSellerOrderBySellerId();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        prv.onRefreshComplete();
        presenter.getSellerOrderBySellerId();
        if (!hasMoreData) {
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            showMessage("没有更多数据！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            page = 1;
            presenter.getSellerOrderBySellerId();
        }
    }

    private int orderStatus = 2;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        line1.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line2.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line3.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line4.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        line5.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        page = 1;
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        switch (checkedId) {
            case R.id.rb_step1:
                orderStatus = 2;
                line1.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step2:
                orderStatus = 3;
                line2.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step3:
                orderStatus = 4;
                line3.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step4:
                orderStatus = 5;
                line4.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
            case R.id.rb_step5:
                orderStatus = 6;
                line5.setBackgroundColor(ContextCompat.getColor(this,R.color.red_btn));
                break;
        }
        presenter.getSellerOrderBySellerId();
    }

}
