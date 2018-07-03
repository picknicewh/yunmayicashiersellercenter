package com.yun.ma.yi.app.module.common;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CategoryChooseAdapter  extends CommonRecyclerAdapter<GoodsListInfo>{
    public CategoryChooseAdapter(Context context, List<GoodsListInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, GoodsListInfo s) {
        if (holder != null){
            TextView textView  = holder.getView(R.id.tv_category);
            textView.setText(s.getCategory_name());
        }
    }
}
