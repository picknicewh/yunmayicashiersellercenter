package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/9/4
 * 名称：员工上下班详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutWorkInfoVos {
   private double totalCash;
   private List<InOutWorkInfo> list;

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public List<InOutWorkInfo> getList() {
        return list;
    }

    public void setList(List<InOutWorkInfo> list) {
        this.list = list;
    }
}
