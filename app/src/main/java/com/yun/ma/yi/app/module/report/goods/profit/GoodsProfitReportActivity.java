package com.yun.ma.yi.app.module.report.goods.profit;

import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsProfitInfo;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;


/**
 * Created by ys on 2017/6/13.
 * 商品收益报表
 */

public class GoodsProfitReportActivity extends BaseActivity implements GoodsProfitReportContract.View{
    /**
     * 销售时间
     */
    @BindView(R.id.sale_time)
    ItemTextView saleTime;
    /**
     * 销售单件数
     */
    @BindView(R.id.sale_singular)
    ItemTextView saleSingular;
    /**
     * 销售金额
     */
    @BindView(R.id.sale_amount)
    ItemTextView saleAmount;
    /**
     * 退货单数
     */
    @BindView(R.id.refund_singular)
    ItemTextView refundSingular;
    /**
     * 退货金额
     */
    @BindView(R.id.refund_amount)
    ItemTextView refundAmount;
    /**
     * 销售成本
     */
    @BindView(R.id.sale_cost)
    ItemTextView saleCost;
    /**
     * 销售毛利
     */
    @BindView(R.id.sale_maori)
    ItemTextView saleMaori;
    /**
     * 毛利率
     */
    @BindView(R.id.maori_rate)
    ItemTextView maoriRate;
    /**
     * 数据处理类接口
     */
    private GoodsProfitReportPresenter presenter;
    private String startTime, endTime;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_profit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_profit_report);
        presenter = new GoodsProfitReportPresenter(this,this);
        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");
        presenter.getGoodsProfitReportInfo();
    }

    /**
     * 初始化数据
     */
    private void initData(GoodsProfitInfo goodsProfitInfo){
        saleTime.setText(startTime.substring(0,10)+"～"+endTime.substring(0,10));
        if (goodsProfitInfo != null){
            saleSingular.setText(String.valueOf(goodsProfitInfo.getCount()));
            saleAmount.setText("￥"+ PriceTransfer.chageMoneyToString(goodsProfitInfo.getTotal_fee()));
            refundSingular.setText(String.valueOf(goodsProfitInfo.getReturn_num()));
            refundAmount.setText("￥"+ PriceTransfer.chageMoneyToString(goodsProfitInfo.getReturn_fee()));
            saleCost.setText("￥" + PriceTransfer.chageMoneyToString(goodsProfitInfo.getCost_fee()));
            saleMaori.setText("￥" + PriceTransfer.chageMoneyToString(goodsProfitInfo.getProfit()));
            maoriRate.setText(goodsProfitInfo.getRate());
        }
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getSource() {
        return getIntent().getStringExtra("source");
    }

    @Override
    public void getGoodsProfitReportInfo(GoodsProfitInfo goodsProfitInfo) {
        initData(goodsProfitInfo);
    }
}
