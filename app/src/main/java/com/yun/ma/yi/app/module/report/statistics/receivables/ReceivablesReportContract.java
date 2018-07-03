package com.yun.ma.yi.app.module.report.statistics.receivables;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ReceivablesInfo;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 收款统计报表
 */

public class ReceivablesReportContract {

    interface View extends BaseView{
        String getStartTime();
        String getEndTime();
        String getSource();
        int getUid();

        void getReceivablesReportInfo(List<ReceivablesInfo> receivablesInfos);
    }

    interface Presenter extends BasePresenter{
        void getReceivablesReportInfo();
    }
}
