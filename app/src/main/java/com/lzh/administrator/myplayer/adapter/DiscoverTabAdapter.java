package com.lzh.administrator.myplayer.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lzh.administrator.myplayer.fragment.DiscoveritemFragment;

/**
 * Created by Administrator on 2016/5/13.
 */
public class DiscoverTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"个性推荐","歌单","主播电台","排行榜"};

    public DiscoverTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DiscoveritemFragment mFragment = new DiscoveritemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("test",TITLES[position]);
        mFragment.setArguments(bundle);
        return mFragment;
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
