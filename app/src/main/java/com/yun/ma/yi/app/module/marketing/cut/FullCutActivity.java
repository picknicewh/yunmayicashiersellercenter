package com.yun.ma.yi.app.module.marketing.cut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.FullCutInfo;
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
 * 名称：新增/编辑满减界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FullCutActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener, FullCutContract.View, PullToRefreshBase.OnRefreshListener2<RecyclerView> {
    /**
     * 满减适配器
     */
    @BindView(R.id.rv_cut)
    PullToRefreshRecyclerView prv;
    RecyclerView rvCut;
    /**
     * 没有数据显示
     */
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private FullCutAdapter adapter;
    /**
     * 数据处理类
     */
    private FullCutPresenter presenter;
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
    private List<FullCutInfo> fullCutInfoList;
    /**
     * 是否还有很多数据
     */
    private boolean hasMoreData;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_full_cut;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.full_cut);
        setSubTitleText("新增");
        setSubtitleClickListener(this);
        fullCutInfoList = new ArrayList<>();
        adapter = new FullCutAdapter(fullCutInfoList);
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        prv.setOnRefreshListener(this);
        rvCut = prv.getRefreshableView();
        rvCut.setLayoutManager(new LinearLayoutManager(this));
        rvCut.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new FullCutPresenter(this, this);
        presenter.getFullCutList();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FullCutEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_ADD);
        startActivityForResult(intent, Constants.REQUEST_GOODS_ADD);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, FullCutEditActivity.class);
        intent.putExtra("operation", Constants.REQUEST_GOODS_EDIT);
        intent.putExtra("fullCutInfo", fullCutInfoList.get(position));
        startActivityForResult(intent, Constants.REQUEST_GOODS_EDIT);
    }

    @Override
    public void setFullCutInfoList(List<FullCutInfo> fullCutInfoList) {
        if (page == 1) {
            this.fullCutInfoList.clear();
        }
        hasMoreData = fullCutInfoList.size() == size;
        this.fullCutInfoList.addAll(fullCutInfoList);
        tvNodata.setVisibility(this.fullCutInfoList.size()==0?View.VISIBLE:View.GONE);
        prv.setVisibility(this.fullCutInfoList.size()==0?View.GONE:View.VISIBLE);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        new ItemDao(this).deleteAll();
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
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
      //  prv.setMode(PullToRefreshBase.Mode.BOTH);
        if (!hasMoreData) {
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        presenter.getFullCutList();
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getFullCutList();
        if (!hasMoreData) {
            showMessage("没有更多数据了");
            prv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        }
        refreshView.onRefreshComplete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.RESULT_SUCCESS) {
            presenter.getFullCutList();
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
