package com.yun.ma.yi.app.module.marketing.choose;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.StockSearchInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：一级商品分类数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsChooseContract {

    public interface View extends BaseView {
        int getUser_id();
        int getCategoryId();
        int getPage();
        int getSize();
        String getKeyWord();
        void setGoodsInfoList(List<GoodsListInfo> goodsInfoList);
        void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsInfoList);
    }
    public interface ViewSingle extends BaseView{
        String getIds();
        /**获取搜索详情*/
        void setNoData();
        /**分类ID*/
        int  getCategoryId();
        /**关键词*/
        String getKeyword();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
        int getUserId();
        void setGoodInfoList(StockSearchInfo stockSearchInfo);
        void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList);
    }

    public interface ViewSingleEdit extends BaseView{
        int getUserId();
        String getIds();
        void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList);
    }
    public interface ViewSearch extends BaseView{
        int getUserId();
        String getKeyWord();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
        void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList);
    }
    interface Presenter extends BasePresenter {
        void   getGoodSortInfo();
        void   getGoodsDetailInfoList();
        void getSearchGoodsList();
        void getGoodList();
       void   getGoodItemsByIds();
    }
}
