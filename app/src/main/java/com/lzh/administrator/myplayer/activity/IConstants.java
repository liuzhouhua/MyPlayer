package com.lzh.administrator.myplayer.activity;

/**
 * Created by Administrator on 2016/7/3.
 */
public interface IConstants {
    // 播放状态
    public static final int MPS_NOFILE = -1; // 无音乐文件
    public static final int MPS_INVALID = 0; // 当前音乐文件无效
    public static final int MPS_PREPARE = 1; // 准备就绪
    public static final int MPS_PLAYING = 2; // 播放中
    public static final int MPS_PAUSE = 3; // 暂停

    // 播放模式
    public static final int MPM_LIST_LOOP_PLAY = 0; // 列表循环
    public static final int MPM_ORDER_PLAY = 1; // 顺序播放
    public static final int MPM_RANDOM_PLAY = 2; // 随机播放
    public static final int MPM_SINGLE_LOOP_PLAY = 3; // 单曲循环
}
