package com.lzh.administrator.myplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lzh.administrator.myplayer.IMediaServer;
import com.lzh.administrator.myplayer.Song;
import com.lzh.administrator.myplayer.control.MusicControl;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MediaService extends Service{

    private final IBinder mBinder = new ServerStub();

    private MusicControl mMusicControl;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class ServerStub extends IMediaServer.Stub{
        @Override
        public void refreshMusicList(List<Song> musicList) throws RemoteException {

        }

        @Override
        public int duration() throws RemoteException {
            return 0;
        }

        @Override
        public boolean playById(int id) throws RemoteException {
            return false;
        }

        @Override
        public boolean next() throws RemoteException {
            return false;
        }

        @Override
        public boolean rePlay() throws RemoteException {
            return false;
        }

        @Override
        public void getMusicList(List<Song> musicList) throws RemoteException {

        }

        @Override
        public boolean play(int pos) throws RemoteException {
            return false;
        }

        @Override
        public int position() throws RemoteException {
            return 0;
        }

        @Override
        public boolean pause() throws RemoteException {
            return false;
        }

        @Override
        public boolean seekTo(int progress) throws RemoteException {
            return false;
        }

        @Override
        public boolean prev() throws RemoteException {
            return false;
        }
    }
}
