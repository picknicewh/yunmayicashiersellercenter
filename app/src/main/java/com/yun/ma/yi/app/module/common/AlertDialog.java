package com.yun.ma.yi.app.module.common;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

/**
 * 弹出
 */
public class AlertDialog {
	private Activity context;
	private android.app.AlertDialog ad;
	private TextView messageView, updateInfo,updateVersion;
	private Button no_onclik,yes_onclik;
	private Window window;
	public AlertDialog(Activity context) {
		this.context=context;
		G.initDisplaySize(context);
		ad = new android.app.AlertDialog.Builder(context).create();
		ad.show();
		//关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
		window = ad.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.width = (int) (G.size.W * 0.85);
		params.height=WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(params);
		window.setContentView(R.layout.alertdialog);
		messageView = (TextView)window.findViewById(R.id.message);
		yes_onclik = (Button)window.findViewById(R.id.yes_onclik);
		no_onclik = (Button)window.findViewById(R.id.no_onclik);
		updateInfo = (TextView) window.findViewById(R.id.update_info);
		updateVersion = (TextView) window.findViewById(R.id.update_version);

	}
	public void setMessage(int resId) {
		messageView.setText(resId);
	}

	public void setMessage(String message)
	{
		messageView.setText(message);
	}

	/**
	 * 设置按钮
	 * @param text
	 * @param listener
	 */
	public void setPositiveButton(String text, final View.OnClickListener listener) {
		yes_onclik.setText(text);
		yes_onclik.setOnClickListener(listener);

	}
 
	/**
	 * 设置按钮
	 * @param text
	 * @param listener
	 */
	public void setNegativeButton(String text, final View.OnClickListener listener) {
		no_onclik.setText(text);
		no_onclik.setOnClickListener(listener);
 
	}
	/**
	 * 设置版本信息
	 * @param version
	 */
	public void updateVersion(String version) {
		updateVersion.setText(version);
	}
	/**
	 * 设置内容
	 * @param info
	 */
	public void setUpdateInfo(String info) {
		updateInfo.setText(info);
	}
	/**
	 * 关闭对话框
	 */
	public void dismiss() {
		ad.dismiss();
	}
	/**
	 * 显示对话框
	 */
	public void show() {
		ad.show();
	}
	/**
	 * 边框外点击不消失
	 */
	public void setCanceledOnTouchOutside(boolean isFalse){
		ad.setCanceledOnTouchOutside(isFalse);
	}
}
