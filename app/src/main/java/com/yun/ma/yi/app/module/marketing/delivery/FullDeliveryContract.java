package com.yun.ma.yi.app.module.marketing.delivery;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.FullDeliveryInfo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/9/4
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class FullDeliveryContract {
    public interface View extends BaseView {
        /**满就减列表*/
        void setFullDeliveryInfoList(List<FullDeliveryInfo> fullCutInfoList);
        /**	用户ID*/
        int getUserId();
        /**活动开始时间*/
        String getActivityStartTime();
        /**活动结束时间*/
        String getActivityEndTime();
        /**页码*/
        int getPage();
        /**页长*/
        int getSize();
    }
    public interface ViewEdit extends BaseView{
        /**数据结构类*/
        FullDeliveryInfo getData();
        /**获取满就减id*/
        int getId();
        void editSuccess(String message);
    }
    public interface Presenter extends BasePresenter {
        void getFullDeliveryList();
        void deleteFullDeliveryInfoById();
        void editFullDeliveryInfo();
        void addFullDeliveryInfo();
    }
}
