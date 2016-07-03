package com.lzh.administrator.myplayer.manager;

import android.util.Log;

import java.util.List;

import com.lzh.administrator.myplayer.Song;

/**
 * Created by Administrator on 2016/6/2.
 */
public class SongManager{

    private static SongManager instance;

    private List<Song> mSongs;

    public static SongManager getInstance(){
        if(instance==null){
            instance = new SongManager();
        }
        return instance;
    }

    public List<Song> getmSongs() {
        return mSongs;
    }

    public void setmSongs(List<Song> mSongs) {
        this.mSongs = mSongs;
    }

    @Override
    public String toString() {
        if(mSongs==null){
            return null;
        }
        for(Song song : mSongs){
            Log.d("xxxx","song :"+song.toString());
        }
        return "SongManager{" +
                "mSongs=" + mSongs +
                '}';
    }
}
