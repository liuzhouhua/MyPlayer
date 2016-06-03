package adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment.LocalMusicitemFragment;

/**
 * Created by Administrator on 2016/5/13.
 */
public class LocalMusicTabAdapter extends FragmentPagerAdapter{

    private static final String[] TITLES = new String[]{"单曲","歌手","专辑","文件夹"};

    public LocalMusicTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        LocalMusicitemFragment mFragment = new LocalMusicitemFragment();
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
