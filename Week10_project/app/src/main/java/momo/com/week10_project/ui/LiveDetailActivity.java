package momo.com.week10_project.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.vov.vitamio.widget.VideoView;
import momo.com.week10_project.R;
import momo.com.week10_project.utils.Constant;

public class LiveDetailActivity extends AppCompatActivity {

    private String url;

    private VideoView surfaceView;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);

        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.DETAIL_URL);

        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        surfaceView = (VideoView) findViewById(R.id.livedetail_surfaceview);
        surfaceView.setVideoURI(Uri.parse("http://zv.3gv.ifeng.com/live/zixun64kaudio.m3u8"));
        surfaceView.start();
        initSurfaceView();

    }

    private void initSurfaceView() {

    }
}
