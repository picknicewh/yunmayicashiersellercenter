package com.yun.ma.yi.app.utils;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.StockChangeInfo;

/**
 * 作者： wh
 * 时间：  2017/7/3
 * 名称：类型转换类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ClassChangeUtil {

    /**
     * GoodsDetailInfo 转换成StockChangeInfo类
     * @param goodsDetailInfo 商品信息详情
     */
    public static StockChangeInfo setStockChangeInfoList(GoodsDetailInfo goodsDetailInfo){
            StockChangeInfo stockChangeInfo = new StockChangeInfo();
            stockChangeInfo.setUser_id(goodsDetailInfo.getUser_id());
            stockChangeInfo.setUpdate_date(goodsDetailInfo.getUpdate_date());
            stockChangeInfo.setUnit(goodsDetailInfo.getUnit());
            stockChangeInfo.setTotal_sales(goodsDetailInfo.getTotal_sales());
            stockChangeInfo.setTitle(goodsDetailInfo.getTitle());
            stockChangeInfo.setStock_warning_low(goodsDetailInfo.getStock_warning_low());
            stockChangeInfo.setStock_warning_high(goodsDetailInfo.getStock_warning_high());
            stockChangeInfo.setSpec(goodsDetailInfo.getSpec());
            stockChangeInfo.setStock(goodsDetailInfo.getStock());
            stockChangeInfo.setBar_code(goodsDetailInfo.getBar_code());
            stockChangeInfo.setShelf_life(goodsDetailInfo.getShelf_life());
            stockChangeInfo.setCategory_id(goodsDetailInfo.getCategory_id());
            stockChangeInfo.setCategory_ids(goodsDetailInfo.getCategory_ids());
            stockChangeInfo.setNumber(goodsDetailInfo.getNumber());
            stockChangeInfo.setImage_url(goodsDetailInfo.getImage_url());
            stockChangeInfo.setPrice(goodsDetailInfo.getPrice());
            stockChangeInfo.setCost(goodsDetailInfo.getCost());
            stockChangeInfo.setItem_status(goodsDetailInfo.getItem_status());
            stockChangeInfo.setIs_delete(goodsDetailInfo.getIs_delete());
            stockChangeInfo.setIs_weigh(goodsDetailInfo.getIs_weigh());
            stockChangeInfo.setId(goodsDetailInfo.getId());
            stockChangeInfo.setCreate_date(goodsDetailInfo.getCreate_date());
            stockChangeInfo.setAfter_stock(goodsDetailInfo.getStock());
            stockChangeInfo.setBefroe_stock(goodsDetailInfo.getStock());
            stockChangeInfo.setChange_stock(0);
        return stockChangeInfo;
     }
}
