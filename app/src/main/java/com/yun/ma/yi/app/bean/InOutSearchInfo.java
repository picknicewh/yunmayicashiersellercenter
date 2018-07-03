package com.yun.ma.yi.app.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/4
 * 名称：出入口信息详情
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InOutSearchInfo {

    /**
     * error : false
     * info : 查询成功
     * data : [{"item_id":"100673149405332310196173","tile":"卡通笔记本","spec":"","unit":"16","cost":150,"price":500,"number":"","bar_code":"6925293597782","change_total":50,"type":2}]
     */

    private boolean error;
    private String info;
    private List<InOutSearchInfoDetail> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<InOutSearchInfoDetail> getData() {
        return data;
    }

    public void setData(List<InOutSearchInfoDetail> data) {
        this.data = data;
    }

    public static class InOutSearchInfoDetail {
        /**
         * item_id : 100673149405332310196173
         * tile : 卡通笔记本
         * spec :
         * unit : 16
         * cost : 150
         * price : 500
         * number :
         * bar_code : 6925293597782
         * change_total : 50
         * type : 2
         */
        private String item_id;
        private String tile;
        private String spec;
        private String unit;
        private int cost;
        private int price;
        private String number;
        private String bar_code;
        private int change_total;
        private int type;
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getTile() {
            return tile;
        }

        public void setTile(String tile) {
            this.tile = tile;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public int getChange_total() {
            return change_total;
        }

        public void setChange_total(int change_total) {
            this.change_total = change_total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
