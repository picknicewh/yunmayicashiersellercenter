package com.yun.ma.yi.app.module.report.goods.profit;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsProfitInfo;

/**
 * Created by ys on 2017/6/23.
 */

public class GoodsProfitReportContract {

    interface View extends BaseView {

        /**用户id*/
        int getUid();
        /**获取开始时间*/
        String getStartTime();
        /**获取结束时间*/
        String getEndTime();
        /**获取来源*/
        String getSource();
        /**获取详细信息*/
        void getGoodsProfitReportInfo(GoodsProfitInfo goodsProfitInfo);
    }

    interface Presenter extends BasePresenter {
        void getGoodsProfitReportInfo();
    }
}
