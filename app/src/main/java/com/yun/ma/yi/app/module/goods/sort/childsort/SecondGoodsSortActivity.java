package com.yun.ma.yi.app.module.goods.sort.childsort;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/6/19
 * 名称：二级和三级分类界面
 * 版本说明：
 * 附加注释：根据传递的sortLevel值来分别判断；是二级还是三级分类界面
 * 主要接口：
 */
public class SecondGoodsSortActivity extends BaseActivity implements GoodsSecondSortContract.View, OnItemClickListener {
    @BindView(R.id.rv_goods_second_sort)
    RecyclerView rvGoodsSecondSort;
    /**
     * 适配器
     */
    private GoodsSecondSortAdapter adapter;
    /**
     * 数据列表
     */
    private List<SecondCategoryInfo> categoryInfos;
    /**
     * 数据处理
     */
    private GoodsSecondSortPresenter presenter;
    /**
     * 分类父级id
     */
    private int parentId;
    /**
     * 是否是还有分类
     */
    private static boolean isHasSort =true;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_second_sort;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        parentId = intent.getIntExtra("parentId",-1);
        setTitleText(title);
        categoryInfos  = new ArrayList<>();
        adapter  = new GoodsSecondSortAdapter(this,categoryInfos,R.layout.item_goods_second_sort);
        rvGoodsSecondSort.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvGoodsSecondSort.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter  = new GoodsSecondSortPresenter(SecondGoodsSortActivity.this,this);
        presenter.getSortList();

   }
    @Override
    public void setBack() {
        super.setBack();
        isHasSort= true;
    }
    @Override
    public void setSortList(List<SecondCategoryInfo> categoryInfos) {
        this.categoryInfos.clear();
        this.categoryInfos.addAll(categoryInfos);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public int getParentId() {
        return parentId;
    }

    @Override
    public void onClick(View view, int position) {
        if (isHasSort){
            Intent intent = new Intent(this, SecondGoodsSortActivity.class);
            intent.putExtra("title",categoryInfos.get(position).getName());
            intent.putExtra("parentId",categoryInfos.get(position).getId());
            startActivity(intent);
            isHasSort  =false;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( presenter!= null) {
            presenter.unSubscribe();
        }
    }
}
