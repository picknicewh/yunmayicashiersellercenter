package com.yun.ma.yi.app.module.Home;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.yun.ma.yi.app.api.ConfigConstants;
import com.yun.ma.yi.app.application.YunmayiApplication;
import com.yun.ma.yi.app.base.BaseSimpleActivity;
import com.yun.ma.yi.app.bean.CountTrade;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.Shop;
import com.yun.ma.yi.app.module.Login.LoginActivity;
import com.yun.ma.yi.app.module.common.MoreInfoAdapter;
import com.yun.ma.yi.app.module.common.MoreInfoItem;
import com.yun.ma.yi.app.module.common.OnItemClickListener;
import com.yun.ma.yi.app.module.common.view.CashDetailDialog;
import com.yun.ma.yi.app.module.goods.GoodsManagerActivity;
import com.yun.ma.yi.app.module.inoutstock.InOutStockManagerActivity;
import com.yun.ma.yi.app.module.marketing.MarketingManagerActivity;
import com.yun.ma.yi.app.module.member.MemberManagerActivity;
import com.yun.ma.yi.app.module.report.ReportManagerActivity;
import com.yun.ma.yi.app.module.setting.AboutInfoActivity;
import com.yun.ma.yi.app.module.setting.MessageCenterActivity;
import com.yun.ma.yi.app.module.setting.NoticeCenterActivity;
import com.yun.ma.yi.app.module.setting.ShopInfoActivity;
import com.yun.ma.yi.app.module.setting.UpdatePasswordActivity;
import com.yun.ma.yi.app.module.shop.ShopManagerActivity;
import com.yun.ma.yi.app.module.staff.StaffManagerActivity;
import com.yun.ma.yi.app.module.stock.StockManagerActivity;
import com.yun.ma.yi.app.userdb.UserMessage;
import com.yun.ma.yi.app.userdb.UserRuleInfoDb;
import com.yun.ma.yi.app.userdb.UserRuleInfoDbHelper;
import com.yun.ma.yi.app.utils.DateUtil;
import com.yun.ma.yi.app.utils.G;
import com.yun.ma.yi.app.utils.PriceTransfer;
import com.yun.ma.yi.app.utils.StringUtils;
import com.yun.ma.yi.app.utils.SystemTime;
import com.yun.ma.yi.app.utils.ToastUtils;
import com.yunmayi.app.manage.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity2 extends BaseSimpleActivity implements HomeContract.IHomeView, TimePickerView.OnTimeSelectListener, View.OnClickListener, OnItemClickListener {
    @BindView(R.id.summary)
    ImageView summary;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    /**
     * 用户名
     */
    @BindView(R.id.login_username)
    TextView loginUsername;
    /**
     * 当前时间
     */
    @BindView(R.id.week_day)
    TextView weekDay;
    /**
     * 今日收益
     */
    @BindView(R.id.toady_profit)
    TextView toadyProfit;
    /**
     * 现金收益
     */
    @BindView(R.id.cash_profit)
    TextView cashProfit;
    /**
     * 支付宝收益
     */
    @BindView(R.id.alipay_profit)
    TextView alipayProfit;
    /**
     * 微信收益
     */
    @BindView(R.id.wechat_profit)
    TextView wechatProfit;
    /**
     * 现金比数
     */
    @BindView(R.id.cash_number)
    TextView cashNumber;
    /**
     * 支付宝笔数
     */
    @BindView(R.id.alipay_number)
    TextView alipayNumber;
    /**
     * 微信笔数
     */
    @BindView(R.id.wechat_number)
    TextView wechatNumber;
    /**
     * 销售现金种类数
     */
    @BindView(R.id.sold_goods_number)
    TextView soldGoodsNumber;
    /**
     * 销售总金额
     */
    @BindView(R.id.sold_cost_total)
    TextView soldCostTotal;
    /**
     * 今日毛利
     */
    @BindView(R.id.gross_profit)
    TextView grossProfit;
    /**
     * 刷新
     */
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * 导航栏
     */
    @BindView(R.id.nav_view_right)
    NavigationView navViewRight;
    /**
     * 侧滑抽屉
     */
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    /**
     * 导航栏
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_cash_income)
    LinearLayout llCashIncome;
    /**
     * 网络数据处理类
     */
    private HomeContract.IHomePresenter homePersenter;
    /**
     * 时间选择
     */
    private TimePickerView timePickerView;
    /**
     * 时间选择
     */
    private Date mDate;
    /**
     * 记录选中开始日期
     */
    private Calendar mCalendar = Calendar.getInstance();
    /**
     * 现金交易详情对话框
     */
    private CashDetailDialog detailDialog;
    /**
     * 用户信息
     */
    private UserMessage userinfo;
    /**
     * 记录第一次点击退出键的时间
     */
    private long exitTime;
    /**
     * 权限管理数据库
     */
    private SQLiteDatabase database;
    /**
     * 侧滑中用户名
     */
    private TextView userInfoName;
    /**
     * 侧滑中商品名称
     */
    private TextView shopName;
    /**
     * 侧滑菜单项
     */
    private RecyclerView navListRight;
    /**
     * 推出登陆
     */
    private Button out;
    /**
     * 侧滑菜单适配器
     */
    private MoreInfoAdapter navRightAdapter;
    /**
     * 策划菜单数据列表
     */
    private ArrayList<MoreInfoItem> navRightItems;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_home2;
    }

    @Override
    protected void initView() {
        mDate = new Date(System.currentTimeMillis());
        timePickerView = DateUtil.getDatePickerView("选择日期", this, mCalendar, this);
        detailDialog = new CashDetailDialog(this);
        database = UserRuleInfoDb.getInstance().getWritableDatabase();
        userinfo = UserMessage.getInstance();
        loginUsername.setText(userinfo == null ? "" : userinfo.getUsername());
        weekDay.setText(SystemTime.getDateTime());
        homePersenter = new HomePersenter(this, this);
        homePersenter.subscribe();
        //获取用门店信息
        homePersenter.getShopByUserId();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePersenter.subscribe();
            }
        });
        intNav();
    }
    private void initNavList(){
        navRightItems = new ArrayList<>();
        if (userinfo.getParent_id() == 0) {
            navRightItems.add(new MoreInfoItem(R.mipmap.setting_pas, getString(R.string.up_pas)));
        }
        navRightItems.add(new MoreInfoItem(R.mipmap.shop_nav, getString(R.string.shop_info)));
        navRightItems.add(new MoreInfoItem(R.mipmap.help_video_nav, getString(R.string.help)));
        navRightItems.add(new MoreInfoItem(R.mipmap.message_center, getString(R.string.message_center)));
        navRightItems.add(new MoreInfoItem(R.mipmap.about_nav, getString(R.string.about)));
    }
   private void intNav(){
       initNavList();
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
       mDrawerLayout.addDrawerListener(toggle);
       toggle.syncState();
       View headerViewRight = navViewRight.getHeaderView(0);
       userInfoName = headerViewRight.findViewById(R.id.user_info_name);
       userInfoName.setText(UserMessage.getInstance().getUsername());
       shopName =headerViewRight.findViewById(R.id.shop_name);
       out =  headerViewRight.findViewById(R.id.out);
       out.setOnClickListener(this);
       navListRight = headerViewRight.findViewById(R.id.nav_list_right);
       //2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列
       navListRight.setLayoutManager( new LinearLayoutManager(this));
       navListRight.setHasFixedSize(true);
       navRightAdapter = new MoreInfoAdapter(this, navRightItems);
       //3 设置数据适配器
       navListRight.setAdapter(navRightAdapter);
       navRightAdapter.notifyDataSetChanged();
       navRightAdapter.setClickListener(this);
   }
    @OnClick(R.id.goods)
    public void goods() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_GOODS_MANAGER))
            startActivity(new Intent(this, GoodsManagerActivity.class));
        else G.showToast(this, "你没有商品管理权限哦！");
    }

    @OnClick(R.id.report)
    public void report() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_REPORT_MANAGER))
            startActivity(new Intent(this, ReportManagerActivity.class));
        else G.showToast(this, "你没有报表管理权限哦！");
    }

    @OnClick(R.id.account)
    public void account() {
        ToastUtils.makeText(this, "开发中......");
    }

    @OnClick(R.id.member)
    public void member() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_CARD_MANNGER))
            startActivity(new Intent(this, MemberManagerActivity.class));
        else G.showToast(this, "你没有会员管理权限哦！");

    }

    @OnClick(R.id.marketing)
    public void marketing() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_MARKETING))
            startActivity(new Intent(this, MarketingManagerActivity.class));
        else G.showToast(this, "你没有营销管理权限哦！");

    }


    @OnClick(R.id.logistics)
    public void logistics() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_INOUTSTOCK_MANAGER))
            startActivity(new Intent(this, InOutStockManagerActivity.class));
        else G.showToast(this, "你没有出入库管理权限哦！");


    }

    @OnClick(R.id.stock)
    public void stock() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_STOCK_MANAGER))
            startActivity(new Intent(this, StockManagerActivity.class));
        else G.showToast(this, "你没有库存管理权限哦！");

    }

    @OnClick(R.id.employee)
    public void employee() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_STAFF_MANAGER))
            startActivity(new Intent(this, StaffManagerActivity.class));
        else G.showToast(this, "你没有员工管理权限哦！");

    }

    @OnClick(R.id.mayi_shop)
    public void mayiShop() {
        if (YunmayiApplication.isCanOperation(ConfigConstants.ACTION_MAYI_SHOP_MANAGER))
            startActivity(new Intent(this, ShopManagerActivity.class));
        else G.showToast(this, "你没有蚂蚁小店管理权限哦！");

    }

    @OnClick(R.id.micro_shop)
    public void micro_shop() {
        ToastUtils.makeText(this, "开发中......");
    }

    @OnClick(R.id.comment)
    public void comment() {
        ToastUtils.makeText(this, "开发中......");
    }


    @Override
    public int getUid() {
        return UserMessage.getInstance().getUId();
    }

    @Override
    public String getTime() {
        return SystemTime.getDate(mDate);
    }

    private CountTrade countTrade;

    @Override
    public void getCountTrade(CountTrade countTrade) {
        swipeRefreshLayout.setRefreshing(false);
        if (countTrade != null) {
            this.countTrade = countTrade;
            toadyProfit.setText(PriceTransfer.chageMoneyToString(countTrade.getTodayIncome()));
            cashProfit.setText(PriceTransfer.chageMoneyToString(countTrade.getCashIncome() + countTrade.getAliCashIncome() + countTrade.getWxCashIncome()));
            alipayProfit.setText(PriceTransfer.chageMoneyToString(countTrade.getAliIncome()));
            wechatProfit.setText(PriceTransfer.chageMoneyToString(countTrade.getWxIncome()));
            cashNumber.setText(String.valueOf(countTrade.getCashCount()));
            alipayNumber.setText(String.valueOf(countTrade.getAliCount()));
            wechatNumber.setText(String.valueOf(countTrade.getWxCount()));
            soldGoodsNumber.setText(String.valueOf(countTrade.getItemCount()));
            soldCostTotal.setText(PriceTransfer.chageMoneyToString(countTrade.getTotalCost()));
            grossProfit.setText(PriceTransfer.chageMoneyToString(countTrade.getProfit()));
        }
    }

    @Override
    public void getShopByUserId(Shop shop) {
             shopName.setText(shop.getCompany());
        YunmayiApplication.setShop(shop);
    }

    @Override
    public void getCategoryList(List<GoodsListInfo> infos) {
        GoodsListInfo categoryInfo = infos.get(infos.size() - 1);
        infos.add(0, categoryInfo);
        infos.remove(infos.size() - 1);
        YunmayiApplication.setCategoryInfos(infos);
    }

    @OnClick(R.id.ll_cash_income)
    public void cashIncome() {
        int y = G.dp2px(this, 130);
        detailDialog.showAtLocation(llCashIncome, Gravity.RIGHT, 0, -y);
        detailDialog.setTradeInfo(countTrade);
    }

    @OnClick(R.id.week_day)
    public void weekDay() {
        timePickerView.show();
    }

    @Override
    public void onTimeSelect(Date date, View v) {
        mCalendar.setTime(date);
        mDate = date;
        weekDay.setText(SystemTime.getDateTimeByDate(date));
        homePersenter.countTrade();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePersenter != null) {
            homePersenter.unSubscribe();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 退出
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序！",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            YunmayiApplication.exitApp();
            UserRuleInfoDbHelper.delete(database);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.out) {
            //退出
            startActivity(new Intent(this, LoginActivity.class));
            //清空数据库
            UserRuleInfoDbHelper.delete(database);
            finish();

        }
    }

    @Override
    public void onClick(View view, int position) {
        MoreInfoItem infoItem = navRightItems.get(position);
        if (infoItem != null) {
            Intent intent = new Intent();
            if (StringUtils.isEquals(infoItem.getText(), getString(R.string.up_pas))) {
                // 修改密码
                intent.setClass(HomeActivity2.this, UpdatePasswordActivity.class);
                startActivity(intent);
            } else if (StringUtils.isEquals(infoItem.getText(), getString(R.string.shop_info))) {
                // 店铺信息
                intent.setClass(HomeActivity2.this, ShopInfoActivity.class);
                startActivity(intent);
            } else if (StringUtils.isEquals(infoItem.getText(), getString(R.string.help))) {
                // 帮助中心
                intent.setClass(HomeActivity2.this, NoticeCenterActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            } else if (StringUtils.isEquals(infoItem.getText(), getString(R.string.message_center))) {
                // 消息中心
                intent.setClass(HomeActivity2.this, MessageCenterActivity.class);
                startActivity(intent);
            } else if (StringUtils.isEquals(infoItem.getText(), getString(R.string.about))) {
                // 关于系统
                intent.setClass(HomeActivity2.this, AboutInfoActivity.class);
                startActivity(intent);
            }
        }
    }
}