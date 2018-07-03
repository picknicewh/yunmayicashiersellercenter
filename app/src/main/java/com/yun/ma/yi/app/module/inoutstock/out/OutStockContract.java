package com.yun.ma.yi.app.module.inoutstock.out;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称: 退货入库数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OutStockContract {

    interface View extends BaseView{
        int getUserId();
        String getKeyWord();
       void setOrderInfoDetailList(List<OrderInfoDetail> orderInfoDetailList);

    }
    interface ViewDetail extends BaseView{
        /**	子订单ID*/
        int getDetailId();
        /**退货数量*/
        int getQuantity();
        /**退款方式（1现金，2余额）*/
        int getReturnWay();
        /**退货原因*/
        String getReturnReason();
        /**备注*/
        String getBuyerRemark();
        /**商品ID*/
        String getItemId();
        void setConformInStockInfo(ConformInStockInfo conformInStockInfo);
        void back();
    }
    interface Presenter extends BasePresenter{
        void conformOutStock();
        void conformOutStockDetail();
        void conformOutStockDetailById();
    }
}
