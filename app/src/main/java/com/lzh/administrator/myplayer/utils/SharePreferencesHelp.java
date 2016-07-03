package com.lzh.administrator.myplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/6/2.
 */
public class SharePreferencesHelp {

    private static SharePreferencesHelp instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharePreferencesHelp() {

    }

    public static SharePreferencesHelp getInstance(Context context){
        if(instance==null){
            instance = new SharePreferencesHelp();
            instance.sp = context.getSharedPreferences("MyMusic",context.MODE_PRIVATE);
            instance.editor = instance.sp.edit();
        }
        return instance;
    }

    public void setTagForScanMusic(boolean isScan){
        editor.putBoolean("isScan",isScan);
        editor.commit();
    }

    public boolean getTagForScanMusic(){
        return sp.getBoolean("isScan",false);
    }

    public void setLocalMusicCount(int count){
        editor.putInt("localMusicCount",count);
        editor.commit();
    }

    public int getLocalMusicCount(){
        return sp.getInt("localMusicCount",0);
    }
}
