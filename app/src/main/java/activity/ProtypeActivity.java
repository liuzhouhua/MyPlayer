package activity;

import android.content.ContentValues;
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

import java.util.ArrayList;
import java.util.List;

import db.DatabaseContext;
import db.DatabaseHelper;
import manager.SongManager;
import model.Song;
import utils.SharePreferencesHelp;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ProtypeActivity extends FragmentActivity{

    private final static int SCAN_MUSIC_START = 1;
    private final static int SCAN_MUSIC_END = 2;

    private ImageView ivLayProtype;
    private ImageView ivLocalScanImg;
    private FrameLayout flLocalScanLayout;
    private Button btnBackToMusic;
    private Button btnCancelScanMusic;
    private LinearLayout llBackToMusicLayout;
    private TextView tvMusicSongnameDisplay;

    private Thread mThread;
    private Handler mHandler;
    private boolean isNeedBreak = false;

    private List<Song> mSongs = new ArrayList<>();
    private List<ContentValues> valuesList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.local_scal_layout);

        ivLayProtype = (ImageView) findViewById(R.id.iv_lay_protype);
        ivLocalScanImg = (ImageView) findViewById(R.id.local_scan_img);
        flLocalScanLayout = (FrameLayout) findViewById(R.id.fl_local_scan_layout);
        btnBackToMusic = (Button) findViewById(R.id.btn_back_to_music);
        btnCancelScanMusic = (Button) findViewById(R.id.btn_cancel_scan_music);
        llBackToMusicLayout = (LinearLayout)findViewById(R.id.ll_back_to_music_layout);
        tvMusicSongnameDisplay = (TextView) findViewById(R.id.tv_music_songname_display);

        DatabaseContext databaseContext = new DatabaseContext(this);

        databaseHelper = DatabaseHelper.getInstance(this);


        ivLayProtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mThread!=null && mThread.isAlive()){
                    isNeedBreak = true;
                }
                finish();
            }
        });

        btnBackToMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancelScanMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mThread!=null && mThread.isAlive()){
                    isNeedBreak = true;
                }
                finish();
            }
        });

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case SCAN_MUSIC_START:
                        tvMusicSongnameDisplay.setText((String)msg.obj);
                        btnCancelScanMusic.setVisibility(View.VISIBLE);
                        break;
                    case SCAN_MUSIC_END:
                        if(btnBackToMusic!=null && btnCancelScanMusic!=null){
                            btnCancelScanMusic.setVisibility(View.GONE);
                            btnBackToMusic.setVisibility(View.VISIBLE);
                        }
                        break;
                }
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
                String strAlbum;
                String strSinger;
                String strDisplayName;
                int strDuration;
                long strSize;
                Message message;
                Song song;
                ContentValues values;

                for (int i = 0; i < mAutoCursor.getCount(); i++) {

                    if(isNeedBreak)
                        break;

                    mAutoCursor.moveToNext();
                    song = new Song();
                    values = new ContentValues();
                    //歌曲的名称 ：MediaStore.Audio.Media.TITL
                    strTitle = mAutoCursor.getString(mAutoCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE));

                    //歌曲文件的全路径 ：MediaStore.Audio.Media.DATA
                    strPath = mAutoCursor.getString(mAutoCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));

                    //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                    strSinger = mAutoCursor.getString(mAutoCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                    //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                    strAlbum = mAutoCursor.getString(mAutoCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));

                    //歌曲文件的名称：MediaStroe.Audio.Media.DISPLAY_NAME
                    strDisplayName = mAutoCursor.getString(mAutoCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));

                    //歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
                    strDuration= mAutoCursor.getInt(mAutoCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                    //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                    strSize = mAutoCursor.getLong(mAutoCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                    song.setmTitile(strTitle);
                    song.setmSinger(strSinger);
                    song.setmAlbum(strAlbum);
                    song.setmPath(strPath);
                    song.setmDuration(strDuration+"");
                    song.setmFileSize(strSize+"");
                    mSongs.add(song);

                    values.put("title",strTitle);
                    values.put("artist",strSinger);
                    values.put("album",strAlbum);
                    values.put("path",strPath);
                    values.put("name",strDisplayName);
                    values.put("duration",strDuration);
                    values.put("file_size",strSize);
                    valuesList.add(values);

                    message = new Message();
                    message.what = SCAN_MUSIC_START;
                    message.obj = strTitle + "---" + strPath;
                    mHandler.sendMessage(message);
                    try{
                        Thread.currentThread().sleep(150);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                SongManager.getInstance().setmSongs(mSongs);
                SharePreferencesHelp.getInstance(ProtypeActivity.this).setTagForScanMusic(true);
                databaseHelper.insert(DatabaseHelper.TABLE_LOCAL_MUSIC,valuesList);


                message = new Message();
                message.what = SCAN_MUSIC_END;
                mHandler.sendMessage(message);
            }
        };

        mThread = new Thread(runnable);
        mThread.start();
    }
}
