package com.yun.ma.yi.app.module.stock.search;

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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseScanActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.StockSearchInfo;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：库存查询界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockSearchActivity extends BaseScanActivity implements StockSearchContract.View, TextWatcher, PullToRefreshBase.OnRefreshListener2<RecyclerView>, OnItemClickListener {
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 分类导航栏
     */
    @BindView(R.id.nav_view_sort)
    NavigationView navViewSort;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    /**
     * 搜索编辑框
     */
    @BindView(R.id.et_code_search)
    ClearEditText etCodeSearch;
    /**
     * 库存总数
     */
    @BindView(R.id.tv_total_count)
    TextView tvTotalCount;
    /**
     * 库存总金额
     */
    @BindView(R.id.tv_total_sale)
    TextView tvTotalSale;
    /**
     * 没有查询到任何数据
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
    private StockSearchAdapter adapter;
    /**
     * 列表
     */
    private RecyclerView recyclerView;
    /**
     * 数据处理类
     */
    private StockSearchPresenter presenter;
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
    private int category_Id = 0;
    /**
     * 查询数据列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 分类列表
     */
    private List<GoodsListInfo> categoryInfos;
    private RecyclerView.LayoutManager mLayoutRightManager;
    /**
     * 右边侧滑菜单分类选择
     */
    private RecyclerView navListSort;
    /**
     * 右边侧滑菜单适配器
     */
    private CategoryNavItemAdapter categoryNavItemAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_stock_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitleTextId(R.string.goods_stock_statistics);
        categoryInfos = YunmayiApplication.getCategoryInfos();
        initDrawerRightLayout();
        goodsDetailInfoList = new ArrayList<>();
        adapter = new StockSearchAdapter(this, goodsDetailInfoList);
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView = pullRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        pullRecyclerView.setOnRefreshListener(this);
        presenter = new StockSearchPresenter(StockSearchActivity.this, this);
        etCodeSearch.addTextChangedListener(this);
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
/*  @OnClick(R.id.tv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQUEST_STOCK_SEARCH);
    }*/



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


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_STOCK_SEARCH && data != null) {
            //扫码返回
            Bundle bundle = data.getExtras();
            String scanCode = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
            etCodeSearch.setText(scanCode);
        }
    }*/

    @Override
    public void setStockSearchInfo(StockSearchInfo stockSearchInfo) {
        tvNodata.setVisibility(View.GONE);
        hasData = stockSearchInfo.getList().size() == size;
        if (page == 1) {
            this.goodsDetailInfoList.clear();
            tvTotalCount.setText(stockSearchInfo.getTotal().getSum_stock());
            tvTotalSale.setText(PriceTransfer.chageMoneyToString(Double.valueOf(stockSearchInfo.getTotal().getSum_price())));
        }
        this.goodsDetailInfoList.addAll(stockSearchInfo.getList());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setNoData() {
        tvNodata.setVisibility(View.VISIBLE);
        tvTotalCount.setText("0");
        tvTotalSale.setText("0");
    }

    @Override
    public int getCategoryId() {
        return category_Id;
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        page = 1;
        category_Id = 0;
        if (!s.toString().contains("'")) {
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

    private boolean hasData = true;

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

    @Override
    public void onClick(View view, int position) {
        if (categoryNavItemAdapter != null) {
            categoryNavItemAdapter.initData(position);
        }
        GoodsListInfo categoryInfo = categoryInfos.get(position);
        this.category_Id = categoryInfo.getCategory_id();
        tvSort.setText(categoryInfo.getCategory_name());
        page = 1;
        presenter.searchStock();
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
