package com.yun.ma.yi.app.module.shop.balance;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.BalanceAccountInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：余额对账数据处理类接口
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BalanceAccountContract {
    public interface View extends BaseView {
        int getUid();
        String getStartTime();
        String getEndTime();
        int getPage();
        int getSize();
        String getToken();
        void setBalanceDetailList(List<BalanceAccountInfo.BalanceDetailInfo> balanceDetailList);
    }

    public interface Presenter extends BasePresenter {
       void getBalanceAccountList();
    }
}
