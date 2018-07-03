package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ys on 2017/6/20.
 * 商品销售详情
 */

public class GoodsSalesInfoBo implements Serializable {
    /**
     * total_quantity => 总商品销售数
     * total_fee => 总销售金额
     */
    private int total_quantity;
    private Double total_fee;
    private List<GoodsSalesInfo> list;

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public List<GoodsSalesInfo> getList() {
        return list;
    }

    public void setList(List<GoodsSalesInfo> list) {
        this.list = list;
    }
}
