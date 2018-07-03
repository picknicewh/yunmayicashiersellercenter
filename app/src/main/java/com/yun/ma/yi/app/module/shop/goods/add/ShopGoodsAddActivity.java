package com.yun.ma.yi.app.module.shop.goods.add;

import android.content.Intent;
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
import com.yun.ma.yi.app.base.BaseScanActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.shop.goods.ShopGoodsEditContract;
import com.yun.ma.yi.app.module.shop.goods.ShopGoodsEditPresenter;
import com.yun.ma.yi.app.module.stock.search.CategoryNavItemAdapter;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopGoodsAddActivity extends BaseScanActivity implements ShopGoodsEditContract.ViewAdd, PullToRefreshBase.OnRefreshListener2<RecyclerView>, View.OnClickListener, OnItemClickListener, TextWatcher {
    /**
     * 条形码/商品名称/速记码
     */
    @BindView(R.id.et_code_search)
    ClearEditText etCodeSearch;
    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 侧滑分类选择菜单
     */
    @BindView(R.id.nav_view_sort)
    NavigationView navViewSort;
    /**
     * 商品列表
     */
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 全选/全不选
     */
    @BindView(R.id.tv_select)
    TextView tvSelect;
    private RecyclerView recyclerView;
    /**
     * 分类
     */
    @BindView(R.id.tv_search)
    TextView tvSort;

    @BindView(R.id.tv_scan)
    TextView tvScan;
    /**
     * 抽屉布局
     */
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    /**
     * 适配器
     */
    private ShopGoodsAddAdapter adapter;
    /**
     * 数据处理类
     */
    private ShopGoodsEditPresenter presenter;
    /**
     * 商品列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 页数
     */
    private int size = 10;
    /**
     * 是否有更多数据
     */
    private boolean hasMoreData = true;
    /**
     * 分类适配器
     */
    private CategoryNavItemAdapter categoryNavItemAdapter;
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
     * 分类id
     */
    private int shopCatId = -1;
    /**
     * 是否全选
     */
    private boolean isSelectAll = false;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_goods_add;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitleTextId(R.string.shop_add_goods);
        setSubTitleText("确定");
        setSubtitleClickListener(this);
        goodsDetailInfoList = new ArrayList<>();
        etCodeSearch.addTextChangedListener(this);
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullRecyclerView.setOnRefreshListener(this);
        adapter = new ShopGoodsAddAdapter(goodsDetailInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        categoryInfos = new ArrayList<>();
        initDrawerRightLayout();
        presenter = new ShopGoodsEditPresenter(this, this);
        presenter.getCashierItemList();
        showProgress();

    }

    @Override
    public void initScanData() {
        setEtCodeSearch(etCodeSearch);
        tvSort.setVisibility(View.VISIBLE);
        tvSort.setText("分类");
        tvScan.setOnClickListener(this);
        tvSort.setOnClickListener(this);
    }

    @OnClick(R.id.tv_select)
    public void select() {
        if (isSelectAll) {
            isSelectAll = false;
            tvSelect.setText("全选");
            adapter.initData(isSelectAll);
        } else {
            isSelectAll = true;
            tvSelect.setText("全不选");
            adapter.initData(isSelectAll);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_search) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        } else if (v.getId() == R.id.subtitle_text) {
            if (G.isEmteny(getGoodsIds())) {
                showMessage("你没有选择任何商品");
                return;
            }
            presenter.addCashierItem();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        page = 1;
        this.shopCatId = 0;
        if (!s.toString().contains("'")) {
            presenter.getCashierItemList();
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


    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getKeyWord() {
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
    public int getShopId() {
        return getIntent().getIntExtra("shopId", 0);
    }

    @Override
    public int getShopCatId() {
        return shopCatId;
    }

    @Override
    public String getGoodsIds() {
        return adapter.getGoodsIds();
    }

    @Override
    public void setCategoryList(List<GoodsListInfo> categoryList) {
        this.categoryInfos.clear();
        for (int i = 0; i < categoryList.size(); i++) {
            if (i == categoryList.size() - 1) {
                categoryInfos.add(0, categoryList.get(i));
            } else {
                categoryInfos.add(categoryList.get(i));
            }
        }
        if (categoryNavItemAdapter != null) {
            categoryNavItemAdapter.initData(0);
        }
    }


    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsDetailInfoList) {
        hideProgress();
        if (page == 1) {
            this.goodsDetailInfoList.clear();
            boolean hasData = goodsDetailInfoList.size() == 0;
            tvNodata.setVisibility(hasData ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(hasData ? View.GONE : View.VISIBLE);
        }
        hasMoreData = goodsDetailInfoList.size() == size;
        this.goodsDetailInfoList.addAll(goodsDetailInfoList);

        Map<Integer, Boolean> mapList = new HashMap<>();
        for (int i = 0; i < this.goodsDetailInfoList.size(); i++) {
            if (this.goodsDetailInfoList.size() > size) {
                if (i < this.goodsDetailInfoList.size() - size) {
                    mapList.put(i, adapter.getMapList().get(i));
                } else {
                    mapList.put(i, isSelectAll);
                }
                G.log("xxx" + "非第一页" + i + "条数据" + mapList.get(i));
            } else if (this.goodsDetailInfoList.size() == size) {
                mapList.put(i, isSelectAll);
                G.log("xxx" + "第一次加载第" + i + "条数据" + mapList.get(i));
            } else {
                mapList.put(i, isSelectAll);
               /* if (adapter.getMapList() == null) {

                    G.log("xxx"+"maplist是空的"+i+"条数据"+mapList.get(i));
                } else {
                    mapList.put(i, adapter.getMapList().get(i));
                    G.log("xxx"+"maplist不等于空的"+i+"条数据"+mapList.get(i));
                }*/
            }
        }

        if (adapter != null) {
            adapter.initData(mapList);
        }
    }

    @Override
    public void setSuccessBack() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getCashierItemList();
        refreshView.onRefreshComplete();
        if (!hasMoreData) {
            showMessage("没有更多数据了！");
            pullRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
    }

    @Override
    public void onClick(View view, int position) {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        page = 1;
        isSelectAll = false;
        categoryNavItemAdapter.initData(position);
        pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        tvSort.setText(categoryInfos.get(position).getCategory_name());
        shopCatId = categoryInfos.get(position).getCategory_id();
        showProgress();
        presenter.getCashierItemList();

    }

}
