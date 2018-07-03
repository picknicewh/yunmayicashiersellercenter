package com.yun.ma.yi.app.module.report.statistics.employee;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.EmployeeInfo;


/**
 * 作者： wh
 * 时间：  2017/8/3
 * 名称：员工统计报表计数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */

public class EmployeeReportContract {

    interface View extends BaseView{
        String getStartTime();
        String getEndTime();
        int getUid();
        void setEmployeeInfo(EmployeeInfo employeeInfo);
    }

    interface Presenter extends BasePresenter{
        void getEmployeeReport();
    }
}
