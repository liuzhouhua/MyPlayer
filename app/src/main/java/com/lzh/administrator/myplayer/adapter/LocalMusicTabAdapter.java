package com.lzh.administrator.myplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class LocalMusicTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"单曲","歌手","专辑","文件夹"};
    private List<Fragment> fragments;

    public LocalMusicTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public LocalMusicTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }
}
