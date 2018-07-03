package com.yun.ma.yi.app.module.report;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.utils.ToastUtils;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * Created by ys on 2017/6/10.\
 * 报表中心
 */

public class ReportManagerActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_report_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.report);

    }

    /**
     * 收款统计报表
     */
    @OnClick(R.id.receivables_report)
    public void onCashierReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_STATISTIC_READ)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.RECEIVABLES);
            startActivity(intent);
        } else {
            showMessage("你没有查看收款统计报表权限o！");
        }
    }

    /**
     * 员工业绩报表
     */
    @OnClick(R.id.employee_report)
    public void onEmployeeReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_STAFF_ACHIEVEMENT)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.EMPLOYEE);
            startActivity(intent);
        } else {
            showMessage("你没有查看员工业绩报表权限哦！");
        }
    }
    /**
     * 商品交易流水报表
     */
    @OnClick(R.id.goods_trade_report)
    public void onGoodsTradeReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_TRADE)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.GOODS_TRADE);
            startActivity(intent);
        } else {
            showMessage("你没有查看商品交易流水报表权限哦！");
        }
    }
    /**
     * 商品销售报表
     */
    @OnClick(R.id.goods_sales_report)
    public void onGoodsSalesReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_SALE)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.GOODS_SALES);
            startActivity(intent);
        } else {
            showMessage("你没有查看商品销售报表权限哦！");
        }

    }

    /**
     * 商品收益报表
     */
    @OnClick(R.id.goods_profit_report)
    public void onGoodsProfitReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_PROFIT)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.GOODS_PROFIT);
            startActivity(intent);
        } else {
            showMessage("你没有查看商品收益报表权限哦！");
        }
    }

    /**
     * 商品采购报表
     */
    @OnClick(R.id.goods_purchase_report)
    public void onGoodsPurchaseReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_PURCHASE)) {
            ToastUtils.makeText(this, "开发中......");
        } else {
            showMessage("你没有查看商品采购报表权限哦！");
        }
    }

    /**
     * 库存结存报表
     */
    @OnClick(R.id.stock_balance_report)
    public void onStockBalanceReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_STOCK_LEAVE)) {
            ToastUtils.makeText(this, "开发中......");
        } else {
            showMessage("你没有查看库存结存报表权限哦！");
        }
    }

    /**
     * 库存盘点报表
     */
    @OnClick(R.id.stock_inventory_report)
    public void onStockInventoryReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_GOODS_STOCK_STATISTIC)) {
            ToastUtils.makeText(this, "开发中......");
        } else {
            showMessage("你没有查看库存盘点报表权限哦！");
        }
    }

    @OnClick(R.id.staff_in_out_work)
    public void staffInOutWork() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_STAFF_INOUT_WORK)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.STAFF_INOUTWORK);
            startActivity(intent);
        } else {
            showMessage("你没有查看员工上下班报表权限哦！");
        }

    }


    @OnClick(R.id.ll_integral_report)
    public void integralReport() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_INTENGER_REPORT)) {
            Intent intent = new Intent(this, ReportSearchActivity.class);
            intent.putExtra(Constants.FUNCTION, Constants.MEMBER_INTEGRAL_REPORT);
            startActivity(intent);
        } else {
            showMessage("你没有查看员工上下班报表权限哦！");
        }
    }
}
