package com.yun.ma.yi.app.module.marketing;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.report.ReportSearchActivity;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7831
 * 名称：营销管理界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MarketingManagerActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_marketing_manager;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.marketing);
        new ItemDao(this).deleteAll();
    }

    @OnClick(R.id.tv_bargain_goods)
    public void bargainGoods() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_BARGAIN_GOODS)){
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.MARKING_BARGAIB_GOODS);
            startActivity(intent);
        }else {
            showMessage("你没有查看特价商品的权限！");
        }
    }

    @OnClick(R.id.tv_full_delivery)
    public void fullDelivery() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_FULL_DELIVERY)){
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.MARKING_FULL_DELIVERY);
            startActivity(intent);
        }else {
            showMessage("你没有查看满送的权限！");
        }
    }

    @OnClick(R.id.tv_full_cut)
    public void fullCut() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_FULL_CUT)){
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.MARKING_FULL_CUT);
            startActivity(intent);
        }else {
            showMessage("你没有查看满减的权限！");
        }
    }
}
