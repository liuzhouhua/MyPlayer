package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import adapter.MusicConmentAdapter;
import adapter.MusicCreatedMusicListAdapter;

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

        return view;
    }
}
