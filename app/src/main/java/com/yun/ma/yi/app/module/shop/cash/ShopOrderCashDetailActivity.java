package com.yun.ma.yi.app.module.shop.cash;

import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ShopCashInfo;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：蚂蚁小店申请详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderCashDetailActivity extends BaseActivity {

    /**
     *提现金额
     */
    @BindView(R.id.tv_order_money)
    ItemTextView tvOrderMoney;
    /**
     * 手续费
     */
    @BindView(R.id.tv_server_charge)
    ItemTextView tvServerCharge;
    /**
     *实际到账
     */
    @BindView(R.id.tv_actual_money)
    ItemTextView tvActualMoney;
    /**
     * 到账时间
     */
    @BindView(R.id.tv_time)
    ItemTextView tvTime;
    /**
     * 账户信息
     */
    @BindView(R.id.tv_account_info)
    ItemTextView tvAccountInfo;
    /**
     * 开户人
     */
    @BindView(R.id.tv_accounter)
    ItemTextView tvAccounter;
    /**
     * 小店编号
     */
    @BindView(R.id.tv_shop_number)
    ItemTextView tvShopNumber;
    private ShopCashInfo.ShopCashDetailInfo shopCashDetailInfo;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_order_cash_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.order_cash_detail);
        initData();
    }
    private void initData(){
        shopCashDetailInfo = (ShopCashInfo.ShopCashDetailInfo) getIntent().getSerializableExtra("shopCashDetailInfo");
        tvOrderMoney.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getAmount()));
        tvServerCharge.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getFormalities()));
        tvActualMoney.setText(PriceTransfer.chageMoneyToString(shopCashDetailInfo.getAccount()));
        tvTime.setText(shopCashDetailInfo.getCreated_at());
        tvAccountInfo.setText(shopCashDetailInfo.getAccount_no());
        tvAccounter.setText(shopCashDetailInfo.getAccount_name());
        tvShopNumber.setText(shopCashDetailInfo.getAccount_type());


    }
}
