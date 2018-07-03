package com.yun.ma.yi.app.module.member.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.bean.MemberTotalAccountInfo;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：会员查询
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberSearchActivity extends BaseActivity implements MemberSearchContract.View {

    /**
     * 搜索编辑框
     */
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    /**
     * 搜索
     */
    @BindView(R.id.tv_search)
    TextView tvSearch;
    /**
     * 会员总数
     */
    @BindView(R.id.tv_member_count)
    TextView tvMemberCount;
    /**
     * 发卡总数
     */
    @BindView(R.id.tv_card_count)
    TextView tvCardCount;
    /**
     * 会员余额
     */
    @BindView(R.id.tv_member_balance)
    TextView tvMemberBalance;
    /**
     * 会员积分
     */
    @BindView(R.id.tv_member_integral)
    TextView tvMemberIntegral;
    /**
     * 数据处理类
     */
    private MemberSearchPresenter presenter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.member_search);
        presenter = new MemberSearchPresenter(this,MemberSearchActivity.this);
        presenter.getTotalMember();
    }

    @OnClick(R.id.ll_member_info)
    public void memberInfo() {
        Intent intent = new Intent(this, MemberTotalListActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.tv_search)
    public void search() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_CARD_SEARCH)){
            presenter.findMemberCard();
        }else {
            showMessage("你没有查看会员的权限！");
        }
    }

    @Override
    public int getShopId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getKeyWord() {
       return etSearch.getText().toString();
}

    @Override
    public void setTotalInfo(MemberTotalAccountInfo accountInfo) {
        tvCardCount.setText(String.valueOf(accountInfo.getCardCount()));
        tvMemberCount.setText(String.valueOf(accountInfo.getUserTotal()));
        tvMemberBalance.setText(PriceTransfer.chageMoneyToString(accountInfo.getMoneyTotal()));
        tvMemberIntegral.setText(String.valueOf(accountInfo.getIntegralTotal()));
    }
    @Override
    public void setMemberInfo(MemberInfo memberInfo) {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_CARD_SEARCH)){
            Intent intent = new Intent(this,MemberInfoActivity.class);
            intent.putExtra("memberInfo",memberInfo);
            startActivity(intent);
        }else {
            showMessage("你没有查看会员的权限！");
        }
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
