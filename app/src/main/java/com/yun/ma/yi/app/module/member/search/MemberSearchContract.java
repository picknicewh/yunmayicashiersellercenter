package com.yun.ma.yi.app.module.member.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.bean.MemberTotalAccountInfo;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员查询数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberSearchContract {
    interface View extends BaseView{
        int getShopId();
        String getKeyWord();
        void setTotalInfo(MemberTotalAccountInfo accountInfo);
        void setMemberInfo(MemberInfo memberInfo);
        void NoMemberInfo();
    }
    interface Presenter extends BasePresenter{
        void getTotalMember();
        void findMemberCard();
    }
}
