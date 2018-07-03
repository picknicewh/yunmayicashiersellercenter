package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ys on 2017/6/14.
 * 商品交易流水详情
 */

public class GoodsTradeDetailInfoBo implements Serializable{

    /**
     * trade_id : 1
     * pay_type : 1
     * pay_platform : cod
     * total_fee : 900
     * received_fee : 900
     * create_date : 2017-06-10 10:45:09
     * cashier_name :
     * count : 14
     */

    private String trade_id;
    private int pay_type;
    private String pay_platform;
    private Double total_fee;
    private Double received_fee;
    private String create_date;
    private String cashier_name;
    private int count;
    private List<GoodsTradeDetailInfo> detail;

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(String pay_platform) {
        this.pay_platform = pay_platform;
    }

    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public Double getReceived_fee() {
        return received_fee;
    }

    public void setReceived_fee(Double received_fee) {
        this.received_fee = received_fee;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GoodsTradeDetailInfo> getDetail() {
        return detail;
    }

    public void setDetail(List<GoodsTradeDetailInfo> detail) {
        this.detail = detail;
    }
}
