package com.yun.ma.yi.app.module.member.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：余额明细/积分明细数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberInfoDetailContract {
    interface View extends BaseView{
        int getPage();
        int getSize();
        int getSellerId();
        int getUserId();
        String getType();

        void setMemberInfo(List<MemberInfoChangeInfo> memberInfoChangeInfoList);

    }
    interface Presenter extends BasePresenter{
        void getMoneyOrIntegralList();
        void editMemberInfo();
    }
    interface InfoView extends BaseView{
        int getId();
        int getSellerId();
        String getUsername();
        int getUserSex();
        String getUserBirthday();
        String getUserCertifyId();
        void NoMemberInfo();
        void  back();
    }
}
