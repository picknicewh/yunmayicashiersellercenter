package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： 库存搜索资源
 * 时间：  2017/7/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StockSearchInfo {
    private TotalInfo total;
    private List<GoodsDetailInfo> list;
   public static class TotalInfo{
       /**
        * sum_price : 1533002373
        * sum_stock : 3801011
        */
        private String sum_price;
        private String sum_stock;

        public String getSum_price() {
            return sum_price;
        }

        public void setSum_price(String sum_price) {
            this.sum_price = sum_price;
        }

        public String getSum_stock() {
            return sum_stock;
        }

        public void setSum_stock(String sum_stock) {
            this.sum_stock = sum_stock;
        }
    }

    public TotalInfo getTotal() {
        return total;
    }
    public void setTotal(TotalInfo total) {
        this.total = total;
    }

    public List<GoodsDetailInfo> getList() {
        return list;
    }

    public void setList(List<GoodsDetailInfo> list) {
        this.list = list;
    }
}
