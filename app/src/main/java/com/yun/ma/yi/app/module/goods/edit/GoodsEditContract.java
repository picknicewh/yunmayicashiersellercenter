package com.yun.ma.yi.app.module.goods.edit;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.bean.EditGoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsItemCodeInfo;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称： 添加/编辑/删除商品数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsEditContract {

    interface View extends BaseView {
        /**设置一级分类类别*/
        void setSortList(List<CategoryInfo> categoryInfoList);
        /**设置二级分类类别*/
        void setSecondSortList(List<SecondCategoryInfo> secondCategoryInfoList);
        /**设置三级分类类别*/
        void setThirdSortList(List<SecondCategoryInfo> secondCategoryInfoList);
        /**设置商品信息分類*/
        void setItemInfo(EditGoodsDetailInfo editGoodsDetailInfo);
        /**设置商品信息*/
        void setGoodsDetailInfo(GoodsDetailInfo goodsDetailInfo);
        /**通过扫描二维码获取的商品；列表*/
        void setGoodsItemCodeInfoList( List<GoodsItemCodeInfo> goodsItemCodeInfoList );
        /**获取uid*/
        int getUid();

        /**获取一级类目ID*/
        int getTopCatId();
        /**获取二级类目ID*/
        int getMidCatId();
        /**获取三级类目*/
        int getLeafCatId();
        /**获取商品名称*/
        String getName();
        /**获取销售价*/
        float getPrice();
        /**获取成本价*/
        float getCost();
        float getVipPrice();
        /**获取速记码*/
        String getNumber();
        /**获取库存*/
        int getStock();
        /**获取条形码*/
        String getBarCode();
        /**获取库存警告-高位*/
        int getStockWaringHigh();
        /**获取库存警告-低位*/
        int getStockWaringLow();
        /**获取规格*/
        String getSpec();
        /**获取单位*/
        String getUnit();
        /**获取保质期*/
        int getShelfLife();
        /**是否称重*/
        int getIsWeight();
        /**获取商品id*/
        String getId();
        /**获取扫描的商品编码*/
        String getCode();
        /**商品是否上架*/
        int getItemStatus();
        /**清空数据重新添加*/
        void cleanInfo();
        /**是的继续添加*/
        boolean getIsContinue();
        /**获取图片url*/
        String getImageUrl();
        /**设置图片url*/
        void setImageUrl(String imageUrl);

    }

    interface Presenter extends BasePresenter {
        /**获取一级分类列表*/
        void   getSortList();
        /**获取二三级分类列表*/
        void   getSecondSortList(int cid,String sort);
        /**获取一级分类标题列表*/
        List<String> getCategoryTitle(List<CategoryInfo> categoryInfos);
        /**获取二，三级分类标题列表*/
        List<String> getSecondCategoryTitle(List<SecondCategoryInfo> categoryInfos);
        /**根据商品ID获取商品信息*/
        void getGoodInfoById();
        /**上传图片*/
        void upLoadImage(String path);
        /**添加/或编辑商品*/
        void addItemInfo();
        /**删除商品*/
        void deleteItemInfo();
        /**根据扫描获取的商品条形码获取商品信息*/
        void getGoodsInfoByCode();
        /**通过扫描获取的商品信息*/
        void getScanGoodsInfo();
    }
}
