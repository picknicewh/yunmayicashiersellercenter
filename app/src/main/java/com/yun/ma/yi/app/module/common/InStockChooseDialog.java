package com.yun.ma.yi.app.module.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：入库商品选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InStockChooseDialog extends android.app.AlertDialog implements OnItemClickListener {
    /**
     * 入库选择列表
     */
    private   RecyclerView rvInstock;
    /**
     * 适配器
     */
    private InStockChooseDialogAdapter adapter;
    private Activity context;
    /**
     * r入库商品选择列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 回调
     */
    private onItemChooseListener onItemChooseListener;
    public InStockChooseDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_instock_choose);
        rvInstock = (RecyclerView) findViewById(R.id.rv_instock);
        setDialogWidth();
        goodsDetailInfoList = new ArrayList<>();
        adapter = new InStockChooseDialogAdapter(context,goodsDetailInfoList);
        rvInstock.setLayoutManager(new LinearLayoutManager(context));
        rvInstock.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }
    /**
     * 设置屏幕的宽度
     */
    private void setDialogWidth(){
        G.initDisplaySize(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (G.size.W* 0.8);
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager. LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }
    public void  setGoodsDetailInfoList(List<GoodsDetailInfo> goodsDetailInfoList){
        this.goodsDetailInfoList.clear();
        this.goodsDetailInfoList.addAll(goodsDetailInfoList);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View view, int position) {
       dismiss();
        if (onItemChooseListener!=null){
            onItemChooseListener.onChoose(view,position);
        }
    }

    public void setOnItemChooseListener(InStockChooseDialog.onItemChooseListener onItemChooseListener) {
        this.onItemChooseListener = onItemChooseListener;
    }

    public interface  onItemChooseListener{
        void  onChoose(View view ,int position);
    }
}
