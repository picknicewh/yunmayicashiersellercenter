package com.yun.ma.yi.app.module.goods;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.module.goods.edit.GoodsEditActivity;
import com.yun.ma.yi.app.module.goods.list.GoodsListActivity;
import com.yun.ma.yi.app.module.goods.sort.parentsort.GoodsSortActivity;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/6/19
 * 名称：商品管理界面
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsManagerActivity extends BaseActivity {


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_manager;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_manager);
    }

    /**
     * 商品分類
     */
    @OnClick(R.id.ll_goods_sort)
    public void goodsSort() {
        Intent intent = new Intent(this, GoodsSortActivity.class);
        startActivity(intent);
    }
    /**
     * 商品新增
     */
    @OnClick(R.id.ll_goods_add)
    public void goodsAdd() {
        Intent intent = new Intent(this, GoodsEditActivity.class);
        startActivity(intent);
    }
    /**
     * 商品列表
     */
    @OnClick(R.id.ll_goods_list)
    public void goodsList() {
        Intent intent = new Intent(this, GoodsListActivity.class);
        intent.putExtra("isShop",false);
        startActivity(intent);
    }

}
