package com.yun.ma.yi.app.module.stock.add;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseScanActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.StockChangeInfo;
import com.yun.ma.yi.app.bean.StockSearchInfo;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.stock.search.CategoryNavItemAdapter;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 新增盘点
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AddStatisticsActivity extends BaseScanActivity implements AddStatisticContract.View, TextWatcher, PullToRefreshBase.OnRefreshListener2<RecyclerView>, AddStatisticAdapter.StockInfoChangeListener, OnItemClickListener {

    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 侧滑分类选择菜单
     */
    @BindView(R.id.nav_view_sort)
    NavigationView navViewSort;
    /**
     * 抽屉布局
     */
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
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
    /**
     * 分类
     */
    @BindView(R.id.tv_search)
    TextView tvSort;

    @BindView(R.id.tv_scan)
    TextView tvScan;
    /**
     * 适配器
     */
    private AddStatisticAdapter adapter;
    private RecyclerView recyclerView;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 一页条目
     */
    private int size = 10;
    /**
     * 类目id
     */
    private int categoryId = 0;

    /**
     * 查询数据列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 数据处理类
     */
    private AddStatisticPresenter presenter;
    /**
     * 是否还有数据
     */
    private boolean hasData = true;
    /**
     * 分类列表
     */
    private List<GoodsListInfo> categoryInfos;
    /**
     * 列表管理
     */
    private RecyclerView.LayoutManager mLayoutRightManager;
    /**
     * 分类列表控件
     */
    private RecyclerView navListSort;
    /**
     *分类适配器
     */
    private CategoryNavItemAdapter categoryNavItemAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_add_statistics;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitleTextId(R.string.goods_stock_add_statistics);
        categoryInfos = YunmayiApplication.getCategoryInfos();
        initDrawerRightLayout();
        etCodeSearch.addTextChangedListener(this);
        pullRecyclerView.setOnRefreshListener(this);
        goodsDetailInfoList = new ArrayList<>();
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new AddStatisticAdapter(this, goodsDetailInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setInfoChangeListener(this);
        presenter = new AddStatisticPresenter(AddStatisticsActivity.this, this);
        presenter.searchStock();
    }

    @Override
    public void initScanData() {
         setEtCodeSearch(etCodeSearch);
        tvSort.setVisibility(View.VISIBLE);
        tvSort.setText("分类");
        tvScan.setOnClickListener(this);
        tvSort.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==R.id.tv_search){
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }
    }


    /**
     * 右边分类侧滑
     */
    private void initDrawerRightLayout() {
        navViewSort.inflateHeaderView(R.layout.sort_nav);
        View headerViewRight = navViewSort.getHeaderView(0);
        navListSort = (RecyclerView) headerViewRight.findViewById(R.id.nav_list_sort);
        mLayoutRightManager = new LinearLayoutManager(this);
        //2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列
        navListSort.setLayoutManager(mLayoutRightManager);
        navListSort.setHasFixedSize(true);
        categoryNavItemAdapter = new CategoryNavItemAdapter(this, categoryInfos);
        //3 设置数据适配器
        navListSort.setAdapter(categoryNavItemAdapter);
        categoryNavItemAdapter.initData(0);
        categoryNavItemAdapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.tv_delete)
    public void delete() {
        adapter.initData();
    }

    @OnClick(R.id.tv_conform_adjust)
    public void conformAdjust() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STOCK_ADD_STATISTIC)){
            presenter.insertStock();
        }else {
            showMessage("你没有新增库存盘点的权限哦！");
        }
    }

    @Override
    public void setStockSearchInfo(StockSearchInfo stockSearchInfo) {
        tvNodata.setVisibility(View.GONE);
        hasData = stockSearchInfo.getList().size() == size;
        if (page == 1) {
            this.goodsDetailInfoList.clear();
        }
        this.goodsDetailInfoList.addAll(stockSearchInfo.getList());
        if (adapter != null) {
            adapter.initData();
        }
    }

    @Override
    public void setNoData() {
        tvNodata.setVisibility(View.VISIBLE);
    }

    @Override
    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String getKeyword() {
        String keyWord  =  etCodeSearch.getText().toString();
        if (keyWord.contains("\r")){
            keyWord = keyWord.replaceAll("\\r","");
        }
        return keyWord;
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        page = 1;
        this.categoryId = 0;
        if (!s.toString().contains("'")){
            presenter.searchStock();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        presenter.searchStock();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.searchStock();
        refreshView.onRefreshComplete();
        if (!hasData) {
            pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            G.showToast(this, "没有更多数据了");
        }
    }

    private String info;

    @Override
    public void onStockInfoChange(List<StockChangeInfo> stockChangeInfoList) {
        Gson gson = new Gson();
        info = gson.toJson(stockChangeInfoList);
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void onClick(View view, int position) {
        if (adapter!=null){
            categoryNavItemAdapter.initData(position);
        }
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        GoodsListInfo categoryInfo =categoryInfos.get(position);
        this.categoryId = categoryInfo.getCategory_id();
        tvSort.setText(categoryInfo.getCategory_name());
        page = 1;
        presenter.searchStock();
        drawerLayout.closeDrawer(GravityCompat.END);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
