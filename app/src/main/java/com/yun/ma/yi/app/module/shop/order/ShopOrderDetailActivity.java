package com.yun.ma.yi.app.module.shop.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ShopOrderDetailInfo;
import com.yun.ma.yi.app.bean.ShopOrderInfo;
import com.yun.ma.yi.app.module.common.view.ConformEditDialog;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/11/21
 * 名称：店铺订单详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderDetailActivity extends BaseActivity implements ShopOrderContract.ViewEdit, ConformEditDialog.OnConformCallBack {
    /**
     * 订单状态
     */
    @BindView(R.id.tv_order_status)
    ItemTextView tvOrderStatus;
    /**
     * 订单编号
     */
    @BindView(R.id.tv_order_number)
    ItemTextView tvOrderNumber;
    /**
     * 下单时间
     */
    @BindView(R.id.tv_order_time)
    ItemTextView tvOrderTime;
    /**
     * 支付方式
     */
    @BindView(R.id.tv_pay_way)
    ItemTextView tvPayWay;
    /**
     * 订单金额
     */
    @BindView(R.id.tv_order_money)
    ItemTextView tvOrderMoney;
    /**
     * 收货信息
     */
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    /**
     * 收货地址
     */
    @BindView(R.id.tv_address)
    TextView tvAddress;
    /**
     * 订单商品列表
     */
    @BindView(R.id.rv_order_goods)
    RecyclerView rvOrderGoods;
    /**
     * 订单总金额
     */
    @BindView(R.id.tv_goods_total_money)
    ItemTextView tvGoodsTotalMoney;
    /**
     * 配送费
     */
    @BindView(R.id.tv_deliver_money)
    ItemTextView tvDeliverMoney;
    /**
     * 优惠金额
     */
    @BindView(R.id.tv_discount_money)
    ItemTextView tvDiscountMoney;
    /**
     * 拒绝接单
     */
    @BindView(R.id.tv_reject)
    TextView tvReject;
    /**
     * 确定
     */
    @BindView(R.id.tv_conform)
    TextView tvConform;
    /**
     * 接单请求
     */
    @BindView(R.id.ll_operation)
     LinearLayout  llOperation;
    /**
     * 订单金额
     */
    @BindView(R.id.tv_order_total_money)
    ItemTextView tvOrderTotalMoney;
    /**
     * 适配器
     */
    private ShopOrderDetailAdapter adapter;
    /**
     * 信息
     */
    private ShopOrderInfo shopOrderInfo;
    /**
     * 订单详情列表
     */
    private List<ShopOrderDetailInfo> shopOrderDetailInfoList;
    /**
     * 数据处理类
     */
    private ShopOrderPresenter presenter;
    /**
     * 状态信息
     */
    private String stateInfo;
    /**
     * 拒绝原因
     */
    private String  reason ="";
    /**
     * 拒绝原因对话框
     */
    private ConformEditDialog dialog;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_order_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_order_detail);
        dialog = new ConformEditDialog(this);
        dialog.setOnConformCallBack(this);
        shopOrderDetailInfoList = new ArrayList<>();
        adapter = new ShopOrderDetailAdapter(this, shopOrderDetailInfoList);
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(this));
        rvOrderGoods.setAdapter(adapter);
        initData();
        presenter  =  new ShopOrderPresenter(this,this);
    }

    private void initData() {
        shopOrderInfo = (ShopOrderInfo) getIntent().getSerializableExtra("shopOrderInfo");
        if (shopOrderInfo != null) {
            this.shopOrderDetailInfoList.clear();
            this.shopOrderDetailInfoList.addAll(shopOrderInfo.getOrders());
            tvOrderStatus.setText(TextUtils.getShopOrderByStatus(shopOrderInfo.getStatus()));
            tvOrderNumber.setText(shopOrderInfo.getId());
            tvOrderTime.setText(shopOrderInfo.getPay_time());
            if (shopOrderInfo.getPay_type().equals("cod")) {
                tvPayWay.setText("货到付款");
            } else if (shopOrderInfo.getPay_type().equals("online")) {
                tvPayWay.setText(shopOrderInfo.getPay_platform().equals("weixinpay") ? "微信支付" : "支付宝支付");
            }
            tvOrderMoney.setText(PriceTransfer.chageMoneyToString(shopOrderInfo.getOrder_pay_price()));
            tvAddress.setText(shopOrderInfo.getConsignee_address());
            tvUserInfo.setText(shopOrderInfo.getConsignee_name() + "   " + shopOrderInfo.getConsignee_mobile());
            tvGoodsTotalMoney.setText(PriceTransfer.chageMoneyToString(shopOrderInfo.getOrder_total_price()-shopOrderInfo.getFreight_fee()));
            tvDeliverMoney.setText(PriceTransfer.chageMoneyToString(shopOrderInfo.getFreight_fee()));
            tvOrderTotalMoney.setText(PriceTransfer.chageMoneyToString(shopOrderInfo.getOrder_real_price()));
            tvDiscountMoney.setText(PriceTransfer.chageMoneyToString(shopOrderInfo.getOrder_discount_price()));
            if (shopOrderInfo.getStatus()==2){
                llOperation.setVisibility(View.VISIBLE);
            }else if (shopOrderInfo.getStatus()==3){
                llOperation.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.GONE);
                tvConform.setText("确认配送");
            }else if (shopOrderInfo.getStatus()==4){
                llOperation.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.GONE);
                tvConform.setText("完成配送");
            }else {
                llOperation.setVisibility(View.GONE);
            }
        }
    }


    @OnClick({R.id.tv_reject, R.id.tv_conform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reject:
                 stateInfo  = "reject";
                 dialog.show();
                break;
            case R.id.tv_conform:
                if (shopOrderInfo.getStatus()==3){
                    stateInfo  = "send";
                }else if (shopOrderInfo.getStatus()==2){
                    stateInfo  = "accept";
                }else if (shopOrderInfo.getStatus()==4){
                    stateInfo = "complete";
                }else if (shopOrderInfo.getStatus()==7){
                    showMessage("该订单已经取消！");
                    return;
                }
                presenter.editOrderState();
                break;
        }
    }

    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getOrderId() {
        return shopOrderInfo.getId();
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public String getStateInfo() {
        return stateInfo;
    }

    @Override
    public void setSuccessBack() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public void onCallBack( String reason) {
        this.reason=reason;
        presenter.editOrderState();
    }
}
