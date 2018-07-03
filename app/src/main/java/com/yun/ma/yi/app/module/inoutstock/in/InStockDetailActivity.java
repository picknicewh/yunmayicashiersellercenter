package com.yun.ma.yi.app.module.inoutstock.in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ConformDialog;
import com.yun.ma.yi.app.module.common.InStockChooseDialog;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.common.view.EditSellPriceDialog;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称:收货入库明细
 * 版本说明：
 * 附加注释：
 * 主要接口：1.获取订单
 */
public class InStockDetailActivity extends BaseActivity implements InStockContract.ViewDetail, OnItemClickListener, InStockChooseDialog.onItemChooseListener, EditSellPriceDialog.OnSalePriceChangeListener {

    /**
     * 订单号
     */
    @BindView(R.id.tv_number)
    TextView tvNumber;
    /**
     * 订单状态
     */
    @BindView(R.id.tv_status)
    TextView tvStatus;
    /**
     * 配送员
     */
    @BindView(R.id.tv_distributor)
    TextView tvDistributor;
    /**
     * 配送员联系方式
     */
    @BindView(R.id.tv_distributor_mobile)
    TextView tvDistributorMobile;
    /**
     * 支付方式
     */
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    /**
     * 订单总金额
     */
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    /**
     * 订单应支付金额
     */
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    /**
     * 订单实付金额
     */
    @BindView(R.id.tv_actual_money)
    TextView tvActualMoney;
    /**
     * 订单商品列表
     */
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_conform)
    TextView tvConform;
    /**
     * 适配器
     */
    private InStockDetailAdapter adapter;
    private OrderInfo orderInfo;
    /**
     * 数据处理类
     */
    private InStockPresenter presenter;
    /**
     * 订单详情列表
     */
    private List<OrderInfoDetail> orderInfoDetailList;
    /**
     * 单个订单信息
     */
    private OrderInfoDetail orderInfoDetail;
    /**
     * 订单选择对话框
     */
    private InStockChooseDialog dialog;
    /**
     * 子订单ID
     */
    private int detail_id;
    /**
     * 选择的商品id
     */
    private String goodsId;
    /**
     * 选择商品列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 确认对话框
     */
    private ConformDialog conformDialog;
    /**
     * 选择入库的位置
     */
    private int isInStockPosition = 0;
    /**
     * 是否入库成功
     */
    private boolean isInStock = false;
    /**
     * 标记位sign==1数据时表示全部一起入库，sign==0数据表示单个入库
     */
    private int sign = 0;
    /**
     *编辑商品的售价对话框（如果入库的商品单价和当前商品的单价不同，则弹出对话框，调整对应的售价）
     */
    private EditSellPriceDialog sellPriceDialog;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_stock_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.in_stock_detail);
        dialog = new InStockChooseDialog(this);
        dialog.setOnItemChooseListener(this);
        conformDialog = new ConformDialog(this);
        sellPriceDialog = new EditSellPriceDialog(this);
        sellPriceDialog.setOnSalePriceChangeListener(this);
        orderInfoDetailList = new ArrayList<>();
        goodsDetailInfoList = new ArrayList<>();
        adapter = new InStockDetailAdapter(orderInfoDetailList);
        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        rvGoods.setNestedScrollingEnabled(false);
        rvGoods.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new InStockPresenter(this, this);
        presenter.getStockOrder();
        presenter.getStockOrderDetail();
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        presenter.conformAllInStock();
        sign = 1;
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getOrderId() {
        return getIntent().getStringExtra("orderId");
    }

    /**
     * 订单信息
     *
     * @param orderInfoList 订单列表
     */
    @Override
    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        if (orderInfoList.size() > 0) {
            orderInfo = orderInfoList.get(0);
            tvNumber.setText(orderInfo.getOrder_id());
            tvDistributorMobile.setText(orderInfo.getBuyer_mobile());
            tvStatus.setText("订单状态：" + TextUtils.getOrderStatus(orderInfo.getState()));
            tvDistributor.setText(orderInfo.getDeliveryman_name());
            tvDistributorMobile.setText(orderInfo.getDeliveryman_mobile());
            tvOrderMoney.setText("订单总金额：￥" + PriceTransfer.chageMoneyToString(orderInfo.getOrigin_total_sell_price()));
            tvActualMoney.setText("订单应付金额：￥" + PriceTransfer.chageMoneyToString(orderInfo.getPay_total_sell_price()));
            tvPayMoney.setText("订单实付金额：￥" + PriceTransfer.chageMoneyToString(orderInfo.getReal_pay_total_sell_price()));
            tvPayWay.setText("支付方式：" + TextUtils.getPayWay(orderInfo.getPay_type(), orderInfo.getPay_platform()));
        }
    }

    /**
     * 订单商品列表
     *
     * @param orderInfoDetailList 订单列表详情
     */
    @Override
    public void setOrderInfoDetail(List<OrderInfoDetail> orderInfoDetailList) {
        this.orderInfoDetailList.clear();
        this.orderInfoDetailList.addAll(orderInfoDetailList);
        for (int i = 0; i < orderInfoDetailList.size(); i++) {
            int isEnter = orderInfoDetailList.get(i).getIs_enter();
            if (isEnter == 0) {
                isAllInStock = false;
                break;
            } else {
                isAllInStock = true;
            }
        }
        tvConform.setEnabled(!isAllInStock);
        tvConform.setBackgroundResource(isAllInStock?R.drawable.round_grey_btn:R.drawable.round_red_btn);
        if (adapter != null) {
            adapter.initData();
        }
    }
    /**
     * 是否已经全部入库
     */
    private boolean isAllInStock = false;

    /**
     * 点击确认返回商品的数据信息
     *
     * @param conformInStockInfo 商品信息
     *                           如果返回查询当前的收银机中有且只有一条数据则入库成功
     *                           如果返回查询当前收银机中没有数据，则弹出确认按钮是否新增改数据
     *                           如有返回查询有多条数据，则弹出商品选择对话框，对相应的数据进行入库操作
     */
    @Override
    public void setConformInStockInfo(ConformInStockInfo conformInStockInfo) {
        if (conformInStockInfo.getData().size() > 1) {
            dialog.show();
            goodsDetailInfoList = conformInStockInfo.getData();
            dialog.setGoodsDetailInfoList(goodsDetailInfoList);
            isInStock = false;
        } else {
            if (sign == 0) {//单个入库
                if (conformInStockInfo.isError()) {
                    if (conformInStockInfo.getInfo().contains("无法找到对应商品")) {//当前没有改商品
                        conformDialog.show();
                        conformDialog.setOrderInfoDetail(orderInfoDetail);
                    }else if (conformInStockInfo.getData().size()==1){//如果是单个，并且有商品，则判断是否大于现在成本价和入库的成本价是否相同
                        GoodsDetailInfo goodsDetailInfo = conformInStockInfo.getData().get(0);
                        sellPriceDialog.setPrice(goodsDetailInfo.getCost());
                        sellPriceDialog.setConformPrice(goodsDetailInfo.getUnit_sell_price());
                        sellPriceDialog.show();
                    } else {
                        showMessage(conformInStockInfo.getInfo());//没有条形码、当前维护总数不对（所有返回数组为空的其他错误）
                    }
                    isInStock = false;
                } else {
                    showMessage(conformInStockInfo.getInfo());//单个入库一次成功！
                    isInStock = true;
                }
            } else if (sign == 1) {//全部一起入库
                if (conformInStockInfo.isError()) {
                    showMessage(conformInStockInfo.getInfo());
                    isInStock = false;
                } else {
                    showMessage(conformInStockInfo.getInfo());
                    isInStock = true;
                    adapter.setCheck(isInStock);
                    Intent intent = new Intent();
                    setResult(Constants.RESULT_INSTOCK_DETAIL, intent);
                    finish();
                    return;
                }
            }
        }
        //改变当前的单个入库的状态
        adapter.setPositionCheck(isInStockPosition, isInStock);
    }

    @Override
    public String getGoodsId() {
        return goodsId;
    }

    @Override
    public int getDetailId() {
        return detail_id;
    }

    @Override
    public int getQuantity() {
        return adapter.getQuantity(isInStockPosition);
    }

    @Override
    public String getData() {
        if (adapter != null) {
            Gson gson = new Gson();
            if (adapter.getOrderInfoDetailList().size() != 0) {
                String data = gson.toJson(adapter.getOrderInfoDetailList());
                G.log("xxxxxxxxxxxx" + data);
                return data;
            }
        }
        return null;
    }

    /**
     * 商品单个确认按钮的点击事件
     *
     * @param position 点击的位置
     */
    @Override
    public void onClick(View view, int position) {
        isInStockPosition = position;
        orderInfoDetail = orderInfoDetailList.get(position);
        detail_id = orderInfoDetail.getId();
        if (TextUtils.isCodeBarEmpty(orderInfoDetail.getProduct_bar_code())) {
            showMessage("该商品没有条形码不可入库");
        } else {
            sign = 0;
            presenter.conformInStock();
        }
    }

    /**
     * 弹出对话框对应商品选择的监听回调事件
     *
     * @param position 对应选择商品的位置
     */
    @Override
    public void onChoose(View view, int position) {
        goodsId = goodsDetailInfoList.get(position).getId();
        presenter.changeStockById();
        sign = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
     private double salePrice;
    @Override
    public void onSaleChange(double price) {
        this.salePrice = price;
    }
    public double getSalePrice(){
        return salePrice;
    }
}
