package fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import java.lang.reflect.Field;
import java.util.List;

import activity.LocalMusicActivity;
import activity.ProtypeActivity;
import adapter.MusicConmentAdapter;
import adapter.MusicCreatedMusicListAdapter;
import constant.LocalMusicConstant;
import db.DatabaseHelper;
import manager.SongManager;
import model.Song;
import utils.SharePreferencesHelp;

/**
 * Created by lzh27651 on 2016/5/13.
 */
public class MusicFragment extends Fragment{

    private ListView mConmentList;
    private ImageView mIcoOpen;
    private TextView mDisplayListCount;
    private LinearLayout mDisplayCreatedLayout;
    private ImageView mListCofig;
    private ListView mMusicOrderList;

    private MusicConmentAdapter mMusicConmentAdapter;
    private MusicCreatedMusicListAdapter mMusicCreatedMusicListAdapter;
    private ViewStub viewStub;

    private DatabaseHelper databaseHelper;

    private boolean isOpen = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_main_layout,container,false);

        mConmentList = (ListView) view.findViewById(R.id.lv_music_main_listview);
        mIcoOpen = (ImageView)view.findViewById(R.id.iv_musci_main_imageview);
        mDisplayListCount = (TextView)view.findViewById(R.id.tv_music_main_textview);
        mDisplayCreatedLayout = (LinearLayout) view.findViewById(R.id.ll_music_main_created_layout);
        mListCofig = (ImageView) view.findViewById(R.id.iv_music_main_imageview_cofig);
        mMusicOrderList = (ListView) view.findViewById(R.id.lv_music_main_created_list);


        mMusicConmentAdapter = new MusicConmentAdapter(getActivity());
        viewStub= new ViewStub(getActivity());
        mConmentList.addFooterView(viewStub);
        mConmentList.setAdapter(mMusicConmentAdapter);
        mConmentList.setFooterDividersEnabled(true);


        mMusicCreatedMusicListAdapter = new MusicCreatedMusicListAdapter(getActivity());
        mMusicOrderList.addFooterView(viewStub);
        mMusicOrderList.setAdapter(mMusicCreatedMusicListAdapter);
        mMusicOrderList.setFooterDividersEnabled(true);

        mDisplayCreatedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    isOpen = false;
                    mIcoOpen.setImageResource(R.drawable.list_head_icn_fold);
                    mMusicOrderList.setVisibility(View.GONE);
                }else{
                    isOpen = true;
                    mIcoOpen.setImageResource(R.drawable.list_head_icn_open);
                    mMusicOrderList.setVisibility(View.VISIBLE);
                }
            }
        });

        mListCofig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mConmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(SharePreferencesHelp.getInstance(getActivity()).getTagForScanMusic()){
                            if (SharePreferencesHelp.getInstance(getActivity()).getLocalMusicCount() <= 0
                                    || SongManager.getInstance().getmSongs() == null) {
                                queryMusic();
                            }
                            Intent intent = new Intent(getActivity(), LocalMusicActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(getActivity(), ProtypeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });

        return view;
    }


    private void queryMusic() {
        databaseHelper = DatabaseHelper.getInstance(getActivity());
        List<Song> all = databaseHelper.queryForList(new DatabaseHelper.RowMapper<Song>() {
            @Override
            public Song mapRow(Cursor cursor, int index) {
                Song song = new Song();
                song.setmTitile(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_TITLE)));
                song.setmSinger(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_ARTIST)));
                song.setmAlbum(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_ALBUM)));
                song.setmPath(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_PATH)));
                song.setmDuration(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_DURATION)));
                song.setmFileSize(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_FILE_SIZE)));
                song.setmAlbumImgPath(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_ALBUM_IMG_PATH)));
                song.setmAlbumImgTitle(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_ALBUM_IMG_TITLE)));
                song.setmLrcPath(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_LRC_PATH)));
                song.setmLrcTitle(cursor.getString(cursor.getColumnIndex(LocalMusicConstant.LOCAL_MUSIC_LRC_TITLE)));
                return song;
            }
        },"select * from "+DatabaseHelper.TABLE_LOCAL_MUSIC,null);

        if(all!=null && all.size()>0){
            SongManager.getInstance().setmSongs(all);
            SharePreferencesHelp.getInstance(getActivity()).setLocalMusicCount(all.size());
            mMusicConmentAdapter.notifyDataSetChanged();
        }
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
