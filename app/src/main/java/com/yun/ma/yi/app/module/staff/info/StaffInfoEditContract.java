package com.yun.ma.yi.app.module.staff.info;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.SubUserInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/27
 * 名称：新增/修改员工数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StaffInfoEditContract {
    public interface View extends BaseView{
       // int getUserId();
        void setSubUserList(List<SubUserInfo> subUserList);
    }
    public interface ViewEdit extends BaseView {

        String getUserName();
        String getPassword();
        String getName();
        String getMobile();
        int getRoleId();
        int getSubUserId();
        void  setRoleList(List<RoleInfo> roleInfoList);
        void back();
    }

    public interface Presenter extends BasePresenter {
        void addSubUser();
        void getSubUserList();
        void editSubUser();
        void delSubUser();
        void getRoleList();
    }
}
