package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ys on 2017/5/25.
 * 用户信息
 */

public class UserInfo implements Serializable {
    /**
     * parent_id : 100673
     * user_id : 1
     * username : 001
     * password : f3f050cad600fb0bf858811e7000ae88
     * salt : fnoAFIUVZ0
     * wx_merchant_id :
     * alipay_merchant_id :
     * unique_number : 0
     * auth_code :
     * rule : [{"id":2,"parent_id":0,"name":"收银机1","url":"cashier1","action":"add1","level":1,"node":"/0/","sort_order":0,"is_high_risk":0,"is_delete":0,"create_time":"2017-07-27 10:51:09","update_time":"2017-07-27 10:58:55"}]
     * shopName : ce
     * shopPhone : ce
     * shopAddress : fffffff
     * prov_id : 330000
     * city_id : 330100
     * area_id : 330104
     * mobile : 18358119525
     * prov_name : 浙江省
     * city_name : 杭州市
     * area_name : 江干区
     */
    private int parent_id;
    private int user_id;
    private String username;
    private String password;
    private String salt;
    private String wx_merchant_id;
    private String alipay_merchant_id;
    private int unique_number;
    private String auth_code;
    private int is_chain;
    private List<Rule> rule;
    private String shopName;
    private String shopPhone;
    private String shopAddress;
    private int prov_id;
    private int city_id;
    private int area_id;
    private String mobile;
    private String prov_name;
    private String city_name;
    private String area_name;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getProv_id() {
        return prov_id;
    }

    public void setProv_id(int prov_id) {
        this.prov_id = prov_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public static class Rule  {
        /**
         * id : 2
         * parent_id : 0
         * name : 收银机1
         * url : cashier1
         * action : add1
         * level : 1
         * node : /0/
         * sort_order : 0
         * is_high_risk : 0
         * is_delete : 0
         * create_time : 2017-07-27 10:51:09
         * update_time : 2017-07-27 10:58:55
         */

        private int id;
        private int parent_id;
        private String name;
        private String url;
        private String action;
        private int level;
        private String node;
        private int sort_order;
        private int is_high_risk;
        private int is_delete;
        private String create_time;
        private String update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

        public int getIs_high_risk() {
            return is_high_risk;
        }

        public void setIs_high_risk(int is_high_risk) {
            this.is_high_risk = is_high_risk;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getWx_merchant_id() {
        return wx_merchant_id;
    }

    public void setWx_merchant_id(String wx_merchant_id) {
        this.wx_merchant_id = wx_merchant_id;
    }

    public String getAlipay_merchant_id() {
        return alipay_merchant_id;
    }

    public void setAlipay_merchant_id(String alipay_merchant_id) {
        this.alipay_merchant_id = alipay_merchant_id;
    }

    public int getUnique_number() {
        return unique_number;
    }

    public void setUnique_number(int unique_number) {
        this.unique_number = unique_number;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public int getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(int is_chain) {
        this.is_chain = is_chain;
    }

    public List<Rule> getRule() {
        return rule;
    }

    public void setRule(List<Rule> rule) {
        this.rule = rule;
    }
}
