package com.yun.ma.yi.app.module.marketing.choose.mul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.yun.ma.yi.app.base.BaseActivity;
import com.yun.ma.yi.app.bean.GoodsChooseVo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.ClearEditText;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChooseContract;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChoosePresenter;
import com.yun.ma.yi.app.userdb.BeanChangeUtil;
import com.yun.ma.yi.app.userdb.Item;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsChooseSearchActivity extends BaseActivity implements GoodsChooseContract.ViewSearch, OnItemClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView>, GoodsAddChooseAdapter.OnCheckChangeListener {
    /**
     * 搜索编辑
     */
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    /**
     * 搜索列表
     */
    @BindView(R.id.rv_search)
    PullToRefreshRecyclerView refreshRecyclerView;
    RecyclerView rvSearch;
    @BindView(R.id.tv_save)
    TextView tvSave;
    /**
     * 数据处理类
     */
    private GoodsChoosePresenter presenter;
    /**
     * 来源
     */
    private int source;
    /**
     * 商品数据列表
     */
    private List<GoodsDetailInfo> goodsDetailInfoList;
    /**
     * 被删除的id
     */
    private String deleteId;
    /**
     * 页数
     */
    private int page = 1;
    /**
     * 页码
     */
    private int size = 10;
    /**
     * 是否还有更多数据
     */
    private boolean hasMoreData;
    /**
     * 本地数据列表
     */
    private ItemDao itemDao;
    private GoodsAddChooseAdapter adapter;

    private ArrayList<GoodsChooseVo> goodsChooseVoList;
    /**
     * 搜索出来的所有商品
     */
    private List<GoodsDetailInfo> allGoodsDetailInfoList;
    /**
     * 当前选择的分类id
     */
    private int currentCategoryId;
    /**
     * 条形码
     */
    private String scanCode;
    /**
     * 当前选中的否全选
     */
    private boolean isAllSelect;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_goods_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        allGoodsDetailInfoList = new ArrayList<>();
        tvSave.setVisibility(View.VISIBLE);
        itemDao = new ItemDao(this);
        source = getIntent().getIntExtra("source", Constants.BARGAIN_GOODS_CHOOSE);
        isAllSelect = getIntent().getBooleanExtra("isSelectAll",false);
        goodsChooseVoList = new ArrayList<>();
        goodsChooseVoList = getIntent().getParcelableArrayListExtra("goodsChooseVoList");
        rvSearch = refreshRecyclerView.getRefreshableView();
        refreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        refreshRecyclerView.setOnRefreshListener(this);
        goodsDetailInfoList = new ArrayList<>();
        adapter = new GoodsAddChooseAdapter(goodsDetailInfoList);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(adapter);
        adapter.setOnCheckChangeListener(this);
        adapter.setOnItemClickListener(this);
        presenter = new GoodsChoosePresenter(this, this);
        scanCode = getIntent().getStringExtra("scanCode");
        etSearch.setText(scanCode);
        if (!G.isEmteny(scanCode)){
            presenter.getSearchGoodsList();
        }
    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        hideTitleLayout(true);
    }

    @OnClick(R.id.tv_search)
    public void search() {
        page=1;
        presenter.getSearchGoodsList();
    }


    @Override
    public int getUserId() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getKeyWord() {
        return etSearch.getText().toString();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> detailInfoList) {
        if (page == 1) {
            goodsDetailInfoList.clear();
        }
        hasMoreData = detailInfoList.size() == size ? true : false;
        goodsDetailInfoList.addAll(detailInfoList);
        allGoodsDetailInfoList.addAll(goodsDetailInfoList);
        if (isAllSelect){
            saveEditGoods();
        }else {
            initShowData();
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    /**
     * 保存编辑时商品的信息并且还原编辑商品列表
     */
    private void initShowData() {
        for (int i = 0; i < goodsDetailInfoList.size(); i++) {
            GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(i);
            int categoryId = goodsDetailInfoList.get(i).getCategory_id();
            boolean isCheck = itemDao.getIsCheckById(goodsDetailInfo.getId(),categoryId);
            goodsDetailInfoList.get(i).setCheck(isCheck);
        }
    }
    /**
     * 全选的还原编辑商品列表
     */
    private void saveEditGoods() {
        for (int i = 0; i < goodsChooseVoList.size(); i++) {
            GoodsChooseVo goodsChooseVo = goodsChooseVoList.get(i);
            String goodsIds = goodsChooseVo.getGoodsIds();
            boolean  isAllSelect = goodsChooseVo.isSelect();
                for (int j = 0; j < goodsDetailInfoList.size(); j++) {
                    GoodsDetailInfo goodsDetailInfo = goodsDetailInfoList.get(j);
                        if (goodsIds.contains(goodsDetailInfo.getId())) {
                            goodsDetailInfoList.get(j).setCheck(!isAllSelect);
                        } else {
                            goodsDetailInfoList.get(j).setCheck(isAllSelect);
                        }
                    int categoryId = goodsDetailInfo.getCategory_id();
                    if (categoryId==0){
                        categoryId=99;
                    }
                    List<Item> items= itemDao.getListByGoodsId(goodsDetailInfo.getId(),categoryId);
                    if (items.size()==0){
                        Item item = BeanChangeUtil.GoodsDetailInfo2item(goodsDetailInfoList.get(j), categoryId);
                        itemDao.save(item);
                    }
                }
                break;
            }
    }

    @Override
    public void onClick(View view, int position) {
        deleteId = goodsDetailInfoList.get(position).getId();
        goodsDetailInfoList.remove(position);
        if (adapter != null) {
            adapter.notifyItemRemoved(position);
        }
        finish();
    }

    @OnClick(R.id.tv_back)
    public void back() {
        for (int i = 0 ;i<allGoodsDetailInfoList.size();i++){
            int categoryId = allGoodsDetailInfoList.get(i).getCategory_id();
            if (categoryId==0){
                categoryId=99;
            }//没有保存就关闭则，删除
            itemDao.deleteById(categoryId, allGoodsDetailInfoList.get(i).getId());
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getSearchGoodsList();
        refreshView.onRefreshComplete();
        if (!hasMoreData) {
            G.showToast(this, "没有更多数据了");
            refreshRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        }
    }
    private void saveCheckGoods(boolean isCheck,String goodsId,int categoryId){
        GoodsChooseVo    goodsChooseVo = new GoodsChooseVo();
        goodsChooseVo.setSelect(isCheck);
        goodsChooseVo.setGoodsIds(goodsId);
        goodsChooseVo.setCategoryId(categoryId);
        goodsChooseVoList.add(goodsChooseVo);
    }
    @Override
    public void onCheck(int position, boolean isCheck) {
        goodsDetailInfoList.get(position).setCheck(isCheck);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        int categoryId = goodsDetailInfoList.get(position).getCategory_id();
        if (categoryId==0){
            categoryId=99;
        }
        String goodsId = goodsDetailInfoList.get(position).getId();
        if (isCheck) {//从false-true从无到有
            List<Item> items = itemDao.getListByGoodsId(goodsId,categoryId);//查询当前商品的是否存在
            GoodsChooseVo goodsChooseVo;
            if (items.size() == 0) {
                int  categoryIdCount = itemDao.getCheckByCategoryId(categoryId);//判断当前分类的类目选中数，如果等于0，则新加一个选中分类
                if (categoryIdCount==0){
                    saveCheckGoods(false,goodsId,categoryId);
                }
                itemDao.save(BeanChangeUtil.GoodsDetailInfo2item(goodsDetailInfoList.get(position), categoryId));//保存当前商品的信息
            } else {//如果存在更新商品信息
                itemDao.updateCheckByInfo(categoryId, goodsId, 1);//修改当前保存的状态
                int choosePosition = isinclude(goodsChooseVoList, categoryId);//判断当前商品是否选中
                if (choosePosition != -1) {//存在更新选中商品数
                    goodsChooseVo = goodsChooseVoList.get(choosePosition);
                    boolean isSelectAll = goodsChooseVo.isSelect();
                    if (!isSelectAll) {
                        goodsChooseVo.setGoodsIds(goodsChooseVo.getGoodsIds() + "," + goodsId);
                    }
                    goodsChooseVoList.set(choosePosition, goodsChooseVo);
                }else {
                    //保存商品
                    saveCheckGoods(false,goodsId,categoryId);
                }
            }
        } else {//从true-false从有到无
            itemDao.updateCheckByInfo(categoryId, goodsId, 0);//修改当前保存的状态
            int choosePosition = isinclude(goodsChooseVoList, categoryId);
            int  count = itemDao.getCheckByCategoryId(categoryId);//判断当前分类的类目选中数，如果等于0，则新加一个选中分类
            if (choosePosition != -1) {
                GoodsChooseVo goodsChooseVo = goodsChooseVoList.get(choosePosition);
                if (count==0&&!isAllSelect){
                    goodsChooseVoList.remove(choosePosition);
                }else {
                    boolean isSelectAll = goodsChooseVo.isSelect();
                    if (isSelectAll) {
                        goodsChooseVo.setGoodsIds(goodsChooseVo.getGoodsIds() + "," + goodsId);
                    }
                    goodsChooseVoList.set(choosePosition, goodsChooseVo);
                }
            }
        }
    }

    /**
     * 是否包含当前的选中信息
     *
     * @param goodsChooseVoList 选中商品的信息
     * @return 选中的第几个位置
     */
    private int isinclude(List<GoodsChooseVo> goodsChooseVoList, int categoryId) {
        int position = -1;
        for (int i = 0; i < goodsChooseVoList.size(); i++) {
            if (categoryId == goodsChooseVoList.get(i).getCategoryId()) {
                position = i;
            }
        }
        return position;
    }


    @OnClick(R.id.tv_save)
    public void save() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("goodsChooseVoList", goodsChooseVoList);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            for (int i = 0 ;i<allGoodsDetailInfoList.size();i++){
                int categoryId = allGoodsDetailInfoList.get(i).getCategory_id();
                if (categoryId==0){
                    categoryId=99;
                }//没有保存就关闭则，删除
                itemDao.deleteById(categoryId, allGoodsDetailInfoList.get(i).getId());
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
