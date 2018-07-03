package com.yun.ma.yi.app.module.member.cardgrant;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberCardInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称：会员卡发放数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardGrantContract {
    interface View extends BaseView{
        int getSellerId();
        String getMobile();
        String getDynamic();
        String getKeyWord();
        void setHasCard(boolean hasCard);

    }
    interface Presenter extends BasePresenter{
        void sendSms();
        void addCard();
        void findMemberCard();
        void memberGradeList();

    }
    interface  ViewEdit extends BaseView{
        int getSellerId();
        String getMobile();
        String getUserName();
        int getSex();
        String getBirthday();
        String getCertifyId();
        String getCardName();
        String getExpireDateTime();
        void setMemberInfo(List<MemberCardInfo> memberCardInfoList);
        void NoMemberInfo();
        void backHome();
    }
}
