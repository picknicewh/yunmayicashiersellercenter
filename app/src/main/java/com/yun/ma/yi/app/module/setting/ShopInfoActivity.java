package com.yun.ma.yi.app.module.setting;

import android.os.Bundle;

import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.Shop;
import com.yun.ma.yi.app.module.common.view.ItemTextView;
import com.yunmayi.app.manage.R;

import butterknife.BindView;

/**
 * Created by ys on 2017/6/16.
 * 店铺信息
 */

public class ShopInfoActivity extends BaseActivity{


    @BindView(R.id.shop_name)
    ItemTextView shopName;
    @BindView(R.id.address)
    ItemTextView address;
    @BindView(R.id.contacts)
    ItemTextView contacts;
    @BindView(R.id.phone)
    ItemTextView phone;
    private Shop shop;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shopinfo;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.shop_info);

        shopName.initLabel(getString(R.string.shop_name),"");
        address.initLabel(getString(R.string.address), "");
        contacts.initLabel(getString(R.string.contacts), "");
        phone.initLabel(getString(R.string.phone), "");

        initData();

    }
    /**
     * 初始化数据
     */
    private void initData(){
        shop = YunmayiApplication.getShop();
        shopName.initData(YunmayiApplication.getShop().getCompany());
        address.initData(shop.getAddress());
        contacts.initData(shop.getName());
        phone.initData(shop.getMobile());
    }
}
