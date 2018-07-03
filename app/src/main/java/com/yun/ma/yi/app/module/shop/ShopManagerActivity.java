package com.yun.ma.yi.app.module.shop;

import android.content.Intent;
import android.os.Bundle;

import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.ShopInfo;
import com.yun.ma.yi.app.module.goods.list.GoodsListActivity;
import com.yun.ma.yi.app.module.shop.balance.BalanceAccountActivity;
import com.yun.ma.yi.app.module.shop.cash.ShopOrderCashActivity;
import com.yun.ma.yi.app.module.shop.code.ShopEwCodeActivity;
import com.yun.ma.yi.app.module.shop.order.ShopOrderManagerActivity;
import com.yun.ma.yi.app.module.shop.setting.ShopSettingActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yunmayi.app.manage.R;

import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/11/20
 * 名称：蚂蚁小店管理
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopManagerActivity extends BaseActivity implements ShopManagerContract.View {
    private ShopManagerPresenter presenter;
    private ShopInfo shopInfo;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_shop_manager;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.mayi_shop);
        presenter = new ShopManagerPresenter(this, this);
        presenter.getShopInfo();
    }

    /**
     * 店铺二维码
     */
    @OnClick(R.id.tv_shop_ewcode)
    public void shopCode() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_READING_SHOP_EWCODE)) {
            if (shopInfo == null) {
                showMessage("你的蚂蚁小店店铺信息不完善，先完善店铺信息！");
            } else {
                Intent intent = new Intent(this, ShopEwCodeActivity.class);
                intent.putExtra("shopId", shopInfo.getId());
                intent.putExtra("shopName", shopInfo.getTitle());
                startActivity(intent);
            }
        } else {
            showMessage("你没有店铺二维码权限！");
        }
    }

    /**
     * 店铺设置
     */
    @OnClick(R.id.tv_shop_settings)
    public void shopSettings() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_SHOP_SETTING)) {
            Intent intent = new Intent(this, ShopSettingActivity.class);
            intent.putExtra("shopInfo", shopInfo);
            startActivityForResult(intent, 0);
        } else {
            showMessage("你没有店铺设置权限！");
        }

    }

    /**
     * 订单管理
     */
    @OnClick(R.id.tv_order_manager)
    public void orderManager() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_SHOP_OREDR_MANAGER)) {
            if (shopInfo == null) {
                showMessage("你的蚂蚁小店店铺信息不完善，先完善店铺信息！");
            } else {
                Intent intent = new Intent(this, ShopOrderManagerActivity.class);
                startActivity(intent);
            }
        } else {
            showMessage("你没有店铺订单管理权限！");
        }
    }

    /**
     * 商品管理
     */
    @OnClick(R.id.tv_goods_manager)
    public void goodsManager() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_SHOP_GOODS_MANAGER)) {
            if (shopInfo == null) {
                showMessage("你的蚂蚁小店店铺信息不完善，先完善店铺信息！");
            } else {
                Intent intent = new Intent(this, GoodsListActivity.class);
                intent.putExtra("isShop", true);
                intent.putExtra("shopId", shopInfo.getId());
                intent.putExtra("isHaveCashier", shopInfo.getIsHaveCashier() == 1);
                startActivity(intent);
            }
        } else {
            showMessage("你没有店铺商品管理权限！");
        }
    }

    /**
     * 提现申请
     */
    @OnClick(R.id.tv_order_cash)
    public void orderCash() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_SHOP_ORDER_CASH)) {
            if (shopInfo == null) {
                showMessage("你的蚂蚁小店店铺信息不完善，先完善店铺信息！");
            } else {
                Intent intent = new Intent(this, ShopOrderCashActivity.class);
                intent.putExtra("shopId", shopInfo.getId());
                intent.putExtra("balance", shopInfo.getBalance());
                startActivity(intent);
            }
        } else {
            showMessage("你没有店铺提现申请权限！");
        }
    }

    /**
     * 提现申请
     */
    @OnClick(R.id.tv_check_count)
    public void checkAccount() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_SHOP_CHECK_ACCOUNT)) {
            if (shopInfo == null) {
                showMessage("你的蚂蚁小店店铺信息不完善，先完善店铺信息！");
            } else {
                Intent intent = new Intent(this, BalanceAccountActivity.class);
                intent.putExtra("shopId", shopInfo.getId());
                intent.putExtra("balance", shopInfo.getBalance());
                startActivity(intent);
            }
        } else {
            showMessage("你没有余额对账权限！");
        }
    }

    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUser_id();
    }


    @Override
    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    @Override
    public String getUserName() {
        return UserMessage.getInstance().getUsername();
    }

    @Override
    public String getPassword() {
        return UserMessage.getInstance().getPassword();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            presenter.getShopInfo();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
