package com.yun.ma.yi.app.module.member.common;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberRechargeInfo;

/**
 * 作者： wh
 * 时间：  2017/7/14
 * 名称：充值/赠积分/修改卡密码/挂失数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberModifyContract {
    interface View extends BaseView{
        int getSellerId();
        int getId();
        int getValue();
        MemberRechargeInfo getRechargeData();
        String getResult();
        void finish();
        void backHome();
    }
    interface Presenter extends BasePresenter{
        void memberReportLost();
        void memberCardRecharge();
    }
}
