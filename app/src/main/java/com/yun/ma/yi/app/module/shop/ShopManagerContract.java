package com.yun.ma.yi.app.module.shop;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ShopInfo;

/**
 * 作者： wh
 * 时间：  2017/11/27
 * 名称：：库存查询页数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopManagerContract {
    public interface View extends BaseView {
        int getUserId();
        void setShopInfo(ShopInfo shopInfo);
        String getUserName();
        String getPassword();

    }

    public interface Presenter extends BasePresenter {
        void getShopInfo();

        void getToken();
    }
}
