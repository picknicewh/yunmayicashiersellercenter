package com.yun.ma.yi.app.module;

/**
 * Created by ys on 2017/6/13.
 */

public class Constants {
    /**
     * 微信公众平台的appid
     */
    public static final String WECHAR_APP_ID = "wx88e14098eedb2cbf";



    public static final String FUNCTION = "function";
    /**收款统计报表*/
    public static final String RECEIVABLES = "receivables";
    /**员工业绩报表*/
    public static final String EMPLOYEE = "employee";
    /**商品交易流水报表*/
    public static final String GOODS_TRADE = "goods_trade";
    /**商品分类销售报表*/
    public static final String GOODS_CATEGORY = "goods_category";
    /**商品销售报表*/
    public static final String GOODS_SALES = "goods_sales";
    /**商品收益报表*/
    public static final String GOODS_PROFIT = "goods_profit";
    /**商品采购报表*/
    public static final String GOODS_PURCHASE = "goods_purchase";
    /**库存结存报表*/
    public static final String STOCK_BALANCE = "stock_balance";
    /**库存盘点报表*/
    public static final String STOCK_INVENTORY = "stock_inventory";
    /**员工上下班报表*/
    public static final String STAFF_INOUTWORK = "staff_in_out_work";
    /**会员积分详情报表*/
    public static final String MEMBER_INTEGRAL_REPORT = "member_integral_report";
    /**营销-->满减时间*/
    public static final String MARKING_FULL_CUT = "marking_full_cut";
    /**营销-->满送时间*/
    public static final String MARKING_FULL_DELIVERY = "marking_full_delivery";
    /**营销-->满送特价商品*/
    public static final String MARKING_BARGAIB_GOODS = "marking_bargain_goods";

    /**商品分类*/
    public  static final String GOODS_SORT = "goods_sort";
    /**商品一级分类*/
    public  static final String GOODS_FIRST_SORT = "goods_first_sort";
    /**商品二级分类*/
    public  static final String GOODS_SECOND_SORT = "goods_second_sort";
    /**商品三级分类*/
    public  static final String GOODS_THIRD_SORT = "goods_third_sort";
    /**选择照片来源*/
    public static final int RESULT_IMAG =1;
    public static final int RESULT_CAMERA = 10;

    /**扫码成功返回的key**/
    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
    /**商品销售报表扫码*/
    public static  final  int REQUEST_REPORE_SALES = 0x01;
    /**商品-->商品列表搜索扫码*/
    public static  final  int REQUEST_GOOODS_SEARCH = 0x02;
    /**商品-->新增商品扫码*/
    public static  final  int REQUEST_GOOODS_ADD= 0x03;
    /**库存-->库存查询扫码*/
    public static  final  int REQUEST_STOCK_SEARCH= 0x04;
    /**库存-->库存盘点库存记录扫码*/
    public static  final  int REQUEST_STOCK_STATISTIC_RECORD =  0x05;
    /**入库--详情页请求码*/
    public static  final  int  REQUEST_IN_STOCK_DETAIL=  0x08;
    /**入库--扫描请求码*/
    public static  final  int  REQUEST_IN_STOCK=  0x06;
    /**出库--扫描请求码*/
    public static  final  int   REQUEST_OUT_STOCK=  0x07;

    /**入库--详情页返回扫码*/
    public static  final  int REQUEST_STOCK_ADD =  0x05;
    /**商品删除/编辑请求码*/
    public static  final  int REQUEST_GOOODS_EDIT= 0x07;
    /**商品编辑返回码*/
    public static  final  int RESULT_GOOODS_EDIT= 0x08;
    /**商品编辑返回码*/
    public static  final  int RESULT_GOOODS_DELETE= 0x09;
    /**出库--扫描请求码*/
    public static  final  int  RESULT_INSTOCK_DETAIL=  0x010;
    /**余额发生明细*/
    public static  final  int BALANCE_HAPPEND_DETAIL= 1;
    /**积分发生明细*/
    public static  final  int INTEGRAL_HAPPEND_DETAIL= 2;


    /**会员交易明细*/
    public static  final  int MEMBER_TRADE_DETAIL= 1;
    /**报表交易明细*/
    public static  final  int REPORT_TRADE_DETAIL= 2;

    /**新增会员卡*/
    public static  final  int ADD_MEMBER_CARD= 1;
    /**编辑会员卡*/
    public static  final  int EDIT_MEMBER_CARD= 2;

    /**新增充值设置*/
    public static  final  int ADD_RECHARGE_SETTING= 1;
    /**编辑充值设置*/
    public static  final  int EDIT_RECHARGE_SETTING= 2;

    /**新增积分兑换设置*/
    public static  final  int ADD_INTENGRAL_SETTING= 1;
    /**编辑积分兑换设置*/
    public static  final  int EDIT_INTENGRAL_SETTING= 2;

    /**会员卡充值*/
    public static  final  int CRRD_RECHARGE= 1;
    /**会员赠积分*/
    public static  final  int GRAMT_INTEGRAL= 2;
    /**修改密码*/
    public static  final  int MODIFY_PASSWORD= 3;
    /**挂失与激活*/
    public static  final  int REPORT_ACTIVITE= 4;
    /**会员换卡*/
    public static  final  int EXCHANGE_CARD =5;

    /**适配器type--会员明细*/
    public static  final  int TYPR_MEMBER_LIST= 1;
    /**适配器type--会员卡查询*/
    public static  final  int TYPE_MEMBER_SEARCH= 2;


    /**明细类型type--余额*/
    public static  final  String TYPR_MEMBER_BALANCE= "money";
    /**明细类型type--积分*/
    public static  final  String TYPE_MEMBER_INTEGRAL= "integral";

    /**新增角色*/
    public static  final  int TYPR_ROLE_ADD= 1;

    /**编辑角色*/
    public static  final  int TYPR_ROLE_EDIT= 2;


    public static  final  int REQUEST_CHOOSE= 2;


    /**营销-->新增商品*/
    public static  final  int REQUEST_GOODS_ADD= 0x01;
    /**营销-->编辑商品*/
    public static  final  int REQUEST_GOODS_EDIT= 0x02;

    /**营销-->新增特价商品*/
    public static  final  int REQUEST_ADD_BARGAIN= 0x01;
    /**营销-->新增编辑商品*/
    public static  final  int REQUEST_EDIT_BARGAIN= 0x02;

    public static final int RESULT_SUCCESS = 0x03;
    /**删除商品成功返回码*/
    public static final int RESULT_DELETE_SUCCESS = 0x05;

    /**营销-->满减商品选择*/
    public static  final  int DELIVERY_GOODS_CHOOSE= 0x01;
    /**营销-->满送商品选择*/
    public static  final  int CUT_GOODS_CHOOSE= 0x02;
    /**营销-->满送特价商品选择*/
    public static  final  int BARGAIN_GOODS_CHOOSE= 0x03;
    /**营销-->满送商品选择一个*/
    public static  final  int DELIVERY_ONE_GOODS_CHOOSE= 0x04;

    /**蚂蚁小店-->新增商品选择*/
    public static  final  int REQUEST_SHOP_GOODS_ADDD= 0x10;
}
