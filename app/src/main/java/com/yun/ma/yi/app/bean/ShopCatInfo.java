package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/12/2
 * 名称：蚂蚁小店商品分类信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopCatInfo {

    /**
     * "count":2,
     "shop_cat_id":1066,
     "title":"火机",
     "shop_id":1000175
     */
    private  int count;
    private int shop_cat_id;
    private String category_title;
  //  private String v3Category_name;
    private int shop_id;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getShop_cat_id() {
        return shop_cat_id;
    }

    public void setShop_cat_id(int shop_cat_id) {
        this.shop_cat_id = shop_cat_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

  /*  public String getV3Category_name() {
        return v3Category_name;
    }

    public void setV3Category_name(String v3Category_name) {
        this.v3Category_name = v3Category_name;
    }
*/
    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
