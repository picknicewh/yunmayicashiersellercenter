package com.yun.ma.yi.app.module.report.goods.sales;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsSalesInfoBo;

/**
 * Created by ys on 2017/6/13.
 * 商品销售报表
 */

public class GoodsSalesReportContract {

    interface View extends BaseView{
        /**获取开始时间*/
        String getStartTime();
        /**获取结束时间*/
        String getEndTime();
        /**获取来源*/
        String getSource();
        /**获取页数*/
        int getPageNo();
        /**获取每页长度*/
        int getPageSize();
        /**用户id*/
        int getUid();
        /**统计方式*/
        String getGroupType();
        /**排序方式*/
        String getOrderType();
        /**关键字*/
        String getKeyword();
        void setGoodsSalesReportInfo(GoodsSalesInfoBo result);

    }

    interface IGoodsSalesViewPresenter extends BasePresenter{
        void getGoodsSalesReportInfo();
    }
}
