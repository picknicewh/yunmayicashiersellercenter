package com.yun.ma.yi.app.module.marketing.bargain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.BargainGoodsInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：特价商品界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BargainGoodsActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener, BargainGoodsContract.View, PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    /**
     * 特价商品列表
     */
    @BindView(R.id.rv_bargain)
    PullToRefreshRecyclerView prv;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private RecyclerView rvBargain;
    /**
     * 适配器
     */
    private BargainGoodsAdapter adapter;
    /**
     * 数据处理类
     */
    private BargainGoodsPresenter presenter;
    /**
     * 页面
     */
    private int page = 1;
    /**
     * 页数
     */
    private int size = 10;
    /**
     * 满减列表
     */
    private List<BargainGoodsInfo> bargainGoodsInfoList;
    /**
     * 是否还有很多数据
     */
    private boolean hasMoreData;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bargain_goods;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.bargain_goods);
        setSubTitleText("新增");
        setSubtitleClickListener(this);
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        rvBargain = prv.getRefreshableView();
        bargainGoodsInfoList = new ArrayList<>();
        adapter = new BargainGoodsAdapter(bargainGoodsInfoList);
        rvBargain.setLayoutManager(new LinearLayoutManager(this));
        rvBargain.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new BargainGoodsPresenter(this, this);
        presenter.getBargainGoodsInfoList();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, BargainGoodsEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_ADD);
        startActivityForResult(intent, Constants.REQUEST_GOODS_ADD);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, BargainGoodsEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_EDIT);
        intent.putExtra("bargainGoodsInfo", bargainGoodsInfoList.get(position));
        startActivityForResult(intent, Constants.REQUEST_GOODS_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.RESULT_SUCCESS) {
            presenter.getBargainGoodsInfoList();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        if (!hasMoreData) {
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        presenter.getBargainGoodsInfoList();
        refreshView.onRefreshComplete();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ItemDao(this).deleteAll();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getBargainGoodsInfoList();
        if (!hasMoreData) {
            showMessage("没有更多数据了");
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        refreshView.onRefreshComplete();
    }

    @Override
    public void setBargainGoodsInfoList(List<BargainGoodsInfo> fullCutInfoList) {
        if (page == 1) {
            this.bargainGoodsInfoList.clear();
        }
        this.bargainGoodsInfoList.addAll(fullCutInfoList);
        hasMoreData = fullCutInfoList.size() == size;
        tvNodata.setVisibility(this.bargainGoodsInfoList.size()==0?View.VISIBLE:View.GONE);
        prv.setVisibility(this.bargainGoodsInfoList.size()==0?View.GONE:View.VISIBLE);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUser_id();
    }

    @Override
    public String getActivityStartTime() {
        return getIntent().getStringExtra("startTime");
    }

    @Override
    public String getActivityEndTime() {
        return getIntent().getStringExtra("endTime");
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
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
