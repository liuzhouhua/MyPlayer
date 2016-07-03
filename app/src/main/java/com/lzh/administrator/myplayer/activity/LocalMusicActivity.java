package com.lzh.administrator.myplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.lzh.administrator.myplayer.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import com.lzh.administrator.myplayer.adapter.LocalMusicTabAdapter;
import com.lzh.administrator.myplayer.fragment.LocalMusicitemFragment;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LocalMusicActivity extends BaseActivity {

    private TabPageIndicator mTabPagerIndicator;
    private ViewPager mVpViewPager;
    private FragmentPagerAdapter mAdapter;
    private LinearLayout mLocalMusicActionbarLayout;
    private LinearLayout mActionBarBack;
    private LinearLayout mSearch;
    private LinearLayout mMore;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_music_layout);
        initView();
        initListener();
    }

    private void initListener() {
        mActionBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        this.mVpViewPager = (ViewPager) findViewById(R.id.vp_viewpager_main);
        this.mTabPagerIndicator = (TabPageIndicator) findViewById(R.id.ti_tanpagerindicator_main);
        this.mLocalMusicActionbarLayout = (LinearLayout)findViewById(R.id.local_music_actionbar);
        this.mActionBarBack = (LinearLayout)mLocalMusicActionbarLayout.findViewById(R.id.ll_actionbar_menu);
        this.mSearch = (LinearLayout)mLocalMusicActionbarLayout.findViewById(R.id.ll_actionbar_search);
        this.mMore = (LinearLayout)mLocalMusicActionbarLayout.findViewById(R.id.ll_actionbar_more);

        fragments = new ArrayList<>();
        fragments.add(new LocalMusicitemFragment());

        fragments.add(new LocalMusicitemFragment());
        fragments.add(new LocalMusicitemFragment());
        fragments.add(new LocalMusicitemFragment());
        mAdapter = new LocalMusicTabAdapter(getSupportFragmentManager(),fragments);
        mVpViewPager.setAdapter(mAdapter);
        mTabPagerIndicator.setViewPager(mVpViewPager,0);
        mTabPagerIndicator.setVisibility(View.VISIBLE);
    }
}
