package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import java.util.List;

import model.Song;

/**
 * Created by Administrator on 2016/6/9.
 */
public class LocalMusicFragmentAdapter extends BaseAdapter{

    private List<Song> songs;
    private Context mContext;

    public LocalMusicFragmentAdapter(Context context,List<Song> songs) {
        this.mContext = context;
        this.songs = songs;
    }

    @Override
    public int getCount() {
        if(songs!=null){
            return songs.size();
        }else{
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.local_music_item_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.ivmusicmore = (ImageView) view.findViewById(R.id.iv_music_more);
            viewHolder.ivbtnmv = (ImageView) view.findViewById(R.id.iv_btn_mv);
            viewHolder.ivloginicnok = (ImageView) view.findViewById(R.id.iv_login_icn_ok);
            viewHolder.tvmusicname = (TextView) view.findViewById(R.id.tv_music_name);
            viewHolder.tvmusicSinger = (TextView) view.findViewById(R.id.tv_music_singer);
            viewHolder.ivtrumpet = (ImageView) view.findViewById(R.id.iv_trumpet);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Song song = songs.get(position);
        if(song!=null){
            viewHolder.tvmusicname.setText(song.getmTitile());
            viewHolder.tvmusicSinger.setText(song.getmSinger());
        }

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Song getItem(int position) {
        if(songs!=null){
            return songs.get(position);
        }else{
            return null;
        }
    }

    class ViewHolder {
        private ImageView ivtrumpet;
        private TextView tvmusicname;
        private TextView tvmusicSinger;
        private ImageView ivloginicnok;
        private ImageView ivbtnmv;
        private ImageView ivmusicmore;
    }
}
