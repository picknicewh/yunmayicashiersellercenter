package com.yun.ma.yi.app.module.shop.balance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/12/12
 * 名称：余额对账
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 余额账户
     */
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    /**
     * 余额
     */
    private double balance;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_balance_account;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_check_account);
        setSubTitleText("明细");
        setSubtitleClickListener(this);
        balance = getIntent().getDoubleExtra("balance",0);
        tvBalance.setText(PriceTransfer.chageMoneyToString(balance));

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.subtitle_text){
            Intent intent = new Intent(this,BalanceAccountListActivity.class);
            intent.putExtra("shopId",getIntent().getIntExtra("shopId",0));
            startActivity(intent);
        }
    }
}
