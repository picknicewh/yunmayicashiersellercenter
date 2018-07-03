package com.yun.ma.yi.app.module.shop.balance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.BalanceAccountInfo;
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
 * 时间：  2017/12/12
 * 名称：余额对账明细
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountListActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<RecyclerView>,BalanceAccountContract.View {

   /**
     * 余额对账明细列表
   **/
    @BindView(R.id.prv)
    PullToRefreshRecyclerView prv;
    RecyclerView recyclerView;
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
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private BalanceAccountListAdapter adapter;
    /**
     * 是否有更多数据
     */
    private boolean hasMoreData=true;
    /**
     * 页码
     */
    private int page=1;
    /**
     * 页数
     */
    private int size=10;
    /**
     * 数据处理类
     */
    private BalanceAccountPresenter presenter;
    /**
     * 数据列表
     */
    private List<BalanceAccountInfo.BalanceDetailInfo>  balanceDetailInfoList;
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
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_balance_account_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_check_account_detail);
        initTime();
        balanceDetailInfoList = new ArrayList<>();
        adapter = new BalanceAccountListAdapter(balanceDetailInfoList);
        recyclerView = prv.getRefreshableView();
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter= new BalanceAccountPresenter(this,this);
        presenter.getBalanceAccountList();
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
            presenter.getBalanceAccountList();
        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = DateUtil.geteEndDate(date);
            String mDate = DateUtil.getFormatDate(date);
            tvEndTime.setText(mDate);
            presenter.getBalanceAccountList();
        }
    };
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
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page=1;
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        refreshView.onRefreshComplete();
        presenter.getBalanceAccountList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        refreshView.onRefreshComplete();
        presenter.getBalanceAccountList();
        if (hasMoreData){
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            showMessage("没有更多数据了！");
        }
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
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
    public void setBalanceDetailList(List<BalanceAccountInfo.BalanceDetailInfo> balanceDetailList) {
        if (page==1){
            this.balanceDetailInfoList.clear();
            tvNodata.setVisibility(balanceDetailList.size() == 0 ? View.VISIBLE : View.GONE);
        }
        this.balanceDetailInfoList.addAll(balanceDetailList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }


}
