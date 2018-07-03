package com.yun.ma.yi.app.module.stock.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.StockSearchInfo;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：：库存查询页数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockSearchContract {
    public interface View extends BaseView {

        /**获取搜索详情*/
        void setStockSearchInfo(StockSearchInfo stockSearchInfo);
        void setNoData();
        /**分类ID*/
        int  getCategoryId();
        /**关键词*/
        String getKeyword();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
    }

    public interface Presenter extends BasePresenter {
        /**库存查询*/
        void  searchStock();
    }
}
