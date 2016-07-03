package com.lzh.administrator.myplayer;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/5/28.
 */
public class Song implements Parcelable {

    private static final String KEY_TITLE = "title";
    private static final String KEY_SINGER = "singer";
    private static final String KEY_ALBUM = "album";
    private static final String KEY_PATH = "path";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_FILESIZE = "filesize";
    private static final String KEY_LRCTITLE = "lrctitle";
    private static final String KEY_LRCPATH = "lrcpath";
    private static final String KEY_ALBUMIMGTITLE = "albumimgtitle";
    private static final String KEY_ALBUMIMGPATH = "albumimgpath";


    private String mTitile;//歌名
    private String mSinger;//歌手
    private String mAlbum;//专辑
    private String mPath;//路径
    private String mDuration;//时长
    private String mFileSize;//文件大小
    private String mLrcTitle;//歌词名称
    private String mLrcPath;//歌词路径
    private String mAlbumImgTitle;//专辑图片名称
    private String mAlbumImgPath;//专辑图片路径

    public String getmTitile() {
        return mTitile;
    }

    public void setmTitile(String mTitile) {
        this.mTitile = mTitile;
    }

    public String getmAlbum() {
        return mAlbum;
    }

    public void setmAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getmSinger() {
        return mSinger;
    }

    public void setmSinger(String mSinger) {
        this.mSinger = mSinger;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getmFileSize() {
        return mFileSize;
    }

    public void setmFileSize(String mFileSize) {
        this.mFileSize = mFileSize;
    }

    public String getmLrcPath() {
        return mLrcPath;
    }

    public void setmLrcPath(String mLrcPath) {
        this.mLrcPath = mLrcPath;
    }

    public String getmLrcTitle() {
        return mLrcTitle;
    }

    public void setmLrcTitle(String mLrcTitle) {
        this.mLrcTitle = mLrcTitle;
    }

    public String getmAlbumImgTitle() {
        return mAlbumImgTitle;
    }

    public void setmAlbumImgTitle(String mAlbumImgTitle) {
        this.mAlbumImgTitle = mAlbumImgTitle;
    }

    public String getmAlbumImgPath() {
        return mAlbumImgPath;
    }

    public void setmAlbumImgPath(String mAlbumImgPath) {
        this.mAlbumImgPath = mAlbumImgPath;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE,mTitile);
        bundle.putString(KEY_SINGER,mSinger);
        bundle.putString(KEY_ALBUM,mAlbum);
        bundle.putString(KEY_PATH,mPath);
        bundle.putString(KEY_DURATION,mDuration);
        bundle.putString(KEY_FILESIZE,mFileSize);
        bundle.putString(KEY_LRCPATH,mLrcPath);
        bundle.putString(KEY_LRCTITLE,mLrcTitle);
        bundle.putString(KEY_ALBUMIMGTITLE,mAlbumImgTitle);
        bundle.putString(KEY_ALBUMIMGPATH,mAlbumImgPath);
        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            Song song = new Song();
            Bundle bundle = new Bundle();
            bundle = source.readBundle();
            song.mTitile = bundle.getString(KEY_TITLE);
            song.mSinger = bundle.getString(KEY_SINGER);
            song.mAlbum = bundle.getString(KEY_ALBUM);
            song.mPath = bundle.getString(KEY_PATH);
            song.mDuration = bundle.getString(KEY_DURATION);
            song.mFileSize = bundle.getString(KEY_FILESIZE);
            song.mLrcPath = bundle.getString(KEY_LRCPATH);
            song.mLrcTitle = bundle.getString(KEY_LRCTITLE);
            song.mAlbumImgTitle = bundle.getString(KEY_ALBUMIMGTITLE);
            song.mAlbumImgPath = bundle.getString(KEY_ALBUMIMGPATH);

            return song;
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "com.lzh.administrator.myplayer.Song{" +
                "mTitile='" + mTitile + '\'' +
                ", mSinger='" + mSinger + '\'' +
                ", mAlbum='" + mAlbum + '\'' +
                ", mPath='" + mPath + '\'' +
                ", mDuration='" + mDuration + '\'' +
                ", mFileSize='" + mFileSize + '\'' +
                ", mLrcTitle='" + mLrcTitle + '\'' +
                ", mLrcPath='" + mLrcPath + '\'' +
                ", mAlbumImgTitle='" + mAlbumImgTitle + '\'' +
                ", mAlbumImgPath='" + mAlbumImgPath + '\'' +
                '}';
    }
}
