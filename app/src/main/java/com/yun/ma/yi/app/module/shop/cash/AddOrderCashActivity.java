package com.yun.ma.yi.app.module.shop.cash;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yun.ma.yi.app.widget.MySpinner;
import com.yunmayi.app.manage.R;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：蚂蚁小店新增提现
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AddOrderCashActivity extends BaseActivity implements MySpinner.OnItemSelectListener, ShopCashContract.ViewEdit {
    /**
     * 店铺金额
     */
    @BindView(R.id.tv_shop_money)
    ItemTextView tvShopMoney;
    /**
     * 提现金额
     */
    @BindView(R.id.et_order_money)
    ItemEditText2 etOrderMoney;
    /**
     * 提现类型
     */
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;
    /**
     * 开户人
     */
    @BindView(R.id.et_accounter)
    ItemEditText2 etAccounter;
    /**
     * 开户账号
     */
    @BindView(R.id.et_account_number)
    ItemEditText2 etAccountNumber;
    /**
     * 银行卡类型
     */
    @BindView(R.id.tv_bank)
    ItemEditText2 tvBank;
    /**
     * 下拉匡
     */
    private MySpinner mSpinner;
    /**
     * 下拉数据列表
     */
    private List<String> dataList;
    private ShopCashPresenter presenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_addorder_cash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.add_shop_order_cash);
        mSpinner = new MySpinner(this);
        dataList = TextUtils.getOrderType();
        mSpinner.setOnItemSelectListener(this);
        presenter = new ShopCashPresenter(this, this);
        tvShopMoney.setText(PriceTransfer.chageMoneyToString(getIntent().getDoubleExtra("balance", 0)));
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(rightNow.DAY_OF_WEEK);
        if (day == 2) {
            presenter.withdrawBalance();
        }else {
            showMessage("只有周一才能提现！");
        }
    }

    @OnClick(R.id.tv_order_type)
    public void orderType(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        mSpinner.setWidth(view.getWidth());
        String data = tvOrderType.getText().toString();
        int position = TextUtils.getPosition(dataList, data);
        mSpinner.setDataList(view.getId(), dataList, position);
        mSpinner.showAsDropDown(view);
    }

    private int position;

    @Override
    public void onItemSelect(int viewId, int position) {
        this.position = position;
        tvOrderType.setText(dataList.get(position));
        tvBank.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
    }


    @Override
    public int getShopId() {
        return getIntent().getIntExtra("shopId", 0);
    }

    @Override
    public String getAccountType() {

        return position == 0 ? tvOrderType.getText().toString() : tvBank.getText();
    }

    @Override
    public String getAccountName() {
        return etAccounter.getText();
    }

    @Override
    public String getAccountNoo() {
        return etAccountNumber.getText();
    }

    @Override
    public double getAmount() {
        String accounts = etOrderMoney.getText();
        double account = G.isEmteny(accounts) ? 0 : Double.parseDouble(accounts);
        return account;
    }


    @Override
    public void setSuccessBack() {
        finish();
    }

}
