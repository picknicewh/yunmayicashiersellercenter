package com.yun.ma.yi.app.module.marketing.bargain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.BargainGoodsInfo;
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
 * 名称：特价商品新增/编辑界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BargainGoodsEditActivity extends BaseActivity implements BargainGoodsContract.ViewEdit, ConformDeleteDialog.DeleteCallBack {
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
     * 特价类型
     */
    @BindView(R.id.tv_bargain_type)
    TextView tvBargainType;
    /**
     * 折扣率/特价（元）
     */
    @BindView(R.id.tv_bargain)
    ItemEditText2 tvBargain;
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
     * 数据处理类
     */
    private BargainGoodsPresenter presenter;
    /**
     * 编辑时特价商品id，如果id=-1说明添加
     */
    private int bargainGoodsId = -1;
    private BargainGoodsInfo bargainGoodsInfo;
    /**
     * 特价商品id
     */
    private String assign_product_ids;
    /**
     * 删除对话框
     */
    private ConformDeleteDialog deleteDialog;
    /**
     * 选中上商品选择选中的的数据列表
     */
    private ArrayList<GoodsChooseVo> goodsChooseVoList;
    private Gson gson;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_bargain_goods_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        typeList = new ArrayList<>();
        typeList.add("特价（元）");
        typeList.add("折扣率%");
        deleteDialog = new ConformDeleteDialog(this);
        deleteDialog.setDeleteCallBack(this);
        gson = new Gson();
        operation = getIntent().getIntExtra("operation", -1);
        goodsChooseVoList = new ArrayList<>();

        setOperation();
        startTimePickerView = DateUtil.getTimePickerView("开始时间", this, startCalendar, startListener);
        endTimePickerView = DateUtil.getTimePickerView("结束时间", this, endCalendar, endListener);
        typeOptionPickerView = DateUtil.getOptionPickerView("选择类型", typeList, typePosition, this, typeListener);
        presenter = new BargainGoodsPresenter(this, this);

    }

    private void setOperation() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                setTitleText("新增特价商品");
                startDate = DateUtil.getNeedTime(0, 0, 0, 0);
                endDate = DateUtil.getNeedTime(0, 0, 0, 7);
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                setTitleText("编辑特价商品");
                tvDelete.setVisibility(View.VISIBLE);
                initView();
                break;
        }
        tvEndTime.setText(DateUtil.getFormatHourDate(endDate));    //默认日期
        tvStartTime.setText(DateUtil.getFormatHourDate(startDate));  //默认日期的后7天
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
    }

    private void initView() {
        bargainGoodsInfo = (BargainGoodsInfo) getIntent().getSerializableExtra("bargainGoodsInfo");
        if (!G.isEmteny(bargainGoodsInfo.getAssign_product_list())){
            goodsChooseVoList = DataFactory.jsonToArrayList(bargainGoodsInfo.getAssign_product_list(), GoodsChooseVo.class);
        }
        assign_product_ids = bargainGoodsInfo.getAssign_product_list();

        bargainGoodsId = bargainGoodsInfo.getId();
        tvActivityName.setText(bargainGoodsInfo.getName());
        tgVip.setChecked(bargainGoodsInfo.getIs_apply_member() == 1);
        tgPhysicalStore.setChecked(bargainGoodsInfo.getIs_apply_entity() == 1);
        tgMayiStore.setChecked(bargainGoodsInfo.getIs_apply_mini_shop() == 1);
        tgCashPay.setChecked(bargainGoodsInfo.getIs_support_cash_pay() == 1);
        typePosition = bargainGoodsInfo.getSpecial_type() == 1 ? 0 : 1;
        tvBargainType.setText(typeList.get(typePosition));
        tvBargain.setLabelText(typeList.get(typePosition));
        if (typePosition == 0) {
            tvBargain.setText(PriceTransfer.chageMoneyToString(bargainGoodsInfo.getSpecial_price()));
        } else {
            tvBargain.setText(String.valueOf(bargainGoodsInfo.getRate()));
        }
        tvBargain.setText(typePosition == 0 ? PriceTransfer.chageMoneyToString(bargainGoodsInfo.getSpecial_price()) : String.valueOf(bargainGoodsInfo.getRate()));
        String startTime = DateUtil.getDate(Long.parseLong(bargainGoodsInfo.getActivity_start_time()));
        String endTime = DateUtil.getDate(Long.parseLong(bargainGoodsInfo.getActivity_end_time()));
        endDate = DateUtil.getTimeDate(endTime);
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
            tvBargainType.setText(typeList.get(options1));
            tvBargain.setLabelText(typeList.get(options1));
        }
    };


    @OnClick(R.id.rl_bargain_type)
    public void bargainType() {
        typeOptionPickerView.show();
    }

    @OnClick(R.id.rl_start_time)
    public void startTime() {
        startTimePickerView.show();
    }

    @OnClick(R.id.rl_end_time)
    public void endTime() {
        endTimePickerView.show();
    }

    @OnClick(R.id.tv_conform)
    public void save() {
        switch (operation) {
            case Constants.REQUEST_ADD_BARGAIN:
                presenter.addBargainGoodsInfo();
                break;
            case Constants.REQUEST_EDIT_BARGAIN:
                presenter.editBargainGoodsInfo();
                break;
        }
    }

    @OnClick(R.id.tv_delete)
    public void delete() {
        deleteDialog.show();
        deleteDialog.setContent(bargainGoodsInfo.getName() + "活动吗", -1);
    }

    @OnClick(R.id.tv_goods_range)
    public void goodsRange() {
        Intent intent = new Intent(this, GoodsEditChooseActivity2.class);
        intent.putParcelableArrayListExtra("goodsChooseVoList", goodsChooseVoList);
        intent.putExtra("isSelectAll",isSelectAll);
        if (operation == Constants.REQUEST_GOODS_ADD) {
            intent.putExtra("source", Constants.REQUEST_GOODS_ADD);
            startActivityForResult(intent, Constants.BARGAIN_GOODS_CHOOSE);
        } else if (operation == Constants.REQUEST_GOODS_EDIT) {
            intent.putExtra("source", Constants.REQUEST_GOODS_EDIT);
            intent.putExtra("activityId", bargainGoodsId);
            intent.putExtra("type", "specialProduct");
            startActivityForResult(intent, Constants.REQUEST_GOODS_EDIT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            goodsChooseVoList = data.getParcelableArrayListExtra("goodsChooseVoList");
            isSelectAll = data.getBooleanExtra("isSelectAll",false);
            assign_product_ids = gson.toJson(goodsChooseVoList);

        }
    }
    private boolean isSelectAll ;
    private UserMessage userMessage = UserMessage.getInstance();

    @Override
    public BargainGoodsInfo getData() {
        BargainGoodsInfo bargainGoodsVo = new BargainGoodsInfo();
        bargainGoodsVo.setId(bargainGoodsId);
        bargainGoodsVo.setName(tvActivityName.getText());
        bargainGoodsVo.setIs_apply_member(tgVip.isChecked() ? 1 : 0);
        bargainGoodsVo.setIs_apply_entity(tgPhysicalStore.isChecked() ? 1 : 0);
        bargainGoodsVo.setIs_apply_mini_shop(tgMayiStore.isChecked() ? 1 : 0);
        bargainGoodsVo.setIs_support_cash_pay(tgCashPay.isChecked() ? 1 : 0);
        bargainGoodsVo.setIs_support_net_pay(tgOnlinePay.isChecked() ? 1 : 0);
        bargainGoodsVo.setAssign_product_ids(assign_product_ids);

        bargainGoodsVo.setSpecial_type(typePosition == 0 ? 1 : 2);
        String bg = tvBargain.getText();
        if (typePosition == 0) {
            double bargain = G.isEmteny(bg) ? 0 : Double.parseDouble(bg) * 100;
            bargainGoodsVo.setSpecial_price(bargain);
        } else {
            double rate = G.isEmteny(bg) ? 0 : Double.parseDouble(bg);
            bargainGoodsVo.setRate(rate);
        }
        String startTime = DateUtil.getFormatTimeDate(startDate);
        String endTime = DateUtil.getFormatTimeDate(endDate);
        bargainGoodsVo.setActivity_start_time(startTime);
        bargainGoodsVo.setActivity_end_time(endTime);
        bargainGoodsVo.setAdd_user_id(userMessage.getUser_id());
        bargainGoodsVo.setAdd_user_name(userMessage.getUsername());
        bargainGoodsVo.setEdit_user_id(userMessage.getUser_id());
        bargainGoodsVo.setEdit_user_name(userMessage.getUsername());
        return bargainGoodsVo;
    }

    @Override
    public int getId() {
        return bargainGoodsId;
    }

    @Override
    public void editSuccess(String message) {
        showMessage(message);
        Intent intent = new Intent();
        setResult(Constants.RESULT_SUCCESS, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void callBack(int position) {
        presenter.deleteBargainGoodsInfoById();
    }
}
