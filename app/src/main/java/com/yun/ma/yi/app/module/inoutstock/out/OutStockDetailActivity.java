package com.yun.ma.yi.app.module.inoutstock.out;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.module.common.InStockChooseDialog;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：退货入库详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OutStockDetailActivity extends BaseActivity implements OutStockContract.ViewDetail, InStockChooseDialog.onItemChooseListener {

    /**
     * 下单时间
     */
    @BindView(R.id.tv_order_time)
    ItemTextView tvOrderTime;
    /**
     * 商品名称
     */
    @BindView(R.id.tv_goods_name)
    ItemTextView tvGoodsName;
    /**
     * 商品规格
     */
    @BindView(R.id.tv_goods_rule)
    ItemTextView tvGoodsRule;
    /**
     * 商品单位
     */
    @BindView(R.id.tv_goods_unit)
    ItemTextView tvGoodsUnit;
    /**
     * 下单数量
     */
    @BindView(R.id.tv_order_count)
    ItemTextView tvOrderCount;
    /**
     * 异常数量
     */
    @BindView(R.id.tv_bad_count)
    ItemTextView tvBadCount;
    /**
     * 退货数量
     */
    @BindView(R.id.tv_adjust_count)
    TextView tvAdjustCount;
    /**
     * 备注
     */
    @BindView(R.id.tv_remark)
    ItemEditText2 tvRemark;
    /**
     * 退货原因
     */
    @BindView(R.id.tv_return_reason)
    TextView tvReturnReason;
    /**
     * 退款方式
     */
    @BindView(R.id.tv_return_way)
    TextView tvReturnWay;
    /**
     * 已经退货的数量
     */
    @BindView(R.id.tv_back_count)
    ItemTextView tvBackCount;
    @BindView(R.id.tv_conform)
    TextView tvConform;
    /**
     * 退货数量
     */
    private int count = 0;
    /**
     * 数据处理类
     */
    private OutStockPresenter presenter;
    /**
     * 最大退货数量
     */
    private int maxCount;
    /**
     * 退货原因选择
     */
    private OptionsPickerView reasonOptionsPickerView;
    /**
     * 退货原因选中位置
     */
    private int reasonPosition = 0;
    /**
     * 退货原因
     */
    private String reason;
    /**
     * 退款方式
     */
    private OptionsPickerView wayOptionsPickerView;
    /**
     * 退款方式选中位置
     */
    private int wayPosition = 0;
    /**
     * 退款方式
     */
    private int way;
    private int detail_id;
    /**
     * 订单选择对话框
     */
    private InStockChooseDialog dialog;
    /**
     * 选择的商品id
     */
    private String goodsId;
    /**
     * 选择商品列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_out_stock_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.out_stock_detail);
        dialog = new InStockChooseDialog(this);
        dialog.setOnItemChooseListener(this);
        initData();
        presenter = new OutStockPresenter(this, this);
    }

    private void initData() {
        OrderInfoDetail orderInfoDetail = (OrderInfoDetail) getIntent().getSerializableExtra("orderInfoDetail");
        if (orderInfoDetail != null) {
            tvOrderTime.setText(orderInfoDetail.getCreate_datetime());
            tvGoodsName.setText(orderInfoDetail.getProduct_title());
            tvGoodsRule.setText(orderInfoDetail.getProduct_spec());
            tvGoodsUnit.setText(orderInfoDetail.getProduct_unit());
            tvBadCount.setText(String.valueOf(orderInfoDetail.getOut_of_stock_num()));
            tvOrderCount.setText(String.valueOf(orderInfoDetail.getQuantity()));
            tvBackCount.setText(String.valueOf(orderInfoDetail.getAlready_return()));
            maxCount = orderInfoDetail.getQuantity() - orderInfoDetail.getOut_of_stock_num() - orderInfoDetail.getAlready_return();
            detail_id = orderInfoDetail.getId();
            tvConform.setEnabled(orderInfoDetail.getAlready_return()==0);
            tvConform.setBackgroundResource(orderInfoDetail.getAlready_return()==0?R.drawable.round_red_btn:R.drawable.round_grey_btn);
        }
        reasonOptionsPickerView = DateUtil.getOptionPickerView("选择退货原因", TextUtils.getReturnGoodsReason(), reasonPosition, this, reasonListener);
        wayOptionsPickerView = DateUtil.getOptionPickerView("选择退款方式", TextUtils.getReturnMoneyWay(), wayPosition, this, wayListener);
        reason = TextUtils.getReturnGoodsReason().get(reasonPosition);//默认日期不好，传递日期不好
        way = 1;//默认现金支付，传递1
    }

    private OptionsPickerView.OnOptionsSelectListener reasonListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            reasonPosition = options1;
            reason = TextUtils.getReturnGoodsReason().get(options1);
            tvReturnReason.setText(reason);

        }
    };
    private OptionsPickerView.OnOptionsSelectListener wayListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            wayPosition = options1;
            way = wayPosition + 1;
            tvReturnWay.setText(TextUtils.getReturnMoneyWay().get(options1));
        }
    };

    @OnClick(R.id.iv_minus)
    public void minus() {
        count--;
        if (count <= 0) {
            count = 0;
        }
        tvAdjustCount.setText(String.valueOf(count));
    }

    @OnClick(R.id.iv_add)
    public void add() {
        count++;
        if (count > maxCount) {
            count--;
            showMessage("你已经达到最大退货数量了！");
        }
        tvAdjustCount.setText(String.valueOf(count));
    }

    @Override
    public int getDetailId() {
        return detail_id;
    }

    @Override
    public int getQuantity() {
        return count;
    }

    @Override
    public int getReturnWay() {
        return way;
    }

    @Override
    public String getReturnReason() {
        return reason;
    }

    @Override
    public String getBuyerRemark() {
        return tvRemark.getText();
    }

    @Override
    public String getItemId() {
        return goodsId;
    }

    @Override
    public void setConformInStockInfo(ConformInStockInfo conformInStockInfo) {
        if (conformInStockInfo.getData().size() > 1) {
            dialog.show();
            goodsDetailInfoList = conformInStockInfo.getData();
            dialog.setGoodsDetailInfoList(goodsDetailInfoList);
        } else {
            showMessage(conformInStockInfo.getInfo());
        }
    }

    @Override
    public void back() {
        finish();
    }

    @OnClick(R.id.tv_return_reason)
    public void returnReason() {
        reasonOptionsPickerView.show();
    }

    @OnClick(R.id.tv_return_way)
    public void returnWay() {
        wayOptionsPickerView.show();
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        presenter.conformOutStockDetail();
    }

    @Override
    public void onChoose(View view, int position) {
        goodsId = goodsDetailInfoList.get(position).getId();
        presenter.conformOutStockDetailById();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }
}
