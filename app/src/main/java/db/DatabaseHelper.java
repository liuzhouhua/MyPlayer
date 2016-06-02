package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态

    private static final String name = "SmallBoallsDataBase"; //数据库名称

    private static final int version = 1; //数据库版本

    private static DatabaseHelper instance;
    private SQLiteDatabase mDb;

    public static String TABLE_LOCAL_MUSIC = "localmusic";

    private static String CREATE_LOCAL_MUSIC = "CREATE TABLE localmusic " +
            "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", title TEXT" +
            ", name TEXT" +
            ", artist TEXT" +
            ", album TEXT" +
            ", path TEXT UNIQUE NOT NULL" +
            ", duration LONG" +
            ", file_size LONG" +
            ", lrc_title TEXT" +
            ", lrc_path TEXT" +
            ", album_img_title TEXT" +
            ", album_img_path TEXT" +
            ");";

    public synchronized static DatabaseHelper getInstance(Context context) {
        if(instance==null){
            instance = new DatabaseHelper(context);
            instance.mDb = instance.getWritableDatabase();
        }
        return instance;
    }


    public DatabaseHelper(Context context){
        this(context,name,null,version);
    }

    public DatabaseHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOCAL_MUSIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){

        }
    }

    /**
     * 批量插入数据
     * @param table
     * @param contents
     */
    public void insert(String table, List<ContentValues> contents){
        if(contents == null){
            return;
        }

        mDb.beginTransaction();

        for(ContentValues values : contents){
            mDb.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
        contents.clear();
    }


    /**
     * 查询data，以列表形式返回
     * @param rowMapper
     * @param sql
     * @param selectionArgs
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(RowMapper<T> rowMapper, String sql,
                                    String[] selectionArgs){
        List<T> all;
        Cursor cursor;

        mDb.beginTransaction();

        cursor = mDb.rawQuery(sql,selectionArgs);
        all = new ArrayList<>();
        T t = null;
        while(cursor.moveToNext()){
            t = rowMapper.mapRow(cursor,cursor.getPosition());
            if(t!=null){
                all.add(t);
            }
        }

        mDb.setTransactionSuccessful();
        mDb.endTransaction();

        cursor.close();
        return all;
    }


    public interface RowMapper<T> {
        public T mapRow(Cursor cursor, int index);
    }

}
