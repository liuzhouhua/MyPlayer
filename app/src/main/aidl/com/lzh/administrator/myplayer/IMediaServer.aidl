// IMyAidlInterface.aidl
package com.lzh.administrator.myplayer;
import com.lzh.administrator.myplayer.Song;
// Declare any non-default types here with import statements

interface IMediaServer {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     boolean play(int pos);
     boolean playById(int id);
     boolean rePlay();
     boolean pause();
     boolean prev();
     boolean next();
     int duration();
     int position();
     boolean seekTo(int progress);
     void refreshMusicList(in List<Song> musicList);
     void getMusicList(out List<Song> musicList);
}
