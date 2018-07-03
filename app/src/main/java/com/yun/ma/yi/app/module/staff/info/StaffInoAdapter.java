package com.yun.ma.yi.app.module.staff.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.module.common.CommonRecyclerAdapter;
import com.yun.ma.yi.app.module.common.CommonRecyclerHolder;
import com.yunmayi.app.manage.R;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/26
 * 名称:角色权限适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffInoAdapter extends CommonRecyclerAdapter<SubUserInfo> {

    public StaffInoAdapter(Context context, List<SubUserInfo> data, int layoutId) {
        super(context, data, layoutId);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_role;
        ViewHolder(View itemView) {
            super(itemView);
            tv_role = (TextView) itemView.findViewById(R.id.tv_role);
            itemView.setTag(this);
        }
    }
    @Override
    public void convert(CommonRecyclerHolder holder, SubUserInfo userinfo) {
        TextView tv_role = holder.getView(R.id.tv_role);
        tv_role.setText(userinfo.getName());
    }
}
