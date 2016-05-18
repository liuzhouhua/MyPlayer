package activity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzh.administrator.myplayer.R;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ProtypeActivity extends FragmentActivity{

    ImageView ivLayProtype;
    ImageView ivLocalScanImg;
    FrameLayout flLocalScanLayout;
    Button btnBackToMusic;
    LinearLayout llBackToMusicLayout;
    TextView tvMusicSongnameDisplay;


    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.local_scal_layout);

        ivLayProtype = (ImageView) findViewById(R.id.iv_lay_protype);
        ivLocalScanImg = (ImageView) findViewById(R.id.local_scan_img);
        flLocalScanLayout = (FrameLayout) findViewById(R.id.fl_local_scan_layout);
        btnBackToMusic = (Button) findViewById(R.id.btn_back_to_music);
        llBackToMusicLayout = (LinearLayout)findViewById(R.id.ll_back_to_music_layout);
        tvMusicSongnameDisplay = (TextView) findViewById(R.id.tv_music_songname_display);


        ivLayProtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBackToMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                tvMusicSongnameDisplay.setText((String)msg.obj);
                return true;
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Cursor mAutoCursor = getContentResolver().query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER
                );

                String strTitle;
                String strPath;
                Message message;

                for (int i = 0; i < mAutoCursor.getCount(); i++) {

                    mAutoCursor.moveToNext();
                    strTitle = mAutoCursor.getString(mAutoCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));
                    strPath = mAutoCursor.getString(mAutoCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));
                    message = new Message();
                    message.obj = strTitle + "---" + strPath;
                    mHandler.sendMessage(message);
                    try{
                        Thread.currentThread().sleep(150);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(runnable).start();
    }
}
