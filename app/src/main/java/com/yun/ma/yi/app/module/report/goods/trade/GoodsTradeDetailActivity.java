package com.yun.ma.yi.app.module.report.goods.trade;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsTradeDetailInfo;
import com.yun.ma.yi.app.bean.GoodsTradeDetailInfoBo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： ys
 * 时间： 2017/6/14.
 * 名称：商品交易流水详情/会员交易发生详情
 * 版本说明：由于会员交易详情交易发生详情的页面和这个页面一致，公用一个界面
 * 附加注释：传递from字段判断来源
 * 主要接口：商品交易流水详情 2 会员交易发生详情
 */
public class GoodsTradeDetailActivity extends BaseActivity implements GoodsTradeReportContract.ReportView {

    /**
     * 单号
     */
    @BindView(R.id.single_code)
    TextView singleCode;
    /**
     * 创建时间
     */
    @BindView(R.id.create_time)
    TextView createTime;
    /**
     * 应收金额
     */
    @BindView(R.id.amount_money)
    TextView amountMoney;
    /**
     * 实收金额
     */
    @BindView(R.id.goods_trade_money)
    TextView goodsTradeMoney;
    /**
     * 支付明细
     */
    @BindView(R.id.pay_info)
    TextView payInfo;
    /**
     * 管理员
     */
    @BindView(R.id.mamager)
    TextView mamager;
    /**
     * 商品列表
     */
    @BindView(R.id.goods_list)
    PullToRefreshRecyclerView goodsList;
    /**
     * 交易id
     */
    private String tradeId;
    /**
     * 数据处理类
     */
    private GoodsTradeReportPresenter presenter;
    /**
     * 适配器
     */
    private GoodsTradeDetailAdapter goodsTradeDetailAdapter;
    /**
     * 数据列表
     */
    private List<GoodsTradeDetailInfo> goodsTradeDetailInfos;
    /**
     * 列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 来自哪个页面标记
     */
    private int form;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_trade_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.bill_info);
        tradeId = getIntent().getStringExtra("tradeId").toString();
        form  =  getIntent().getIntExtra("form", Constants.REPORT_TRADE_DETAIL);
        setTitleTextId(form== Constants.REPORT_TRADE_DETAIL ?R.string.bill_info:R.string.trade_happen_detail);
        recyclerView = goodsList.getRefreshableView();
        initFrom();

    }

    private void initFrom(){
        goodsTradeDetailInfos = new ArrayList<>();
        presenter = new GoodsTradeReportPresenter(this, this);
        goodsTradeDetailAdapter = new GoodsTradeDetailAdapter(this, goodsTradeDetailInfos, R.layout.goods_trade_detail_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(goodsTradeDetailAdapter);
        presenter.getGoodsTradeDetailInfo();

    }
    @Override
    public String getTradeId() {
        return tradeId;
    }

    @Override
    public void getGoodsTradeDetailInfo(GoodsTradeDetailInfoBo result) {
        if (result != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            singleCode.setText(result.getTrade_id());
            Date createDate;
            try {
                createDate = simpleDateFormat.parse(result.getCreate_date());
            } catch (ParseException e) {
                createDate = new Date();
            }
            createTime.setText(simpleDateFormat.format(createDate));
            amountMoney.setText(" ¥ " + PriceTransfer.chageMoneyToString(result.getTotal_fee()));
            goodsTradeMoney.setText(" ¥ " + PriceTransfer.chageMoneyToString(result.getReceived_fee()));
            mamager.setText(result.getCashier_name());
            int payType = result.getPay_type();
            String payForm = result.getPay_platform();
            if (payType == 1 && "cod".equals(payForm)){
                payInfo.setText(getString(R.string.cash)+"¥"+PriceTransfer.chageMoneyToString(result.getReceived_fee()));
            }else if(payType == 2){
                if ("alipay".equals(payForm)){
                    payInfo.setText(getString(R.string.alipay)+"¥"+PriceTransfer.chageMoneyToString(result.getReceived_fee()));
                }else if("wxpay".equals(payForm)){
                    payInfo.setText(getString(R.string.wechat)+"¥"+PriceTransfer.chageMoneyToString(result.getReceived_fee()));
                }
            }
            goodsTradeDetailInfos.addAll(result.getDetail());
            goodsTradeDetailAdapter.notifyDataSetChanged();
        }
    }

}
