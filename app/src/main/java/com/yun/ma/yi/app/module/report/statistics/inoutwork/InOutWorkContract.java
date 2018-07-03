package com.yun.ma.yi.app.module.report.statistics.inoutwork;

import com.yun.ma.yi.app.base.BasePresenter;
import com.yun.ma.yi.app.base.BaseView;
import com.yun.ma.yi.app.bean.InOutWorkInfoVos;

/**
 * Created by wh on 2017/9/14.
 * 上下班数据处理类接口
 */

public class InOutWorkContract {

    interface View extends BaseView{
        String getStartTime();
        String getEndTime();
        String getKeyWord();
        int getUserId();
        void setInOutWorkVos(InOutWorkInfoVos inOutWorkVos);
    }

    interface Persenter extends BasePresenter{
        void getInOutWorkList();
    }
}
