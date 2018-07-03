package com.yun.ma.yi.app.module.staff.role;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.RoleRuleInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：员工权限数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffRoleContract {
    public interface View extends BaseView {
    //  int getUserId();
        void setRoleList(List<RoleInfo> roleInfoList);
    }
    public interface ViewEdit extends BaseView {
        RoleInfo getData();
        int getRoleId();
     //   int getUserId();
        void setRolePermissionInfoList(List<RoleRuleInfo> rolePermissionInfoList);
        void back();
    }

    public interface Presenter extends BasePresenter {
        void addRole();
        void editRole();
        void getRoleList();
        void getRuleByRole();
        void getRuleList();
    }
}
