package com.yun.ma.yi.app.module.inoutstock.in;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称: 收货入库/入库详情数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockContract {

    interface View extends BaseView{
        int getUserId();
        String getOrderId();
        void setOrderInfoList(List<OrderInfo> orderInfoList);
    }
    interface ViewDetail extends View{
        void setOrderInfoDetail(List<OrderInfoDetail> orderInfoDetailList);
        void setConformInStockInfo(ConformInStockInfo conformInStockInfo);
        String getGoodsId();
        int getDetailId();
        int getQuantity();
        String getData();
    }

    interface Presenter extends BasePresenter{
        void getStockOrder();
        void getStockOrderDetail();
        void conformInStock();
        void changeStockById();
        void conformAllInStock();
    }

}
