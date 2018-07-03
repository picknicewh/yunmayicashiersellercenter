package com.yun.ma.yi.app.module.member.cardsearch;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberCardInfo;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：新增/编辑会员卡数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardEditContract {
    interface View extends BaseView{
        MemberCardInfo getData();
        int getId();
        void finish();
    }
    interface Presenter extends BasePresenter{
        void addMemberGrade();
        void editMemberGrade();
       void   deleteMemberGrade();
    }
}
