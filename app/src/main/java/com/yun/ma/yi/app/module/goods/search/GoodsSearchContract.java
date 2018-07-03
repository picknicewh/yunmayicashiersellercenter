package com.yun.ma.yi.app.module.goods.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfo;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 搜索商品数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSearchContract {
    interface View extends BaseView{
        int getUser_id();
        int getPage();
        int getSize();
        String getKeyWord();
        int getShopId();
        String getToken();
        void setGoodsInfo(GoodsInfo goodsInfo);
        void setShopGoodsInfo(ShopGoodsInfo shopGoodsInfo);
    }
    interface Presenter extends BasePresenter {
        void   getGoodsDetailInfoList();
        void  getShopGoodInfoByCatId();
    }
}
