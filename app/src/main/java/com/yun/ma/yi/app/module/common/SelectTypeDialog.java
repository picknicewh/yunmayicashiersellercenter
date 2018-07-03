/**
 * 
 */
package com.yun.ma.yi.app.module.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.yun.ma.yi.app.utils.StringUtils;
import com.yun.ma.yi.app.widget.WheelView;
import com.yun.ma.yi.app.widget.adapters.AbstractWheelTextAdapter;
import com.yunmayi.app.manage.R;

/**
 * Created by ys on 2017/6/12.
 * 来源
 */
public class SelectTypeDialog extends Dialog {

	private Context mContext;
	private CardTypeAdapter mAdapter;
	private WheelView mWheelView;
	private Button mConfirmButton, mCancelButton;
	private String datas[];
	private TextView title;

	public SelectTypeDialog(Context context) {
		super(context, R.style.dialog);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.source_type_dialog);

		mConfirmButton = (Button) findViewById(R.id.card_type_confirm);
		mCancelButton = (Button) findViewById(R.id.card_type_cancel);
		title = (TextView) findViewById(R.id.card_type_title);
		this.setCanceledOnTouchOutside(true);
		Window window = this.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		Point p = new Point();
		window.getWindowManager().getDefaultDisplay().getSize(p);
		params.width = p.x;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.gravity = Gravity.BOTTOM;
		window.setAttributes(params);

		mWheelView = (WheelView) findViewById(R.id.card_type_wheel);
		mWheelView.setVisibleItems(3); // Number of items
		mWheelView.setWheelBackground(android.R.color.transparent);
		mWheelView.setWheelForeground(android.R.color.transparent);
		mWheelView.setShadowColor(0x00000000, 0x00000000, 0x00000000);
		mAdapter = new CardTypeAdapter(mContext);
		mWheelView.setViewAdapter(mAdapter);
		mWheelView.setCurrentItem(0);
	}

	public Button getConfirmButton() {
		return mConfirmButton;

	}

	public Button getCancelButton() {
		return mCancelButton;

	}

	public TextView getTitle() {
		return title;
	}

	/**赋值*/
	public void initData(String datas[]){
		this.datas = datas;
	}
	/**修改选中*/
	public void updateType(String sourceType){
		for (int i = 0; i < datas.length; i++ ){
			if (!StringUtils.isEmpty(sourceType) && sourceType.equals(datas[i])){
				mWheelView.setCurrentItem(i);
			}
		}
	}

	public String getCurrentData() {
		return mAdapter.getItemText(mWheelView.getCurrentItem()).toString();

	}

	private class CardTypeAdapter extends AbstractWheelTextAdapter {
		/**
		 * Constructor
		 */
		protected CardTypeAdapter(Context context) {
			super(context, R.layout.type_wheel, NO_RESOURCE);
			setItemTextResource(R.id.card_type_text);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return datas.length;
		}

		@Override
		protected CharSequence getItemText(int index) {
			return datas[index];
		}
	}

}
