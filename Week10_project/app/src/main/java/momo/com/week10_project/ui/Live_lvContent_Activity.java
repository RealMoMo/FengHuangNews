package momo.com.week10_project.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import momo.com.week10_project.R;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.LogUtils;

public class Live_lvContent_Activity extends AppCompatActivity {



    private WebView webView;
    private ImageView iv;
    private String url;
    private RotateAnimation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_lv_content_);

        webView = (WebView) findViewById(R.id.live_lvcontent_webview);
        iv = (ImageView) findViewById(R.id.live_lvcontent_iv);

        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.LIVE_LVCONTENT_URL);


        initAnimation();
        initWebView();




    }

    private void initAnimation() {
        animation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(2000);
        animation.setRepeatCount(50);
        animation.setInterpolator(new LinearInterpolator());

    }

    private void initWebView() {

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                iv.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                iv.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                LogUtils.MyLog("onPageFinished");
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                iv.startAnimation(animation);
            }
        });

        webView.loadUrl(url);
    }

    //点击backspace可返回上个页面，而不是退出(若webview只加载了一个页面)
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            // 按返回时，看网页是否能返回
//            if (webView.canGoBack()) {
//                webView.goBack();
//                //返回true webview自己处理
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
