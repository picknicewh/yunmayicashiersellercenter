package com.yun.ma.yi.app.module.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.ma.yi.app.module.common.view.listener.IItemIsChangeListener;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.StringUtils;
import com.yun.ma.yi.app.utils.ToastUtils;
import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/6/29
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public  class ItemEditText2 extends LinearLayout implements TextWatcher {
    private final static String TYPE_NUMBER = "number";
    private final static String TYPE_TEXT = "text";
    private final static String TYPE_PASSWORD = "password";
    private final static String TYPE_PHONE = "phone";
    private final static String TYPE_NUMBER_DECIMALD = "numberDecimal";
    /**
     * <code>当前值</code>.
     */
    private String currVal="";
    /**
     * <code>原来值</code>.
     */
    private String oldVal="";

    /**
     * <code>状态：是否改变</code>.
     */
    protected Boolean changeStatus = false;

    /**
     * <code>改变标签</code>.
     */
    protected TextView saveTag;
    /**
     * <code>清空按钮</code>
     */
    protected ImageView img;
    /**
     * 左边文字
     */
    private TextView lblName;
    /**
     * 编辑框
     */
    private EditText lblVal;

    /**
     * 修改数据监听器，如果数据有发生改变，更新UI Title信息
     */
    protected IItemIsChangeListener itemChange;
    /**
     * 左边的label值
     */
    public String label;
    /**
     * 背景颜色
     */
    public int mBackgroundColor;
    /**
     * EditText输入类型之
     */
    public String mInputType;
    /**
     * EditText 提示文字
     */
    private String hidText;
    /**
     * 最大字符数
     */
    private int maxlength;
    /**
     * 上下文本
     */
    private Context mContext;
    /**
     * 提示文字的颜色
     */
    private int hitTextColor;
    /**
     * 编辑框文字的颜色
     */
    private int editTextColor;
    public ItemEditText2(Context context) {
        this(context,null);
    }
    public ItemEditText2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context,attrs);
    }
    private void initView(Context context, AttributeSet attrs){
        TypedArray array =  context.obtainStyledAttributes(attrs, R.styleable.ItemBase);
        label  = array.getString(R.styleable.ItemBase_label);
        hidText  = array.getString(R.styleable.ItemBase_hitText);
        mInputType = array.getString(R.styleable.ItemBase_mInputType);
        hitTextColor = array.getColor(R.styleable.ItemBase_hitTextColor, ContextCompat.getColor(context,R.color.red_btn));
        editTextColor = array.getColor(R.styleable.ItemBase_mEditTextColor,ContextCompat.getColor(context,R.color.goods_trade_text));
        maxlength = array.getInteger(R.styleable.ItemBase_mMaxLength,100);
        mBackgroundColor = array.getResourceId(R.styleable.ItemBase_mBackgroundColor, Color.WHITE);
        array.recycle();
        initLayout(context);
    }
    private void initLayout(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.globe_item_edit_text, this);
        lblName = (TextView) findViewById(R.id.lblName);
        lblVal = (EditText)findViewById(R.id.lblVal);
        lblVal.setTextColor(editTextColor);
        lblVal.setHintTextColor(hitTextColor);
        saveTag = (TextView) findViewById(R.id.saveTag);
        img = (ImageView)findViewById(R.id.img);
        lblName.setText(StringUtils.isEmpty(label) ? "" : label);
        lblVal.setHint(StringUtils.isEmpty(hidText) ? "" : hidText);
        lblVal.setBackgroundColor(mBackgroundColor);
        lblVal.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxlength)});
        lblVal.addTextChangedListener(this);
        if (TYPE_NUMBER.equals(mInputType)) {
            lblVal.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (TYPE_PASSWORD.equals(mInputType)){
            lblVal.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (TYPE_TEXT.equals(mInputType)){
            lblVal.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (TYPE_PHONE.equals(mInputType)){
            lblVal.setInputType(InputType.TYPE_CLASS_PHONE);
        }else if (TYPE_NUMBER_DECIMALD.equals(mInputType)){
            lblVal.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

            lblVal.setFilters(new InputFilter[]{new CashierInputFilter()});
        }
        lblVal.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && !"".equals(lblVal.getText().toString())) {
                    img.setVisibility(View.VISIBLE);
                }else if (!hasFocus) {
                    img.setVisibility(View.GONE);
                }
            }
        });
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTextVal();
            }
        });
    }
    /**
     * 初始化控件的值
     * @param label   表单项名称.
     * @param hit  提示文字.
     * @param isRequest    是否必填.
     * @param inputType    键盘理性，数字例子：InputType.TYPE_CLASS_NUMBER，
     */
    public void initLabel(String label, String hit, Boolean isRequest, int inputType) {
        lblName.setText(StringUtils.isEmpty(label) ? "" : label);
        lblVal.setHintTextColor(isRequest ? Color.RED : Color.GRAY);
        lblVal.setHint(isRequest ? getResources().getString(R.string.NECESSARY) : getResources().getString(R.string.NOT_NECESSARY));
        lblVal.setInputType(inputType);
    }
    /**
     * 获取编辑框的值
     * @return  编辑框的值
     */
    public String getText(){
        return  lblVal.getText().toString();
    }
    /**
     * 设置编辑框的值
     * @param text
     */
    public void setText(String text){
        //初始值
        oldVal =text;
        currVal= text;
        this.lblVal.setText(text);
    }
    /**
     * 设置编辑框的值
     * @param text
     */
    public void setHintText(String text){
        this.lblVal.setHint(text);
    }
    public void setLabelText(String text){
        lblName.setText(text);
    }
    public String getlLabelText(){
        return lblName.getText().toString();
    }

    public void setEnabled(boolean enabled){
        lblVal.setEnabled(enabled);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(lblVal.getText().length() > maxlength){
            ToastUtils.makeText(mContext, lblName.getText()+getResources().getString(R.string.not_than_max_length)+maxlength);
            return;
        }
    }
    @Override
    public void afterTextChanged(Editable s) {
        String val=lblVal.getText().toString();
        if (lblVal.isFocused()) {
            if (!val.equals("")) {
                img.setVisibility(View.VISIBLE);
            }
        }
        currVal  = G.isEmteny(val) ? "" : val;
        if(G.isEmteny(val)){
            lblVal.setHint(hidText);
            img.setVisibility(View.GONE);
        }
        isChangeEditText();
        if(itemChange!=null){
            itemChange.onItemIsChangeListener(this);
        }
    }
    /**
     * @return
     */
    public Boolean isChangeEditText() {
        changeStatus = !StringUtils.isEquals(currVal, oldVal);
        saveTag.setVisibility(changeStatus ? View.VISIBLE : View.GONE);
        //如果有修改，把是否修改的结果信息反馈给UI界面
        if(itemChange!= null)
            itemChange.onItemIsChangeListener(this);
        return changeStatus;
    }
    /**
     * 清空editText里面的
     */
    public void clearEditTextVal() {
        lblVal.setText("");
        img.setVisibility(View.GONE);
    }

    /**
     * 设置可输入的文本的最大长度
     */
    public void setMaxLength(int length) {
        maxlength = length;
        lblVal.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }
    public void setItemChangeListener(IItemIsChangeListener itemChange) {
        this.itemChange = itemChange;
    }
}
