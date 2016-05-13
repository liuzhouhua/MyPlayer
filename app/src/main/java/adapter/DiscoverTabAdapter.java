package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.DiscoveritemFragment;

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
