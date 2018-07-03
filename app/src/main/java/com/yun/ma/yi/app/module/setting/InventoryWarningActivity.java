package com.yun.ma.yi.app.module.setting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.StcokWarningInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.module.setting.SettingContract.ISettingPresenter;
import com.yun.ma.yi.app.module.setting.SettingContract.IStockWarningView;
import com.yun.ma.yi.app.module.setting.adapter.InventoryListAdapter;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ys on 2017/6/28.
 * 库存预警
 */

public class InventoryWarningActivity extends BaseActivity implements IStockWarningView ,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    @BindView(R.id.stock_inventory_warning_list)
    PullToRefreshRecyclerView stockInventoryWarningList;
    private ISettingPresenter settingPresenter;
    private int page = 1;
    private List<StcokWarningInfo> stcokWarningInfos;
    private InventoryListAdapter inventoryListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_inventory_warning;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.inventory_warning);

        settingPresenter = new SettingPersenter(this, this);
        stcokWarningInfos = new ArrayList<>();
        recyclerView = stockInventoryWarningList.getRefreshableView();
        stockInventoryWarningList.setOnRefreshListener(this);
        inventoryListAdapter = new InventoryListAdapter(this, stcokWarningInfos, R.layout.inventory_warning_list_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(inventoryListAdapter);
        stockInventoryWarningList.setRefreshing(false);
    }

    @Override
    public int getUser_id() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return 15;
    }

    @Override
    public void getStockWarningData(List<StcokWarningInfo> infos) {
        if (infos != null){
            if (infos.size() == 0){
                stockInventoryWarningList.setMode(PullToRefreshBase.Mode.DISABLED);
            }else{
                if (infos.size() < 15){
                    stockInventoryWarningList.setMode(PullToRefreshBase.Mode.DISABLED);
                }
                stcokWarningInfos.addAll(infos);
                inventoryListAdapter.notifyDataSetChanged();
            }
        }
        stockInventoryWarningList.onRefreshComplete();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        settingPresenter.getStockWarningData();
        stockInventoryWarningList.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        settingPresenter.getStockWarningData();
    }

}
