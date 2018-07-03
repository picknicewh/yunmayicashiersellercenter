package com.yun.ma.yi.app.module.shop.goods;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/27
 * 名称：：库存查询页数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsEditContract {
    public interface View extends BaseView {
        /**
         * item_id	是	int	商品ID
         shop_cat_id	是	int	分类ID
         pic_url	是	string	商品组图图片地址
         title	是	string	商品标题
         sub_title	是	string	商品副标题
         sell_price	是	float	销售价
         market_price	是	float	市场价
         cost_price	是	float	成本价
         stock_num	是	int	商品库存数
         bar_code	是	string	条形码
         limit_num	是	int	限购数量
         shop_sort_id	是	int	商品在店铺内排序
         */
        int getItemId();
        String getShopCatId();
        String getPicUrl();
        String getGoodsTitle();
        String getSubTitle();
        double getSellPrice();
        double getMarketPrice();
        double getCostPrice();
        int getStockNum();
        String getBarCode();
        int getLimitNum();
        int getShopSortId();
        String getToken();
        int getShopId();
        /**上架状态，上架4，下架2*/
        int getItemStatus();
        void setSuccessBack(int type);
        void setImageUrl(String imageUrl);
        void setShopGoodsInfoDetail(ShopGoodsInfoDetail shopGoodsInfoDetail);
    }
   public  interface ViewAdd extends BaseView{
        int getUid();
        String getKeyWord();
        int getPage();
        int getSize();
        int getShopId();
        int getShopCatId();
        String getGoodsIds();
        void setCategoryList(List<GoodsListInfo> categoryList);
        void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsDetailInfoList);
        void setSuccessBack();

   }
    public interface Presenter extends BasePresenter {
        void  getShopInfoById();
        void editShopGoodInfo();
        void deleteShopGoodInfo();
        void upLoadImage(String imageUrl);
        void getCashierItemList();
        void addCashierItem();
       void getCategoryList();
    }
}
