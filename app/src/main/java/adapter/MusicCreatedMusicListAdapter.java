package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class MusicCreatedMusicListAdapter extends BaseAdapter{

    private Context mContext;

    private static final int[] IMG = new int[]{R.drawable.fm_run_love,R.drawable.placeholder_disk_play_fm} ;
    private List<String> mDisplay = new ArrayList<>();

    public MusicCreatedMusicListAdapter(Context context) {
        mContext = context;
        mDisplay.add(mContext.getString(R.string.myFavorateSong));
    }

    public void setmDisplay(String mDisplay) {
        if(mDisplay==null || mDisplay.equals("")){
            return;
        }
        this.mDisplay.add(mDisplay);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.music_main_created_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.iv_music_main_conment_list_item);
            viewHolder.mTextDisplay = (TextView) view.findViewById(R.id.tv_music_main_conment_list_item);
            viewHolder.mTextCount = (TextView) view.findViewById(R.id.tv_music_main_conment_list_item_count);
            viewHolder.mConfig = (ImageView) view.findViewById(R.id.iv_music_main_imageview_created_list_item_cofig);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if(position < mDisplay.size() && mContext.getString(R.string.myFavorateSong).equals(mDisplay.get(position))){
            viewHolder.icon.setImageResource(IMG[0]);
        }else{
            viewHolder.icon.setImageResource(IMG[1]);
        }
        if(position < mDisplay.size()){
            viewHolder.mTextDisplay.setText(mDisplay.get(position));
        }

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mDisplay.get(position);
    }

    @Override
    public int getCount() {
        return mDisplay.size();
    }

    class  ViewHolder{
        ImageView icon;
        TextView mTextDisplay;
        TextView mTextCount;
        ImageView mConfig;
    }
}
