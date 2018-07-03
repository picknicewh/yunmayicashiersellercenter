package com.yun.ma.yi.app.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/11/27
 * 名称：申请提现列表
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ShopCashInfo {

    /**
     * total : 154
     * list : {"id":4,"user_id":105305,"shop_id":87,"audit_user_id":100669,"shop_name":"","owner_name":"","account_type":"zhifubao","account_name":"","account_no":"340482023@qq.com","amount":20000,"status":2,"remark":"测试","created_at":"2015-12-26 16:12:37","updated_at":"2015-12-31 14:30:09","formalities":"0.0","account":0,"user":{"id":105305,"username":"571010542","email":"","mobile":"18761908173","password":"96e79218965eb72c92a549dd5a330112","salt":"","avatar":"","is_email_bind":0,"is_mobile_bind":0,"status":99,"register_time":"2015-06-16 16:48:04","register_ip":0,"last_login_time":"2017-06-01 15:43:00","last_login_ip":2130706433,"login_count":798,"myd":0,"integral":0,"is_lock":0,"is_del":0}}
     hasNext：false
     */

    private int total;
    private List<ShopCashDetailInfo> list;
   private boolean hasNext;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ShopCashDetailInfo> getList() {
        return list;
    }

    public void setList(List<ShopCashDetailInfo> list) {
        this.list = list;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public static class ShopCashDetailInfo implements Serializable {
        /**
         * id : 4
         * user_id : 105305
         * shop_id : 87
         * audit_user_id : 100669
         * shop_name :
         * owner_name :
         * account_type : zhifubao
         * account_name :
         * account_no : 340482023@qq.com
         * amount : 20000
         * status : 2
         * remark : 测试
         * created_at : 2015-12-26 16:12:37
         * updated_at : 2015-12-31 14:30:09
         * formalities : 0.0
         * account : 0
         * user : {"id":105305,"username":"571010542","email":"","mobile":"18761908173","password":"96e79218965eb72c92a549dd5a330112","salt":"","avatar":"","is_email_bind":0,"is_mobile_bind":0,"status":99,"register_time":"2015-06-16 16:48:04","register_ip":0,"last_login_time":"2017-06-01 15:43:00","last_login_ip":2130706433,"login_count":798,"myd":0,"integral":0,"is_lock":0,"is_del":0}
         */

        private int id;
        private int user_id;
        private int shop_id;
        private int audit_user_id;
        private String shop_name;
        private String owner_name;
        private String account_type;
        private String account_name;
        private String account_no;
        private double amount;
        private int status;
        private String remark;
        private String created_at;
        private String updated_at;
        private double formalities;
        private double account;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getAudit_user_id() {
            return audit_user_id;
        }

        public void setAudit_user_id(int audit_user_id) {
            this.audit_user_id = audit_user_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public double getFormalities() {
            return formalities;
        }

        public void setFormalities(double formalities) {
            this.formalities = formalities;
        }

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable {
            /**
             * id : 105305
             * username : 571010542
             * email :
             * mobile : 18761908173
             * password : 96e79218965eb72c92a549dd5a330112
             * salt :
             * avatar :
             * is_email_bind : 0
             * is_mobile_bind : 0
             * status : 99
             * register_time : 2015-06-16 16:48:04
             * register_ip : 0
             * last_login_time : 2017-06-01 15:43:00
             * last_login_ip : 2130706433
             * login_count : 798
             * myd : 0
             * integral : 0
             * is_lock : 0
             * is_del : 0
             */

            private int id;
            private String username;
            private String email;
            private String mobile;
            private String password;
            private String salt;
            private String avatar;
            private int is_email_bind;
            private int is_mobile_bind;
            private int status;
            private String register_time;
            private String register_ip;
            private String last_login_time;
            private String last_login_ip;
            private int login_count;
            private int myd;
            private int integral;
            private int is_lock;
            private int is_del;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getIs_email_bind() {
                return is_email_bind;
            }

            public void setIs_email_bind(int is_email_bind) {
                this.is_email_bind = is_email_bind;
            }

            public int getIs_mobile_bind() {
                return is_mobile_bind;
            }

            public void setIs_mobile_bind(int is_mobile_bind) {
                this.is_mobile_bind = is_mobile_bind;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRegister_time() {
                return register_time;
            }

            public void setRegister_time(String register_time) {
                this.register_time = register_time;
            }

            public String getRegister_ip() {
                return register_ip;
            }

            public void setRegister_ip(String register_ip) {
                this.register_ip = register_ip;
            }

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public String getLast_login_ip() {
                return last_login_ip;
            }

            public void setLast_login_ip(String last_login_ip) {
                this.last_login_ip = last_login_ip;
            }

            public int getLogin_count() {
                return login_count;
            }

            public void setLogin_count(int login_count) {
                this.login_count = login_count;
            }

            public int getMyd() {
                return myd;
            }

            public void setMyd(int myd) {
                this.myd = myd;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public int getIs_lock() {
                return is_lock;
            }

            public void setIs_lock(int is_lock) {
                this.is_lock = is_lock;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }
        }
    }
}
