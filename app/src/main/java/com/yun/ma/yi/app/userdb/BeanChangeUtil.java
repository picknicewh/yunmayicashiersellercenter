package com.yun.ma.yi.app.userdb;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;

/**
 * 作者： wh
 * 时间：  2017/10/12
 * 名称
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BeanChangeUtil {

    /**
     * 本地商品详情转换成订单商品详情
     *
     * @param item 本地商品详情
     */
    public static GoodsDetailInfo item2GoodsDetailInfo(Item item) {
        GoodsDetailInfo goodsDetailInfo = new GoodsDetailInfo();
        goodsDetailInfo.setId(item.getGoodsId());
        goodsDetailInfo.setUser_id(item.getUserId());
        goodsDetailInfo.setTitle(item.getTitle());
        goodsDetailInfo.setBar_code(item.getBarCode());
        goodsDetailInfo.setCategory_id(item.getCategoryId());
         goodsDetailInfo.setCheck(item.isCheck()==1?true:false);
        return goodsDetailInfo;
    }
    /**
     * 本地商品详情转换成订单商品详情
     *
     * @param goodsDetailInfo 本地商品详情
     */
    public static Item GoodsDetailInfo2item(GoodsDetailInfo goodsDetailInfo,int categoryId) {
        Item item = new Item();
        item.setCheck(goodsDetailInfo.isCheck()?1:0);
        item.setGoodsId(goodsDetailInfo.getId());
        item.setCategoryId(categoryId==0?0:goodsDetailInfo.getCategory_id());
        if (categoryId==99){
            item.setCategoryId(99);
        }
        item.setBarCode(goodsDetailInfo.getBar_code());
        item.setTitle(goodsDetailInfo.getTitle());
        item.setUserId(goodsDetailInfo.getUser_id());
        return item;
    }
}
