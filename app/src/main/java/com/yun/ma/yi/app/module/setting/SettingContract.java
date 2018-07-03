package com.yun.ma.yi.app.module.setting;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yun.ma.yi.app.bean.StcokWarningInfo;
import com.yun.ma.yi.app.bean.UserResponse;

import java.util.List;

/**
 * Created by ys on 2017/5/31.
 * 设置
 */

public class SettingContract {

    /**修改密码*/
    interface IUpdatePasswordView extends BaseView{

        /**获取用户名*/
        UserResponse getUser();
        /**修改*/
        void updateUser();
    }

    /**获取库存预警*/
    interface IStockWarningView extends BaseView{

        /**获取用户名*/
        int getUser_id();
        /**修改*/
        int getPage();
        /**页长*/
        int getSize();
        /**获取预警商品*/
        void getStockWarningData(List<StcokWarningInfo> stcokWarningInfos);
    }

    /**帮助中心，公告*/
    interface INoticeiView extends BaseView{

        /**类型 1 帮助 2 公告*/
        int gettType();
        /**修改*/
        int getPage();
        /**页长*/
        int getSize();
        /**获取预警商品*/
        void getNoticeList(List<NoticeInfo> noticeInfos);
    }

    /**设置*/
    interface ISettingPresenter extends BasePresenter{

        void updateUser();

        void getStockWarningData();

        void getNoticeList();
    }

}
