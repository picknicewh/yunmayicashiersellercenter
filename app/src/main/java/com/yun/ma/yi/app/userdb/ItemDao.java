package com.yun.ma.yi.app.userdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.yun.ma.yi.app.utils.G;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by smile on 2017/3/8.
 */
public class ItemDao extends BaseDao<Item, Integer> {

    public ItemDao(Context context) {
        super(context);
    }

    /**
     * 删除全部的数据
     */
     public void deleteAll(){
         SQLiteDatabase db = mHelper.getWritableDatabase();
         String sql  = "delete  from temp_data";
          db.execSQL(sql);
     }
    /**
     *根据分类id查询所有的本地商品数据
     * @param  category_id 分类id
     */
    public List<Item> getListByCategoryId(int category_id){
        try {
            QueryBuilder<Item, Integer> qb = mDao.queryBuilder();
            qb.where().eq(Item.CATEGORY_ID, category_id);
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据商品id和category_id获取商品信息
     * @param  goodsId 商品id
     *  @param  category_id 分类id
     */
    public List<Item> getListByGoodsId(String goodsId,int category_id){
        try {
            QueryBuilder<Item, Integer> qb = mDao.queryBuilder();
            qb.where().eq(Item.GOODSID, goodsId).and().eq(Item.CATEGORY_ID, category_id);
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取当前类目选中的的条数
     * @param  category_id 分类id
     */
    public int getCheckByCategoryId(int category_id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql  = "select *  from temp_data where category_id= "+category_id +" and isCheck =1 " ;
        Cursor cursor  = db.rawQuery(sql,null);
        return cursor.getCount();
    }
    /**
     * 获取已经选中的类目的条目数已选中的
     * @param goodsId 类目id
     */
    public boolean getIsCheckById(String  goodsId,int category_id){
        boolean isCheck =false;
        try {
            QueryBuilder<Item, Integer> qb = mDao.queryBuilder();
            qb.where().eq(Item.GOODSID, goodsId).and().eq(Item.CATEGORY_ID, category_id);
            List<Item> itemList = qb.query();
            if (itemList.size()>0){
                isCheck = itemList.get(0).isCheck()==1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCheck;
    }
    /**
     * 获取已经选中的类目的条目数
     * @param category_id 类目id
     */
    public int getCountByCategoryId(int category_id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql  = "select *  from temp_data where category_id= "+category_id  ;
        Cursor cursor  = db.rawQuery(sql,null);
        return cursor.getCount();
    }
    public void queryAll(){
        try {
            List<Item> itemList = mDao.queryForAll();
            for (Item item:itemList){
                G.log("--xxxxxxxxx"+item.getTitle()+"-----category_id="+item.getCategoryId()+"--------id="+item.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新数据库中选择的状态
     * @param category_id 类目id
     * @param  id 商品id
     * @param isCheck 是否选择
     */
    public int  updateCheckByInfo(int category_id,String id,int isCheck){
        int update = -1;
        try {
            UpdateBuilder<Item, Integer> ub = mDao.updateBuilder();
            ub.updateColumnValue(Item.IS_CHECK,isCheck)
                    .where().
                     eq(Item.CATEGORY_ID, category_id).
                    and().
                    eq(Item.GOODSID,id);
            update = ub.update();

            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }

    /**
     * 查询本地数据库中是否存改条件的商品
     * @param category_id 类目id
     * @param  id 商品id
     */
    public boolean  isHasData(int category_id,String id){
        boolean update =false;
        try {
            QueryBuilder<Item, Integer> qb = mDao.queryBuilder();
            qb .where().eq(Item.CATEGORY_ID, category_id).and().
                    eq(Item.GOODSID,id);
            update = qb.query().size()>0?true:false;
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
    /**
     * 删除本地数据中相应分类的数据
     * @param category_id 类目id
     */
    public int deleteByCategoryId(int category_id){
         int delete = -1;
        try {
            DeleteBuilder<Item,Integer> deleteBuilder = mDao.deleteBuilder();
            deleteBuilder.where(). eq(Item.CATEGORY_ID, category_id);
            delete = deleteBuilder.delete();
            return delete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delete;
    }
    /**
     * 删除本地数据中相应分类下的具体商品的数据
     * @param category_id 类目id
     *@param goodsId 商品id
     */
    public int deleteById(int category_id,String goodsId){
        int delete = -1;
        try {
            DeleteBuilder<Item,Integer> deleteBuilder = mDao.deleteBuilder();
            deleteBuilder.where(). eq(Item.CATEGORY_ID, category_id)
            .and().eq(Item.GOODSID,goodsId);
            delete = deleteBuilder.delete();
            return delete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delete;
    }
}
