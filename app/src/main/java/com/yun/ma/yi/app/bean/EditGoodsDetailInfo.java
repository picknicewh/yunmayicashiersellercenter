package com.yun.ma.yi.app.bean;

/**
 * 作者： wh
 * 时间：  2017/7/4
 * 名称：编辑商品信息
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class EditGoodsDetailInfo {
    /**
     * item : {"id":"10000014991584891498897","user_id":100673,"title":"五仁花生","category_id":100089,"category_ids":",100089,100091,100089,","category_name":"休闲食品","price":2500,"cost":1800,"image_url":"http://i1.yunmayi.com/upload/2017/07/04/94387c7172f264f00541cfc2a8504908.jpg","number":"123","bar_code":"123456789","stock":200,"stock_warning_high":1000,"stock_warning_low":20,"spec":"","unit":"包","total_sales":0,"shelf_life":20180909,"item_status":1,"is_weigh":0,"is_delete":0,"create_date":"2017-07-04 16:54:49","update_date":"2017-07-04 16:54:49","category_names":",休闲食品,坚果蜜饯,核果类,"}
     * info : {"aid":"100089","bid":"100091","cid":"100089","mid_category_list":[{"id":100090,"parent_cid":100089,"name":"饼干糕点"},{"id":100091,"parent_cid":100089,"name":"坚果蜜饯"},{"id":100092,"parent_cid":100089,"name":"休闲小吃"},{"id":100093,"parent_cid":100089,"name":"方便速食"},{"id":100094,"parent_cid":100089,"name":"冲调饮品"},{"id":100095,"parent_cid":100089,"name":"糖果甜品"},{"id":100097,"parent_cid":100089,"name":"进口食品"}],"leaf_category_list":[{"id":100255,"parent_cid":100091,"name":"花生瓜果"},{"id":100256,"parent_cid":100091,"name":"杏仁松果"},{"id":100257,"parent_cid":100091,"name":"话梅蜜饯"},{"id":100258,"parent_cid":100091,"name":"核果类"}]}
     */

  /*  private ItemBean item;
    private InfoBean info;

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class ItemBean {
        *//**
         * id : 10000014991584891498897
         * user_id : 100673
         * title : 五仁花生
         * category_id : 100089
         * category_ids : ,100089,100091,100089,
         * category_name : 休闲食品
         * price : 2500
         * cost : 1800
         * image_url : http://i1.yunmayi.com/upload/2017/07/04/94387c7172f264f00541cfc2a8504908.jpg
         * number : 123
         * bar_code : 123456789
         * stock : 200
         * stock_warning_high : 1000
         * stock_warning_low : 20
         * spec :
         * unit : 包
         * total_sales : 0
         * shelf_life : 20180909
         * item_status : 1
         * is_weigh : 0
         * is_delete : 0
         * create_date : 2017-07-04 16:54:49
         * update_date : 2017-07-04 16:54:49
         * category_names : ,休闲食品,坚果蜜饯,核果类,
         *//*

        private String id;
        private int user_id;
        private String title;
        private int category_id;
        private String category_ids;
        private String category_name;
        private int price;
        private int cost;
        private String image_url;
        private String number;
        private String bar_code;
        private int stock;
        private int stock_warning_high;
        private int stock_warning_low;
        private String spec;
        private String unit;
        private int total_sales;
        private int shelf_life;
        private int item_status;
        private int is_weigh;
        private int is_delete;
        private String create_date;
        private String update_date;
        private String category_names;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_ids() {
            return category_ids;
        }

        public void setCategory_ids(String category_ids) {
            this.category_ids = category_ids;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getStock_warning_high() {
            return stock_warning_high;
        }

        public void setStock_warning_high(int stock_warning_high) {
            this.stock_warning_high = stock_warning_high;
        }

        public int getStock_warning_low() {
            return stock_warning_low;
        }

        public void setStock_warning_low(int stock_warning_low) {
            this.stock_warning_low = stock_warning_low;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getTotal_sales() {
            return total_sales;
        }

        public void setTotal_sales(int total_sales) {
            this.total_sales = total_sales;
        }

        public int getShelf_life() {
            return shelf_life;
        }

        public void setShelf_life(int shelf_life) {
            this.shelf_life = shelf_life;
        }

        public int getItem_status() {
            return item_status;
        }

        public void setItem_status(int item_status) {
            this.item_status = item_status;
        }

        public int getIs_weigh() {
            return is_weigh;
        }

        public void setIs_weigh(int is_weigh) {
            this.is_weigh = is_weigh;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getCategory_names() {
            return category_names;
        }

        public void setCategory_names(String category_names) {
            this.category_names = category_names;
        }
    }

    public static class InfoBean {
        *//**
         * aid : 100089
         * bid : 100091
         * cid : 100089
         * mid_category_list : [{"id":100090,"parent_cid":100089,"name":"饼干糕点"},{"id":100091,"parent_cid":100089,"name":"坚果蜜饯"},{"id":100092,"parent_cid":100089,"name":"休闲小吃"},{"id":100093,"parent_cid":100089,"name":"方便速食"},{"id":100094,"parent_cid":100089,"name":"冲调饮品"},{"id":100095,"parent_cid":100089,"name":"糖果甜品"},{"id":100097,"parent_cid":100089,"name":"进口食品"}]
         * leaf_category_list : [{"id":100255,"parent_cid":100091,"name":"花生瓜果"},{"id":100256,"parent_cid":100091,"name":"杏仁松果"},{"id":100257,"parent_cid":100091,"name":"话梅蜜饯"},{"id":100258,"parent_cid":100091,"name":"核果类"}]
         *//*

        private String aid;
        private String bid;
        private String cid;
        private List<MidCategoryListBean> mid_category_list;
        private List<LeafCategoryListBean> leaf_category_list;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public List<MidCategoryListBean> getMid_category_list() {
            return mid_category_list;
        }

        public void setMid_category_list(List<MidCategoryListBean> mid_category_list) {
            this.mid_category_list = mid_category_list;
        }

        public List<LeafCategoryListBean> getLeaf_category_list() {
            return leaf_category_list;
        }

        public void setLeaf_category_list(List<LeafCategoryListBean> leaf_category_list) {
            this.leaf_category_list = leaf_category_list;
        }

        public static class MidCategoryListBean {
            *//**
             * id : 100090
             * parent_cid : 100089
             * name : 饼干糕点
             *//*

            private int id;
            private int parent_cid;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParent_cid() {
                return parent_cid;
            }

            public void setParent_cid(int parent_cid) {
                this.parent_cid = parent_cid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class LeafCategoryListBean {
            *//**
             * id : 100255
             * parent_cid : 100091
             * name : 花生瓜果
             *//*

            private int id;
            private int parent_cid;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParent_cid() {
                return parent_cid;
            }

            public void setParent_cid(int parent_cid) {
                this.parent_cid = parent_cid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }*/
    private EditItemInfo   info ;
    private GoodsDetailInfo  item;

    public EditItemInfo getInfo() {
        return info;
    }

    public void setInfo(EditItemInfo info) {
        this.info = info;
    }

    public GoodsDetailInfo getItem() {
        return item;
    }

    public void setItem(GoodsDetailInfo item) {
        this.item = item;
    }
}
