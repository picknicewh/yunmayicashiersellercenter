package com.yun.ma.yi.app.module.member.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.module.report.goods.trade.GoodsTradeReportActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：会员信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoActivity extends BaseActivity implements MemberInfoDetailContract.InfoView, OptionsPickerView.OnOptionsSelectListener, TimePickerView.OnTimeSelectListener {
    /**
     * 会员名
     */
    @BindView(R.id.tv_member_name)
    ItemEditText2 tvMemberName;
    /**
     * 性别
     */
    @BindView(R.id.tv_sex)
    ItemTextView tvSex;
    /**
     * 生日
     */
    @BindView(R.id.tv_birthday)
    ItemTextView tvBirthday;
    /**
     * 手机号
     */
    @BindView(R.id.tv_number)
    ItemTextView tvNumber;
    /**
     * 身份证
     */
    @BindView(R.id.tv_idard)
    ItemEditText2 tvIdard;
    /**
     * 卡类型
     */
    @BindView(R.id.tv_card_type)
    ItemTextView tvCardType;
    /**
     * 卡号
     */
    @BindView(R.id.tv_card_number)
    ItemTextView tvCardNumber;
    /**
     * 优惠方式
     */
    @BindView(R.id.tv_discount_way)
    ItemTextView tvDiscountWay;
    /**
     * 折扣率
     */
    @BindView(R.id.tv_discount_rate)
    ItemTextView tvDiscountRate;
    /**
     * 余额
     */
    @BindView(R.id.tv_balance)
    ItemTextView tvBalance;
    /**
     * 积分
     */
    @BindView(R.id.tv_integral)
    ItemTextView tvIntegral;
    /**
     * 卡状态
     */
    @BindView(R.id.tv_card_status)
    ItemTextView tvCardStatus;
    /**
     * 交易明细
     */
    @BindView(R.id.tv_trade_detail)
    ItemTextView tvTradeDetail;
    /**
     * 余额箭头
     */
    @BindView(R.id.iv_balance_arrow_left)
    ImageView ivBalanceArrowLeft;
    /**
     * 积分 箭头
     */
    @BindView(R.id.iv_integral_arrow_left)
    ImageView ivIntegralArrowLeft;
    /**
     * 交易箭头
     */
    @BindView(R.id.iv_trade_arrow_left)
    ImageView ivTradeArrowLeft;
    /**
     * 余额
     */
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    /**
     * 积分
     */
    @BindView(R.id.ll_integral)
    LinearLayout llIntegral;
    /**
     *交易详情
     */
    @BindView(R.id.ll_trade_detail)
    LinearLayout llTradeDetail;
    /**
     * 会员信息
     */
    private MemberInfo memberInfo;
    private MemberInfoDetailPresenter presenter;
    /**
     * 选择性别
     */
    private OptionsPickerView sexPickerView;
    /**
     * 性别列表
     */
    private List<String> sexList;
    /**
     * 选中的性别
     */
    private String sex;
    private int sexPosition = 0;
    /**
     * 选择时间
     */
    private TimePickerView timePickerView;
    /**
     * 默认日期
     */
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_info);
        presenter = new MemberInfoDetailPresenter(this, this);
        initData();
        intEditData();

    }

    private void initData() {
        memberInfo = (MemberInfo) getIntent().getSerializableExtra("memberInfo");
        if (memberInfo != null) {
            tvMemberName.setText(memberInfo.getUser_name());
            tvSex.setText(memberInfo.getUser_sex() == 1 ? "男" : "女");
            tvBirthday.setText(memberInfo.getUser_birthday());
            tvNumber.setText(memberInfo.getUser_mobile());
            tvIdard.setText(memberInfo.getUser_certify_id());
            tvCardType.setText(memberInfo.getCard_name());
            tvCardNumber.setText(memberInfo.getCard_number());
            tvBalance.setText(PriceTransfer.chageMoneyToString(memberInfo.getCard_money()));
            tvCardStatus.setText(memberInfo.getStatus() == 99 ? "正常" : "挂失");
            tvIntegral.setText(String.valueOf(memberInfo.getCard_integral()));
            tvTradeDetail.setText(memberInfo.getCount() + "笔");
            ivBalanceArrowLeft.setVisibility(memberInfo.getCard_money() == 0 ? View.GONE : View.VISIBLE);
           // llBalance.setEnabled(memberInfo.getCard_money() != 0);
            ivIntegralArrowLeft.setVisibility(memberInfo.getCard_integral() == 0 ? View.GONE : View.VISIBLE);
         //   llIntegral.setEnabled(memberInfo.getCard_integral()!=0);
            ivTradeArrowLeft.setVisibility(memberInfo.getCount() == 0 ? View.GONE : View.VISIBLE);
            llTradeDetail.setEnabled(memberInfo.getCount()!=0);
            tvDiscountWay.setText(TextUtils.getDiscountType(memberInfo.getDiscount_type()));
            tvDiscountRate.setVisibility(tvDiscountWay.getText().toString().equals("折扣率") ? View.VISIBLE : View.GONE);
            tvDiscountRate.setText(String.valueOf(memberInfo.getDiscount_rate()));
        }

    }

    private void intEditData() {
        sexList = new ArrayList<>();
        sexList.add("女");
        sexList.add("男");
        sexPickerView = DateUtil.getOptionPickerView("选择性别", sexList, sexPosition, this, this);
        timePickerView = DateUtil.getDatePickerView("选择生日", this, calendar, this);
    }

    /**
     * 编辑---》选择性别
     */
    @OnClick(R.id.ll_sex)
    public void sex() {
        sexPickerView.show();
    }

    /**
     * 编辑---》选择生日
     */
    @OnClick(R.id.ll_birthday)
    public void birthday() {
        timePickerView.show();
    }

    @OnClick(R.id.tv_cancel)
    public void cance1() {
        initData();
        finish();
    }
    @OnClick(R.id.tv_conform)
    public void conform() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_CARD_EDIT)){
            presenter.editMemberInfo();
        }else {
            showMessage("你没有编辑会员卡的权限！");
        }
    }

    /**
     * 余额明细
     */
    @OnClick(R.id.ll_balance)
    public void balance() {
        Intent intent = new Intent(this, MemberInfoDetailActivity.class);
        intent.putExtra("source", Constants.BALANCE_HAPPEND_DETAIL);
        intent.putExtra("user_id", memberInfo.getUser_id());
        startActivity(intent);
    }

    /**
     * 积分明细
     */
    @OnClick(R.id.ll_integral)
    public void integral() {
        Intent intent = new Intent(this, MemberInfoDetailActivity.class);
        intent.putExtra("source", Constants.INTEGRAL_HAPPEND_DETAIL);
        intent.putExtra("user_id", memberInfo.getUser_id());
        startActivity(intent);
    }

    @OnClick(R.id.ll_trade_detail)
    public void tradeDetail() {
        Intent intent = new Intent(this, GoodsTradeReportActivity.class);
        intent.putExtra("form", Constants.MEMBER_TRADE_DETAIL);
        intent.putExtra("user_id", memberInfo.getUser_id());
        startActivity(intent);
    }

    @Override
    public int getId() {
        return memberInfo.getId();
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getUsername() {
        return tvMemberName.getText().toString();
    }

    @Override
    public int getUserSex() {
        return sexPosition;
    }

    @Override
    public String getUserBirthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public String getUserCertifyId() {
        return tvIdard.getText().toString();
    }
    @Override
    public void NoMemberInfo() {
        G.showToast(this, "没有会员信息！");
    }

    @Override
    public void back() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        sexPosition = options1;
        sex = sexList.get(sexPosition);
        tvSex.setText(sex);
    }
    @Override
    public void onTimeSelect(Date date, View v) {
        calendar.setTime(date);
        String mDate = DateUtil.getFormatDate(date);
        tvBirthday.setText(mDate);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
