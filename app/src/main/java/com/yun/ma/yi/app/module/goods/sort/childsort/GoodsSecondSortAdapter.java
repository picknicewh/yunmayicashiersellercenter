package com.yun.ma.yi.app.module.goods.sort.childsort;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.SecondCategoryInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/22
 * 名称：一级分类列表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsSecondSortAdapter extends CommonRecyclerAdapter<SecondCategoryInfo> {

    public GoodsSecondSortAdapter(Context context, List<SecondCategoryInfo> sortList, int layoutId) {
        super(context, sortList, layoutId);
    }
    @Override
    public void convert(CommonRecyclerHolder holder, SecondCategoryInfo sort) {
        if (holder != null){
            TextView tv_sort = holder.getView(R.id.tv_second_sort);
             tv_sort.setText(sort.getName());
            Drawable drawableRight  = ContextCompat.getDrawable(mContext,R.mipmap.ic_arrow_right);
            drawableRight.setBounds(0,0,drawableRight.getIntrinsicWidth(),drawableRight.getIntrinsicHeight());
            tv_sort.setCompoundDrawables(null,null,drawableRight,null);
        }
    }
}
