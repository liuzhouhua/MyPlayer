package com.lzh.administrator.myplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import com.lzh.administrator.myplayer.adapter.DrawerLayoutMenuAdapter;
import com.lzh.administrator.myplayer.fragment.DiscoverFragment;
import com.lzh.administrator.myplayer.fragment.FriendsFragment;
import com.lzh.administrator.myplayer.fragment.MusicFragment;


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

    private Button mLoginBtn,mConfigBtn,mExitBtn;
    private DrawerLayout mDrawerLayout;
    private TextView mLoginDisplay;
    private ListView mDrawerList;
    private DrawerLayoutMenuAdapter mDrawerLayoutMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        //初始化侧滑菜单
        initDrawerLayout();

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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.lv_left_menu);
        mLoginDisplay = (TextView)findViewById(R.id.tv_login_display);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mConfigBtn = (Button) findViewById(R.id.btn_config);
        mExitBtn = (Button) findViewById(R.id.btn_exit);
    }

    private void initListener(){
        mActionBarMenu.setOnClickListener(this);
        mActionBarDiscover.setOnClickListener(this);
        mActionBarMusic.setOnClickListener(this);
        mActionBarFriends.setOnClickListener(this);
        mActionBarSearch.setOnClickListener(this);

        mLoginBtn.setOnClickListener(this);
        mConfigBtn.setOnClickListener(this);
        mExitBtn.setOnClickListener(this);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void initDrawerLayout(){
        mDrawerLayoutMenuAdapter = new DrawerLayoutMenuAdapter(this);
        mDrawerList.setAdapter(mDrawerLayoutMenuAdapter);
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

            case R.id.ll_actionbar_menu://打开侧滑菜单
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.iv_actionbar_discover://打开discover页面
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_selected);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_prs);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_prs);
                if(mDiscoverFragment==null){
                    mDiscoverFragment = new DiscoverFragment();
                }
                ft.replace(R.id.content_layout,mDiscoverFragment);
                ft.commit();
                break;
            case R.id.iv_actionbar_music://打开music页面
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_prs);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_selected);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_prs);
                if(mMusicFragment==null){
                    mMusicFragment = new MusicFragment();
                }
                ft.replace(R.id.content_layout,mMusicFragment);
                ft.commit();
                break;
            case R.id.iv_actionbar_friends://打开friend页面n
                mActionBarDiscover.setImageResource(R.drawable.actionbar_discover_prs);
                mActionBarMusic.setImageResource(R.drawable.actionbar_music_prs);
                mActionBarFriends.setImageResource(R.drawable.actionbar_friends_selected);
                if(mFriendsFragment==null){
                    mFriendsFragment = new FriendsFragment();
                }
                ft.replace(R.id.content_layout,mFriendsFragment);
                ft.commit();
                break;
            case R.id.ll_actionbar_search:
                break;
            case R.id.btn_login:
                break;
            case R.id.btn_config:
                break;
            case R.id.btn_exit:
                break;
        }

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 设置期待Activity时没有动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        super.startActivity(intent);
    }

    /**
     * 防止退出Activity时闪烁
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
