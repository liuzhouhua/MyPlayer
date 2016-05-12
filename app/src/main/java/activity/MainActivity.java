package activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.lzh.administrator.myplayer.R;
import com.viewpagerindicator.TabPageIndicator;

import adapter.MainTabAdapter;


public class MainActivity extends FragmentActivity {

    private TabPageIndicator miIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        miIndicator = (TabPageIndicator) findViewById(R.id.ti_tanpagerindicator_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_viewpager_main);

        mAdapter = new MainTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        miIndicator.setViewPager(mViewPager,0);

    }
}
