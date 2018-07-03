package com.yun.ma.yi.app.module.marketing.choose.mul;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.module.Constants;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChooseContract;
import com.yun.ma.yi.app.module.marketing.choose.GoodsChoosePresenter;
import com.yun.ma.yi.app.userdb.BeanChangeUtil;
import com.yun.ma.yi.app.userdb.Item;
import com.yun.ma.yi.app.userdb.ItemDao;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.zxing.activity.CaptureActivity;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yun.ma.yi.app.module.Constants.REQUEST_STOCK_SEARCH;

/**
 * 新增/编辑商品
 */
public class GoodsEditChooseActivity2 extends BaseActivity implements GoodsChooseContract.View, View.OnClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView>, GoodsAddChooseAdapter.OnCheckChangeListener, OnItemClickListener {
    /**
     * 搜索编辑框
     */
    @BindView(R.id.et_code_search)
    TextView etCodeSearch;
    /**
     * 商品列表
     */
    @BindView(R.id.pullRecyclerView)
    PullToRefreshRecyclerView pullRecyclerView;
    /**
     * 是否全选
     */
    @BindView(R.id.tv_select)
    TextView tvSelectAll;
    /**
     * 全部全选
     */
    @BindView(R.id.tv_select_all)
    TextView tvSelectAlMost;
    private RecyclerView rv_goods;
    /**
     * 分类选择
     */
    @BindView(R.id.rv_category_list)
    RecyclerView rvCategoryList;
    /**
     * 分类id
     */
    private int categoryId = 99;
    /**
     * 商品分类列表
     */
    private List<GoodsListInfo> goodsListInfoList;
    /**
     * 所以的商品详情列表
     */
    private List<GoodsDetailInfo> goodsDetailInfos;
    /**
     * 商品详情适配器
     */
    private GoodsAddChooseAdapter detailAdapter;

    /**
     * 分类选择适配器
     */
    private GoodSortChooseAdapter sortChooseAdapter;
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
     * 选中商品的分类和商品对应下id
     */
    private ArrayList<GoodsChooseVo> goodsChooseVoList;
    /**
     * 数据处理类
     */
    private GoodsChoosePresenter presenter;
    /**
     * 当前选中的分类是否全选
     */
    private boolean isAllSelect;
    /**
     * 当前选中分类的位置
     */
    private int sortPosition;
    private ItemDao itemDao;
    /**
     * 是否全部全选
     */
    private boolean isAlmostChoose;
    /**
     * 操作添加还是编辑
     */
    private int operation;
    /**
     * 保存当前没有修改前的信息
     */
    private ArrayList<GoodsChooseVo> beforeGoodsChooseVoList;
    /**
     * 是否按了返回键
     */
    private boolean isBack;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_good_add_choose;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitleTextId(R.string.goods_choose);
        setSubTitleText("确定");
        setSubtitleClickListener(this);
        setBackClickListener(this);
        beforeGoodsChooseVoList = new ArrayList<>();
        itemDao = new ItemDao(this);
        tempList = new ArrayList<>();
        goodsChooseVoList = getIntent().getParcelableArrayListExtra("goodsChooseVoList");
        beforeGoodsChooseVoList.addAll(goodsChooseVoList);
        operation = getIntent().getIntExtra("source", Constants.REQUEST_GOODS_ADD);
        //商品
        rv_goods = pullRecyclerView.getRefreshableView();
        pullRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullRecyclerView.setOnRefreshListener(this);
        goodsDetailInfos = new ArrayList<>();
        detailAdapter = new GoodsAddChooseAdapter(goodsDetailInfos);
        rv_goods.setLayoutManager(new LinearLayoutManager(this));
        rv_goods.setAdapter(detailAdapter);
        detailAdapter.setOnCheckChangeListener(this);
        //分类
        goodsListInfoList = new ArrayList<>();
        rvCategoryList.setLayoutManager(new LinearLayoutManager(this));
        sortChooseAdapter = new GoodSortChooseAdapter(goodsListInfoList, this);
        rvCategoryList.setAdapter(sortChooseAdapter);
        sortChooseAdapter.setOnItemClickListener(this);
        presenter = new GoodsChoosePresenter(this, this);
        presenter.getGoodSortInfo();
        isAlmostChoose = getIntent().getBooleanExtra("isSelectAll", false);
        tvSelectAlMost.setText(!isAlmostChoose ? "全部选中" : "取消全选");
        Drawable drawableTop = isAlmostChoose ? ContextCompat.getDrawable(this, R.mipmap.ic_goods_checked) :
                ContextCompat.getDrawable(this, R.mipmap.ic_goods_unchecked);
        drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
        tvSelectAlMost.setCompoundDrawables(null, drawableTop, null, null);
    }


    @Override
    public void setGoodsInfoList(List<GoodsListInfo> goodsInfoList) {
        this.goodsListInfoList.clear();
        for (int i = 0; i < goodsInfoList.size() - 1; i++) {
            this.goodsListInfoList.add(goodsInfoList.get(i));
            goodsListInfoList.get(i).setCheckAll(false);
        }
        if (goodsChooseVoList.size() > 0) {
            Map<Integer, Boolean> selectList = initSortSelect();//编辑时还原当前的数据
            sortChooseAdapter.initData(selectList);
        } else {
            sortChooseAdapter.initData(false);//保存数据
        }
        if (goodsInfoList.size()>0){
            categoryId = goodsInfoList.get(0).getCategory_id();
            List<Item> itemList = itemDao.getListByCategoryId(categoryId);
            if (itemList.size() == 0) {
                page = 1;
                presenter.getGoodsDetailInfoList();
            } else {
                page = itemList.size() / size;
                //初始化
                this.goodsDetailInfos.clear();
                for (int i = 0; i < itemList.size(); i++) {
                    this.goodsDetailInfos.add(BeanChangeUtil.item2GoodsDetailInfo(itemList.get(i)));
                }
                detailAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 当选中列表不等空的时候初始化列表
     *
     * @return selectList
     */
    private Map<Integer, Boolean> initSortSelect() {
        Map<Integer, Boolean> selectList = new HashMap<>();
        for (int i = 0; i < goodsListInfoList.size(); i++) {
            GoodsListInfo goodsListInfo = goodsListInfoList.get(i);
            for (int j = 0; j < goodsChooseVoList.size(); j++) {
                if (goodsChooseVoList.get(j).getCategoryId() == goodsListInfo.getCategory_id()) {
                    selectList.put(i, true);
                    goodsListInfoList.get(i).setCheckAll(goodsChooseVoList.get(j).isSelect());
                    break;
                } else {
                    selectList.put(i, false);
                    goodsListInfoList.get(i).setCheckAll(false);
                }
            }
        }
        return selectList;
    }

    /**
     * 分类点击事件
     *
     * @param position 点击的位置
     */
    @Override
    public void onClick(View view, int position) {
        G.log("xxxxxxxxxxxxxxxx" + goodsListInfoList.get(position).getCategory_id());
        sortPosition = position;//赋值分类的位置
        sortChooseAdapter.setCurrentPosition(position);//设置当前选中分类的位置
        categoryId = goodsListInfoList.get(position).getCategory_id();//获取当前分类的id
        isAllSelect = goodsListInfoList.get(position).isCheckAll();
        tvSelectAll.setText(isAllSelect ? "全不选" : "全选");
        List<Item> itemList = itemDao.getListByCategoryId(categoryId);//查询当前分类是类目条数（已加载的条数）
        int itemSize = itemList.size();
        if (itemSize == 0) {//没有加载
            page = 1;
            presenter.getGoodsDetailInfoList();
        } else {
            int totalSize = goodsListInfoList.get(sortPosition).getCount();//获取当前分类总数
            int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;//当前分类的总页码数
            page = itemSize % size == 0 ? itemSize / size : itemSize / size + 1;//当前的页数
            if (totalPage == 1 && totalSize != 1 && page == 1) {
                presenter.getGoodsDetailInfoList();
            } else {
                G.log("xxxxxxxxxxxx--page:" + page);
                G.log("xxxxxxxxxxxx--totalPage:" + totalPage);
                pullRecyclerView.setMode(page >= totalPage ? PullToRefreshBase.Mode.DISABLED : PullToRefreshBase.Mode.PULL_FROM_END);//设置当前的下拉状态
                isAllSelect = goodsListInfoList.get(position).isCheckAll();
                this.goodsDetailInfos.clear();
                for (int i = 0; i < itemList.size(); i++) {
                    this.goodsDetailInfos.add(BeanChangeUtil.item2GoodsDetailInfo(itemList.get(i)));
                }
                detailAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setGoodsDetailInfoList(List<GoodsDetailInfo> goodsInfoList) {
        hasMoreData = goodsInfoList.size() == size;
        if (page == 1) {
            goodsDetailInfos.clear();
        }
        for (int i = 0; i < goodsInfoList.size(); i++) {//修改当前的数据的选中状态，如果是
            List<Item> itemList = itemDao.getListByGoodsId(goodsInfoList.get(i).getId(), categoryId);//查询当前分类下指定分类的
            if (itemList.size() == 1) {
                goodsInfoList.get(i).setCheck(itemList.get(0).isCheck() == 1);
            } else {
                goodsInfoList.get(i).setCheck(isAllSelect);
            }
        }
        goodsDetailInfos.addAll(goodsInfoList);
        if (isBack || operation == Constants.REQUEST_GOODS_EDIT) {
            saveEditGoods();//编辑时，还原当前的数据
        } else {
            saveGoods(goodsDetailInfos);//保存商品到本地
        }
        G.log("xxxxxxxxxxxxxxxxxx该分类下的" + categoryId + "的数据条数等于" + itemDao.getCountByCategoryId(categoryId));
        G.log("xxxxxxxxxxxxxxxxxx该分类下的" + categoryId + "当前为第" + page + "页此页有" + goodsInfoList.size() + "条数据");
        detailAdapter.notifyDataSetChanged();
    }

    /**
     * 保存新增时时商品的信息并且还原编辑商品列表
     */
    private void saveGoods(List<GoodsDetailInfo> goodsDetailInfos) {
        for (int i = 0; i < goodsDetailInfos.size(); i++) {
            if (!itemDao.isHasData(categoryId, goodsDetailInfos.get(i).getId())) {
                Item item = BeanChangeUtil.GoodsDetailInfo2item(goodsDetailInfos.get(i), categoryId);
                itemDao.save(item);
            } else {
                itemDao.updateCheckByInfo(categoryId, goodsDetailInfos.get(i).getId(), goodsDetailInfos.get(i).isCheck() ? 1 : 0);//修改当前保存的状态
            }
        }
    }

    /**
     * 保存编辑时商品的信息并且还原编辑商品列表
     */
    private void saveEditGoods() {
        for (int i = 0; i < goodsChooseVoList.size(); i++) {
            GoodsChooseVo goodsChooseVo = goodsChooseVoList.get(i);
            String goodsIds = goodsChooseVo.getGoodsIds();
            isAllSelect = goodsChooseVo.isSelect();
            if (categoryId == goodsChooseVo.getCategoryId()) {
                for (int j = 0; j < goodsDetailInfos.size(); j++) {
                    GoodsDetailInfo goodsDetailInfo = goodsDetailInfos.get(j);
                    if (!itemDao.isHasData(categoryId, goodsDetailInfo.getId())) {
                        if (goodsIds.contains(goodsDetailInfo.getId())) {
                            goodsDetailInfos.get(j).setCheck(!isAllSelect);
                        } else {
                            goodsDetailInfos.get(j).setCheck(isAllSelect);
                        }
                        Item item = BeanChangeUtil.GoodsDetailInfo2item(goodsDetailInfos.get(j), categoryId);
                        itemDao.save(item);
                    }
                }
                break;
            }
        }
    }

    /**
     * 是否包含当前的选中信息
     *
     * @param goodsChooseVoList 选中商品的信息
     * @return 选中的第几个位置
     */
    private int isInclude(List<GoodsChooseVo> goodsChooseVoList, int categoryId) {
        int position = -1;
        for (int i = 0; i < goodsChooseVoList.size(); i++) {
            if (categoryId == goodsChooseVoList.get(i).getCategoryId()) {
                position = i;
            }
        }
        return position;
    }

    /**
     * 是否包含当前的选中信息
     */
    private GoodsChooseVo getGoodsChooseVo(int categoryId) {
        GoodsChooseVo chooseVo = new GoodsChooseVo();
        for (int i = 0; i < goodsChooseVoList.size(); i++) {
            if (categoryId == goodsChooseVoList.get(i).getCategoryId()) {
                chooseVo = goodsChooseVoList.get(i);
            }
        }
        return chooseVo;
    }

    /**
     * 保存选择信息，默认点击分类是选中的状态
     */
    private void saveCheckData() {
        GoodsChooseVo goodsChooseVo = new GoodsChooseVo();
        goodsChooseVo.setCategoryId(categoryId);
        goodsChooseVo.setGoodsIds(detailAdapter.getGoodsIds(!isAllSelect));
        goodsChooseVo.setSelect(isAllSelect);
        int position = isInclude(goodsChooseVoList, categoryId);
        if (position == -1) {//不包含，第一次添加
            goodsChooseVoList.add(goodsChooseVo);
        } else {//包含修改当前的信息
            goodsChooseVoList.set(position, goodsChooseVo);
        }
    }

    @Override
    public void onCheck(int position, boolean isCheck) {
        goodsDetailInfos.get(position).setCheck(isCheck);//修改当前选择中的状态
        if (itemDao.getListByCategoryId(categoryId).size() == 0) {//如果不包含当前的信息，则添加，否则更新操作
            itemDao.save(BeanChangeUtil.GoodsDetailInfo2item(goodsDetailInfos.get(position), categoryId));
        } else {
            itemDao.updateCheckByInfo(categoryId, goodsDetailInfos.get(position).getId(), isCheck ? 1 : 0);//修改当前保存的状态
        }
        detailAdapter.notifyDataSetChanged();
        saveCheckData();//保存/更新数据
        if (isAllCheck(false)) {//如果全部没选中则更新左边菜单选项
            sortChooseAdapter.initData(sortPosition, false);
            goodsListInfoList.get(sortPosition).setCheckAll(false);
            goodsChooseVoList.remove(getGoodsChooseVo(categoryId));
        } else {
            sortChooseAdapter.initData(sortPosition, true);
        }
    }

    @OnClick({R.id.tv_select})
    public void operation(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                if (isAllSelect) {//如果全选则修改当前选择的状态
                    isAllSelect = false;
                    tvSelectAll.setText("全选");
                } else {
                    isAllSelect = true;
                    tvSelectAll.setText("全不选");
                }
                setAllCheck(isAllSelect);
                break;
        }
    }

    /**
     * 设置当前的商品详情是否全选/同时设置当前侧边分类菜单选择的状态
     * @param isCheck 是否全选
     */
    public void setAllCheck(boolean isCheck) {
        for (int i = 0; i < goodsDetailInfos.size(); i++) {
            goodsDetailInfos.get(i).setCheck(isCheck);
        }
        detailAdapter.notifyDataSetChanged();
        saveGoods(goodsDetailInfos);//保存数据
        if (!isCheck) {//非全选删除选中的数据
            goodsChooseVoList.remove(getGoodsChooseVo(categoryId));
        } else {//全选则更新选中的数据
            saveCheckData();
        }
        goodsListInfoList.get(sortPosition).setCheckAll(isCheck);
        sortChooseAdapter.initData(sortPosition, isCheck);
    }

    /**
     * 是否当前的商品列表是全选中的
     */
    public boolean isAllCheck(boolean check) {
        boolean isCheck = false;
        int count = 0;
        for (GoodsDetailInfo goodsDetailInfo : goodsDetailInfos) {
            if (goodsDetailInfo.isCheck() == check) {
                count++;
            }
        }
        if (count == goodsDetailInfos.size()) {
            isCheck = true;
        }
        return isCheck;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.getGoodsDetailInfoList();
        refreshView.onRefreshComplete();
        pullRecyclerView.setMode(!hasMoreData ? PullToRefreshBase.Mode.DISABLED : PullToRefreshBase.Mode.PULL_FROM_END);//设置当前的下拉状态
    }

    private String scanCode;
    private List<GoodsChooseVo> tempList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_STOCK_SEARCH) {
                Bundle bundle = data.getExtras();
                scanCode = bundle.getString(Constants.INTENT_EXTRA_KEY_QR_SCAN);
                search();
            } else if (requestCode == operation) {
                goodsChooseVoList = data.getParcelableArrayListExtra("goodsChooseVoList");
                List<GoodsChooseVo> changeGoodsChooseVoList = new ArrayList<>();
                for (int i = 0; i < tempList.size(); i++) {
                    for (int j = 0; j < goodsChooseVoList.size(); j++) {
                        if (goodsChooseVoList.get(j).getGoodsIds().equals(tempList.get(i).getGoodsIds())) {
                            changeGoodsChooseVoList.add(goodsChooseVoList.get(j));
                            goodsChooseVoList.remove(j);
                            break;
                        }
                    }
                }
                tempList = goodsChooseVoList;
                for (int i = 0; i < goodsChooseVoList.size(); i++) {
                    int count = itemDao.getCheckByCategoryId(goodsChooseVoList.get(i).getCategoryId());
                    for (int j = 0; j < goodsListInfoList.size(); j++) {
                        if (goodsListInfoList.get(j).getCategory_id() == goodsChooseVoList.get(i).getCategoryId()) {
                            if (count == 0 && isAlmostChoose && goodsListInfoList.get(j).getCount() == 1) {
                                goodsChooseVoList.remove(i);
                                break;
                            }
                        }
                    }
                }
                goodsChooseVoList.addAll(changeGoodsChooseVoList);
                if (goodsChooseVoList != null && goodsChooseVoList.size() > 0) {
                    Map<Integer, Boolean> selectList = initSortSelect();
                    sortChooseAdapter.initData(selectList);
                }
                List<Item> itemList = itemDao.getListByCategoryId(categoryId);//查询当前分类是类目条数（已加载的条数）
                this.goodsDetailInfos.clear();
                for (int i = 0; i < itemList.size(); i++) {
                    this.goodsDetailInfos.add(BeanChangeUtil.item2GoodsDetailInfo(itemList.get(i)));
                }
                detailAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick(R.id.tv_scan)
    public void scan() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_STOCK_SEARCH);
    }

    @OnClick(R.id.et_code_search)
    public void search() {
        Intent intent = new Intent(this, GoodsChooseSearchActivity.class);
        //保存改变钱的数据
        tempList = goodsChooseVoList;
        intent.putParcelableArrayListExtra("goodsChooseVoList", goodsChooseVoList);
        intent.putExtra("scanCode", scanCode);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("isSelectAll", isAlmostChoose);
        startActivityForResult(intent, operation);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent();
        switch (viewId) {
            case R.id.subtitle_text:
                isBack = false;
                intent.putParcelableArrayListExtra("goodsChooseVoList", goodsChooseVoList);
                intent.putExtra("isSelectAll", isAlmostChoose);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.title_layout:
                isBack = true;
                intent.putParcelableArrayListExtra("goodsChooseVoList", beforeGoodsChooseVoList);
                intent.putExtra("isSelectAll", isAlmostChoose);
                setResult(RESULT_OK, intent);
                for (int i = 0; i < goodsChooseVoList.size(); i++) {
                    GoodsChooseVo goodsChooseVo = goodsChooseVoList.get(i);
                    if (!beforeGoodsChooseVoList.contains(goodsChooseVo)) {
                        itemDao.deleteByCategoryId(goodsChooseVo.getCategoryId());
                    }
                }
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isBack = true;
            intent.putParcelableArrayListExtra("goodsChooseVoList", beforeGoodsChooseVoList);
            intent.putExtra("isSelectAll", isAlmostChoose);
            setResult(RESULT_OK, intent);
            for (int i = 0; i < goodsChooseVoList.size(); i++) {
                GoodsChooseVo goodsChooseVo = goodsChooseVoList.get(i);
                if (!beforeGoodsChooseVoList.contains(goodsChooseVo)) {
                    itemDao.deleteByCategoryId(goodsChooseVo.getCategoryId());
                }
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getUser_id() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public int getCategoryId() {
        return categoryId;
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
    public String getKeyWord() {
        return etCodeSearch.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    @OnClick(R.id.tv_select_all)
    public void selectAll() {
        //初始化全选按钮
        Drawable drawableTop;
        if (isAlmostChoose) {
            isAlmostChoose = false;
            tvSelectAlMost.setText("全部选中");
            drawableTop = ContextCompat.getDrawable(this, R.mipmap.ic_goods_unchecked);
        } else {
            isAlmostChoose = true;
            tvSelectAlMost.setText("取消全选");
            drawableTop = ContextCompat.getDrawable(this, R.mipmap.ic_goods_checked);
        }
        drawableTop.setBounds(0, 0, drawableTop.getIntrinsicWidth(), drawableTop.getIntrinsicHeight());
        tvSelectAlMost.setCompoundDrawables(null, drawableTop, null, null);
        setSelectAll(isAlmostChoose);
    }

    private void saveChooseGoods(int categoryId) {
        GoodsChooseVo goodsChooseVo = new GoodsChooseVo();
        goodsChooseVo.setCategoryId(categoryId);
        goodsChooseVo.setGoodsIds("");
        goodsChooseVo.setSelect(true);
        int position = isInclude(goodsChooseVoList, categoryId);
        if (position == -1) {//不包含，第一次添加
            goodsChooseVoList.add(goodsChooseVo);
        } else {//包含修改当前的信息
            goodsChooseVoList.set(position, goodsChooseVo);
        }
    }

    private void setSelectAll(boolean isAlmostChoose) {
        for (int i = 0; i < goodsListInfoList.size(); i++) {
            goodsListInfoList.get(i).setCheckAll(isAlmostChoose);
            int categoryId = goodsListInfoList.get(i).getCategory_id();
            List<Item> items = itemDao.getListByCategoryId(categoryId);
            for (Item item : items) {//更新全部信息
                itemDao.updateCheckByInfo(categoryId, item.getGoodsId(), isAlmostChoose ? 1 : 0);
            }
        }
        sortChooseAdapter.initData(isAlmostChoose);//修改分类的状态
        if (isAlmostChoose) {//保存全部分类
            for (int i = 0; i < goodsListInfoList.size(); i++) {
                saveChooseGoods(goodsListInfoList.get(i).getCategory_id());
            }
        } else {
            goodsChooseVoList.clear();//移除全部
        }
        for (int i = 0; i < goodsDetailInfos.size(); i++) {//更新当前的数据
            goodsDetailInfos.get(i).setCheck(isAlmostChoose);
        }
        detailAdapter.notifyDataSetChanged();//刷新当前的信息详情
    }
}


