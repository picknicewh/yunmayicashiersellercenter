package com.yun.ma.yi.app.module.marketing.choose.single;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.StockSearchInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChooseContract;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChoosePresenter;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7831
 * 名称：选择赠送商品页面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SingleGoodsAddChooseActivity extends BaseActivity implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2<RecyclerView>, GoodsChooseContract.ViewSingle {

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
     * 商品列表
     */
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 分类选择
     */
    @BindView(R.id.nav_view_sort)
    NavigationView navViewSort;
    /**
     * 抽屉布局
     */
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    /**
     * 列表
     */
    private RecyclerView recyclerView;
    /**
     * 选择分类id
     */
    private int category_Id=0;
    /**
     * 单个商品选择适配器
     */
    private SingleGoodsAddChooseAdapter singleAdapter;
    /**
     * 数据处理类
     */
    private GoodsChoosePresenter presenter;

    /**
     * 商品列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 页码
     */
    private int page = 1;
    /**
     * 一页条目
     */
    private int size = 20;
    /**
     * 请求获取id
     */
    private String delivery_product_id;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_single_goods_choose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_choose);
        setSubTitleText("确定");
        setSubtitleClickListener(this);
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        pullRecyclerView.setOnRefreshListener(this);
        recyclerView = pullRecyclerView.getRefreshableView();
        goodsDetailInfoList = new ArrayList<>();
        initSource();
    }

    private void initSource(){
        searchDetailInfoList = new ArrayList<>();
        delivery_product_id = getIntent().getStringExtra("delivery_product_id");
        singleAdapter = new SingleGoodsAddChooseAdapter(goodsDetailInfoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(singleAdapter);
        presenter = new GoodsChoosePresenter(this,this);
        if (!G.isEmteny(delivery_product_id)){
            presenter.getGoodItemsByIds();
        }
        presenter.getGoodList();
    }

    @OnClick(R.id.tv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Constants.REQUEST_STOCK_SEARCH);
    }

    @OnClick(R.id.tv_sort)
    public void sort() {
        page = 1;
        category_Id = 0;
        presenter.getGoodList();
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.subtitle_text:
                Intent intent = new Intent();
                int  position = singleAdapter.getPosition();
                if (goodsDetailInfoList.size()>0){
                    delivery_product_id = goodsDetailInfoList.get(position).getId();
                    intent.putExtra("delivery_product_id",delivery_product_id);
                }
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    @Override
    public void setGoodInfoList(StockSearchInfo stockSearchInfo) {
        tvNodata.setVisibility(View.GONE);
        hasData = stockSearchInfo.getList().size() == size;
        if (page==1){
            goodsDetailInfoList.clear();
            goodsDetailInfoList.addAll(searchDetailInfoList);
        }
         goodsDetailInfoList.addAll(stockSearchInfo.getList());
        if (singleAdapter!=null){
             if (G.isEmteny(delivery_product_id)){
                 singleAdapter.initData(-1);
             }else {
                 singleAdapter.initData(0);
             }

        }
    }
    @Override
    public void setNoData() {
        tvNodata.setVisibility(View.VISIBLE);
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
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getIds() {
        return delivery_product_id;
    }


    private List<GoodsDetailInfo> searchDetailInfoList;
    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList) {
        searchDetailInfoList.clear();
        searchDetailInfoList.addAll(detailInfoList);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        presenter.getGoodList();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshView.onRefreshComplete();
    }
    private boolean hasData = true;
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getGoodList();
        refreshView.onRefreshComplete();
        if (!hasData) {
            pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            G.showToast(this, "没有更多数据了");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
