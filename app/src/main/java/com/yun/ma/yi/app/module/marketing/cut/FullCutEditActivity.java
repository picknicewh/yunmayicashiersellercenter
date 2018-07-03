package com.yun.ma.yi.app.module.marketing.cut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.FullCutInfo;
import com.yun.ma.yi.app.bean.GoodsChooseVo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ConformDeleteDialog;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.marketing.choose.mul.GoodsEditChooseActivity2;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.DataFactory;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：新增/编辑满减界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FullCutEditActivity extends BaseActivity implements FullCutContract.ViewEdit, ConformDeleteDialog.DeleteCallBack {
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
     * 指定范围
     */
    @BindView(R.id.tv_goods_range)
    TextView tvGoodsRange;
    /**
     * 购买金额
     */
    @BindView(R.id.tv_buy_price)
    ItemEditText2 tvBuyPrice;
    /**
     * 减扣金额
     */
    @BindView(R.id.tv_cut_price)
    ItemEditText2 tvCutPrice;
    /**
     * 是否封顶
     */
    @BindView(R.id.tv_is_top)
    TextView tvIsTop;
    /**
     * 封顶价格
     */
    @BindView(R.id.tv_top_price)
    ItemEditText2 tvTopPrice;
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
     * 类型选项
     */
    private OptionsPickerView typeOptionPickerView;
    /**
     * 记录选中开始日期
     */
    private Calendar startCalendar = Calendar.getInstance();
    /**
     * 记录选中结束日期
     */
    private Calendar endCalendar = Calendar.getInstance();
    /**
     * 类型列表
     */
    private List<String> typeList;
    /**
     * 记录当前选中统计的位置
     */
    private int typePosition;
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
     * 选择范围商品列表
     */
    private String  assign_product_ids;
    /**
     * 数据处理类
     */
    private FullCutPresenter presenter;
    /**
     * 满减商品id(编辑时需要的id，默认为0)
     */
    private int fullcutGoodsId=-1;
    /**
     * 满减信息列表
     */
    private FullCutInfo fullCutInfo;
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
        return R.layout.activity_full_cut_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
      //  setSubTitleText("新增");
    //    setSubtitleClickListener(this);
        deleteDialog =new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        userMessage = UserMessage.getInstance();
        goodsChooseVoList = new ArrayList<>();
        operation = getIntent().getIntExtra("operation", -1);
        setOperation();
        typeList = new ArrayList<>();
        typeList.add("封顶");
        typeList.add("不封顶");
        startTimePickerView = DateUtil.getTimePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getTimePickerView("结束时间", this, endCalendar, endListener);
        typeOptionPickerView = DateUtil.getOptionPickerView("选择类型", typeList, typePosition, this, typeListener);
        presenter = new FullCutPresenter(this,this);
    }
    private void setOperation() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                setTitleText("新增满减商品");
              //  endDate   =new Date(System.currentTimeMillis());
                //startDate = DateUtil.getDateBetweenDays(endDate,7);
                startDate =DateUtil.getNeedTime(0,0,0,0);
                endDate   = DateUtil.getNeedTime(0,0,0,7);
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                setTitleText("编辑满减商品");
                tvDelete.setVisibility(View.VISIBLE);
                initView();
                break;
        }
        tvEndTime.setText(DateUtil.getFormatHourDate(endDate));    //默认日期
        tvStartTime.setText(DateUtil.getFormatHourDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
    }
    private void initView(){
        fullCutInfo = (FullCutInfo) getIntent().getSerializableExtra("fullCutInfo");
        assign_product_ids = fullCutInfo.getAssign_product_list();
        if (!G.isEmteny(fullCutInfo.getAssign_product_list())){
            goodsChooseVoList = DataFactory.jsonToArrayList(fullCutInfo.getAssign_product_list(),GoodsChooseVo.class);
        }
        fullcutGoodsId = fullCutInfo.getId();
        tvActivityName.setText(fullCutInfo.getName());
        tgVip.setChecked(fullCutInfo.getIs_apply_member()==1);
        tgPhysicalStore.setChecked(fullCutInfo.getIs_apply_entity()==1);
        tgMayiStore.setChecked(fullCutInfo.getIs_apply_mini_shop()==1);
        tgCashPay.setChecked(fullCutInfo.getIs_support_cash_pay()==1);
        tgOnlinePay.setChecked(fullCutInfo.getIs_support_net_pay()==1);
        tvCutPrice.setText(PriceTransfer.chageMoneyToString(fullCutInfo.getMinus_money()));
        tvBuyPrice.setText(PriceTransfer.chageMoneyToString(fullCutInfo.getBuy_money()));
        tvTopPrice.setVisibility(fullCutInfo.getIs_upper_limit()==1?View.VISIBLE:View.GONE);
        tvTopPrice.setText(PriceTransfer.chageMoneyToString(fullCutInfo.getUpper_limit_money()));
        typePosition=fullCutInfo.getIs_upper_limit()==1?0:1;
        tvIsTop.setText(typePosition==0?"封顶":"不封顶");
        String startTime=   DateUtil.getDate(Long.parseLong(fullCutInfo.getActivity_start_time()));
        String endTime=   DateUtil.getDate(Long.parseLong(fullCutInfo.getActivity_end_time()));
        endDate  =DateUtil.getTimeDate(endTime);
        startDate = DateUtil.getTimeDate(startTime);
        tvStartTime.setText(startTime);
        tvEndTime.setText(endTime);
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
    private OptionsPickerView.OnOptionsSelectListener typeListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            typePosition = options1;
            tvIsTop.setText(typeList.get(typePosition));
            tvTopPrice.setVisibility(typePosition==0?View.VISIBLE:View.GONE);
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

    @OnClick(R.id.rl_is_top)
    public void isTop() {
        typeOptionPickerView.show();
    }

    @OnClick(R.id.tv_conform)
    public void save() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                presenter.addFullCutInfo();
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                 presenter.editFullCutInfo();
                break;
        }
    }

    @OnClick(R.id.tv_delete)
    public void delete() {
        //deleteDialog.setContent();
        deleteDialog.show();
        deleteDialog.setContent(fullCutInfo.getName()+"活动吗",-1);

    }

    @OnClick(R.id.tv_goods_range)
    public void goodsRange() {
        Intent intent = new Intent(this, GoodsEditChooseActivity2.class);
        intent.putParcelableArrayListExtra("goodsChooseVoList",goodsChooseVoList);
        intent.putExtra("isSelectAll",isSelectAll);
        if (operation == Constants.REQUEST_GOODS_ADD) {
            intent.putExtra("source", Constants.REQUEST_GOODS_ADD);

            startActivityForResult(intent, Constants.CUT_GOODS_CHOOSE);
        } else if (operation == Constants.REQUEST_GOODS_EDIT) {
            intent.putExtra("source", Constants.REQUEST_GOODS_EDIT);
            intent.putExtra("activityId",fullcutGoodsId);
            intent.putExtra("type","mjj");
            startActivityForResult(intent, Constants.REQUEST_GOODS_EDIT);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            goodsChooseVoList = data.getParcelableArrayListExtra("goodsChooseVoList");
            isSelectAll = data.getBooleanExtra("isSelectAll",false);
            assign_product_ids = gson.toJson(goodsChooseVoList);

            G.log("xxxxxxx->FullCutEditActivity"+assign_product_ids);
        }
    }
    private boolean isSelectAll ;
    private Gson gson = new Gson();
    @Override
    public FullCutInfo getData() {
        FullCutInfo fullCutInfo = new FullCutInfo();
        fullCutInfo.setId(getId());
        fullCutInfo.setName(tvActivityName.getText());
        fullCutInfo.setIs_apply_member(tgVip.isChecked()?1:0);
        fullCutInfo.setIs_apply_entity(tgPhysicalStore.isChecked()?1:0);
        fullCutInfo.setIs_apply_mini_shop(tgMayiStore.isChecked()?1:0);
        fullCutInfo.setIs_support_cash_pay(tgCashPay.isChecked()?1:0);
        fullCutInfo.setIs_support_net_pay(tgOnlinePay.isChecked()?1:0);
        fullCutInfo.setAssign_product_ids(assign_product_ids);
        String minusMoney = tvCutPrice.getText().toString();
        String bugMoney = tvBuyPrice.getText().toString();
        String topMoney = tvTopPrice.getText().toString();
        fullCutInfo.setMinus_money(G.isEmteny(minusMoney)?0:Double.parseDouble(minusMoney)*100);
        fullCutInfo.setBuy_money(G.isEmteny(bugMoney)?0:Double.parseDouble(bugMoney)*100);
        fullCutInfo.setIs_upper_limit(typePosition==0?1:0);
        if (typePosition==0){
            fullCutInfo.setUpper_limit_money(G.isEmteny(topMoney)?0:Double.parseDouble(topMoney)*100);
        }
        String startTime = DateUtil.getFormatTimeDate(startDate);
        String endTime = DateUtil.getFormatTimeDate(endDate);
        fullCutInfo.setActivity_start_time(startTime);
        fullCutInfo.setActivity_end_time(endTime);
        fullCutInfo.setAdd_user_id(userMessage.getUser_id());
        fullCutInfo.setAdd_user_name(userMessage.getUsername());
        fullCutInfo.setEdit_user_id(userMessage.getUser_id());
        fullCutInfo.setEdit_user_name(userMessage.getUsername());
        return fullCutInfo;
    }
     private UserMessage userMessage;
    @Override
    public int getId() {
        return fullcutGoodsId;
    }

    @Override
    public void editSuccess(String message) {
        showMessage(message);
        Intent intent  = new Intent();
        setResult(Constants.RESULT_SUCCESS,intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    @Override
    public void callBack(int position) {
        presenter.deleteFullInfoById();
    }
}
