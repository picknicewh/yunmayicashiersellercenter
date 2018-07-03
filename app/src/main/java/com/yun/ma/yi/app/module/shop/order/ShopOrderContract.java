package com.yun.ma.yi.app.module.shop.order;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ShopOrderInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：：库存查询页数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopOrderContract {
    public interface View extends BaseView {
        int getUid();
        //订单状态(1等待付款 2等待接单 3等待发货 4等待确认收货 5交易成功 6交易关闭 7交易关闭:买家或卖家主动关闭)
        int getOrderState();
        int getPage();
        int getSize();
        String getToken();
        String getStartTime();
        String getEndTime();
        void setShopOrderInfoList(List<ShopOrderInfo> shopOrderInfoList);
    }
    public interface ViewEdit extends BaseView {
        int getUid();
        /**交易订单号*/
        String getOrderId();
        /** 原因*/
        String getReason();
        /**状态信息 accept接单 reject拒绝接单   send确认配送*/
        String getStateInfo();
        void setSuccessBack();
    }
    public interface Presenter extends BasePresenter {
       void getSellerOrderBySellerId();
        void editOrderState();
    }
}
