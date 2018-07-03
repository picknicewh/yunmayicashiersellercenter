package com.yun.ma.yi.app.module.shop.goods;

import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：店铺商品管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopGoodsManagerActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_goods_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
      setTitleTextId(R.string.shop_goods_manager);
    }
}
