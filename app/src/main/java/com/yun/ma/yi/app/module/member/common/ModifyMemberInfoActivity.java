package com.yun.ma.yi.app.module.member.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.bean.MemberRechargeInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ItemEditText2;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.module.member.MemberManagerActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunmayi.app.manage.R.id.tv_report;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：充值/赠积分/修改卡密码/挂失
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ModifyMemberInfoActivity extends BaseActivity implements MemberModifyContract.View {
    /**
     * 会员名字
     */
    @BindView(R.id.tv_member_name)
    ItemTextView tvMemberName;
    /**
     * 手机号
     */
    @BindView(R.id.tv_member_number)
    ItemTextView tvMemberNumber;
    /**
     * 卡号
     */
    @BindView(R.id.tv_member_card)
    ItemTextView tvMemberCard;
    /**
     * 充值金额（元）/新密码
     */
    @BindView(R.id.tv_recharge_money)
    ItemEditText2 tvRechargeMoney;
    /**
     * 赠送金额（元）/确认新密码
     */
    @BindView(R.id.tv_grant_money)
    ItemEditText2 tvGrantMoney;
    /**
     * 赠送积分
     */
    @BindView(R.id.tv_give_integral)
    ItemEditText2 tvGiveIntegral;
    /**
     * 编辑框布局
     */
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    /**
     * 卡状态
     */
    @BindView(R.id.tv_card_status)
    ItemTextView tvCardStatus;
    /**
     * 卡内余额
     */
    @BindView(R.id.tv_card_balance)
    ItemTextView tvCardBalance;
    /**
     * 卡内积分
     */
    @BindView(R.id.tv_card_integral)
    ItemTextView tvCardIntegral;
    /**
     * 文本框布局
     */
    @BindView(R.id.ll_text)
    LinearLayout llText;
    /**
     * 挂失
     */
    @BindView(R.id.tv_report)
    TextView tvReport;
    /**
     * 来源
     */
    private int source;
    private MemberModifyPresenter presenter;
    private MemberInfo memberInfo;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        source = getIntent().getIntExtra("source", Constants.CRRD_RECHARGE);
        init();
        presenter = new MemberModifyPresenter(this, this);
    }

    private void setView() {
        memberInfo = (MemberInfo) getIntent().getSerializableExtra("memberInfo");
        tvMemberName.setText(memberInfo.getUser_name());
        tvMemberNumber.setText(memberInfo.getUser_mobile());
        tvMemberCard.setText(memberInfo.getCard_number());
        tvCardStatus.setText(memberInfo.getStatus() == 99 ? "正常" : "挂失");
        tvReport.setText(memberInfo.getStatus() == 99 ? "挂失" : "解挂");
        tvCardBalance.setText(PriceTransfer.chageMoneyToString(memberInfo.getCard_money()));
        tvCardIntegral.setText(String.valueOf(memberInfo.getCard_integral()));
    }

    private void init() {
        llText.setVisibility(source == Constants.REPORT_ACTIVITE ? View.VISIBLE : View.GONE);
        llEdit.setVisibility(source == Constants.REPORT_ACTIVITE ? View.GONE : View.VISIBLE);
        switch (source) {
            case Constants.CRRD_RECHARGE:
                setTitleTextId(R.string.recharge);
                break;
            case Constants.GRAMT_INTEGRAL:
                setTitleTextId(R.string.give_integral);
                tvRechargeMoney.setVisibility(View.GONE);
                tvGrantMoney.setVisibility(View.GONE);
                break;
            case Constants.MODIFY_PASSWORD:
                setTitleTextId(R.string.modify_password);
                tvRechargeMoney.setLabelText("新密码");
                tvGrantMoney.setLabelText("确认新密码");
                tvGiveIntegral.setVisibility(View.GONE);
                break;
            case Constants.REPORT_ACTIVITE:
                setTitleTextId(R.string.report_);
                break;
        }
        setView();
    }

    /**
     * 取消
     */
    @OnClick(R.id.tv_cancel)
    public void cancel() {
        tvGiveIntegral.setText("");
        tvGrantMoney.setText("");
        tvRechargeMoney.setText("");
        finish();
    }

    /**
     * 确认
     */
    @OnClick(R.id.tv_conform)
    public void conform() {
        switch (source) {
            //充值会员卡
            case Constants.CRRD_RECHARGE:
                    presenter.memberCardRecharge();
                break;
            case Constants.GRAMT_INTEGRAL:
                break;
            case Constants.MODIFY_PASSWORD:
                break;

        }
    }
    /**
     * 挂失
     */
    @OnClick(tv_report)
    public void report() {
            presenter.memberReportLost();
    }

    @Override
    public int getSellerId() {
        return memberInfo.getSeller_id();
    }

    @Override
    public int getId() {
        return memberInfo.getId();
    }
    /**
     * value=1 禁用
     * value=99 解挂
     */
    @Override
    public int getValue() {
        int value;
        if (memberInfo.getStatus() == 99) {
            value = 1;
        } else {
            value = 99;
        }
        return value;
    }

    @Override
    public MemberRechargeInfo getRechargeData() {
        MemberRechargeInfo memberRechargeInfo = new MemberRechargeInfo();
        UserMessage userinfo =UserMessage.getInstance();
        memberRechargeInfo.setUser_id(memberInfo.getUser_id());
        memberRechargeInfo.setSeller_id(userinfo.getUser_id());
        memberRechargeInfo.setCreate_name(userinfo.getUsername());
        memberRechargeInfo.setCreate_id(userinfo.getUser_id());
        memberRechargeInfo.setCard_number(memberInfo.getCard_number());
        String grantMoneyString = tvGrantMoney.getText().toString();
        String rechargeMoneyString = tvRechargeMoney.getText().toString();
        String integralString = tvGiveIntegral.getText().toString();
        int grantMoney = G.isEmteny(grantMoneyString)?0:Integer.parseInt(grantMoneyString);
        int rechargeMoney  = G.isEmteny(rechargeMoneyString)?0:Integer.parseInt(rechargeMoneyString);
        int integral =  G.isEmteny(integralString)?0:Integer.parseInt(integralString);
        int changeMoney = (rechargeMoney + grantMoney)*100;
        memberRechargeInfo.setChange_card_money(changeMoney);
        memberRechargeInfo.setChange_card_integral(integral);
        memberRechargeInfo.setTrade_type(1);
        return memberRechargeInfo;
    }

    @Override
    public String getResult() {
       String result =  memberInfo.getStatus() == 99 ? "挂失" : "解挂";
        return result;
    }

    @Override
    public void backHome() {
        Intent intent = new Intent(this, MemberManagerActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
