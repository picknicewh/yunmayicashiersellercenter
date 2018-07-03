package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：商品信息类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsInfo {


    private int count;
     private List<GoodsDetailInfo> data;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GoodsDetailInfo> getData() {
        return data;
    }

    public void setData(List<GoodsDetailInfo> data) {
        this.data = data;
    }
}
