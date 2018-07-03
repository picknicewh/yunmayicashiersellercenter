package com.yun.ma.yi.app.module.inoutstock;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.inoutstock.in.InStockActivity;
import com.yun.ma.yi.app.module.inoutstock.out.OutStockActivity;
import com.yun.ma.yi.app.module.inoutstock.search.InOutStockSearchActivity;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：退货入库管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutStockManagerActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_out_stock_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
     setTitleTextId(R.string.inout_stock_manager);

    }

    @OnClick(R.id.tv_in_stock)
    public void inStock() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_INOUTSTOCK_INSTOCK)){
            Intent intent = new Intent(this,InStockActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有收获入库的权限哦！");
        }


    }
    @OnClick(R.id.tv_out_stock)
    public void outStock() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_INOUTSTOCK_OUTSTOCK)){
            Intent intent = new Intent(this,OutStockActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有退货出库的权限哦！");
        }


    }
    @OnClick(R.id.tv_inout_stock_search)
    public void search() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_INOUTSTOCK_SEARCH)){
            Intent intent = new Intent(this,InOutStockSearchActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有出入库查询的权限哦！");
        }


    }
}
