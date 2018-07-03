package com.yun.ma.yi.app.module.stock.add;

import com.yun.ma.yi.app.module.stock.search.StockSearchContract;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：：新增盘点页数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AddStatisticContract {
    interface View extends StockSearchContract.View {
        /**更改信息*/
       String getInfo();
       void back();
    }
    interface Presenter extends StockSearchContract.Presenter {
      void  insertStock();
    }
}
