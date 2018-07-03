package com.yun.ma.yi.app.module.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.utils.StringUtils;
import com.yunmayi.app.manage.R;

/**
 * 显示静态的文本项.
 */
public class ItemTextView extends LinearLayout {
	private TextView mlblName;
	private EditText lblVal;
//	private TextView lblHit;
	
	/**
	 * <code>当前值</code>.
	 */
	private String currVal;
	/**
	 * 标签名
	 */
	private String label;
	/**
	 * 标签字体颜色
	 */
	private int labelTextColor;
	/**
	 * 输入框提示字体颜色
	 */
	private int hintTextColor;
	/**
	 * 输入框文字
	 */
	private String hitText;
	/**
	 * 是否是小框框
	 */
	private boolean isSmall = true;
	public ItemTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array =  context.obtainStyledAttributes(attrs, R.styleable.ItemTextView);
		label  = array.getString(R.styleable.ItemTextView_mLabel);
		hitText  = array.getString(R.styleable.ItemTextView_mHint);
		labelTextColor = array.getColor(R.styleable.ItemTextView_mLabelTextColor, ContextCompat.getColor(context,R.color.main_text_color));
		hintTextColor  = array.getColor(R.styleable.ItemTextView_mHintTextColor, ContextCompat.getColor(context,R.color.goods_trade_text));
		isSmall =  array.getBoolean(R.styleable.ItemTextView_isSmall,false);
		array.recycle();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (isSmall){
			inflater.inflate(R.layout.globe_item_text_view_small, this);
		}else {
			inflater.inflate(R.layout.globe_item_text_view, this);
		}
		initView(context,attrs);

	}

	private void initView(Context context, AttributeSet attrs){

		mlblName = (TextView) this.findViewById(R.id.mlblName);
		lblVal = (EditText) this.findViewById(R.id.lblVal);
		mlblName.setText(label);
		mlblName.setTextColor(labelTextColor);
		lblVal.setHint(hitText);
		lblVal.setHintTextColor(hintTextColor);
	}
	public void  setLabelText(String text){
		mlblName.setText(text);
	}
	public String getLabelText(){
		return mlblName.getText().toString();
	}
	/**
	 * 设置编辑框的内容
	 */
	public void setText(String text){
		lblVal.setText(text);
	}
	/**
	 * 获取编辑框的内容
	 */
	public String getText(){
		return  lblVal.getText().toString();
	}
	/**
	 * 初始化控件.
	 * 
	 * @param label
	 *            标签名称.
	 * @param hit
	 *            提示信息.
	 */
	public void initLabel(String label, String hit) {
		mlblName.setText(StringUtils.isEmpty(label) ? "" : label);
	//	lblHit.setHint(StringUtils.isEmpty(hit) ? "" : hit);
	//	lblHit.setVisibility(StringUtils.isEmpty(hit) ? View.GONE: View.VISIBLE);
	}


	/**
	 * 初始化数值.
	 * @param dataLabel 数值标签.
	 * @param val 数值.
	 */
	public void initData(String dataLabel, String val) {
		this.currVal= StringUtils.isEmpty(val) ? "" : val;
		lblVal.setText(StringUtils.isEmpty(dataLabel) ? "" : dataLabel);
	}

	/**
	 * 初始化数值.
	 * @param dataLabel 数值标签.
	 */
	public void initData(String dataLabel ) {
		lblVal.setText(StringUtils.isEmpty(dataLabel) ? "" : dataLabel);
	}

	/**
	 * 得到当前值.
	 * @return
	 */
	public String getStrVal() {
		return getCurrVal();
	}

	
	/**
	 * 初始化.
	 */
	private void initMainView() {
		mlblName = (TextView) this.findViewById(R.id.mlblName);
		lblVal = (EditText) this.findViewById(R.id.lblVal);
	//	lblHit = (TextView) this.findViewById(R.id.lblHit);
	}

	/**
	 * 得到名称标签.
	 * 
	 * @return 名称标签.
	 */
	public TextView getLblName() {
		return mlblName;
	}

	/**
	 * 得到值处理.
	 * 
	 * @return 值处理.
	 */
	public EditText getLblVal() {
		return lblVal;
	}
	/**
	 * 得到当前值.
	 * @return 当前值.
	 */
	public String getCurrVal() {
		return currVal;
	}

}
