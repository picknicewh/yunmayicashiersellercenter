package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：订单信息类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class OrderInfo {


    /**
     * id : 782640
     * order_id : 1707201739072657
     * seller_order_id_list : a:1:{i:0;s:21:"170720173907235822586";}
     * buyer_id : 100673
     * buyer_number : 571999998
     * buyer_name : 糊涂斯基
     * buyer_shop_name : 买家测试
     * buyer_address : 测试
     * buyer_mobile : 18358119525
     * buyer_prov_id : 330000
     * buyer_city_id : 330100
     * buyer_area_id : 330104
     * buyer_prov_name : 浙江省
     * buyer_city_name : 杭州市
     * buyer_area_name : 江干区
     * seller_id_list : a:1:{i:0;i:106381;}
     * vendor_id : 0
     * origin_total_sell_price : 51858
     * pay_total_sell_price : 51858
     * real_pay_total_sell_price : 51858
     * origin_total_cost_price : 57800
     * pay_total_cost_price : 57800
     * real_pay_total_cost_price : 57800
     * discount_fee : 0
     * cat_discount_price : 0
     * business_logic_day : null
     * state : 1
     * is_confirm_pay : 0
     * type : 1
     * create_from : 0
     * pay_type : 1
     * pay_platform : cod
     * pay_platform_no :
     * is_use_coupon : 0
     * coupon_id : 0
     * coupon_result_id : 0
     * coupon_condition : 0
     * coupon_value : 0
     * is_mjj : 0
     * mjj_value : 0
     * is_meal : 0
     * meal_id : 0
     * mark : 江干2
     * create_time : 1500543546
     * apply_cancel_time : 0
     * agree_cancel_time : 0
     * disagree_cancel_time : 0
     * scan_time : 0
     * finish_time : 0
     * pay_time : null
     * create_datetime : 2017-07-20 17:39:06
     * apply_cancel_datetime : null
     * agree_cancel_datetime : null
     * disagree_cancel_datetime : null
     * scan_datetime : null
     * finish_datetime : null
     * apply_cancel_remark :
     * agree_cancel_remark :
     * disagree_cancel_remark :
     * buyer_remark :
     * agent_remark :
     * agree_cancel_checker_id : 0
     * disagree_cancel_checker_id : 0
     * agree_cancel_checker_name :
     * disagree_cancel_checker_name :
     * salesman_id : 100669
     * salesman_name : 孟玉涛
     * salesman_mobile : 13333333333
     * deliveryman_id : 0
     * deliveryman_name :
     * deliveryman_mobile :
     * scanner_id : 0
     * scanner_name :
     * scanner_mobile :
     * payee_id : 0
     * payee_name :
     * payee_mobile :
     * is_first_shopping : 0
     * agent_number : 57100000
     * customer_type : c_customer
     * icbc_o2o_pay_url :
     * picker_id : 0
     * picker_name :
     * picker_mobile :
     * order_num : 0
     * loading_id : 0
     * loading_name :
     * car_num :
     * deliver_type : platform_deliver
     */
    private int id;
    private String order_id;
    private String seller_order_id_list;
    private int buyer_id;
    private String buyer_number;
    private String buyer_name;
    private String buyer_shop_name;
    private String buyer_address;
    private String buyer_mobile;
    private int buyer_prov_id;
    private int buyer_city_id;
    private int buyer_area_id;
    private String buyer_prov_name;
    private String buyer_city_name;
    private String buyer_area_name;
    private String seller_id_list;
    private int vendor_id;
    private double origin_total_sell_price;
    private double pay_total_sell_price;
    private double real_pay_total_sell_price;
    private double origin_total_cost_price;
    private double pay_total_cost_price;
    private double real_pay_total_cost_price;
    private int discount_fee;
    private int cat_discount_price;
    private String business_logic_day;
    private int state;
    private int is_confirm_pay;
    private int type;
    private int create_from;
    private int pay_type;
    private String pay_platform;
    private String pay_platform_no;
    private int is_use_coupon;
    private int coupon_id;
    private int coupon_result_id;
    private int coupon_condition;
    private int coupon_value;
    private int is_mjj;
    private int mjj_value;
    private int is_meal;
    private int meal_id;
    private String mark;
    private int create_time;
    private int apply_cancel_time;
    private int agree_cancel_time;
    private int disagree_cancel_time;
    private int scan_time;
    private int finish_time;
    private String pay_time;
    private String create_datetime;
    private String apply_cancel_datetime;
    private String agree_cancel_datetime;
    private String disagree_cancel_datetime;
    private String scan_datetime;
    private String finish_datetime;
    private String apply_cancel_remark;
    private String agree_cancel_remark;
    private String disagree_cancel_remark;
    private String buyer_remark;
    private String agent_remark;
    private int agree_cancel_checker_id;
    private int disagree_cancel_checker_id;
    private String agree_cancel_checker_name;
    private String disagree_cancel_checker_name;
    private int salesman_id;
    private String salesman_name;
    private String salesman_mobile;
    private int deliveryman_id;
    private String deliveryman_name;
    private String deliveryman_mobile;
    private int scanner_id;
    private String scanner_name;
    private String scanner_mobile;
    private int payee_id;
    private String payee_name;
    private String payee_mobile;
    private int is_first_shopping;
    private String agent_number;
    private String customer_type;
    private String icbc_o2o_pay_url;
    private int picker_id;
    private String picker_name;
    private String picker_mobile;
    private int order_num;
    private int loading_id;
    private String loading_name;
    private String car_num;
    private String deliver_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSeller_order_id_list() {
        return seller_order_id_list;
    }

    public void setSeller_order_id_list(String seller_order_id_list) {
        this.seller_order_id_list = seller_order_id_list;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_number() {
        return buyer_number;
    }

    public void setBuyer_number(String buyer_number) {
        this.buyer_number = buyer_number;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_shop_name() {
        return buyer_shop_name;
    }

    public void setBuyer_shop_name(String buyer_shop_name) {
        this.buyer_shop_name = buyer_shop_name;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getBuyer_mobile() {
        return buyer_mobile;
    }

    public void setBuyer_mobile(String buyer_mobile) {
        this.buyer_mobile = buyer_mobile;
    }

    public int getBuyer_prov_id() {
        return buyer_prov_id;
    }

    public void setBuyer_prov_id(int buyer_prov_id) {
        this.buyer_prov_id = buyer_prov_id;
    }

    public int getBuyer_city_id() {
        return buyer_city_id;
    }

    public void setBuyer_city_id(int buyer_city_id) {
        this.buyer_city_id = buyer_city_id;
    }

    public int getBuyer_area_id() {
        return buyer_area_id;
    }

    public void setBuyer_area_id(int buyer_area_id) {
        this.buyer_area_id = buyer_area_id;
    }

    public String getBuyer_prov_name() {
        return buyer_prov_name;
    }

    public void setBuyer_prov_name(String buyer_prov_name) {
        this.buyer_prov_name = buyer_prov_name;
    }

    public String getBuyer_city_name() {
        return buyer_city_name;
    }

    public void setBuyer_city_name(String buyer_city_name) {
        this.buyer_city_name = buyer_city_name;
    }

    public String getBuyer_area_name() {
        return buyer_area_name;
    }

    public void setBuyer_area_name(String buyer_area_name) {
        this.buyer_area_name = buyer_area_name;
    }

    public String getSeller_id_list() {
        return seller_id_list;
    }

    public void setSeller_id_list(String seller_id_list) {
        this.seller_id_list = seller_id_list;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public double getOrigin_total_sell_price() {
        return origin_total_sell_price;
    }

    public void setOrigin_total_sell_price(double origin_total_sell_price) {
        this.origin_total_sell_price = origin_total_sell_price;
    }

    public double getPay_total_sell_price() {
        return pay_total_sell_price;
    }

    public void setPay_total_sell_price(double pay_total_sell_price) {
        this.pay_total_sell_price = pay_total_sell_price;
    }

    public double getReal_pay_total_sell_price() {
        return real_pay_total_sell_price;
    }

    public void setReal_pay_total_sell_price(double real_pay_total_sell_price) {
        this.real_pay_total_sell_price = real_pay_total_sell_price;
    }

    public double getOrigin_total_cost_price() {
        return origin_total_cost_price;
    }

    public void setOrigin_total_cost_price(double origin_total_cost_price) {
        this.origin_total_cost_price = origin_total_cost_price;
    }

    public double getPay_total_cost_price() {
        return pay_total_cost_price;
    }

    public void setPay_total_cost_price(double pay_total_cost_price) {
        this.pay_total_cost_price = pay_total_cost_price;
    }

    public double getReal_pay_total_cost_price() {
        return real_pay_total_cost_price;
    }

    public void setReal_pay_total_cost_price(double real_pay_total_cost_price) {
        this.real_pay_total_cost_price = real_pay_total_cost_price;
    }

    public int getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(int discount_fee) {
        this.discount_fee = discount_fee;
    }

    public int getCat_discount_price() {
        return cat_discount_price;
    }

    public void setCat_discount_price(int cat_discount_price) {
        this.cat_discount_price = cat_discount_price;
    }

    public String getBusiness_logic_day() {
        return business_logic_day;
    }

    public void setBusiness_logic_day(String business_logic_day) {
        this.business_logic_day = business_logic_day;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIs_confirm_pay() {
        return is_confirm_pay;
    }

    public void setIs_confirm_pay(int is_confirm_pay) {
        this.is_confirm_pay = is_confirm_pay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCreate_from() {
        return create_from;
    }

    public void setCreate_from(int create_from) {
        this.create_from = create_from;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_platform() {
        return pay_platform;
    }

    public void setPay_platform(String pay_platform) {
        this.pay_platform = pay_platform;
    }

    public String getPay_platform_no() {
        return pay_platform_no;
    }

    public void setPay_platform_no(String pay_platform_no) {
        this.pay_platform_no = pay_platform_no;
    }

    public int getIs_use_coupon() {
        return is_use_coupon;
    }

    public void setIs_use_coupon(int is_use_coupon) {
        this.is_use_coupon = is_use_coupon;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public int getCoupon_result_id() {
        return coupon_result_id;
    }

    public void setCoupon_result_id(int coupon_result_id) {
        this.coupon_result_id = coupon_result_id;
    }

    public int getCoupon_condition() {
        return coupon_condition;
    }

    public void setCoupon_condition(int coupon_condition) {
        this.coupon_condition = coupon_condition;
    }

    public int getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(int coupon_value) {
        this.coupon_value = coupon_value;
    }

    public int getIs_mjj() {
        return is_mjj;
    }

    public void setIs_mjj(int is_mjj) {
        this.is_mjj = is_mjj;
    }

    public int getMjj_value() {
        return mjj_value;
    }

    public void setMjj_value(int mjj_value) {
        this.mjj_value = mjj_value;
    }

    public int getIs_meal() {
        return is_meal;
    }

    public void setIs_meal(int is_meal) {
        this.is_meal = is_meal;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getApply_cancel_time() {
        return apply_cancel_time;
    }

    public void setApply_cancel_time(int apply_cancel_time) {
        this.apply_cancel_time = apply_cancel_time;
    }

    public int getAgree_cancel_time() {
        return agree_cancel_time;
    }

    public void setAgree_cancel_time(int agree_cancel_time) {
        this.agree_cancel_time = agree_cancel_time;
    }

    public int getDisagree_cancel_time() {
        return disagree_cancel_time;
    }

    public void setDisagree_cancel_time(int disagree_cancel_time) {
        this.disagree_cancel_time = disagree_cancel_time;
    }

    public int getScan_time() {
        return scan_time;
    }

    public void setScan_time(int scan_time) {
        this.scan_time = scan_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getCreate_datetime() {
        return create_datetime;
    }

    public void setCreate_datetime(String create_datetime) {
        this.create_datetime = create_datetime;
    }

    public String getApply_cancel_datetime() {
        return apply_cancel_datetime;
    }

    public void setApply_cancel_datetime(String apply_cancel_datetime) {
        this.apply_cancel_datetime = apply_cancel_datetime;
    }

    public String getAgree_cancel_datetime() {
        return agree_cancel_datetime;
    }

    public void setAgree_cancel_datetime(String agree_cancel_datetime) {
        this.agree_cancel_datetime = agree_cancel_datetime;
    }

    public String getDisagree_cancel_datetime() {
        return disagree_cancel_datetime;
    }

    public void setDisagree_cancel_datetime(String disagree_cancel_datetime) {
        this.disagree_cancel_datetime = disagree_cancel_datetime;
    }

    public String getScan_datetime() {
        return scan_datetime;
    }

    public void setScan_datetime(String scan_datetime) {
        this.scan_datetime = scan_datetime;
    }

    public String getFinish_datetime() {
        return finish_datetime;
    }

    public void setFinish_datetime(String finish_datetime) {
        this.finish_datetime = finish_datetime;
    }

    public String getApply_cancel_remark() {
        return apply_cancel_remark;
    }

    public void setApply_cancel_remark(String apply_cancel_remark) {
        this.apply_cancel_remark = apply_cancel_remark;
    }

    public String getAgree_cancel_remark() {
        return agree_cancel_remark;
    }

    public void setAgree_cancel_remark(String agree_cancel_remark) {
        this.agree_cancel_remark = agree_cancel_remark;
    }

    public String getDisagree_cancel_remark() {
        return disagree_cancel_remark;
    }

    public void setDisagree_cancel_remark(String disagree_cancel_remark) {
        this.disagree_cancel_remark = disagree_cancel_remark;
    }

    public String getBuyer_remark() {
        return buyer_remark;
    }

    public void setBuyer_remark(String buyer_remark) {
        this.buyer_remark = buyer_remark;
    }

    public String getAgent_remark() {
        return agent_remark;
    }

    public void setAgent_remark(String agent_remark) {
        this.agent_remark = agent_remark;
    }

    public int getAgree_cancel_checker_id() {
        return agree_cancel_checker_id;
    }

    public void setAgree_cancel_checker_id(int agree_cancel_checker_id) {
        this.agree_cancel_checker_id = agree_cancel_checker_id;
    }

    public int getDisagree_cancel_checker_id() {
        return disagree_cancel_checker_id;
    }

    public void setDisagree_cancel_checker_id(int disagree_cancel_checker_id) {
        this.disagree_cancel_checker_id = disagree_cancel_checker_id;
    }

    public String getAgree_cancel_checker_name() {
        return agree_cancel_checker_name;
    }

    public void setAgree_cancel_checker_name(String agree_cancel_checker_name) {
        this.agree_cancel_checker_name = agree_cancel_checker_name;
    }

    public String getDisagree_cancel_checker_name() {
        return disagree_cancel_checker_name;
    }

    public void setDisagree_cancel_checker_name(String disagree_cancel_checker_name) {
        this.disagree_cancel_checker_name = disagree_cancel_checker_name;
    }

    public int getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getSalesman_mobile() {
        return salesman_mobile;
    }

    public void setSalesman_mobile(String salesman_mobile) {
        this.salesman_mobile = salesman_mobile;
    }

    public int getDeliveryman_id() {
        return deliveryman_id;
    }

    public void setDeliveryman_id(int deliveryman_id) {
        this.deliveryman_id = deliveryman_id;
    }

    public String getDeliveryman_name() {
        return deliveryman_name;
    }

    public void setDeliveryman_name(String deliveryman_name) {
        this.deliveryman_name = deliveryman_name;
    }

    public String getDeliveryman_mobile() {
        return deliveryman_mobile;
    }

    public void setDeliveryman_mobile(String deliveryman_mobile) {
        this.deliveryman_mobile = deliveryman_mobile;
    }

    public int getScanner_id() {
        return scanner_id;
    }

    public void setScanner_id(int scanner_id) {
        this.scanner_id = scanner_id;
    }

    public String getScanner_name() {
        return scanner_name;
    }

    public void setScanner_name(String scanner_name) {
        this.scanner_name = scanner_name;
    }

    public String getScanner_mobile() {
        return scanner_mobile;
    }

    public void setScanner_mobile(String scanner_mobile) {
        this.scanner_mobile = scanner_mobile;
    }

    public int getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(int payee_id) {
        this.payee_id = payee_id;
    }

    public String getPayee_name() {
        return payee_name;
    }

    public void setPayee_name(String payee_name) {
        this.payee_name = payee_name;
    }

    public String getPayee_mobile() {
        return payee_mobile;
    }

    public void setPayee_mobile(String payee_mobile) {
        this.payee_mobile = payee_mobile;
    }

    public int getIs_first_shopping() {
        return is_first_shopping;
    }

    public void setIs_first_shopping(int is_first_shopping) {
        this.is_first_shopping = is_first_shopping;
    }

    public String getAgent_number() {
        return agent_number;
    }

    public void setAgent_number(String agent_number) {
        this.agent_number = agent_number;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getIcbc_o2o_pay_url() {
        return icbc_o2o_pay_url;
    }

    public void setIcbc_o2o_pay_url(String icbc_o2o_pay_url) {
        this.icbc_o2o_pay_url = icbc_o2o_pay_url;
    }

    public int getPicker_id() {
        return picker_id;
    }

    public void setPicker_id(int picker_id) {
        this.picker_id = picker_id;
    }

    public String getPicker_name() {
        return picker_name;
    }

    public void setPicker_name(String picker_name) {
        this.picker_name = picker_name;
    }

    public String getPicker_mobile() {
        return picker_mobile;
    }

    public void setPicker_mobile(String picker_mobile) {
        this.picker_mobile = picker_mobile;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getLoading_id() {
        return loading_id;
    }

    public void setLoading_id(int loading_id) {
        this.loading_id = loading_id;
    }

    public String getLoading_name() {
        return loading_name;
    }

    public void setLoading_name(String loading_name) {
        this.loading_name = loading_name;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getDeliver_type() {
        return deliver_type;
    }

    public void setDeliver_type(String deliver_type) {
        this.deliver_type = deliver_type;
    }
}
