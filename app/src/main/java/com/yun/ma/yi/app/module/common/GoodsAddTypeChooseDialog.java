package com.yun.ma.yi.app.module.common;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yunmayi.app.manage.R;

/**
 * 作者： wh
 * 时间：  2017/12/2
 * 名称：添加一键上架商品的方式选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class GoodsAddTypeChooseDialog extends PopupWindow implements View.OnClickListener {
    /**
     * 全选类型
     */
    public static final int  TYPE_ALL = 1;
    /**
     * 按分类选择类型
     */
    public static final int  TYPE_SORT = 2;
    /**
     * 全选
     */
    private TextView tvAll;
    /**
     * 按分类选择
     */
    private TextView tvSort;
    /**
     * 取消
     */
    private TextView tvCancel;
    private Activity context;
    /**
     * 选择回调
     */
    private OnTypeChooseCallBack onTypeChooseCallBack;
    /**
     * view
     */
    private View contentView;
    public GoodsAddTypeChooseDialog(Activity context) {
        super(context);
        this.context = context;
       init();
    }
    private void initView(){
        contentView = LayoutInflater.from(context).inflate(R.layout.dialog_goods_add_type,null);
        tvAll = (TextView) contentView.findViewById(R.id.tv_all);
        tvSort = (TextView) contentView.findViewById(R.id.tv_sort);
        tvCancel = (TextView) contentView.findViewById(R.id.tv_cance1);
        tvAll.setOnClickListener(this);
        tvSort.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

    }
    /**
     * 设置屏幕的宽度
     */
    private void init() {
        initView();
        setContentView(contentView);
        //设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SignPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SignPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        //设置SignPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.tv_all) {
            if (onTypeChooseCallBack!=null){
                onTypeChooseCallBack.onCallBack(TYPE_ALL);
            }
        } else if (viewId == R.id.tv_sort) {
            if (onTypeChooseCallBack!=null){
                onTypeChooseCallBack.onCallBack(TYPE_SORT);
            }
        }
        dismiss();
    }

    public void setOnTypeChooseCallBack(OnTypeChooseCallBack onTypeChooseCallBack) {
        this.onTypeChooseCallBack = onTypeChooseCallBack;
    }

    public interface OnTypeChooseCallBack{
        void onCallBack(int type);
    }
}
