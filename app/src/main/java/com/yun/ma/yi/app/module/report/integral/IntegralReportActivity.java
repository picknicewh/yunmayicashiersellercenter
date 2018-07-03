package com.yun.ma.yi.app.module.report.integral;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberIntegralInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间： 2017-11-10
 * 名称：连锁积分报表
 */
public class IntegralReportActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<RecyclerView>,IntegralReportContract.View {

    /**
     * 总订单数
     */
    @BindView(R.id.tv_count)
    TextView tvCount;
    /**
     * 总积分
     */
    @BindView(R.id.tv_total_integral)
    TextView tvTotalIntegral;
    /**
     * 积分详情
     */
    @BindView(R.id.integral_list)
    PullToRefreshRecyclerView integralList;
    private RecyclerView rv_integral;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNOdata;
    /**
     * 页码
     */
    private int page=1;
    /**
     * 页数
     */
    private int size=10;
    /**
     *数据处理类
     */
   private IntegralReportPresenter presenter;
    /**
     * 适配器
     */
    private IntegralReportAdapter adapter;
    /**
     * 是否有更多数据
     */
    private boolean isHasMore;
    /**
     * 积分列表
     */
    private List<MemberIntegralInfo.IntegralInfo> integralInfoList;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_integral_report;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_integral_report);
        integralList.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        integralList.setOnRefreshListener( this);
        rv_integral = integralList.getRefreshableView();
        integralInfoList = new ArrayList<>();
        adapter = new IntegralReportAdapter(integralInfoList);
        rv_integral.setLayoutManager(new LinearLayoutManager(this));
        rv_integral.setAdapter(adapter);
        presenter =new IntegralReportPresenter(this,this);
        presenter.subscribe();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {}

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.subscribe();
        if (!isHasMore){
            integralList.setMode(PullToRefreshBase.Mode.DISABLED);
            G.showToast(this,"没有更多数据了！");
        }
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
    public int getPageNo() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void setIntegralInfo(MemberIntegralInfo memberIntegralInfo) {
        tvCount.setText(String.valueOf(memberIntegralInfo.getCount()));
        tvTotalIntegral.setText(String.valueOf(memberIntegralInfo.getTotal_change()));
        isHasMore = memberIntegralInfo.getList().size()==size;
        if (page==1){
            tvNOdata.setVisibility(memberIntegralInfo.getList().size()==0?View.VISIBLE:View.GONE);
            this.integralInfoList.clear();
        }
        this.integralInfoList.addAll(memberIntegralInfo.getList());
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

}
