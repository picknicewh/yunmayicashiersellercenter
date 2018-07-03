package com.yun.ma.yi.app.module.goods.sort.parentsort;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.CategoryInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：一级商品分类数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSortContract {

    interface View extends BaseView {
        /**设置分类类别*/
        void setSortList(List<CategoryInfo> sortList);
    }

    interface Presenter extends BasePresenter {
        /**获取分类列表*/
        void   getSortList();
    }
}
