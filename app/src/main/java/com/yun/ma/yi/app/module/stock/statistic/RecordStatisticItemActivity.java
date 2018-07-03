package com.yun.ma.yi.app.module.stock.statistic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.api.util.GlideUtils;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.bean.StockStatisticItemInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 库存盘点记录
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticItemActivity extends BaseActivity implements RecordStatisticContract.RecordStatisticItemView, OnItemClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView> {
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private RecordStatisticItemAdapter adapter;
    private RecyclerView recyclerView;
    private int page = 1;
    private Intent intent;
    private RecordStatisticContract.Presenter presenter;
    private StockStatisticInfo stockStatisticInfo;
    private List<StockStatisticItemInfo> stockStatisticItemInfos;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_record_statistic_item;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.stock_statistics_record_adjust);
        intent = getIntent();
        stockStatisticInfo = (StockStatisticInfo) intent.getSerializableExtra("stockStatisticInfo");
        presenter = new RecordStatisticPresenter(this, this);
        stockStatisticItemInfos = new ArrayList<>();
        recyclerView = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setOnRefreshListener(this);
        adapter = new RecordStatisticItemAdapter(this,stockStatisticItemInfos,R.layout.item_statistic_record_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        pullRecyclerView.setRefreshing(false);

        initData();
    }

    private void initData(){
        if (stockStatisticInfo != null){
            GlideUtils.loadImageView(this,stockStatisticInfo.getImage_url(),ivImage);
            tvName.setText(stockStatisticInfo.getTitle());
            tvCode.setText(stockStatisticInfo.getBar_code());
            tvTime.setText("时间："+getStartTime() + "~" + getEndTime());
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, RecordStatisticItemDetailActivity.class);
        intent.putExtra("id",stockStatisticItemInfos.get(position).getId());
        startActivity(intent);
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return 10;
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
    public String getItemId() {
        return stockStatisticInfo.getItem_id();
    }

    @Override
    public void stockChangeForItemId(List<StockStatisticItemInfo> infos) {
        if (infos != null){
            if (infos.size() == 0){
                pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }else{
                if (page == 1){
                    stockStatisticItemInfos.clear();
                }
                stockStatisticItemInfos.addAll(infos);
                adapter.notifyDataSetChanged();
            }
        }
        pullRecyclerView.onRefreshComplete();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        presenter.stockChangeForItemId();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.stockChangeForItemId();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
