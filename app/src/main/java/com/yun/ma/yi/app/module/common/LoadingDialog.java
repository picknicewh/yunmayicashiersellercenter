package com.yun.ma.yi.app.module.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.yunmayi.app.manage.R;

/**
 * @author ys
 */
public class LoadingDialog extends Dialog {
	private Context mContext;
	private boolean mCancelable;
    private String title;
    private boolean showLoadingPercent;
    private TextView percentTxt;

	public LoadingDialog(Context context) {
		super(context, R.style.dialog);
		mContext = context;
	}
	public LoadingDialog(Context context,String title) {
        super(context, R.style.dialog);
        mContext = context;
        this.title = title;
    }
	public LoadingDialog(Context context, boolean cancelable) {
		super(context, R.style.dialog);
		mContext = context;
		mCancelable = cancelable;
	}
	
	public LoadingDialog(Context context,String title,boolean showLoadingPercent) {
        super(context, R.style.dialog);
        mContext = context;
        this.title = title;
        this.showLoadingPercent = showLoadingPercent;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_dialog_layout);
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(mCancelable);
		findViewById(R.id.loading_image).startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.progress_anim));
		if(this.title != null && !"".equals(title)) {
		    ((TextView)findViewById(R.id.loading_title)).setText(title);
		}
		if(showLoadingPercent) {
		    percentTxt = ((TextView)findViewById(R.id.loading_percent));
		    percentTxt.setVisibility(View.VISIBLE);
		}
	}
	public void setPercent(int percent) {
	    if(percentTxt == null) {
	        return;
	    }
	    percentTxt.setText(percent + "%");
	}
}
