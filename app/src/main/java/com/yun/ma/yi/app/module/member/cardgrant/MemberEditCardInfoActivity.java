package com.yun.ma.yi.app.module.member.cardgrant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.member.MemberManagerActivity;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
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
 * 名称：编辑发放会员
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberEditCardInfoActivity extends BaseActivity implements OptionsPickerView.OnOptionsSelectListener, TimePickerView.OnTimeSelectListener, MemberCardGrantContract.ViewEdit {
    /**
     * 会员名
     */
    @BindView(R.id.tv_member_name)
    ItemEditText2 tvMemberName;
    /**
     * 性别
     */
    @BindView(R.id.tv_sex)
    TextView tvSex;
    /**
     * 生日
     */
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    /**
     * 手机号
     */
    @BindView(R.id.tv_number)
    TextView tvNumber;
    /**
     * 身份证
     */
    @BindView(R.id.tv_idard)
    ItemEditText2 tvIdard;
    /**
     * 卡类型
     */
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    /**
     * 卡号
     */
    @BindView(R.id.tv_card_number)
    ItemEditText2 tvCardNumber;
    /**
     * 优惠方式
     */
    @BindView(R.id.tv_discount_way)
    TextView tvDiscountWay;
    /**
     * 优惠率
     */
    @BindView(R.id.tv_discount_rate)
    TextView tvDiscountRate;
    @BindView(R.id.ll_discount_rate)
    LinearLayout llDiscountRate;
    /**
     * 取消
     */
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    /**
     * 确认
     */
    @BindView(R.id.tv_conform)
    TextView tvConform;
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
    /**
     * 数据处理类
     */
    private MemberCardGrantPresenter presenter;
    /**
     * 手机号码
     */
    private String number;
    /**
     * 卡类型选项
     */
    private OptionsPickerView cardOptionsPickerView;
    /**
     * 卡类型选择位置
     */
    private int cardPosition;
    /**
     * 选择的卡类型
     */
    private String cardType;
    /**
     * 卡列表
     */
    private List<String> cardTypeList;

    private List<MemberCardInfo> memberCardInfoList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_edit_membe_card_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_card_grant);
        number = getIntent().getStringExtra("number");
        tvNumber.setText(number);
        cardTypeList = new ArrayList<>();
        memberCardInfoList = new ArrayList<>();
        sexList = new ArrayList<>();
        sexList.add("女");
        sexList.add("男");
        sex = sexList.get(sexPosition);//初始值
        sexPickerView = DateUtil.getOptionPickerView("选择性别", sexList, sexPosition, this, this);
        timePickerView = DateUtil.getDatePickerView("选择生日", this, calendar, this);
        presenter = new MemberCardGrantPresenter(this, this);
        presenter.memberGradeList();
        cardOptionsPickerView = DateUtil.getOptionPickerView("选择卡类型", cardTypeList, cardPosition, this, cardOptionsSelectListener);
        G.log("xxxxxxxxxxxx"+DateUtil.getDateTime());
    }

    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        sexPosition = options1;
        sex = sexList.get(sexPosition);
        tvSex.setText(sex);
    }

    private OptionsPickerView.OnOptionsSelectListener cardOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            cardPosition = options1;
            cardType = cardTypeList.get(cardPosition);
            tvCardType.setText(cardType);
            MemberCardInfo memberCardInfo = memberCardInfoList.get(options1);
            tvDiscountWay.setText(TextUtils.getDiscountType(memberCardInfo.getDiscount_type()));
            llDiscountRate.setVisibility(memberCardInfo.getDiscount_rate()==0 ? View.GONE : View.VISIBLE);
            tvDiscountRate.setText(String.valueOf(memberCardInfo.getDiscount_rate()));
        }
    };

    @Override
    public void onTimeSelect(Date date, View v) {
        calendar.setTime(date);
        String mDate = DateUtil.getFormatDate(date);
        tvBirthday.setText(mDate);
    }

    @OnClick(R.id.tv_sex)
    public void sexChoose() {
        sexPickerView.show();
    }

    @OnClick(R.id.tv_birthday)
    public void birthday() {
        timePickerView.show();
    }

    @OnClick(R.id.tv_card_type)
    public void cardType() {
        cardOptionsPickerView.show();
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getMobile() {
        return tvNumber.getText().toString();
    }

    @Override
    public String getUserName() {
        return tvMemberName.getText().toString();
    }

    @Override
    public int getSex() {
        return sexPosition;
    }

    @Override
    public String getBirthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public String getCertifyId() {
        return tvIdard.getText().toString();
    }

    @Override
    public String getCardName() {
        return cardType;
    }

    @Override
    public String getExpireDateTime() {

        return DateUtil.getDateTime();
    }

    @Override
    public void setMemberInfo(List<MemberCardInfo> memberCardInfoList) {
        this.memberCardInfoList.clear();
        this.memberCardInfoList.addAll(memberCardInfoList);
        for (int i = 0; i < memberCardInfoList.size(); i++) {
            cardTypeList.add(memberCardInfoList.get(i).getCard_name());
        }
    }

    @Override
    public void NoMemberInfo() {
        G.showToast(this, "没有会员信息！");
    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this, MemberManagerActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        presenter.addCard();
    }

    @OnClick(R.id.tv_cancel)
    public void cancle() {
        tvSex.setText("");
        tvBirthday.setText("");
        tvMemberName.setText("");
        tvIdard.setText("");
        tvCardType.setText("");
        tvDiscountWay.setText("");
        tvDiscountRate.setText("");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
