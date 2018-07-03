package com.yun.ma.yi.app.module.shop.code;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yun.ma.yi.app.module.Constants;

public class AppRegister extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		final IWXAPI api = WXAPIFactory.createWXAPI(context, null);
		// 将该app注册到微信
		api.registerApp(Constants.WECHAR_APP_ID);
	}
}
