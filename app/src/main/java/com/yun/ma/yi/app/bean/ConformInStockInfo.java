package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：确认入库信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ConformInStockInfo {
    /**
     * info : 该商品对应收银机系统内多个商品
     * data : [{"id":"100673149405332310200651","user_id":100673,"title":"川崎火锅调料海鲜味","category_id":100108,"category_ids":",100108,100003,100004,","category_name":"粮油调味","price":400,"cost":250,"image_url":"","number":"","bar_code":"6955887900032","stock":99999,"stock_warning_high":5,"stock_warning_low":999,"spec":"","unit":"2","total_sales":0,"shelf_life":0,"item_status":1,"is_weigh":0,"is_delete":0,"create_date":"2017-05-07 02:48:43","update_date":"2017-05-07 02:48:43"}]
     */
    private boolean error;
    private String info;
    private List<GoodsDetailInfo> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<GoodsDetailInfo> getData() {
        return data;
    }

    public void setData(List<GoodsDetailInfo> data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
