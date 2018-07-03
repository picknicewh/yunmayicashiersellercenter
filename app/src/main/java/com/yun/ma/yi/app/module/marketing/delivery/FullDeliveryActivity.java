package com.yun.ma.yi.app.module.marketing.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.FullDeliveryInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FullDeliveryActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener, FullDeliveryContract.View, PullToRefreshBase.OnRefreshListener2<RecyclerView> {
    /**
     * 满送活动列表
     */
    @BindView(R.id.rv_delivery)
    PullToRefreshRecyclerView prv;
    RecyclerView rvDelivery;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private FullDeliveryAdapter adapter;
    /**
     * 页面
     */
    private int page = 1;
    /**
     * 页数
     */
    private int size = 10;
    /**
     * 是否还有很多数据
     */
    private boolean hasMoreData;
    /**
     * 数据列表
     */
    private List<FullDeliveryInfo> fullDeliveryInfoList;
    /**
     * 数据处理类
     */
    private FullDeliveryPresenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_full_delivery;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.full_delivery);
        setSubTitleText("新增");
        setSubtitleClickListener(this);

        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        rvDelivery = prv.getRefreshableView();
        fullDeliveryInfoList = new ArrayList<>();
        adapter = new FullDeliveryAdapter(fullDeliveryInfoList);
        rvDelivery.setLayoutManager(new LinearLayoutManager(this));
        rvDelivery.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new FullDeliveryPresenter(this, this);
        presenter.getFullDeliveryList();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FullDeliveryEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_ADD);
        startActivityForResult(intent, Constants.REQUEST_GOODS_ADD);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, FullDeliveryEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_EDIT);
        intent.putExtra("fullDeliveryInfo", fullDeliveryInfoList.get(position));
        startActivityForResult(intent, Constants.REQUEST_GOODS_EDIT);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
      //  prv.setMode(PullToRefreshBase.Mode.BOTH);
        if (!hasMoreData) {
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        presenter.getFullDeliveryList();
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getFullDeliveryList();
        if (!hasMoreData) {
            showMessage("没有更多数据了");
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        refreshView.onRefreshComplete();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ItemDao(this).deleteAll();
    }

    @Override
    public void setFullDeliveryInfoList(List<FullDeliveryInfo> fullDeliveryInfoList) {
        if (page == 1) {
            this.fullDeliveryInfoList.clear();
        }
        this.fullDeliveryInfoList.addAll(fullDeliveryInfoList);
        hasMoreData = fullDeliveryInfoList.size() == size;
        tvNodata.setVisibility(this.fullDeliveryInfoList.size() == 0 ? View.VISIBLE : View.GONE);
        prv.setVisibility(this.fullDeliveryInfoList.size()==0?View.GONE:View.VISIBLE);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.RESULT_SUCCESS) {
            presenter.getFullDeliveryList();
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
