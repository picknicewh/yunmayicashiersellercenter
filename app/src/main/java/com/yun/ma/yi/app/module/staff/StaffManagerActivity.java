package com.yun.ma.yi.app.module.staff;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.staff.info.StaffInfoActivity;
import com.yun.ma.yi.app.module.staff.role.RoleChooseActivity;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

public class StaffManagerActivity extends BaseActivity {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_manager_staff;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.staff_manager);
    }

    @OnClick(R.id.tv_role_permissions)
    public void rolePermissions() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_ROLE_READ)){
            Intent intent  = new Intent(this, RoleChooseActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有查看角色权限哦！");
        }
    }

    @OnClick(R.id.tv_staff_info)
    public void staffInfo() {
        if (YunmayiApplication.isCanOperation( ConfigConstants.ACTION_STAFF_INFO_READ)){
            Intent intent  = new Intent(this, StaffInfoActivity.class);
            startActivity(intent);
        }else {
            showMessage("你没有查看员工信息哦！");
        }

    }
}
