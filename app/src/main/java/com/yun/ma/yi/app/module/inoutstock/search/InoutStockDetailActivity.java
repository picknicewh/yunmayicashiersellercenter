package com.yun.ma.yi.app.module.inoutstock.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.InOutSearchInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InoutStockDetailActivity extends BaseActivity implements InOutStockContract.View {

    @BindView(R.id.rv_stock)
    RecyclerView rvStock;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    /**
     * 适配器
     */
    private InOutStockDetailAdapter adapter;
    /**
     * 数据处理类
     */
    private InOutStockPresenter presenter;

    private List<InOutSearchInfo.InOutSearchInfoDetail> infoDetailList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_inout_stock_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.inout_stock_detail);
        infoDetailList = new ArrayList<>();
        adapter = new InOutStockDetailAdapter(this, infoDetailList);
        rvStock.setLayoutManager(new LinearLayoutManager(this));
        rvStock.setAdapter(adapter);
        presenter = new InOutStockPresenter(this, this);
        presenter.searchEnterOutStock();
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getStartTime() {
        return getIntent().getStringExtra("startTime");
    }

    @Override
    public String getEndTime() {
        return getIntent().getStringExtra("endTime");
    }

    @Override
    public int getType() {
        return getIntent().getIntExtra("type", 2);
    }

    @Override
    public void setInOutSearchInfo(InOutSearchInfo inOutSearchInfo) {
        if (!inOutSearchInfo.isError()) {
            infoDetailList.clear();
            infoDetailList.addAll(inOutSearchInfo.getData());
            tvNodata.setVisibility(inOutSearchInfo.getData().size()==0? View.VISIBLE: View.GONE);
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } else {
            showMessage(inOutSearchInfo.getInfo());
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
