package com.yun.ma.yi.app.module.member.common;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.member.changecard.MemberCardChangeActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员卡充值/会员赠积分/修改卡密码/挂失与激活
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberSearchActivity extends BaseActivity implements MemberCardSearchContract.View {

    /**
     * 手机号/卡号编辑框
     */
    @BindView(R.id.et_card_id)
    ClearEditText etCardId;
    /**
     * 来源
     */
    private int source;
    /**
     * 数据处理类
     */
    private MemberCardSearchPresenter presenter;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_recharge;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        source = getIntent().getIntExtra("source", Constants.CRRD_RECHARGE);
        if (source==Constants.CRRD_RECHARGE){
            setTitleTextId(R.string.member_card_recharge);
        }else if (source==Constants.GRAMT_INTEGRAL){
            setTitleTextId(R.string.member_give_integral);
        }else if (source==Constants.MODIFY_PASSWORD){
            setTitleTextId(R.string.member_modify_password);
        }else if (source==Constants.REPORT_ACTIVITE){
            setTitleTextId(R.string.member_report_activate);
        }else if (source==Constants.EXCHANGE_CARD){
            setTitleTextId(R.string.change_member_card);
        }
        presenter = new MemberCardSearchPresenter(this,this);
    }

    @OnClick(R.id.tv_conform)
    public void conform() {
        presenter.findMemberCard();

    }

    @Override
    public int getShopId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getKeyWord() {
          return etCardId.getText().toString();
    }
    @Override
    public void setMemberInfo(MemberInfo memberInfo) {
        Intent intent;
        if (source==Constants.EXCHANGE_CARD){
            intent = new Intent(this,MemberCardChangeActivity.class);
        }else {
            intent = new Intent(this,ModifyMemberInfoActivity.class);
            intent.putExtra("source",source);
        }
        intent.putExtra("memberInfo",memberInfo);
        startActivity(intent);
    }

    @Override
    public void NoMemberInfo() {
        G.showToast(this,"没有查询到任何信息！");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
