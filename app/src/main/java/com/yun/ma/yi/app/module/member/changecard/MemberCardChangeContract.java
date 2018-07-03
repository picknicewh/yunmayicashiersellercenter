package com.yun.ma.yi.app.module.member.changecard;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberCardInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/14
 * 名称：充值/赠积分/修改卡密码/挂失数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberCardChangeContract {
    interface View extends BaseView{
        int getId();
        String getUsername();
        int getUserSex();
        String getUserBirthday();
        String getUserCertifyId();
        String getCardNumber();
        String getCardName();
        void  back();
        int getSellerId();
        void setMemberInfo(List<MemberCardInfo> memberCardInfoList);
    }
    interface Presenter extends BasePresenter{
       void memberGradeList();
        void editMemberInfo();
    }
}
