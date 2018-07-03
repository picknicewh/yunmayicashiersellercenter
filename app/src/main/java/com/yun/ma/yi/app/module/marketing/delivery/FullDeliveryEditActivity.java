package com.yun.ma.yi.app.module.marketing.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.FullDeliveryInfo;
import com.yun.ma.yi.app.bean.GoodsChooseVo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.marketing.choose.mul.GoodsEditChooseActivity2;
import com.yun.ma.yi.app.module.marketing.choose.single.SingleGoodsAddChooseActivity;
import com.yun.ma.yi.app.module.marketing.choose.single.SingleGoodsEditChooseActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.DataFactory;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class FullDeliveryEditActivity extends BaseActivity implements FullDeliveryContract.ViewEdit, ConformDeleteDialog.DeleteCallBack {
    /**
     * 活动名称
     */
    @BindView(R.id.tv_activity_name)
    ItemEditText2 tvActivityName;
    /**
     * 会员专享
     */
    @BindView(R.id.tg_vip)
    ToggleButton tgVip;
    /**
     * 适用实体店
     */
    @BindView(R.id.tg_physical_store)
    ToggleButton tgPhysicalStore;
    /**
     * 适用蚂蚁小店
     */
    @BindView(R.id.tg_mayi_store)
    ToggleButton tgMayiStore;
    /**
     * 适用现金支付
     */
    @BindView(R.id.tg_cash_pay)
    ToggleButton tgCashPay;
    /**
     * 适用线上支付
     */
    @BindView(R.id.tg_online_pay)
    ToggleButton tgOnlinePay;
    /**
     * 开始日期
     */
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    /**
     * 结束日期
     */
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    /**
     * 指定商品范围
     */
    @BindView(R.id.tv_goods_range)
    TextView tvGoodsRange;
    /**
     * 购买金额
     */
    @BindView(R.id.tv_buy_price)
    ItemEditText2 tvBuyPrice;
    /**
     * 购买数量
     */
    @BindView(R.id.tv_buy_count)
    ItemEditText2 tvBuyCount;
    /**
     * 赠送商品
     */
    @BindView(R.id.tv_delivery_goods)
    TextView tvDeliveryGoods;
    /**
     * 删除
     */
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    /**
     * 开始时间选择
     */
    private TimePickerView startTimePickerView;
    /**
     * 结束时间选择
     */
    private TimePickerView endTimePickerView;
    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();

    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 添加或新增操作
     */
    private int operation;
    /**
     * 数据处理类
     */
    private FullDeliveryPresenter presenter;

    /**
     * 编辑时特价商品id，如果id=-1说明添加
     */
    private int bargainGoodsId=-1;
    /**
     * 选择范围商品列表
     */
    private String assign_product_ids;
    /**
     * 赠送的唯一商品id
     */
    private String delivery_product_id;
    /**
     * 编辑数据
     */
    private FullDeliveryInfo fullDeliveryInfo;
    private UserMessage userMessage;
    /**
     * 选中上商品选择选中的的数据列表
     */
    private ArrayList<GoodsChooseVo> goodsChooseVoList;
    /**
     * 删除对话框
     */
    private ConformDeleteDialog deleteDialog;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_full_delivery_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        deleteDialog =new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        userMessage = UserMessage.getInstance();
        goodsChooseVoList =new ArrayList<>();
        operation = getIntent().getIntExtra("operation", -1);
        setOperation();
        startTimePickerView = DateUtil.getTimePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getTimePickerView("结束时间", this, endCalendar, endListener);
        presenter = new FullDeliveryPresenter( this,this);
    }

    private void setOperation() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                setTitleText("新增满送商品");
               // endDate   =new Date(System.currentTimeMillis());
                //startDate = DateUtil.getDateBetweenDays(endDate,7);
                startDate =DateUtil.getNeedTime(0,0,0,0);
                endDate   = DateUtil.getNeedTime(0,0,0,7);
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                setTitleText("编辑满送商品");
                tvDelete.setVisibility(View.VISIBLE);
                initView();
                break;
        }
        tvEndTime.setText(DateUtil.getFormatHourDate(endDate));    //默认日期
        tvStartTime.setText(DateUtil.getFormatHourDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
    }

    private TimePickerView.OnTimeSelectListener startListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            startCalendar.setTime(date);
            startDate = date;
            String mDate = DateUtil.getFormatHourDate(date);
            tvStartTime.setText(mDate);
        }
    };
    private TimePickerView.OnTimeSelectListener endListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            endCalendar.setTime(date);
            endDate = date;
            String mDate = DateUtil.getFormatHourDate(endDate);
            tvEndTime.setText(mDate);
        }
    };

    @OnClick(R.id.rl_end_time)
    public void endTime() {
        endTimePickerView.show();
    }

    @OnClick(R.id.rl_start_time)
    public void startTime() {
        startTimePickerView.show();
    }

    @OnClick(R.id.tv_conform)
    public void save() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                presenter.addFullDeliveryInfo();
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                presenter.editFullDeliveryInfo();
                break;
        }
    }

    @OnClick(R.id.tv_delete)
    public void delete() {
        deleteDialog.show();
        deleteDialog.setContent(fullDeliveryInfo.getName()+"活动吗",-1);

    }

    @OnClick(R.id.tv_goods_range)
    public void goodsRange() {
        Intent intent = new Intent(this, GoodsEditChooseActivity2.class);
        intent.putParcelableArrayListExtra("goodsChooseVoList",goodsChooseVoList);
        intent.putExtra("isSelectAll",isSelectAll);
        if (operation == Constants.REQUEST_GOODS_ADD) {
            intent.putExtra("source",Constants.REQUEST_GOODS_ADD);

            startActivityForResult(intent,Constants.REQUEST_GOODS_ADD);
        } else if (operation == Constants.REQUEST_GOODS_EDIT) {
            intent.putExtra("activityId",bargainGoodsId);
            intent.putExtra("type","mjs");
            intent.putExtra("source",Constants.REQUEST_GOODS_EDIT);
            startActivityForResult(intent,Constants.REQUEST_GOODS_EDIT);
        }
    }
    private void initView(){
        fullDeliveryInfo = (FullDeliveryInfo) getIntent().getSerializableExtra("fullDeliveryInfo");
        if (!G.isEmteny(fullDeliveryInfo.getAssign_product_list())){
            goodsChooseVoList = DataFactory.jsonToArrayList(fullDeliveryInfo.getAssign_product_list(),GoodsChooseVo.class);
        }
        bargainGoodsId = fullDeliveryInfo.getId();
        tvActivityName.setText(fullDeliveryInfo.getName());
        tgVip.setChecked(fullDeliveryInfo.getIs_apply_member()==1);
        tgPhysicalStore.setChecked(fullDeliveryInfo.getIs_apply_entity()==1);
        tgMayiStore.setChecked(fullDeliveryInfo.getIs_apply_mini_shop()==1);
        tgCashPay.setChecked(fullDeliveryInfo.getIs_support_cash_pay()==1);
        tgOnlinePay.setChecked(fullDeliveryInfo.getIs_support_net_pay()==1);
        delivery_product_id = fullDeliveryInfo.getGive_product_id();
        assign_product_ids = fullDeliveryInfo.getAssign_product_list();
        tvBuyCount.setText(String.valueOf(fullDeliveryInfo.getGive_product_number()));
        tvBuyPrice.setText(PriceTransfer.chageMoneyToString(fullDeliveryInfo.getBuy_money()));
        String startTime=   DateUtil.getDate(Long.parseLong(fullDeliveryInfo.getActivity_start_time()));
        String endTime=   DateUtil.getDate(Long.parseLong(fullDeliveryInfo.getActivity_end_time()));
        endDate  =DateUtil.getTimeDate(endTime);
        startDate = DateUtil.getTimeDate(startTime);
        tvStartTime.setText(startTime);
        tvEndTime.setText(endTime);
    }

    @OnClick(R.id.tv_delivery_goods)
    public void deliveryGoods() {
        if (operation == Constants.REQUEST_GOODS_ADD) {
            Intent intent = new Intent(this, SingleGoodsAddChooseActivity.class);
            intent.putExtra("source",Constants.REQUEST_GOODS_ADD);
            if (delivery_product_id!=null){
                intent.putExtra("delivery_product_id",delivery_product_id);
            }
            startActivityForResult(intent,Constants.DELIVERY_ONE_GOODS_CHOOSE);
        } else if (operation == Constants.REQUEST_EDIT_BARGAIN) {
            Intent intent = new Intent(this, SingleGoodsEditChooseActivity.class);
            intent.putExtra("source",Constants.DELIVERY_ONE_GOODS_CHOOSE);
            intent.putExtra("delivery_product_id",delivery_product_id);
            intent.putExtra("activityId",bargainGoodsId);
            intent.putExtra("type","mjs");
            startActivityForResult(intent,Constants.DELIVERY_ONE_GOODS_CHOOSE);
        }
    }

    @Override
    public FullDeliveryInfo getData() {
        FullDeliveryInfo fullDeliveryInfo = new FullDeliveryInfo();
        fullDeliveryInfo.setId(getId());
        fullDeliveryInfo.setName(tvActivityName.getText());
        fullDeliveryInfo.setIs_apply_member(tgVip.isChecked()?1:0);
        fullDeliveryInfo.setIs_apply_entity(tgPhysicalStore.isChecked()?1:0);
        fullDeliveryInfo.setIs_apply_mini_shop(tgMayiStore.isChecked()?1:0);
        fullDeliveryInfo.setIs_support_cash_pay(tgCashPay.isChecked()?1:0);
        fullDeliveryInfo.setIs_support_net_pay(tgOnlinePay.isChecked()?1:0);
        fullDeliveryInfo.setAssign_product_ids(assign_product_ids);
        fullDeliveryInfo.setGive_product_id(delivery_product_id);
        String bugMoney = tvBuyPrice.getText().toString();
        String buyCount = tvBuyCount.getText();
        fullDeliveryInfo.setBuy_money(G.isEmteny(bugMoney)?0:Double.parseDouble(bugMoney)*100);
        fullDeliveryInfo.setGive_product_number(G.isEmteny(buyCount)?0:Integer.parseInt(buyCount));
        String startTime = DateUtil.getFormatTimeDate(startDate);
        String endTime = DateUtil.getFormatTimeDate(endDate);
        fullDeliveryInfo.setActivity_start_time(startTime);
        fullDeliveryInfo.setActivity_end_time(endTime);
        fullDeliveryInfo.setAdd_user_id(userMessage.getUser_id());
        fullDeliveryInfo.setAdd_user_name(userMessage.getUsername());
        fullDeliveryInfo.setEdit_user_id(userMessage.getUser_id());
        fullDeliveryInfo.setEdit_user_name(userMessage.getUsername());
        return fullDeliveryInfo;
    }

    @Override
    public int getId() {
        return bargainGoodsId;
    }

    @Override
    public void editSuccess(String message) {
        showMessage(message);
        Intent intent  = new Intent();
        setResult(Constants.RESULT_SUCCESS,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK==resultCode){
             if (requestCode==Constants.DELIVERY_ONE_GOODS_CHOOSE){
                delivery_product_id = data.getStringExtra("delivery_product_id");
                G.log("xxxxxxx->FullDeliveryEditActivity"+delivery_product_id);
            }else {
                 isSelectAll = data.getBooleanExtra("isSelectAll",false);
                 goodsChooseVoList = data.getParcelableArrayListExtra("goodsChooseVoList");
                 assign_product_ids = gson.toJson(goodsChooseVoList);
                 G.log("xxxxxxx->FullDeliveryEditActivity"+assign_product_ids);
             }
        }
    }
    private boolean isSelectAll ;
    private Gson gson = new Gson();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    @Override
    public void callBack(int position) {
        presenter.deleteFullDeliveryInfoById();
    }
}
