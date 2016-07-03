package com.lzh.administrator.myplayer.control;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.lzh.administrator.myplayer.Song;
import com.lzh.administrator.myplayer.activity.IConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class MusicControl implements IConstants,MediaPlayer.OnCompletionListener{

    private MediaPlayer mMediaPlayer;
    private int mPlayMode;
    private int mPlayStatus;
    private List<Song> mSongs = new ArrayList<>();
    private Context mContext;
    private int mCurrentIndex = 0;

    public MusicControl(Context mContext) {
        this.mContext = mContext;

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnCompletionListener(this);
        mPlayMode = MPM_LIST_LOOP_PLAY;
        mPlayStatus = MPS_NOFILE;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (mPlayMode){
            case MPM_LIST_LOOP_PLAY:

                break;
            case MPM_ORDER_PLAY:
                break;
            case MPM_RANDOM_PLAY:
                break;
            case MPM_SINGLE_LOOP_PLAY:
                break;
        }
    }


    /**
     * 播放
     * @param pos
     * @return
     */
    public boolean play(int pos){
        if(mCurrentIndex==pos){
            if(!mMediaPlayer.isPlaying()){
                mMediaPlayer.start();
                mPlayStatus = MPS_PLAYING;
            }else{
                pause();
            }
            return true;
        }

        return  true;
    }


    /**
     * 暂停
     * @return
     */
    public boolean pause(){
        if(mPlayStatus!=MPS_PLAYING){
            return false;
        }
        mMediaPlayer.pause();
        mPlayStatus = MPS_PAUSE;
        return true;
    }
}
