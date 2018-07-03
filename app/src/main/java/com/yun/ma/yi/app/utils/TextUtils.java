package com.yun.ma.yi.app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本工具类
 * *Created by ys on 17/5/27.
 */
public class TextUtils {

    //判断是否为空
    public static boolean isEmpty(String txt) {
        //判断引用对象是否为空，内容是否为空，长度是否为0
        if ((txt == null) || (txt.equals(null)) || (txt.length() == 0))
            return true;
        else
            return false;
    }

    /**
     * 是否是手机号码
     * @param mobiles 手机号码
     */
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
     public static String getTradeType(int type){
         String typeText="";
         switch (type){
             case 1:
                 typeText = "充值余额";
                 break;
             case 2:
                 typeText = "消费余额";
                 break;
             case 3:
                 typeText = "积分新增";
                 break;
             case 4:
                 typeText = "积分兑换";
                 break;
         }
         return typeText;
     }
    public static String getPayWay(int type,String pay_platform){
        String payWay="";
       if (type==1&& pay_platform.equals("cod")){
           payWay="货到付款";
       } else if (type==2){
           if (pay_platform.equals("balance")) payWay="余额支付";
           else if (pay_platform.equals("abc")) payWay="农行支付";
       }else if (type==3 ){
           payWay ="工行支付";
       } else {
           payWay = "未知支付";
       }
        return payWay;
    }
    public static String getDiscountType(int type){
        String typeText="";
        switch (type) {
            case 0:
                typeText = "会员价";
                break;
            case 1:
                typeText = "折扣率";
                break;
        }
        return typeText;
    }
    public static String getOrderStatus(int status){
        String stateText="";
        switch (status){
            case 1:
                stateText = "未发货";
                break;
            case 2:
                stateText = "已发货";
                break;
            case 3:
                stateText = "已完成";
                break;
            case 4:
                stateText = "申请取消";
                break;
            case 5:
                stateText = "取消成功";
                break;
        }
        return stateText;
    }
    public static boolean isCodeBarEmpty(String code_bar){
        //判断引用对象是否为空，内容是否为空，长度是否为0
        if (G.isEmteny(code_bar) || code_bar.equals("无")){
            return true;
        }
        return false;
    }
    public static List<String> getReturnGoodsReason(){
        List<String> reasonList = new ArrayList<>();
        reasonList.add("日期不好");
        reasonList.add("发错货了");
        reasonList.add("口味／规格不对");
        reasonList.add("其他原因");
        return reasonList;
    }
    public static List<String> getReturnMoneyWay(){
        List<String> reasonList = new ArrayList<>();
        reasonList.add("现金支付");
        reasonList.add("现金支付");
        return reasonList;
    }
    public static List<String> getShopOrderStatus(){
        List<String>orderStatus = new ArrayList<>();
        orderStatus.add("等待接单");
        orderStatus.add("等待配送");
        orderStatus.add("等待确认收货");
        orderStatus.add("订单完成");
        orderStatus.add("订单关闭");
        return  orderStatus;
    }
    //订单状态(1等待付款 2等待接单 3等待发货 4等待确认收货 5交易成功 6交易关闭
    public static String getShopOrderByStatus(int status){
        String orderStatus = "";
        switch (status){
            case 1:orderStatus = "等待付款";break;
            case 2:orderStatus = "等待接单";break;
            case 3:orderStatus = "等待发货";break;
            case 4:orderStatus = "等待确认收货";break;
            case 5:orderStatus = "交易成功";break;
            case 6:orderStatus  = "交易关闭";break;
        }
        return orderStatus;
    }
    public static List<String> getOrderMoneyStatus(){
        List<String>orderStatus = new ArrayList<>();
        orderStatus.add("全部");
        orderStatus.add("等待审核");
        orderStatus.add("提现失败");
        orderStatus.add("提现成功");
        return  orderStatus;
    }

    public static String getOrderCashStatus(int status){
        String orderStatus =  "";
        switch (status){
            case 0:orderStatus = "全部";break;
            case 1:orderStatus = "等待审核";break;
            case 2:orderStatus = "提现失败";break;
            case 99:orderStatus = "提现成功";break;
        }
        return  orderStatus;
    }

    public static List<String> getOrderType(){
        List<String>orderStatus = new ArrayList<>();
        orderStatus.add("批发账号");
        orderStatus.add("银行账号");
        return  orderStatus;
    }
    /**
     * 获取营业时间列表
     * @return  营业时间
     */
    public static List<String> getTimeList(){
        List<String> timeList =new ArrayList<>();
        for (int i = 0 ;i<24;i++){
            if (i<10){
                timeList.add("0"+i+":"+"00");
                timeList.add("0"+i+":"+"30");
            }else {
                timeList.add(i+":"+"00");
                timeList.add(i+":"+"30");
            }
        }
        return  timeList;
    }
    /**
     * 获取字符串data对应列表中的位置
     * @param timeDataList 列表
     * @param data 字符串
     * @return  位置
     */
    public   static int getPosition(List<String> timeDataList,String data){
        for (int i = 0; i<timeDataList.size();i++){
            if (data.equals(timeDataList.get(i))){
                return i;
            }
        }
        return 0;
    }
}
