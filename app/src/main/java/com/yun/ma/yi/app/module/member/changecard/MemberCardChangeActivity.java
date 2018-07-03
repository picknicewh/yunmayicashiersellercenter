package com.yun.ma.yi.app.module.member.changecard;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.module.common.view.MessagePopup;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunmayi.app.manage.R.id.tv_discount_rate;

public class MemberCardChangeActivity extends BaseActivity implements MemberCardChangeContract.View {
    /**
     * 会员名
     */
    @BindView(R.id.tv_member_name)
    ItemTextView tvMemberName;
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
     * 已领卡
     */
    @BindView(R.id.tv_card_type)
    ItemTextView tvCardType;
    /**
     * 更换卡
     */
    @BindView(R.id.tv_card_types)
    ItemTextView tvCardTypes;
    /**
     * 卡选择
     */
    @BindView(R.id.iv_card_type_arrow_down)
    ImageView ivCardTypeArrowDown;
    /**
     * 更换卡
     */
    @BindView(R.id.ll_card_types)
    LinearLayout llCardTypes;
    /**
     * 卡号
     */
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    /**
     * 卡号
     */
    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    /**
     * 优惠方式
     */
    @BindView(R.id.tv_discount_way)
    ItemTextView tvDiscountWay;
    /**
     * 折扣率
     */
    @BindView(tv_discount_rate)
    ItemTextView tvDiscountRate;
    /**
     * 查询的会员信息
     */
    private MemberInfo memberInfo;
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
    /**
     * 卡类型选项
     */
    private OptionsPickerView cardOptionsPickerView;
    /**
     * 卡信息列表
     */
    private List<MemberCardInfo> memberCardInfoList;
    /**
     * 数据处理类
     */
    private MemberCardChangePresenter presenter;
    /**
     * 卡号
     */
    private int cardId;
    /**
     * 弹窗
     */
    private MessagePopup messagePopup;
    /**
     * 小店编号
     */
    private String shopNumber = YunmayiApplication.getShop().getNumber();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_card_change;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.change_member_card);
        messagePopup = new MessagePopup(this);
        memberCardInfoList = new ArrayList<>();
        cardTypeList = new ArrayList<>();
        setView();
        presenter = new MemberCardChangePresenter(this, this);
        presenter.memberGradeList();
        cardOptionsPickerView = DateUtil.getOptionPickerView("选择卡类型", cardTypeList, cardPosition, this, cardOptionsSelectListener);
    }

    private void setView() {
        memberInfo = (MemberInfo) getIntent().getSerializableExtra("memberInfo");
        if (memberInfo != null) {
            cardId = memberInfo.getId();
            tvMemberName.setText(memberInfo.getUser_name());
            tvSex.setText(memberInfo.getUser_sex() == 1 ? "男" : "女");
            tvBirthday.setText(memberInfo.getUser_birthday());
            tvNumber.setText(memberInfo.getUser_mobile());
            tvCardType.setText(memberInfo.getCard_name());
            tvCardTypes.setText(memberInfo.getCard_name());
        }
    }

    /**
     * 设置会员卡折扣信息
     *
     * @param position
     */
    private void setCardInfo(int position) {
        MemberCardInfo memberCardInfo = memberCardInfoList.get(position);
        tvDiscountWay.setText(TextUtils.getDiscountType(memberCardInfo.getDiscount_type()));
        tvDiscountRate.setVisibility(memberCardInfo.getDiscount_rate() == 0 ? View.GONE : View.VISIBLE);
        tvDiscountRate.setText(String.valueOf(memberCardInfo.getDiscount_rate()));
    }

    private OptionsPickerView.OnOptionsSelectListener cardOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            cardPosition = options1;
            cardType = cardTypeList.get(cardPosition);
            tvCardTypes.setText(cardType);
            setCardInfo(options1);

        }
    };

    @OnClick(R.id.ll_card_types)
    public void cardType() {
        cardOptionsPickerView.show();
    }

    @Override
    public int getId() {
        return cardId;
    }

    @Override
    public String getUsername() {
        return tvMemberName.getText().toString();
    }

    @Override
    public int getUserSex() {
        int sex = 1;
        if (tvSex.getText().equals("男")) {
            sex = 1;
        }
        return sex;
    }

    @Override
    public String getUserBirthday() {
        return tvBirthday.getText().toString();
    }

    @Override
    public String getUserCertifyId() {
        return memberInfo.getUser_certify_id();
    }

    @Override
    public String getCardNumber() {
        String cardNumber = shopNumber + etCardNumber.getText().toString();
        return cardNumber;
    }

    @Override
    public String getCardName() {
        return tvCardTypes.getText().toString();
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public int getSellerId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public void setMemberInfo(List<MemberCardInfo> memberCardInfoList) {
        this.memberCardInfoList.clear();
        this.memberCardInfoList.addAll(memberCardInfoList);
        for (int i = 0; i < memberCardInfoList.size(); i++) {
            cardTypeList.add(memberCardInfoList.get(i).getCard_name());
            if (memberInfo.getCard_name().equals(memberCardInfoList.get(i).getCard_name())) {
                setCardInfo(i);
                cardPosition = i;

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    @OnClick(R.id.tv_cancel)
    public void cancel() {
        tvCardTypes.setText("");
        etCardNumber.setText("");
    }

    @OnClick(R.id.tv_card_number)
    public void cardNumber() {
        messagePopup.showAsDropDown(tvCardNumber);
        String message = "为了保持一唯一性，建议小店编号+流水号组成15位长度的卡号，其中小店编号为"+shopNumber+"已经默认添加，你只需编辑流水编号。";
        messagePopup.setMessage(message);
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        presenter.editMemberInfo();
    }

}
