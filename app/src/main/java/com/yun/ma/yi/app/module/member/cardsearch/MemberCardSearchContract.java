package com.yun.ma.yi.app.module.member.cardsearch;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberCardInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员查询数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardSearchContract {
    interface View extends BaseView{
        int getSellerId();
        void setMemberInfo(List<MemberCardInfo> memberCardInfoList);
        void NoMemberInfo();
    }
    interface Presenter extends BasePresenter{
        void memberGradeList();
    }
}
