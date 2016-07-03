package com.lzh.administrator.myplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzh.administrator.myplayer.R;
import com.viewpagerindicator.TabPageIndicator;

import java.lang.reflect.Field;

import com.lzh.administrator.myplayer.adapter.DiscoverTabAdapter;

/**
 * Created by Administrator on 2016/5/13.
 */
public class DiscoverFragment extends Fragment{
    private TabPageIndicator miIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    public DiscoverFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_main_layout,container,false);

        miIndicator = (TabPageIndicator) view.findViewById(R.id.ti_tanpagerindicator_main);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_viewpager_main);

        mAdapter = new DiscoverTabAdapter(this.getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        miIndicator.setViewPager(mViewPager,0);
        miIndicator.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
