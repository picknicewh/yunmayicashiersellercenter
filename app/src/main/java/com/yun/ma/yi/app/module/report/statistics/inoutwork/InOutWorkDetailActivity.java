package com.yun.ma.yi.app.module.report.statistics.inoutwork;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.InOutWorkInfo;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

public class InOutWorkDetailActivity extends BaseActivity {


    @BindView(R.id.iv_image)
    ImageView ivImage;
    /**
     * 名字
     */
    @BindView(R.id.tv_name)
    TextView tvName;
    /**
     * 员工号
     */
    @BindView(R.id.tv_number)
    TextView tvNumber;
    /**
     * 实际收入
     */
    @BindView(R.id.tv_entry_money)
    TextView tvEntryMoney;
    /**
     * 差异收入
     */
    @BindView(R.id.tv_diff_money)
    TextView tvDiffMoney;
    /**
     * 异常记录
     */
    @BindView(R.id.tv_diff_remark)
    TextView tvDiffRemark;
    /**
     * 预留备用金
     */
    @BindView(R.id.tv_rest_money)
    TextView tvRestMoney;
    /**
     * 检查结果
     */
    @BindView(R.id.tv_check_result)
    TextView tvCheckResult;
    /**
     * 打包金额
     */
    @BindView(R.id.tv_pack_money)
    TextView tvPackMoney;
    /**
     * 用户角色
     */
    @BindView(R.id.tv_roles)
    TextView tvRoles;
    @BindView(R.id.tv_cash_count)
    TextView tvCashCount;
    @BindView(R.id.tv_cash_zhi_count)
    TextView tvCashZhiCount;
    @BindView(R.id.tv_cash_we_count)
    TextView tvCashWeCount;
    @BindView(R.id.tv_zhi_count)
    TextView tvZhiCount;
    @BindView(R.id.tv_wechat_count)
    TextView tvWechatCount;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    /**
     * 员工上下班详情
     */
    private InOutWorkInfo inOutWorkInfo;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_in_out_work_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.staff_in_out_work_detail);
        inOutWorkInfo = (InOutWorkInfo) getIntent().getSerializableExtra("inOutWorkInfo");
        if (inOutWorkInfo.getSub_user_id() == 0) {
            tvName.setText("店主");
        } else {
            tvName.setText(inOutWorkInfo.getSub_name());
            tvNumber.setText("工号：" + inOutWorkInfo.getSub_username());
            if (inOutWorkInfo.getRoles().size()>0){
                tvRoles.setText(inOutWorkInfo.getRoles().get(0));
            }
        }
        tvStartTime.setText(inOutWorkInfo.getSign_in_datetime());
        tvEndTime.setText(inOutWorkInfo.getKnock_off_datetime());
        tvTotalMoney.setText(PriceTransfer.chageMoneyToString(inOutWorkInfo.getTotal_cash()));
        tvDiffMoney.setText(PriceTransfer.chageMoneyToString(inOutWorkInfo.getAbnormal_money()));
        if (G.isEmteny(inOutWorkInfo.getAbnormal_desc())){
            tvDiffRemark.setText("无");
        }else {
            tvDiffRemark.setText(inOutWorkInfo.getAbnormal_desc());
        }
        tvCheckResult.setText(inOutWorkInfo.getVerification_result());
        tvRestMoney.setText(PriceTransfer.chageMoneyToString(inOutWorkInfo.getAfter_reserve_money()));
        tvPackMoney.setText(PriceTransfer.chageMoneyToString(inOutWorkInfo.getPack_cash()));
        tvEntryMoney.setText(PriceTransfer.chageMoneyToString(inOutWorkInfo.getCashier_order_cash()));

    }

}
