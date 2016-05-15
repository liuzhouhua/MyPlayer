package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

/**
 * Created by Administrator on 2016/5/14.
 */
public class DrawerLayoutMenuAdapter extends BaseAdapter{

    private static final String[] TITLES = new String[]{"听歌识曲","主题皮肤","夜间模式","定时停止播放","音乐闹钟"};

    private static final int[] RES_IMG = new int[]{R.drawable.topmenu_icn_identify,R.drawable.topmenu_icn_skin
                                                ,R.drawable.topmenu_icn_night,R.drawable.topmenu_icn_time
                                                ,R.drawable.topmenu_icn_clock};

    private Context mContext;
    public DrawerLayoutMenuAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Object getItem(int position) {
        return TITLES[position % TITLES.length];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.drawerlayout_menu_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mIcon = (ImageView) view.findViewById(R.id.iv_drawerlayout_menu_list_item);
            viewHolder.mDisplayItem = (TextView) view.findViewById(R.id.tv_drawerlayout_menu_list_item);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mIcon.setImageResource(RES_IMG[position % RES_IMG.length]);
        viewHolder.mDisplayItem.setText(TITLES[position % TITLES.length]);
        return view;
    }

    class  ViewHolder {
        ImageView mIcon;
        TextView mDisplayItem;
    }
}
