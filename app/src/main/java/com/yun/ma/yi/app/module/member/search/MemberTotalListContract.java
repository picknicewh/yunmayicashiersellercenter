package com.yun.ma.yi.app.module.member.search;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.MemberInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/13
 * 名称   会员列表数据处理接口类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MemberTotalListContract {
    interface View extends BaseView{
        int getSellerId();
        int getPage();
        int getSize();
        void setMemberInfoList(List<MemberInfo> memberInfo);

    }
    interface Presenter extends BasePresenter{
        void getTotalMemberList();
    }
}
