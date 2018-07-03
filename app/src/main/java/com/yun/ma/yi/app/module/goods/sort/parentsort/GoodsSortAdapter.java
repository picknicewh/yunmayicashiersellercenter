package com.yun.ma.yi.app.module.goods.sort.parentsort;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.CategoryInfo;
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
public class GoodsSortAdapter  extends CommonRecyclerAdapter<CategoryInfo> {

    public GoodsSortAdapter(Context context, List<CategoryInfo> sortList, int layoutId) {
        super(context, sortList, layoutId);
    }
    @Override
    public void convert(CommonRecyclerHolder holder, CategoryInfo categoryInfo) {
        if (holder != null){
            TextView tv_sort = holder.getView(R.id.tv_goods_sort);
           // int image = Integer.parseInt(String.valueOf(sortList.get("image")));
            tv_sort.setText(categoryInfo.getTitle());
           // Drawable drawableLeft  = ContextCompat.getDrawable(mContext,image);
           // drawableLeft.setBounds(0,0,drawableLeft.getIntrinsicWidth(),drawableLeft.getIntrinsicHeight());
             Drawable drawableRight  = ContextCompat.getDrawable(mContext,R.mipmap.ic_arrow_right);
            drawableRight.setBounds(0,0,drawableRight.getIntrinsicWidth(),drawableRight.getIntrinsicHeight());
            tv_sort.setCompoundDrawables(null,null,drawableRight,null);
        }
    }
}
