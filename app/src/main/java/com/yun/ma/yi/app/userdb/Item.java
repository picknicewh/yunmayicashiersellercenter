package  com.yun.ma.yi.app.userdb;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：商品信息详情类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
@DatabaseTable(tableName = "temp_data")
public class Item {
    /**
     * 商品id
     */
    public static final String ID = "id";
    /**
     * 商品id
     */
    public static final String GOODSID = "goodsId";
    /**
     * 用户id
     */
    public static final String USER_ID = "user_id";
    /**
     * 商品标题
     */
    public static final String TITLE = "title";
    /**
     * 商品分类id
     */
    public static final String CATEGORY_ID = "category_id";
    /**
     * 商品条形码
     */
    public static final String BAR_CODE = "bar_code";
    /**
     * 商品条形码
     */
    public static final String IS_CHECK = "isCheck";

    @DatabaseField(canBeNull = false, columnName = Item.ID, dataType = DataType.INTEGER,generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, columnName = Item.GOODSID, dataType = DataType.STRING)
    private String goodsId;

    @DatabaseField(canBeNull = false, columnName = Item.USER_ID, dataType = DataType.INTEGER)
    private int userId;

    @DatabaseField(canBeNull = false, columnName = Item.TITLE, dataType = DataType.STRING)
    private String title;

    @DatabaseField(canBeNull = false, columnName = Item.CATEGORY_ID, dataType = DataType.INTEGER)
    private int categoryId;

    @DatabaseField(canBeNull = false, columnName = Item.BAR_CODE, dataType = DataType.STRING)
    private String barCode;

    @DatabaseField(canBeNull = false, columnName = Item.IS_CHECK, dataType = DataType.INTEGER)
    private int isCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int isCheck() {
        return isCheck;
    }

    public void setCheck(int check) {
        isCheck = check;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
