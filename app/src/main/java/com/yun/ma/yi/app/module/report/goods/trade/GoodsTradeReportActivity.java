package com.yun.ma.yi.app.module.report.goods.trade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsTradeInfo;
import com.yun.ma.yi.app.bean.GoodsTradeInfoBo;
import com.yun.ma.yi.app.bean.MemberTradeInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemObjClickListener;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： ys
 * 时间：  2017/6/13.
 * 名称：商品交易流水报表/会员交易详情
 * 版本说明：由于会员交易详情交易详情的页面和这个页面一致，公用一个界面
 * 附加注释：传递from字段判断来源
 * 主要接口：1获取商品交易流水报表 2 会员交易详情
 */
public class GoodsTradeReportActivity extends BaseActivity implements GoodsTradeReportContract.View, OnItemObjClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    /**
     * 合计
     */
    @BindView(R.id.count)
    TextView count;
    /**
     * 销售总量
     */
    @BindView(R.id.total_sales)
    TextView totalSales;
    /**
     * RecyclerView下拉刷新控件
     */
    @BindView(R.id.goods_trade_list)
    PullToRefreshRecyclerView goodsTradeList;
    @BindView(R.id.ll_total)
    LinearLayout llTotal;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNOdata;
    /**
     * 列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 适配器
     */
    private GoodsTradeReportAdapter goodsTradeReportAdapter;
    /**
     * 商品交易列表
     */
    private List<GoodsTradeInfo> goodsTradeInfos;
    /**
     * 页数
     */
    private int page = 1;
    /**
     * 交易开始时间
     */
    private String startTime;
    /**
     * 交易结束时间
     */
    private String endTime;
    /**
     * 交易来源
     */
    private String source;
    /**
     * 数据处理
     */
    private GoodsTradeReportPresenter presenter;
    /**
     * 来自哪个页面标记
     */
    private int form;
    /**
     * 会员交易列表
     */
    private List<MemberTradeInfo> memberTradeInfoList;
    /**
     * 适配器
     */
    private MemberTradeReportAdapter adapter;
    /**
     * 员工id
     */
    private String create_id;
    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        source = intent.getStringExtra("source");
        create_id = intent.getStringExtra("create_id");
        form = intent.getIntExtra("form", Constants.REPORT_TRADE_DETAIL);
        presenter = new GoodsTradeReportPresenter(this, this);
        recyclerView = goodsTradeList.getRefreshableView();
        goodsTradeList.setOnRefreshListener(this);
        goodsTradeList.setRefreshing(false);
        initFrom(form);
    }

    private void initFrom(int form) {
        switch (form) {
            case Constants.REPORT_TRADE_DETAIL:
                setTitleTextId(R.string.goods_trade_report);
                goodsTradeInfos = new ArrayList<>();
                llTotal.setVisibility(View.VISIBLE);
                goodsTradeReportAdapter = new GoodsTradeReportAdapter(this, goodsTradeInfos, R.layout.goods_trade_item);
                goodsTradeReportAdapter.setClickListener(this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(goodsTradeReportAdapter);

                break;
            case Constants.MEMBER_TRADE_DETAIL:
                setTitleTextId(R.string.trade_detail);
                llTotal.setVisibility(View.GONE);
                memberTradeInfoList = new ArrayList<>();
                adapter = new MemberTradeReportAdapter(this, memberTradeInfoList, R.layout.goods_trade_item);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(this);
                break;
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_trade;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public int getPageNo() {
        return page;
    }

    @Override
    public int getPageSize() {
        return 15;
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getBuyerId() {
        return getIntent().getIntExtra("user_id",-1);
    }

    @Override
    public String getCreateId() {
        return  create_id;
    }

    @Override
    public void getGoodsTradeReportInfo(GoodsTradeInfoBo goodsTradeInfoBo) {
        if (goodsTradeInfoBo != null) {
            List<GoodsTradeInfo> infos = goodsTradeInfoBo.getList();
            count.setText(" " + String.valueOf(goodsTradeInfoBo.getCount()) + " ");
            totalSales.setText(" ¥ " + PriceTransfer.chageMoneyToString(goodsTradeInfoBo.getReceived()));
            if (infos != null) {

                if (infos.size() == 0) {
                    if (page==1){
                        tvNOdata.setVisibility(infos.size()==0?View.VISIBLE:View.GONE);
                    }
                    goodsTradeList.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                } else {
                    if (page == 1) {
                        goodsTradeInfos.clear();
                    }
                    goodsTradeInfos.addAll(infos);
                    goodsTradeReportAdapter.notifyDataSetChanged();
                }
            }
        }
        goodsTradeList.onRefreshComplete();
    }

    private boolean hasMoreData = true;

    @Override
    public void setMemberTradeInfoList(List<MemberTradeInfo> memberTradeInfoList) {
        if (page == 1) {
            this.memberTradeInfoList.clear();
        }
        hasMoreData = memberTradeInfoList.size() == page;
        this.memberTradeInfoList.addAll(memberTradeInfoList);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, Object oj) {
        Intent intent = new Intent(this, GoodsTradeDetailActivity.class);
        switch (form) {
            case Constants.REPORT_TRADE_DETAIL:
                GoodsTradeInfo goodsTradeInfo = (GoodsTradeInfo) oj;
                intent.putExtra("tradeId", goodsTradeInfo.getTrade_id());
                break;
            case Constants.MEMBER_TRADE_DETAIL:
                MemberTradeInfo memberTradeInfo  = (MemberTradeInfo) oj;
                intent.putExtra("tradeId",memberTradeInfo.getId());
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        goodsTradeList.setMode(PullToRefreshBase.Mode.BOTH);
        switch (form) {
            case Constants.REPORT_TRADE_DETAIL:
                presenter.getGoodsTradeReportInfo();
                break;
            case Constants.MEMBER_TRADE_DETAIL:
                presenter.getTradeList();
                break;
        }
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        switch (form) {
            case Constants.REPORT_TRADE_DETAIL:
                presenter.getGoodsTradeReportInfo();
                break;
            case Constants.MEMBER_TRADE_DETAIL:
                presenter.getTradeList();
                if (!hasMoreData) {
                    G.showToast(this, "没有更多数据了！");
                    goodsTradeList.setMode(PullToRefreshBase.Mode.DISABLED);
                }
                break;
        }
        refreshView.onRefreshComplete();
    }
}
