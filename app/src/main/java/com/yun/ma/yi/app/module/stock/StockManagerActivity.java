package com.yun.ma.yi.app.module.stock;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.stock.add.AddStatisticsActivity;
import com.yun.ma.yi.app.module.stock.search.StockSearchActivity;
import com.yun.ma.yi.app.module.stock.statistic.StockStatisticSearchActivity;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * Created by ys on 2017/6/27.
 * 库存管理
 */
public class StockManagerActivity extends BaseActivity{



    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_stock_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.stock_manage);
    }

    @OnClick(R.id.ll_stock_search)
    public void search() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_STOCK_SEARCH)){
            Intent intent  = new Intent(this,StockSearchActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有查询库存的权限哦！");
        }

    }
    @OnClick(R.id.ll_stock_statistics)
    public void statistics() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STOCK_ADD_STATISTIC)){
            Intent intent  = new Intent(this,AddStatisticsActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有新增库存盘点的权限哦！");
        }
    }
    @OnClick(R.id.ll_stock_statistics_search)
    public void statisticsSearch() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STOCK_STATISTIC_SEARCH)){
            Intent intent  = new Intent(this,StockStatisticSearchActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有库存盘点查询的权限哦！");
        }
    }
}
