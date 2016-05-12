package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.MainItemFragment;

/**
 * Created by Administrator on 2016/5/13.
 */
public class MainTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"个性推荐","歌单","主播电台","排行榜"};

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MainItemFragment fragment = new MainItemFragment();
        return fragment;
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
