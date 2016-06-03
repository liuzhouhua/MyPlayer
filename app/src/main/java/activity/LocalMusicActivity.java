package activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.lzh.administrator.myplayer.R;
import com.viewpagerindicator.TabPageIndicator;

import adapter.LocalMusicTabAdapter;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LocalMusicActivity extends FragmentActivity {

    private TabPageIndicator mTabPagerIndicator;
    private ViewPager mVpViewPager;
    private FragmentPagerAdapter mAdapter;
    private LinearLayout mLocalMusicActionbarLayout;
    private LinearLayout mActionBarBack;
    private LinearLayout mSearch;
    private LinearLayout mMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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

        mAdapter = new LocalMusicTabAdapter(getSupportFragmentManager());
        mVpViewPager.setAdapter(mAdapter);
        mTabPagerIndicator.setViewPager(mVpViewPager,0);
        mTabPagerIndicator.setVisibility(View.VISIBLE);
    }
}
