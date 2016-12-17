package momo.com.week10_project.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import momo.com.week10_project.R;

/**
 * 该activity是展示直播的视频，没有抓到接口。
 * 暂空。
 */
public class LiveDetailActivity extends AppCompatActivity {

    private String url;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);



    }

    private void initSurfaceView() {

    }
}
