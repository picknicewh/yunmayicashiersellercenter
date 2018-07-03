package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseScanActivity;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 商品盘点记录
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticListActivity extends BaseScanActivity implements OnItemClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView>, RecordStatisticContract.View, TextWatcher {
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 搜索编辑框
     */
    @BindView(R.id.et_code_search)
    ClearEditText etCodeSearch;
    /**
     * 没有数据时显示的ui
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    @BindView(R.id.tv_scan)
    TextView tvScan;
    /**
     * 适配器
     */
    private RecordStatisticListAdapter adapter;
    private RecyclerView recyclerView;
    /**
     * 数据处理
     */
    private RecordStatisticPresenter presenter;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 一页条目
     */
    private int size = 10;
    /**
     * 意图
     */
    private Intent intent;
    /**
     * 是否还有更多数据
     */
    private boolean hasMoreData = false;
    /**
     * 数据列表
     */
    private List<StockStatisticInfo> stockStatisticInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_record_statisitic_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitleTextId(R.string.goods_statistics_record_adjust);
        intent = getIntent();
        stockStatisticInfoList = new ArrayList<>();
        etCodeSearch.setText(intent.getStringExtra("keyWord"));
        etCodeSearch.addTextChangedListener(this);
        adapter = new RecordStatisticListAdapter(this, stockStatisticInfoList);
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        pullRecyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new RecordStatisticPresenter(RecordStatisticListActivity.this, this);
        presenter.statisticSearchStock();
    }
    @Override
    public void initScanData() {
        setEtCodeSearch(etCodeSearch);
        tvScan.setOnClickListener(this);
    }
    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, RecordStatisticItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("stockStatisticInfo", stockStatisticInfoList.get(position));
        intent.putExtras(bundle);
        intent.putExtra("startTime", getStartTime());
        intent.putExtra("endTime", getEndTime());
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        presenter.statisticSearchStock();
        refreshView.onRefreshComplete();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.statisticSearchStock();
        refreshView.onRefreshComplete();
        if (!hasMoreData) {
            pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            showMessage("没有更多数据了");
        }
    }

    @Override
    public void setStockStatisticInfoList(List<StockStatisticInfo> stockStatisticInfoList) {
        if (page == 1) {
            this.stockStatisticInfoList.clear();
        }
        hasMoreData = stockStatisticInfoList.size() == size;
        this.stockStatisticInfoList.addAll(stockStatisticInfoList);
        tvNodata.setVisibility(this.stockStatisticInfoList.size() == 0 ? View.VISIBLE : View.GONE);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public String getKeyword() {
        return etCodeSearch.getText().toString();
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
        return intent.getStringExtra("startTime");
    }

    @Override
    public String getEndTime() {
        return intent.getStringExtra("endTime");
    }

    @Override
    public int getStatus() {
        return intent.getIntExtra("status", 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        page = 1;
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        presenter.statisticSearchStock();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
