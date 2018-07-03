package com.yun.ma.yi.app.module.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.yun.ma.yi.app.api.ApiManager;
import com.yun.ma.yi.app.api.BaseSubscriber;
import com.yun.ma.yi.app.api.RequestCallback;
import com.yun.ma.yi.app.api.util.RequestParameter;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/6/23
 * 名称：分类选择对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CategoryChooseDialog extends AlertDialog implements OnItemClickListener {

    private Activity context;
    /**
     * 分类列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 适配器
     */
    private CategoryChooseAdapter adapter;
    /**
     * 分类数据列表
     */
    private List<GoodsListInfo> categoryInfos;
    /**
     * 回调事件
     */
    private OnCategoryClickListener categoryClickListener;
    private Subscription mSubscription;

    public CategoryChooseDialog(Activity context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_category_choose);
     //   setCancelable(false);//设置外部不可点击
        getSortList();
        initView();
    }
    private void initView(){
        setDialogWidth();
        categoryInfos = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_category);

        adapter = new CategoryChooseAdapter(context,categoryInfos,R.layout.item_dialog_categroy_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }
    public void setCategoryInfos(List<GoodsListInfo> categoryInfos){
        this.categoryInfos.clear();
        this.categoryInfos.addAll(categoryInfos);
        this.categoryInfos.remove(categoryInfos.size()-1);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
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

    @Override
    public void onClick(View view, int position) {
        if (categoryClickListener!=null){
             categoryClickListener.OnCategoryIdCatch(categoryInfos.get(position).getCategory_id());
             dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSubscription != null  && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    public void getSortList() {
        RequestParameter parameter = new RequestParameter();
        parameter.setParam("uid", UserMessage.getInstance().getUId());
        mSubscription = ApiManager.getApiManager()
                .getItemCategory(parameter.getMapParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<>(new RequestCallback<Result<List<GoodsListInfo>>>() {
                    @Override
                    public void beforeRequest() {

                    }
                    @Override
                    public void requestError(String msg) {
                        G.showToast(context,msg);

                    }
                    @Override
                    public void requestComplete() {

                    }
                    @Override
                    public void requestSuccess(Result<List<GoodsListInfo>> data) {
                        List<GoodsListInfo> goodsListInfos = data.getData();
                        setCategoryInfos(goodsListInfos);
                    }
                },context));
    }
    public void setCategoryClickListener(OnCategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }


    public interface  OnCategoryClickListener {
        void OnCategoryIdCatch(int categoryId);
    }
}
