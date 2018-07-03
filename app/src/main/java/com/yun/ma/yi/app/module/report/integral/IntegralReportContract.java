package com.yun.ma.yi.app.module.report.integral;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberIntegralInfo;

/**
 * Created by ys on 2017/6/13.
 * 商品交易流水统计报表
 */

public class IntegralReportContract {

    interface View extends BaseView{
        /**获取开始时间*/
        String getStartTime();
        /**获取结束时间*/
        String getEndTime();
        /**获取页数*/
        int getPageNo();
        /**获取每页长度*/
        int getPageSize();
        /**获取用户id*/
        int getUid();
        void setIntegralInfo(MemberIntegralInfo memberIntegralInfo);
    }
    interface Persenter extends BasePresenter{
        void getIntegralReportInfo();
    }
}
