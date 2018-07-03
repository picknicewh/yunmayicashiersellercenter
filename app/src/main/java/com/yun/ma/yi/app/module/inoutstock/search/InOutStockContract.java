package com.yun.ma.yi.app.module.inoutstock.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.InOutSearchInfo;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称: 退货入库数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutStockContract {

    interface View extends BaseView{
        int getUserId();
        /**开始时间*/
        String getStartTime();
        /**结束时间*/
        String getEndTime();
        /**int	查询类型	2(收货入库)/3(退货出库)*/
        int getType();
        void setInOutSearchInfo(InOutSearchInfo inOutSearchInfo);
    }


    interface Presenter extends BasePresenter{
        void searchEnterOutStock();

    }

}
