package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ys on 2017/6/14.
 * 商品交易流水
 */

public class GoodsTradeInfoBo implements Serializable{

    private int count;
    private Double received;
    private List<GoodsTradeInfo> list;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public List<GoodsTradeInfo> getList() {
        return list;
    }

    public void setList(List<GoodsTradeInfo> list) {
        this.list = list;
    }
}
