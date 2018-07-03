package com.yun.ma.yi.app.module.staff.role;

import android.content.Context;
import android.widget.TextView;

import com.yun.ma.yi.app.bean.RoleInfo;
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
public class RoleChooseAdapter extends CommonRecyclerAdapter<RoleInfo> {

    public RoleChooseAdapter(Context context, List<RoleInfo> data, int layoutId) {
        super(context, data, layoutId);
    }
    @Override
    public void convert(CommonRecyclerHolder holder, RoleInfo userinfo) {
        TextView tv_role = holder.getView(R.id.tv_role);
        tv_role.setText(userinfo.getName());
    }
}
