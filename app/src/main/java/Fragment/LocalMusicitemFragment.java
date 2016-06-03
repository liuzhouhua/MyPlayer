package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

/**
 * Created by lzh27651 on 2016/5/13.
 */
public class LocalMusicitemFragment extends Fragment{

    private String title;

    public LocalMusicitemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment_layout,container,false);
        TextView textView = (TextView) view.findViewById(R.id.test_tv);
        textView.setText(title);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("test");
    }
}
