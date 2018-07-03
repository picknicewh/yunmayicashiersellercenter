package com.yun.ma.yi.app.userdb;

import com.yun.ma.yi.app.bean.UserInfo;

/**
 * ================================================
 * 作    者：wh
 * 时    间：2017/8/4
 * 描    述：
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
public class UserAction {
    /**
     * 保存用户信息
     * @param info 用户信息
     */
    public static void saveUserInfo(UserInfo info,String loginName,String password) {
        UserMessage userMessage = UserMessage.getInstance();
        userMessage.setParent_id(info.getParent_id());
        userMessage.setUser_id(info.getUser_id());
        userMessage.setUsername(loginName);
        userMessage.setPassword(password);
        userMessage.setSalt(info.getSalt());
        userMessage.setWx_merchant_id(info.getWx_merchant_id());
        userMessage.setAlipay_merchant_id(info.getAlipay_merchant_id());
        userMessage.setUnique_number(info.getUnique_number());
        userMessage.setAuth_code(info.getAuth_code());
        userMessage.setUId(info.getParent_id()==0?info.getUser_id():info.getParent_id());
        userMessage.setIsChain(info.getIs_chain());
        userMessage.setAreaId(info.getArea_id());
        userMessage.setAreaName(info.getArea_name());
        userMessage.setCityId(info.getCity_id());
        userMessage.setCityName(info.getCity_name());
        userMessage.setProvId(info.getProv_id());
        userMessage.setProvName(info.getProv_name());
        userMessage.setMobile(info.getMobile());
        userMessage.setShopAddress(info.getShopAddress());
        userMessage.setShopName(info.getShopName());


    }
    /**
     * 保存用户登录名和密码
     * @param loginName
     * @param passWord
     */
    public static void saveLoginMessage(String loginName, String passWord) {
        UserMessage um = UserMessage.getInstance();
        um.setUsername(loginName);
        um.setPassword(passWord);
    }
}
