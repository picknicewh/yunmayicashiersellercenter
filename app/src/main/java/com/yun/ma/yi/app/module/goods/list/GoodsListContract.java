package com.yun.ma.yi.app.module.goods.list;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.ShopCatInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：一级商品分类数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsListContract {

    interface View extends BaseView {
        int getUser_id();
        int getCategoryId();
        int getPage();
        int getSize();
        int getOneKeyPage();
        int getOneKeySize();

        String getKeyWord();
        int getShopId();
        int getShopCatId();
        String getToken();
        String getCashierCategoryId();
        void setOnKeyPage(int page);
        void setOneKeyUploadCount(int count);
        void  setGoodsInfo(GoodsInfo goodsInfo);
        void setGoodsInfoList(List<GoodsListInfo> goodsInfoList);
        void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsInfoList);
        void setShopCatInfoList(List<ShopCatInfo> shopCatInfoList);
        void setShopGoodsInfoDetailList(List<ShopGoodsInfoDetail> shopGoodsInfoDetailList);
        void onSuccessBack();

    }

    interface Presenter extends BasePresenter {
        void   getGoodSortInfo();
        void   getGoodsDetailInfoList();
        void  getSearchGoodsList();
        void  getShopCatList();
        void  getShopGoodInfoByCatId();
        void  getOneKeyUploadCount();
        void oneKeyUploadShopGoods();

    }
}
