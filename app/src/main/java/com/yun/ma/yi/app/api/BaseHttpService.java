package com.yun.ma.yi.app.api;

import com.yun.ma.yi.app.bean.BalanceAccountInfo;
import com.yun.ma.yi.app.bean.BargainGoodsInfo;
import com.yun.ma.yi.app.bean.CategoryInfo;
import com.yun.ma.yi.app.bean.CityInfo;
import com.yun.ma.yi.app.bean.ConformInStockInfo;
import com.yun.ma.yi.app.bean.CountTrade;
import com.yun.ma.yi.app.bean.DistrictInfo;
import com.yun.ma.yi.app.bean.EditGoodsDetailInfo;
import com.yun.ma.yi.app.bean.EmployeeInfo;
import com.yun.ma.yi.app.bean.FullCutInfo;
import com.yun.ma.yi.app.bean.FullDeliveryInfo;
import com.yun.ma.yi.app.bean.GoodsDetailInfo;
import com.yun.ma.yi.app.bean.GoodsInfo;
import com.yun.ma.yi.app.bean.GoodsItemCodeInfo;
import com.yun.ma.yi.app.bean.GoodsListInfo;
import com.yun.ma.yi.app.bean.GoodsProfitInfo;
import com.yun.ma.yi.app.bean.GoodsSalesInfoBo;
import com.yun.ma.yi.app.bean.GoodsTradeDetailInfoBo;
import com.yun.ma.yi.app.bean.GoodsTradeInfoBo;
import com.yun.ma.yi.app.bean.InOutSearchInfo;
import com.yun.ma.yi.app.bean.InOutWorkInfoVos;
import com.yun.ma.yi.app.bean.MemberCardInfo;
import com.yun.ma.yi.app.bean.MemberInfo;
import com.yun.ma.yi.app.bean.MemberInfoChangeInfo;
import com.yun.ma.yi.app.bean.MemberIntegralInfo;
import com.yun.ma.yi.app.bean.MemberTotalAccountInfo;
import com.yun.ma.yi.app.bean.MemberTradeInfo;
import com.yun.ma.yi.app.bean.NoticeInfo;
import com.yun.ma.yi.app.bean.OrderInfo;
import com.yun.ma.yi.app.bean.OrderInfoDetail;
import com.yun.ma.yi.app.bean.ProvinceInfo;
import com.yun.ma.yi.app.bean.ReceivablesInfo;
import com.yun.ma.yi.app.bean.Result;
import com.yun.ma.yi.app.bean.RoleInfo;
import com.yun.ma.yi.app.bean.RoleRuleInfo;
import com.yun.ma.yi.app.bean.SecondCategoryInfo;
import com.yun.ma.yi.app.bean.Shop;
import com.yun.ma.yi.app.bean.ShopCashInfo;
import com.yun.ma.yi.app.bean.ShopCatInfo;
import com.yun.ma.yi.app.bean.ShopCategoryInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfo;
import com.yun.ma.yi.app.bean.ShopGoodsInfoDetail;
import com.yun.ma.yi.app.bean.ShopInfo;
import com.yun.ma.yi.app.bean.ShopOrderInfo;
import com.yun.ma.yi.app.bean.ShopSecondCategoryInfo;
import com.yun.ma.yi.app.bean.StcokWarningInfo;
import com.yun.ma.yi.app.bean.StockSearchInfo;
import com.yun.ma.yi.app.bean.StockStatisticInfo;
import com.yun.ma.yi.app.bean.StockStatisticItemInfo;
import com.yun.ma.yi.app.bean.SubUserInfo;
import com.yun.ma.yi.app.bean.UserInfo;
import com.yun.ma.yi.app.bean.Version;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ys on 17/5/27.
 * api
 */
public interface BaseHttpService {
    /**
     * 上传图片
     */
    @Multipart
    @POST("/common/uploader/image")
    Observable<Result> uploadFile(@Part MultipartBody.Part part, @Part("app_id") RequestBody app_id, @Part("timestamp") RequestBody timestamp, @Part("sign") RequestBody sign);


    /**版本更新*/
    @GET("/cashier/version/getAppStore")
    Observable<Result<Version>> getVersion(@QueryMap Map<String,Object> params);

    /**登录*/
    @GET("cashier/passport/login")
    Observable<Result<UserInfo>> login(@QueryMap Map<String,Object> params);

    /**今日数据*/
    @GET("cashier/countinfo/countTrade")
    Observable<Result<CountTrade>> getCountTrade(@QueryMap Map<String,Object> params);

    /**修改用户密码*/
    @GET("/cashier/user/updatePassword")
    Observable<Result<Result>> updatePassword(@QueryMap Map<String,Object> params);

    /**获取门店信息*/
    @GET("/cashier/user/getShopByUserId")
    Observable<Result<Shop>> getShopByUserId(@QueryMap Map<String,Object> params);

    /**获取库存预警商品*/
    @GET("/cashier/item/stockWarning")
    Observable<Result<List<StcokWarningInfo>>> getStockWarningData(@QueryMap Map<String,Object> params);

    /**公告，帮助*/
    @FormUrlEncoded
    @POST("/cashier/version/noticeList")
    Observable<Result<List<NoticeInfo>>> getNoticeList(@FieldMap Map<String,Object> params);

    /**收款统计报表*/
  //  @GET("cashier/countinfo/everyDay")
  //  Observable<Result<List<ReceivablesInfo>>> getReceivablesReportInfo(@QueryMap Map<String,Object> params);

    /**收款统计报表*/
    @FormUrlEncoded
    @POST("/cashier/countinfo/everyDay")
    Observable<Result<List<ReceivablesInfo>>> getReceivablesReportInfo(@FieldMap Map<String,Object> params);

    /**商品交易流水报表*/
    @GET("cashier/countinfo/tradeList")
    Observable<Result<GoodsTradeInfoBo>> getGoodsTradeReportInfo(@QueryMap Map<String,Object> params);

    /**连锁积分报表*/
    @FormUrlEncoded
    @POST("/cashier/countinfo/cardIntegralDetailList")
    Observable<Result<MemberIntegralInfo>> getIntegralReport(@FieldMap Map<String,Object> params);

    @GET("/cashier/countinfo/employeeReport")
    Observable<Result<EmployeeInfo>> getEmployeeReport(@QueryMap Map<String,Object> params);

    /**商品交易详情*/
    @GET("cashier/countinfo/tradeDetail")
    Observable<Result<GoodsTradeDetailInfoBo>> getGoodsTradeReportDetail(@QueryMap Map<String,Object> params);

    /**商品销售统计*/
    @GET("/cashier/countinfo/countProduct")
    Observable<Result<GoodsSalesInfoBo>> getGoodsSalesReportInfo(@QueryMap Map<String,Object> params);

    /**商品收益报表*/
    @GET("/cashier/countinfo/incomeStatement")
    Observable<Result<GoodsProfitInfo>> getGoodsProfitReportInfo(@QueryMap Map<String,Object> params);

    /**获取分类信息*/
    @FormUrlEncoded
    @POST("/cashier/item/getCategoryInfo")
    Observable<Result<List<CategoryInfo>>> getCategoryInfo(@FieldMap Map<String,Object> params);

    /**获取二三级分类*/
    @FormUrlEncoded
    @POST("/cashier/item/getSecondCategory")
    Observable<Result<List<SecondCategoryInfo>>> getSecondCategoryInfo(@FieldMap Map<String,Object> params);

     /**新增/编辑商品接口*/
    @FormUrlEncoded
    @POST("/cashier/item/saveItemByInfo")
    Observable<Result<String>>  saveItemByInfo(@FieldMap Map<String,Object> params);

    /**新增/编辑商品接口*/
    @FormUrlEncoded
    @POST("/cashier/item/delItem")
    Observable<Result<String>>  deleteItemByInfo(@FieldMap Map<String,Object> params);

    /**根据商品ID获取商品信息*/
    @FormUrlEncoded
    @POST("/cashier/item/getItemById")
    Observable<Result<EditGoodsDetailInfo>>  getItemById(@FieldMap Map<String,Object> params);

    /**搜索商品列表*/
    @FormUrlEncoded
    @POST("/cashier/item/searchItemsByKeyword")
    Observable<Result<GoodsInfo>> getGoodsItems(@FieldMap Map<String,Object> params);

    /**店铺商品类目*/
    @FormUrlEncoded
    @POST("/cashier/item/itemCategory")
    Observable<Result<List<GoodsListInfo>>> getItemCategory(@FieldMap Map<String,Object> params);

    /**店铺商品类目id获取商品类目信息*/
    @FormUrlEncoded
    @POST("/cashier/item//getCategoryInfoByIds")
    Observable<Result<List<GoodsListInfo>>> getCategoryInfoByIds(@FieldMap Map<String,Object> params);

    /**库存查询*/
    @FormUrlEncoded
    @POST("/cashier/item/findStock")
    Observable<Result<StockSearchInfo>> searchStock(@FieldMap Map<String,Object> params);

   /**新增盘点*/
    @FormUrlEncoded
    @POST("cashier/stock/insert")
    Observable<Result<String>> submitNewStock(@FieldMap Map<String,Object> params);

    /**库存盘点查询*/
    @FormUrlEncoded
    @POST("/cashier/stock/stockChange")
    Observable<Result<List<StockStatisticInfo>>>  statisticSearchStock(@FieldMap Map<String,Object> params);

    /**单个商品盘点记录查询*/
    @FormUrlEncoded
    @POST("/cashier/stock/stockChangeForItemId")
    Observable<Result<List<StockStatisticItemInfo>>>  stockChangeForItemId(@FieldMap Map<String,Object> params);

    /**库存详情息*/
    @FormUrlEncoded
    @POST("/cashier/stock/getById")
    Observable<Result<StockStatisticItemInfo>>  stockGetById(@FieldMap Map<String,Object> params);

    /**获取会员数据汇总*/
    @FormUrlEncoded
    @POST("/cashier/member/total")
    Observable<Result<MemberTotalAccountInfo>>  getTotalMember(@FieldMap Map<String,Object> params);

    /**会员查询*/
    @FormUrlEncoded
    @POST("/cashier/member/findCard")
    Observable<Result<MemberInfo>>  findCard(@FieldMap Map<String,Object> params);

    /**会员列表接口*/
    @FormUrlEncoded
    @POST("/cashier/member/memberList")
    Observable<Result<List<MemberInfo>>>  getMemberList(@FieldMap Map<String,Object> params);

    /**会员挂失，解挂*/
    @FormUrlEncoded
    @POST("/cashier/member/missingOrActivationCard")
    Observable<Result<String>>  memberReportLost(@FieldMap Map<String,Object> params);

    /**会员等级列表*/
    @FormUrlEncoded
    @POST("/cashier/member/memberGradeList")
    Observable<Result<List<MemberCardInfo>>>  memberGradeList(@FieldMap Map<String,Object> params);

    /**编辑会员信息*/
    @FormUrlEncoded
    @POST("/cashier/member/editCard")
    Observable<Result<String>>  editCard(@FieldMap Map<String,Object> params);

    /**新增会员等级*/
    @FormUrlEncoded
    @POST("/cashier/member/addMemberGrade")
    Observable<Result<String>>  addMemberGrade(@FieldMap Map<String,Object> params);

    /**编辑会员等级*/
    @FormUrlEncoded
    @POST("/cashier/member/editMemberGrade")
    Observable<Result<String>>  editMemberGrade(@FieldMap Map<String,Object> params);

    /**删除会员等级*/
    @FormUrlEncoded
    @POST("/cashier/member/deleteMemberGrade")
    Observable<Result<String>>  deleteMemberGrade(@FieldMap Map<String,Object> params);

    /**会员卡充值*/
    @FormUrlEncoded
    @POST("/cashier/member/memberCardRecharge")
    Observable<Result<String>>  memberCardRecharge(@FieldMap Map<String,Object> params);

    /**会员获取验证码*/
    @FormUrlEncoded
    @POST("/cashier/member/sendSms")
    Observable<Result<String>>  sendSms(@FieldMap Map<String,Object> params);

    /**发放会员卡*/
    @FormUrlEncoded
    @POST("/cashier/member/addCard")
    Observable<Result<String>>  addCard(@FieldMap Map<String,Object> params);

    /**余额，积分明细列表*/
    @FormUrlEncoded
    @POST("/cashier/member/moneyOrIntegralList")
    Observable<Result<List<MemberInfoChangeInfo>>>  getMoneyOrIntegralList(@FieldMap Map<String,Object> params);

    /**交易明细列表*/
    @FormUrlEncoded
    @POST("/cashier/member/tradeList")
    Observable<Result<List<MemberTradeInfo>>>  getTradeList(@FieldMap Map<String,Object> params);


    /**查询店铺员工列表*/
    @FormUrlEncoded
    @POST("/cashier/subuser/querySubUserList")
    Observable<Result<List<SubUserInfo>>>  getSubUserList(@FieldMap Map<String,Object> params);

    /**新增店铺员工列表*/
    @FormUrlEncoded
    @POST("/cashier/subuser/addSubUser")
    Observable<Result<String>>  addSubUser(@FieldMap Map<String,Object> params);

    /**编辑店铺员工列表*/
    @FormUrlEncoded
    @POST("/cashier/subuser/editSubUser")
    Observable<Result<String>>  editSubUser(@FieldMap Map<String,Object> params);

    /**删除店铺员工列表*/
    @FormUrlEncoded
    @POST("/cashier/subuser/delSubUser")
    Observable<Result<String>>  delSubUser(@FieldMap Map<String,Object> params);

    /**新增角色*/
    @FormUrlEncoded
    @POST("/cashier/subuser/addRole")
    Observable<Result<String>>  addRole(@FieldMap Map<String,Object> params);

    /**编辑角色*/
    @FormUrlEncoded
    @POST("cashier/subuser/editRole")
    Observable<Result<String>>  editRole(@FieldMap Map<String,Object> params);

    /**根据用户ID查找对应的角色*/
    @FormUrlEncoded
    @POST("/cashier/subuser/queryRoleByUserId")
    Observable<Result<List<RoleInfo>>>  getRoleByUserId(@FieldMap Map<String,Object> params);

    /**根据用户ID和角色ID获取该角色的权限*/
    @FormUrlEncoded
    @POST("/cashier/subuser/queryRuleByRole")
    Observable<Result<List<RoleRuleInfo>>>  getRuleByRole(@FieldMap Map<String,Object> params);

    /**根据用户ID该角色的权限列表*/
    @FormUrlEncoded
    @POST("/cashier/subuser/queryRule")
    Observable<Result<List<RoleRuleInfo>>>  getRuleList(@FieldMap Map<String,Object> params);

    /**子账号登录*/
    @FormUrlEncoded
    @POST("/cashier/subuser/childAccountLoginForManage")
    Observable<Result<UserInfo>>  childLogin(@FieldMap Map<String,Object> params);

    /**根据扫描的商品编码获取商品信息*/
    @FormUrlEncoded
    @POST("/cashier/item/findItemByCodeFromCashierOrYunmayi")
    Observable<Result<List<GoodsItemCodeInfo>>>  findAllGoods(@FieldMap Map<String,Object> params);


    /**根据扫描的商品编码获取商品信息*/
    @FormUrlEncoded
    @POST("/cashier/item/findItemByCodeFromCashierOrYunmayi")
    Observable<Result<List<GoodsDetailInfo>>>  findCodeGoods(@FieldMap Map<String,Object> params);

    /**收货入库 */
    @FormUrlEncoded
    @POST("/cashier/stock/stockOrder")
    Observable<Result<List<OrderInfo>>>  getStockOrder(@FieldMap Map<String,Object> params);


    /**收货入库明细 */
    @FormUrlEncoded
    @POST("/cashier/stock/stockDetailOrder")
    Observable<Result<List<OrderInfoDetail>>>  getStockOrderDetail(@FieldMap Map<String,Object> params);

    /**确认收货入库*/
    @FormUrlEncoded
    @POST("/cashier/stock/getBuyStockChangeStock")
    Observable<Result<ConformInStockInfo>>  confromInStock(@FieldMap Map<String,Object> params);

    /**选择商品确认收货入库 */
    @FormUrlEncoded
    @POST("cashier/stock/changeStockById")
    Observable<Result<ConformInStockInfo>>  changeStockById(@FieldMap Map<String,Object> params);

    /**批量确认收货入库*/
    @FormUrlEncoded
    @POST("cashier/stock/allConfirm")
    Observable<Result<ConformInStockInfo>>  conformAllInStock(@FieldMap Map<String,Object> params);

    /**退货出库*/
    @FormUrlEncoded
    @POST("/cashier/stock/returnGoodsOut")
    Observable<Result<List<OrderInfoDetail>>> conformOutStock(@FieldMap Map<String,Object> params);

    /** 出入库查询*/
    @FormUrlEncoded
    @POST("/cashier/stock/searchEnterOutStock")
    Observable<Result<InOutSearchInfo>>  searchEnterOutStock(@FieldMap Map<String,Object> params);

    /**确认退货出库.*/
    @FormUrlEncoded
    @POST("/cashier/stock/returnGoodsDetail")
    Observable<Result<ConformInStockInfo>>  conformOutStockDetail(@FieldMap Map<String,Object> params);

    /**确认退货出库.*/
    @FormUrlEncoded
    @POST("/cashier/stock/returnGoodsByItemId")
    Observable<Result<ConformInStockInfo>>  conformOutStockDetailById(@FieldMap Map<String,Object> params);

    /**营销整个模块接口*/
    @FormUrlEncoded
    @POST("/cashier/activity/exec")
    Observable<Result> marketing(@FieldMap Map<String,Object> params);


    /**员工上下班打卡记录*/
    @FormUrlEncoded
    @POST("cashier/subuser/changeShiftList")
    Observable<Result<InOutWorkInfoVos>> getInOutWorkList(@FieldMap Map<String,Object> params);

    /**查询满就减活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/queryMjj")
    Observable<Result<List<FullCutInfo>>> getFullCutList(@FieldMap Map<String,Object> params);
    /**新增满就减活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/addMjj")
    Observable<Result<String>> addFullCutInfo(@FieldMap Map<String,Object> params);

    /**删除满就减活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/deleteMjj")
    Observable<Result<String>> delFullCutInfo(@FieldMap Map<String,Object> params);

    /**编辑满就减活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/editMjj")
    Observable<Result<String>> editFullCutInfo(@FieldMap Map<String,Object> params);

    /**获取满就减活动记录数*/
    @FormUrlEncoded
    @POST("/cashier/activity/countMjj")
    Observable<Result> getFullCutInfoCount(@FieldMap Map<String,Object> params);


    /**删除满就送活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/deleteMjs")
    Observable<Result<String>> delFullDeliveryInfo(@FieldMap Map<String,Object> params);

    /**编辑满就送活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/editMjs")
    Observable<Result<String>> editFullDeliveryInfo(@FieldMap Map<String,Object> params);

    /**查询满就送活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/queryMjs")
    Observable<Result<List<FullDeliveryInfo>>> getFullDeliveryList(@FieldMap Map<String,Object> params);

    /**新增满就送活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/addMjs")
    Observable<Result<String>> addFullDeliveryInfo(@FieldMap Map<String,Object> params);

    /**获取满就送活动记录数*/
    @FormUrlEncoded
    @POST("/cashier/activity/countMjs")
    Observable<Result<String>> getFullDeliveryInfoCount(@FieldMap Map<String,Object> params);


    /**删除特价商品活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/deleteSpecialProduct")
    Observable<Result<String>> delBargainGoodsInfo(@FieldMap Map<String,Object> params);

    /**编辑特价商品活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/editSpecialProduct")
    Observable<Result<String>> editBargainGoodsInfo(@FieldMap Map<String,Object> params);

    /**查询特价商品活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/querySpecialProduct")
    Observable<Result<List<BargainGoodsInfo>>> getBargainGoodsInfoList(@FieldMap Map<String,Object> params);

    /**新增特价商品活动*/
    @FormUrlEncoded
    @POST("/cashier/activity/addSpecialProduct")
    Observable<Result<String>> addBargainGoodsInfo(@FieldMap Map<String,Object> params);

    /**获取特价商品活动记录数*/
    @FormUrlEncoded
    @POST("/cashier/activity/countSpecialProduct")
    Observable<Result<String>> getBargainGoodsInfoCount(@FieldMap Map<String,Object> params);

    /**根据用户IDs和ID列表获取商品信息*/
    @FormUrlEncoded
    @POST("/cashier/item/getItemsByUserIdAndIds")
    Observable<Result<List<GoodsDetailInfo>>> getGoodsItemsByIds(@FieldMap Map<String,Object> params);

    /**删除选择的商品ID*/
    @FormUrlEncoded
    @POST("/cashier/activity/deleteAssignProductId")
    Observable<Result<String>> deleteMarkingGoodsById(@FieldMap Map<String,Object> params);

    /**根据活动ID 类型 关键字查询商品信息*/
    @FormUrlEncoded
    @POST("/cashier/item/searchActivityProductInfo")
    Observable<Result<List<GoodsDetailInfo>>> getMarkingGoodsInfo(@FieldMap Map<String,Object> params);

    /**
     * 分页获取活动的商品列表
     */
    @FormUrlEncoded
    @POST("/cashier/item/getItemInfoByActivityId")
    Observable<Result<GoodsInfo>> getItemInfoByActivityId(@FieldMap Map<String,Object> params);
    /*--------------------------------------------蚂蚁小店------------------------------------------------------*/
    /**
     *获取店铺Token
     */
    @GET("/common/passport/login")
    Observable<Result<String>> getToken(@QueryMap Map<String,Object> params);

    /**
     * 获取省接口
     */
    @GET("/common/area/provinces")
    Observable<Result<List<ProvinceInfo>>> getProvinces(@QueryMap Map<String,Object> params);
    /**
     * 获取市区接口
     */
    @GET("/common/area/cities")
    Observable<Result<List<CityInfo>>> getCities(@QueryMap Map<String,Object> params);
    /**
     * 获取市区接口
     */
    @GET("/common/area/districts")
    Observable<Result<List<DistrictInfo>>> getDistricts(@QueryMap Map<String,Object> params);
    /**
     * 编辑店铺信息
     */
    @GET("Mini/shop/editShopInfoByUserId")
    Observable<Result<String>> updateShopInfo(@QueryMap Map<String,Object> params);
    /**
     * 获取店铺信息
     */
    @GET("Mini/shop/getShopByUserId")
    Observable<Result<ShopInfo>> getShopInfoByUserId(@QueryMap Map<String,Object> params);
    /**
     * 获取订单列表
     */
    @GET("Mini/trade/getSellerOrderBySellerId")
    Observable<Result<List<ShopOrderInfo>>> getSellerOrderBySellerId(@QueryMap Map<String,Object> params);

    /**
     * 提现管理
     */
    @GET("/Mini/shop/drawBlanceList")
    Observable<Result<ShopCashInfo>> drawBlanceList(@QueryMap Map<String,Object> params);

    /**
     * 新增提现
     */
    @GET("/Mini/shop/withdrawBalance")
    Observable<Result<String>> withdrawBalance(@QueryMap Map<String,Object> params);
    /**
     * 编辑店铺订单
     */
    @GET("/Mini/trade/editOrderState")
    Observable<Result<String>> editOrderState(@QueryMap Map<String,Object> params);

    /**
     * 获取蚂蚁小店商品管理商品分类
     */
    @FormUrlEncoded
    @POST("/Mini/item/shopCatList")
    Observable<Result<List<ShopCatInfo>>> getShopCatList(@FieldMap Map<String,Object> params);

    /**
     ** 根据分类id获取蚂蚁小店商品管理商品列表
     */
    @GET("/Mini/item/list")
    Observable<Result<ShopGoodsInfo>> getShopGoodInfoByCatId(@QueryMap Map<String,Object> params);

    /**
     **编辑蚂蚁小店商品
     */
    @GET("/Mini/item/sellerUpdateItemForSellerApp")
    Observable<Result<String>> editShopGoodInfo(@QueryMap Map<String,Object> params);

    /**
     **删除蚂蚁小店商品
     */
    @GET("/Mini/item/sellerDelProductByPid")
    Observable<Result<String>> deletShopGoodById(@QueryMap Map<String,Object> params);

    /**
     * 根据商品ID获取商品信息
     */
    @GET("/Mini/item/getById")
    Observable<Result<ShopGoodsInfoDetail>> getShopInfoById(@QueryMap Map<String,Object> params);

    /**
     * 获取蚂蚁小店一级分类列表
     */
    @GET("/Mini/category/list")
    Observable<Result<List<ShopCategoryInfo>>> getShopCateList(@QueryMap Map<String,Object> params);

    /**
     * 获取蚂蚁小店二/三级分类列表
     */
    @GET("/Mini/category/getSecondCategory")
    Observable<Result<List<ShopSecondCategoryInfo>>> getShopSecondCateList(@QueryMap Map<String,Object> params);

    /**
     * 收银机在售非称重商品
     */
    @FormUrlEncoded
    @POST("/Mini/item/cashierItemListPutWeighAndOnSale")
    Observable<Result<List<GoodsDetailInfo>>> getCashierItemList(@FieldMap Map<String,Object> params);

    /**
     *  收银机商品新增到蚂蚁小店
     */
    @FormUrlEncoded
    @POST("/Mini/item/cashierItemAddToMiniShop")
    Observable<Result> addCashierItem(@FieldMap Map<String,Object> params);

    /**
     *  一键上传商品
     */
    @FormUrlEncoded
    @POST("/Mini/item/onKeyUploadListForPage")
    Observable<Result<String>> oneKeyUploadShopGoods(@FieldMap Map<String,Object> params);

    /**
     * 一键上传商品记录数
     */
    @FormUrlEncoded
    @POST("/Mini/item/onKeyUploadCount")
    Observable<Result<String>> onKeyUploadCount(@FieldMap Map<String,Object> params);


    /**
     *  获取小店余额明细
     */
    @GET("Mini/shop/balanceDetailList")
    Observable<Result<BalanceAccountInfo>> getBalanceDetailList(@QueryMap Map<String,Object> params);
}
