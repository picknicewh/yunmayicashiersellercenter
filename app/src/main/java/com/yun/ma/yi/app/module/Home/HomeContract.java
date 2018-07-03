package com.yun.ma.yi.app.module.Home;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.CountTrade;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Shop;

import java.util.List;

/**
 * Created by ys on 2017/5/31.
 * 首页
 */

public class HomeContract {

    interface IHomeView extends BaseView{
        /**获取用户id*/
        int getUid();
        String getTime();
        /**获取今日统计数据*/
        void getCountTrade(CountTrade countTrade);
        /**获取店铺基本信息*/
        void getShopByUserId(Shop shop);
        /**获取分类*/
        void getCategoryList(List<GoodsListInfo> categoryInfos);
    }

    interface IHomePresenter extends BasePresenter {
        /**获取今日统计数据*/
        void countTrade();
        /**获取店铺信息*/
        void getShopByUserId();
        /**获取分类*/
        void getCategoryList();
    }

}
