package com.yun.ma.yi.app.module.member.search;

import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.TextUtils;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/7/11
 * 名称：余额发生明细/积分发生明细
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoHappenDetailActivity extends BaseActivity {
    /**
     * 类型
     */
    @BindView(R.id.tv_type)
    ItemTextView tvType;
    /**
     * 发生前
     */
    @BindView(R.id.tv_happen_before)
    ItemTextView tvHappenBefore;
    /**
     * 发生
     */
    @BindView(R.id.tv_happen)
    ItemTextView tvHappen;
    /**
     * 发生后
     */
    @BindView(R.id.tv_happen_after)
    ItemTextView tvHappenAfter;
    /**
     * 交易订单号
     */
    @BindView(R.id.tv_trade_order_number)
    ItemTextView tvTradeOrderNumber;
    /**
     * 备注
     */
    @BindView(R.id.tv_remark)
    ItemTextView tvRemark;
    /**
     * 新增人
     */
    @BindView(R.id.tv_operation)
    ItemTextView tvOperation;
    /**
     * 新增时间
     */
    @BindView(R.id.tv_time)
    ItemTextView tvTime;
    /**
     * 来源
     */
    private int source;
    /**
     * 发生详情
     */
   private MemberInfoChangeInfo memberInfoChangeInfo;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_member_happen_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        source = getIntent().getIntExtra("source", Constants.BALANCE_HAPPEND_DETAIL);
        initSource();
    }
    private void initSource() {
        memberInfoChangeInfo = (MemberInfoChangeInfo)getIntent().getSerializableExtra("memberInfoChangeInfo");
        tvType.setText(TextUtils.getTradeType(memberInfoChangeInfo.getTrade_type()));
        tvTradeOrderNumber.setText(memberInfoChangeInfo.getTrade_id());
        tvRemark.setText(G.isEmteny(memberInfoChangeInfo.getRemark())?"无":memberInfoChangeInfo.getRemark());
        tvOperation.setText(memberInfoChangeInfo.getCreate_name());
        tvTime.setText(String.valueOf(memberInfoChangeInfo.getCreate_datetime()));
        if (source == Constants.BALANCE_HAPPEND_DETAIL) {
            setTitleTextId(R.string.balance_happen_detail);
            tvHappen.setText(PriceTransfer.chageMoneyToString(memberInfoChangeInfo.getChange_card_money()));
            tvHappenBefore.setText(PriceTransfer.chageMoneyToString(memberInfoChangeInfo.getBefore_card_money()));
            tvHappenAfter.setText(PriceTransfer.chageMoneyToString(memberInfoChangeInfo.getAfter_card_money()));
        } else if (source == Constants.INTEGRAL_HAPPEND_DETAIL) {
            setTitleTextId(R.string.integral_happen_detail);
            tvHappen.setLabelText(getString(R.string.happen_integral));
            tvHappenBefore.setLabelText(getString(R.string.happen_before_integral));
            tvHappenAfter.setLabelText(getString(R.string.happen_after_integral));
            tvHappen.setText(String.valueOf(memberInfoChangeInfo.getChange_card_integral()));
            tvHappenBefore.setText(String.valueOf(memberInfoChangeInfo.getBefore_card_integral()));
            tvHappenAfter.setText(String.valueOf(memberInfoChangeInfo.getAfter_card_integral()));
        }
    }
}
