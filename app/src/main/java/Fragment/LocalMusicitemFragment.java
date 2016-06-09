package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lzh.administrator.myplayer.R;

import java.util.List;

import adapter.LocalMusicFragmentAdapter;
import manager.SongManager;
import model.Song;

/**
 * Created by lzh27651 on 2016/5/13.
 */
public class LocalMusicitemFragment extends Fragment {

    private List<Song> songs;
    private ListView lvlocalmusiclist;
    private LocalMusicFragmentAdapter mAdapter;

    public LocalMusicitemFragment() {
        this.songs = SongManager.getInstance().getmSongs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_music_fragment_layout,container,false);
        this.lvlocalmusiclist = (ListView) view.findViewById(R.id.lv_local_music_list);

        mAdapter = new LocalMusicFragmentAdapter(getActivity(),songs);
        lvlocalmusiclist.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
