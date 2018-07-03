package com.yun.ma.yi.app.module.goods.list;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.ShopCatInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.CategoryChooseDialog;
import com.yun.ma.yi.app.module.common.GoodsAddTypeChooseDialog;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.common.ProgressChangeDialog;
import com.yun.ma.yi.app.module.goods.edit.GoodsEditActivity;
import com.yun.ma.yi.app.module.goods.search.GoodsSearchActivity;
import com.yun.ma.yi.app.module.shop.goods.ShopGoodsEditActivity;
import com.yun.ma.yi.app.module.shop.goods.add.ShopGoodsAddActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity implements GoodsListContract.View, OnItemClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView>, GoodsAddTypeChooseDialog.OnTypeChooseCallBack, CategoryChooseDialog.OnCategoryClickListener {
    /**
     * 扫码
     */
    @BindView(R.id.iv_scan)
    TextView ivScan;
    /**
     * 父級分類
     */
    @BindView(R.id.rv_sort)
    RecyclerView rvSort;
    /**
     * 子分类
     */
    @BindView(R.id.rv_child_sort)
    PullToRefreshRecyclerView refreshRecyclerView;
    RecyclerView rvChildSort;
    /**
     * 添加商品
     */
    @BindView(R.id.ll_goods_add)
    LinearLayout llGoodsAdd;
    /**
     * 商品列表适配器
     */
    private GoodCateListAdapter adapter;
    /**
     * 商品子列表适配器
     */
    private GoodsListAdapter childAdapter;
    /**
     * 商品详情列表
     */
    private List<GoodsListInfo> goodsInfoList;
    /**
     * 商品分类信息列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 数据处理
     */
    private GoodsListPresenter presenter;
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
     * 扫码返回的值
     */
    private String scanCode = "";
    /**
     * 选中分类的位置
     */
    private int groupPosition = 0;
    /**
     * 子位置
     */
    private int childPosition = 0;
    /**
     * 上拉加载是否有数据了
     */
    private boolean hasData = true;
    /**
     * 是否是蚂蚁小店的商品
     */
    private boolean isShop;
    /**
     * 蚂蚁小店店铺id
     */
    private int shopId;
    /**
     * 商品分类选择
     */
    private GoodsAddTypeChooseDialog typeChooseDialog;
    /**
     * 默认去全部的时候id传递0获取选择蚂蚁小店分类Id
     */
    private int shopCatId = 0;
    /**
     * 蚂蚁小店分类列表
     */
    private List<ShopCatInfo> shopCatInfoList;
    /**
     * 根据分类id获取蚂蚁小店商品信息列表
     */
    private List<ShopGoodsInfoDetail> shopGoodsInfoDetailList;
    /**
     * 适配器
     */
    private ShopGoodsListAdapter childAdapter2;
    /**
     * 是否重新点击
     */
    private boolean isClick = false;
    /**
     * 分类选择对话框
     */
    private CategoryChooseDialog categoryChooseDialog;
    /**
     * 蚂蚁小店是否有收银机
     */
    private boolean isHaveCashier;
    /**
     * 一键上传商品数量
     */
    private int oneKeyCount = -1;
    /**
     * 一键上传商品数量
     */
    private int oneKeyTotalPage;
    /**
     * 一键上传一次传递数
     */
    private int oneKeySize;
    /**
     * 一键上传页
     */
    private int oneKeyPage;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        G.initDisplaySize(this);
        typeChooseDialog = new GoodsAddTypeChooseDialog(this);
        typeChooseDialog.setOnTypeChooseCallBack(this);
        categoryChooseDialog = new CategoryChooseDialog(this);
        categoryChooseDialog.setCategoryClickListener(this);
        progressDialog = new ProgressChangeDialog(this);
        isShop = getIntent().getBooleanExtra("isShop", false);
        setTitleTextId(!isShop ? R.string.goods_list : R.string.shop_goods_list);
        shopId = getIntent().getIntExtra("shopId", 0);
        isHaveCashier = getIntent().getBooleanExtra("isHaveCashier", false);
        llGoodsAdd.setVisibility(isShop ? View.VISIBLE : View.GONE);
        goodsInfoList = new ArrayList<>();
        goodsDetailInfoList = new ArrayList<>();
        shopCatInfoList = new ArrayList<>();
        shopGoodsInfoDetailList = new ArrayList<>();
        if (isShop) {
            adapter = new GoodCateListAdapter(this, shopCatInfoList);
        } else {
            adapter = new GoodCateListAdapter(goodsInfoList, this);
        }
        rvSort.setLayoutManager(new LinearLayoutManager(this));
        rvSort.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new GoodsListPresenter(this, this);
        if (isShop) {
            presenter.getShopCatList();
            category_Id = -1;
        } else {
            presenter.getGoodSortInfo();
            category_Id = 0;
        }
        childSort();
    }

    private void childSort() {
        rvChildSort = refreshRecyclerView.getRefreshableView();
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshRecyclerView.setOnRefreshListener(this);
        if (isShop) {
            childAdapter2 = new ShopGoodsListAdapter(this, shopGoodsInfoDetailList, R.layout.item_goods_child_list);
            rvChildSort.setLayoutManager(new LinearLayoutManager(this));
            rvChildSort.setAdapter(childAdapter2);
            childAdapter2.setOnItemClickListener(childClickListener);
        } else {
            childAdapter = new GoodsListAdapter(this, goodsDetailInfoList, R.layout.item_goods_child_list);
            rvChildSort.setLayoutManager(new LinearLayoutManager(this));
            rvChildSort.setAdapter(childAdapter);
            childAdapter.setOnItemClickListener(childClickListener);
        }
        refreshRecyclerView.setRefreshing(false);
        refreshRecyclerView.setPullToRefreshOverScrollEnabled(false);
    }

    @Override
    public int getUser_id() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getCategoryId() {
        return category_Id;
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
    public int getOneKeyPage() {
        return oneKeyPage;
    }

    @Override
    public int getOneKeySize() {
        return oneKeySize;
    }

    @Override
    public String getKeyWord() {
        return scanCode;
    }

    @Override
    public int getShopId() {
        return shopId;
    }


    @Override
    public int getShopCatId() {
        return shopCatId;
    }

    @Override
    public String getToken() {
        return UserMessage.getInstance().getToken();
    }

    @Override
    public String getCashierCategoryId() {
        return String.valueOf(cashierCategoryId);
    }

    @Override
    public void setOnKeyPage(int page) {
        oneKeyPage = page;
        if (oneKeyPage >= oneKeyTotalPage) {
            progressDialog.setProgress(1);
            G.log("--xxxxx:完成");
            onSuccessBack();
        } else {
            float progress = (float) oneKeyPage / (float) oneKeyTotalPage;
            progressDialog.setProgress(progress);
            presenter.oneKeyUploadShopGoods();
            G.log("--xxxxx:oneKeyTotalPage" + oneKeyTotalPage);
            G.log("--xxxxx:oneKeyCount" + oneKeyCount);
            G.log("--xxxxx:oneKeySize" + oneKeySize);
            G.log("--xxxxx:oneKeyPage" + oneKeyPage);
            G.log("--xxxxx:page" + page);
           // G.log("--xxxxx:progress" + progress);
        }
    }

    @Override
    public void setOneKeyUploadCount(int count) {
        this.oneKeyCount = count;
        if (oneKeyCount == 0) {
            progressDialog.dismiss();
            return;
        }
        oneKeySize = oneKeyCount < 400 ? oneKeyCount : oneKeyCount / 20;
        int page = oneKeyCount / oneKeySize;
        oneKeyTotalPage = oneKeyCount % oneKeySize == 0 ? page : page + 1;
        oneKeyPage = 1;
        if (oneKeyTotalPage==1){
            handler.sendEmptyMessageDelayed(1,10);
        }else {
            float progress = (float) oneKeyPage / (float) oneKeyTotalPage;
            progressDialog.setProgress(progress);
        }

        presenter.oneKeyUploadShopGoods();
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float progress= (float)msg.what/(float) 100;
            progressDialog.setProgress(progress);
            if (msg.what<95){
                int count = msg.what+1;
                handler.sendEmptyMessageDelayed(count,10);
            }

        }
    };
    private int cashierCategoryId = -1;

    @Override
    public void setGoodsInfo(GoodsInfo goodsInfo) {
        Intent intent;
        if (goodsInfo.getCount() == 0) {
            intent = new Intent(this, GoodsEditActivity.class);
        } else {
            intent = new Intent(this, GoodsSearchActivity.class);
        }
        intent.putExtra("scanCode", scanCode);
        startActivity(intent);
        if (goodsInfo.getCount() == 0) {
            finish();
        }
    }

    @Override
    public void setGoodsInfoList(List<GoodsListInfo> goodsInfoList) {
        this.goodsInfoList.clear();
        for (int i = 0; i < goodsInfoList.size(); i++) {
            if (i == goodsInfoList.size() - 1) {
                this.goodsInfoList.add(0, goodsInfoList.get(i));
            } else {
                this.goodsInfoList.add(goodsInfoList.get(i));
            }
        }
        if (adapter != null) {
            adapter.initData(groupPosition);
        }
    }

    @Override
    public void setShopCatInfoList(List<ShopCatInfo> shopCatInfoList) {
        this.shopCatInfoList.clear();
        for (int i = 0; i < shopCatInfoList.size(); i++) {
            if (i == shopCatInfoList.size() - 1) {
                this.shopCatInfoList.add(0, shopCatInfoList.get(i));
            } else {
                this.shopCatInfoList.add(shopCatInfoList.get(i));
            }
        }
        if (adapter != null) {
            adapter.initData(groupPosition);
        }
    }

    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsDetailInfoList) {
        hideProgress();
        if (isClick) {
            this.goodsDetailInfoList.clear();
        }
        hasData = goodsDetailInfoList.size() == size;
        for (int i = 0; i < goodsDetailInfoList.size(); i++) {
            G.log("xxxxxxxss" + goodsDetailInfoList.get(i).getTitle());
        }
        this.goodsDetailInfoList.addAll(goodsDetailInfoList);
        if (childAdapter != null) {
            childAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setShopGoodsInfoDetailList(List<ShopGoodsInfoDetail> shopGoodsInfoDetailList) {
        hideProgress();
        if (isClick) {
            this.shopGoodsInfoDetailList.clear();
        }
        G.log("xxxxxxxss" + shopGoodsInfoDetailList.size());
        hasData = shopGoodsInfoDetailList.size() == size;
        this.shopGoodsInfoDetailList.addAll(shopGoodsInfoDetailList);
        if (childAdapter2 != null) {
            childAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccessBack() {//一键上传成功
        //progressDialog.dismiss();
        presenter.getShopCatList();
        presenter.getShopGoodInfoByCatId();
    }

    private ProgressChangeDialog progressDialog;

    @Override
    public void onClick(View view, int position) {
        groupPosition = position;
        isClick = true;
        hasData = true;
        page = 1;
        showProgress();
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        if (isShop) {
            shopCatId = shopCatInfoList.get(position).getShop_cat_id();
            if (shopCatId == 0) {
                shopCatId = -1;
            } else if (shopCatId == -1) {
                shopCatId = 0;
            }
            presenter.getShopGoodInfoByCatId();
        } else {
            category_Id = goodsInfoList.get(position).getCategory_id();
            presenter.getGoodsDetailInfoList();
          /*  if (UserMessage.getInstance().getParent_id()==0){

            }else {
                G.showToast(this,"你没有编辑商品的权限");
            }*/
        }
    }

    @OnClick({R.id.tv_add, R.id.ll_goods_add})
    public void goodsAdd(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                if (isHaveCashier) {
                    Intent intent = new Intent(this, ShopGoodsAddActivity.class);
                    intent.putExtra("shopId", shopId);
                    startActivityForResult(intent, Constants.REQUEST_SHOP_GOODS_ADDD);
                } else {
                    showMessage("你的蚂蚁小店还没有收银机哦！");
                }
                break;
            case R.id.ll_goods_add:
                if (isHaveCashier) {
                    typeChooseDialog.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                } else {
                    showMessage("你的蚂蚁小店还没有收银机哦！");
                }
                break;
        }
    }

    @OnClick(R.id.tv_code_search)
    public void search() {
        Intent intent = new Intent(this, GoodsSearchActivity.class);
        intent.putExtra("scanCode", scanCode);
        intent.putExtra("isShop", isShop);
        intent.putExtra("shopId", shopId);
        startActivity(intent);
    }

    @OnClick(R.id.iv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQUEST_GOOODS_SEARCH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_GOOODS_EDIT) {
            //编辑删除之后修改列表参数
            if (resultCode == Constants.RESULT_GOOODS_DELETE) {
                if (isShop) {
                    shopGoodsInfoDetailList.remove(childPosition);
                    childAdapter2.notifyDataSetChanged();
                    shopCatInfoList.get(groupPosition).setCount(shopCatInfoList.get(groupPosition).getCount() - 1);//总分类减1
                    if (groupPosition!=0)
                    shopCatInfoList.get(0).setCount(shopCatInfoList.get(0).getCount() - 1);//当前分类树减1
                    if (shopCatInfoList.get(groupPosition).getCount() <= 0) {//如果当前的总数小于等于0，那么移除
                        shopCatInfoList.remove(groupPosition);
                    }
                } else {
                    goodsDetailInfoList.remove(childPosition);
                    childAdapter.notifyDataSetChanged();
                    if (groupPosition!=0)
                    goodsInfoList.get(groupPosition).setCount(goodsInfoList.get(groupPosition).getCount() - 1);
                    goodsInfoList.get(0).setCount(goodsInfoList.get(0).getCount() - 1);
                    if (goodsInfoList.get(groupPosition).getCount() <= 0) {
                        goodsInfoList.remove(groupPosition);
                    }
                }
                adapter.initData(groupPosition);
            } else if (resultCode == Constants.RESULT_GOOODS_EDIT) { //编辑之后重新请求参数
                if (!isShop) {
                    isClick = true;
                    page = 1;
                    presenter.getGoodSortInfo();
                    presenter.getGoodsDetailInfoList();
                } else {
                    presenter.getShopCatList();
                    presenter.getShopGoodInfoByCatId();
                }
            }
        } else if (requestCode == Constants.REQUEST_SHOP_GOODS_ADDD) {//专属蚂蚁小店的新增
            if (resultCode == RESULT_OK) {
                presenter.getShopCatList();
                presenter.getShopGoodInfoByCatId();
            }
        } else if (requestCode == Constants.REQUEST_GOOODS_SEARCH) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                scanCode = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
                page = 1;
                if (isShop) {
                    presenter.getShopGoodInfoByCatId();
                } else {
                    presenter.getSearchGoodsList();
                }
            }
        }
    }

    private OnItemClickListener childClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent();
            if (isShop) {
                intent.setClass(GoodsListActivity.this, ShopGoodsEditActivity.class);
                intent.putExtra("goodsId", shopGoodsInfoDetailList.get(position).getId());
                intent.putExtra("shopId", shopId);
            } else {
                intent.setClass(GoodsListActivity.this, GoodsEditActivity.class);
                intent.putExtra("goodsId", goodsDetailInfoList.get(position).getId());
            }
            childPosition = position;
            startActivityForResult(intent, Constants.REQUEST_GOOODS_EDIT);
        }
    };

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
        isClick = true;
        page = 1;
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        if (isShop) {
            presenter.getShopGoodInfoByCatId();
        } else {
            presenter.getGoodsDetailInfoList();
        }
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
        isClick = false;
        page++;
        if (isShop) {
            presenter.getShopGoodInfoByCatId();
        } else {
            presenter.getGoodsDetailInfoList();
        }
        refreshView.onRefreshComplete();
        if (!hasData) {
            refreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            G.showToast(this, "没有更多数据了。");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }


    @Override
    public void onCallBack(int type) {
        switch (type) {
            case GoodsAddTypeChooseDialog.TYPE_ALL:
                cashierCategoryId = -1;
                presenter.getOneKeyUploadCount();
                progressDialog.show();
                break;
            case GoodsAddTypeChooseDialog.TYPE_SORT:
                categoryChooseDialog.show();
                break;
        }
    }

    @Override
    public void OnCategoryIdCatch(int categoryId) {
        cashierCategoryId = categoryId;
        presenter.getOneKeyUploadCount();
        progressDialog.show();
    }
}
