package com.yun.ma.yi.app.module.report.goods.trade;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.GoodsTradeDetailInfoBo;
import com.yun.ma.yi.app.bean.GoodsTradeInfoBo;
import com.yun.ma.yi.app.bean.MemberTradeInfo;

import java.util.List;

/**
 * Created by ys on 2017/6/13.
 * 商品交易流水统计报表
 */

public class GoodsTradeReportContract {

    interface View extends BaseView{
        /**获取开始时间*/
        String getStartTime();
        /**获取结束时间*/
        String getEndTime();
        /**获取来源*/
        String getSource();
        /**获取页数*/
        int getPageNo();
        /**获取每页长度*/
        int getPageSize();
        /**获取用户id*/
        int getUid();
        /**会员id*/
        int getBuyerId();
        String getCreateId();
        void getGoodsTradeReportInfo(GoodsTradeInfoBo goodsTradeInfoBo);
        void setMemberTradeInfoList(List<MemberTradeInfo> memberTradeInfoList);
    }

    interface ReportView extends BaseView{
        /**获取流水号*/
        String getTradeId();

        void getGoodsTradeDetailInfo(GoodsTradeDetailInfoBo result);
    }

    interface Presenter extends BasePresenter{

        void getGoodsTradeReportInfo();

        void getGoodsTradeDetailInfo();

        void getTradeList();
    }
}
