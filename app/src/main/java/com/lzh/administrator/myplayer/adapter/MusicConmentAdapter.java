package com.lzh.administrator.myplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

import com.lzh.administrator.myplayer.utils.SharePreferencesHelp;

/**
 * Created by Administrator on 2016/5/13.
 */
public class MusicConmentAdapter extends BaseAdapter{

    private Context mContext;

    private static final int[] DIS_TEXT = new int[]{R.string.localMusic,R.string.recentPlay
                                            ,R.string.download,R.string.artist};

    private static final int[] DIS_IMG = new int[]{R.drawable.music_icn_local,R.drawable.music_icn_recent
                                                ,R.drawable.music_icn_dld,R.drawable.music_icn_artist};

    public MusicConmentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.music_main_conment_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.iv_music_main_conment_list_item);
            viewHolder.mTextDisplay = (TextView) view.findViewById(R.id.tv_music_main_conment_list_item);
            viewHolder.mTextCount = (TextView) view.findViewById(R.id.tv_music_main_conment_list_item_size);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.icon.setImageResource(DIS_IMG[position % DIS_IMG.length]);
        viewHolder.mTextDisplay.setText(mContext.getString(DIS_TEXT[position % DIS_TEXT.length]));
        if(position==0){
            viewHolder.mTextCount.setText("("+SharePreferencesHelp.getInstance(mContext).getLocalMusicCount()+")");
        }else if(position==1){
            viewHolder.mTextCount.setText("("+0+")");
        }else if(position==2){
            viewHolder.mTextCount.setText("("+0+")");
        }else if(position==3){
            viewHolder.mTextCount.setText("("+0+")");
        }

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return DIS_TEXT[position % DIS_TEXT.length];
    }

    @Override
    public int getCount() {
        return DIS_TEXT.length;
    }

    class  ViewHolder{
        ImageView icon;
        TextView mTextDisplay;
        TextView mTextCount;
    }
}
