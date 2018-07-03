package com.yun.ma.yi.app.module.stock.statistic;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.bean.StockStatisticItemInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/4
 * 名称：库存盘点查询界面数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class RecordStatisticContract {
    public interface View extends BaseView {

        /**获取搜索详情*/
        void setStockStatisticInfoList(List<StockStatisticInfo> stockStatisticInfoList);
        /**关键词*/
        String getKeyword();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
        /**开始时间*/
        String getStartTime();
        /**结束时间*/
        String getEndTime();
        /**获取状态*/
        int getStatus();
    }

    public interface RecordStatisticItemView extends BaseView {

        /**用户id*/
        int getUid();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
        /**开始时间*/
        String getStartTime();
        /**结束时间*/
        String getEndTime();
        /**商品id*/
        String getItemId();
        /**单个商品盘点记录查询*/
        void stockChangeForItemId(List<StockStatisticItemInfo> stockStatisticItemInfos);

    }

    public interface RecordStatisticItemDetailView extends BaseView {

        /**库存id*/
        int getId();
        /**库存详情息*/
        void stockGetById(StockStatisticItemInfo stockStatisticItemInfo);
    }

    public interface Presenter extends BasePresenter {
        /**库存查询*/
        void  statisticSearchStock();
        /**单个商品盘点记录查询*/
        void stockChangeForItemId();
        /**库存详情息*/
        void stockGetById();

    }
}
