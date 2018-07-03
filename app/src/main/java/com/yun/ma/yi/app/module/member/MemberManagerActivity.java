package com.yun.ma.yi.app.module.member;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.member.cardgrant.MemberCardGrantActivity;
import com.yun.ma.yi.app.module.member.common.MemberSearchActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;
/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：会员中心
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberManagerActivity extends BaseActivity {
    private boolean isChain;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_manager;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member);
        isChain = UserMessage.getInstance().isChain()==1;
        G.log("xxxxxxxxxxxxx"+isChain);
    }
    /**
     * 会员查询
     */
    @OnClick(R.id.tv_member_search)
    public void memberSearch() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_CARD_SEARCH)){
            startActivity(new Intent(this, com.yun.ma.yi.app.module.member.search.MemberSearchActivity.class));
        }else {
            showMessage("你没有查看会员的权限！");
        }
    }
    /**
     * 会员卡查询
     */
    @OnClick(R.id.tv_card_search)
    public void cardSearch() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_MEMBER_CARD_SEARCH)&&!isChain){
            startActivity(new Intent(this, com.yun.ma.yi.app.module.member.cardsearch.MemberCardSearchActivity.class));
        }else {
            showMessage("你没有会员卡查询的权限！");
        }

    }
    /**
     * 会员卡发放
     */
    @OnClick(R.id.tv_card_grant)
    public void cardGrant() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_CARD_ADD)&&!isChain){
            startActivity(new Intent(this, MemberCardGrantActivity.class));
        }else {
            showMessage("你没有查看新增会员卡的权限！");
        }
    }

    /**
     * 会员卡充值
     */
    @OnClick(R.id.tv_member_recharge)
    public void memberRecharge() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_CARD_CHARGE)&&!isChain){
            Intent intent = new Intent(this,MemberSearchActivity.class);
            intent.putExtra("source", Constants.CRRD_RECHARGE);
            startActivity(intent);
        }else {
            showMessage("你没有会员卡充值的权限！");
        }

    }

    /**
     * 积分兑换
     */
    @OnClick(R.id.tv_card_recharge)
    public void cardRecharge() {
        G.showToast(this,"开发中.....");
    }

    /**
     * 会员赠积分
     */
    @OnClick(R.id.tv_give_integral)
    public void giveIntegral() {
        Intent intent = new Intent(this,MemberSearchActivity.class);
        intent.putExtra("source", Constants.GRAMT_INTEGRAL);
        startActivity(intent);
    }

    /**
     * 改卡密码
     */
    @OnClick(R.id.tv_modify_password)
    public void modifyPassword() {
        Intent intent = new Intent(this,MemberSearchActivity.class);
        intent.putExtra("source", Constants.MODIFY_PASSWORD);
        startActivity(intent);
    }

    /**
     * 挂失与激活
     */
    @OnClick(R.id.tv_report_activate)
    public void reportActivite() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_CARD_REPORT_LOSS)&&!isChain){
            Intent intent = new Intent(this,MemberSearchActivity.class);
            intent.putExtra("source", Constants.REPORT_ACTIVITE);
            startActivity(intent);
        }else {
            showMessage("你没有挂失或激活的权限！");
        }

    }

    /**
     * 会员换卡
     */
    @OnClick(R.id.tv_promotion_card)
    public void promotionCard() {
      //  G.showToast(this,"开发中.....");
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_CARD_EXCHANGE)){
            Intent intent = new Intent(this,MemberSearchActivity.class);
            intent.putExtra("source", Constants.EXCHANGE_CARD);
            startActivity(intent);
        }else {
            showMessage("你没有会员卡换卡权限！");
        }
    }
}
