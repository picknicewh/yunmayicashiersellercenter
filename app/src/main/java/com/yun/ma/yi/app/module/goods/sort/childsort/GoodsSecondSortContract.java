package com.yun.ma.yi.app.module.goods.sort.childsort;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：二级和三级商品分类数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSecondSortContract {

    interface View extends BaseView {
        /**设置分类类别*/
        void setSortList(List<SecondCategoryInfo> sortList);
        /**获取分类父级id*/
        int getParentId();
    }

    interface Presenter extends BasePresenter {
        /**获取分类列表*/
        void   getSortList();
    }
}
