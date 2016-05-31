package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态

    private static final String name = "SmallBoallsDataBase"; //数据库名称

    private static final int version = 1; //数据库版本

    private static DatabaseHelper instance = null;

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
        SQLiteDatabase dataBase = null;

        dataBase = getWritableDatabase();
        dataBase.beginTransaction();

        for(ContentValues values : contents){
            dataBase.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }

        dataBase.setTransactionSuccessful();
        dataBase.endTransaction();

    }


}
