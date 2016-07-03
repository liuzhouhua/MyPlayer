package com.lzh.administrator.myplayer.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lzh.administrator.myplayer.fragment.FriendsitemFragment;

/**
 * Created by Administrator on 2016/5/13.
 */
public class FriendsTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"动态","附近","好友"};

    public FriendsTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FriendsitemFragment mFragment = new FriendsitemFragment();
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
