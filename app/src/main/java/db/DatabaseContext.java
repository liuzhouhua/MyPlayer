package db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/31.
 */
public class DatabaseContext extends ContextWrapper{

    public DatabaseContext(Context base) {
        super(base);
    }


    public File getDataBasePath(String name){
        //判断SD卡是否存在
        boolean sdExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        if(!sdExist) {
            return null;
        }else{
            String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"SmallBoallsDataBase";
            boolean isFileCreateSuccess = false;
            File dirFile = new File(dbPath);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            File dbFile = new File(dbPath + File.separator+"SmallBoallsDataBase.db");

            if(!dbFile.exists()){
                try{
                    isFileCreateSuccess = dbFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                isFileCreateSuccess = true;
            }

            if(isFileCreateSuccess){
                return dbFile;
            }else {
                return null;
            }
        }
    }


    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     * @param name
     * @param mode
     * @param factory
     * @return
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {

        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDataBasePath(name),null);
        return result;
    }


    /**
     * Android 4.0会调用此方法获取数据库
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @return
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDataBasePath(name),null);
        return result;
    }
}
