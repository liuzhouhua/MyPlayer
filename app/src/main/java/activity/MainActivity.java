package activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzh.administrator.myplayer.R;

import fragment.DiscoverFragment;
import fragment.FriendsFragment;
import fragment.MusicFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private LinearLayout mActionBar;
    private LinearLayout mActionBarMenu;
    private ImageView mActionBarDiscover;
    private ImageView mActionBarMusic;
    private ImageView mActionBarFriends;
    private LinearLayout mActionBarSearch;
    private DiscoverFragment mDiscoverFragment;
    private MusicFragment mMusicFragment;
    private FriendsFragment mFriendsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        // 设置默认的Fragment
        setDefaultFragment();
    }

    private void initView() {
        mActionBar = (LinearLayout) findViewById(R.id.ll_actionbar);
        mActionBarMenu = (LinearLayout) mActionBar.findViewById(R.id.ll_actionbar_menu);
        mActionBarDiscover = (ImageView) mActionBar.findViewById(R.id.iv_actionbar_discover);
        mActionBarMusic = (ImageView) mActionBar.findViewById(R.id.iv_actionbar_music);
        mActionBarFriends = (ImageView) mActionBar.findViewById(R.id.iv_actionbar_friends);
        mActionBarSearch = (LinearLayout) mActionBar.findViewById(R.id.ll_actionbar_search);
    }

    private void initListener(){
        mActionBarMenu.setOnClickListener(this);
        mActionBarDiscover.setOnClickListener(this);
        mActionBarMusic.setOnClickListener(this);
        mActionBarFriends.setOnClickListener(this);
        mActionBarSearch.setOnClickListener(this);
    }

    public void setDefaultFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        mDiscoverFragment = new DiscoverFragment();
        ft.replace(R.id.content_layout,mDiscoverFragment);
        ft.commit();
    }

    @Override
    public void onClick(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
        switch (view.getId()){

            case R.id.ll_actionbar_menu:
                break;
            case R.id.iv_actionbar_discover:
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_selected);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_prs);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_prs);
                if(mDiscoverFragment==null){
                    mDiscoverFragment = new DiscoverFragment();
                }
                ft.replace(R.id.content_layout,mDiscoverFragment);
                break;
            case R.id.iv_actionbar_music:
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_prs);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_selected);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_prs);
                if(mMusicFragment==null){
                    mMusicFragment = new MusicFragment();
                }
                ft.replace(R.id.content_layout,mMusicFragment);
                break;
            case R.id.iv_actionbar_friends:
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_prs);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_prs);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_selected);
                if(mFriendsFragment==null){
                    mFriendsFragment = new FriendsFragment();
                }
                ft.replace(R.id.content_layout,mFriendsFragment);
                break;
            case R.id.ll_actionbar_search:
                break;
        }

        ft.commit();
    }
}
