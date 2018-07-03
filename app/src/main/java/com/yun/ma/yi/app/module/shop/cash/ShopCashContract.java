package com.yun.ma.yi.app.module.shop.cash;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.ShopCashInfo;

/**
 * 作者： wh
 * 时间：  2017/7/1
 * 名称：：申请提现
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopCashContract {
    public interface View extends BaseView {
        //int getUserId();
        // status:0全部 1待审核 2提现失败 99提现成功
        int getState();
        int getPage();
        int getSize();
        String getStartTime();
        String getEndTime();
        void setShopCashInfo(ShopCashInfo shopCashInfo);
    }
    public interface ViewEdit extends BaseView {
        int getShopId();
        String getAccountType();
        String getAccountName();
        String getAccountNoo();
        double getAmount();
       // int getUserId();
        void setSuccessBack();
    }
    public interface Presenter extends BasePresenter {
       void drawBalanceList();
        void withdrawBalance();
    }
}
