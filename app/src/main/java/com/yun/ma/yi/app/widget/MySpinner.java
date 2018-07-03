package com.yun.ma.yi.app.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yun.ma.yi.app.widget.adapters.SpinnerAdapter;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/22
 * 名称：下拉列表框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MySpinner  extends PopupWindow implements AdapterView.OnItemClickListener {
    /**
     * 装载view
     */
    private View contentView;
    /**
     * 上下文本
     */
    private Context context;
    /**
     * 列表数据
     */
    private List<String> dataList;
    /**
     * 列表
     */
    private ListView mListView;
    /**
     * 适配器
     */
    private SpinnerAdapter adapter;
    /**
     * 监听
     */
    private OnItemSelectListener onItemSelectListener;
    public MySpinner(Context context) {
        super(context);
        this.context  =context;
        init();
    }

    private void initView(){
        contentView = LayoutInflater.from(context).inflate(R.layout.spiner_window_layout,null);
        dataList = new ArrayList<>();
        mListView = (ListView) contentView.findViewById(R.id.listview);
        adapter = new SpinnerAdapter(context,dataList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }
    /**
     * 初始化popwindow各项参数
     */
    public void init() {
        initView();
        //设置SignPopupWindow的View
        this.setContentView(contentView);
        //设置SignPopupWindow弹出窗体的高
        //设置SignPopupWindow弹出窗体的宽
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置SignPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        //设置SignPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
    private int viewId=0;
    public void setDataList(int viewId,List<String> dataList,int index) {
        this.viewId = viewId;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        adapter.initData(index);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        if (onItemSelectListener!=null){
            adapter.initData(position);
            onItemSelectListener.onItemSelect(viewId,position);
        }
    }
    public  interface OnItemSelectListener{
        void onItemSelect(int viewId,int position);
    }
}
