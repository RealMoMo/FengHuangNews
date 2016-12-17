package momo.com.week10_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import momo.com.week10_project.R;
import momo.com.week10_project.utils.Constant;


/**
 * 直接用WebView展示，后续可以自定义布局展示内容
 */
public class NewsTopContent_Activity extends AppCompatActivity {


//    private String itemId;
//    private String commentsUrl;
    private String webUrl;
    //title ==小专题
//    private String title;
    //0 单图 1 多图 2 专题 3 视频
//    private int viewType;

//    private ScrollView scrollView;
//    private NewsContentView newsContentView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstop_content);
        Intent intent = getIntent();

//        intent.putExtra(Constant.ITEM_ID,itemId);
//        intent.putExtra(Constant.ITEM_COMMENTSURL,commentsUrl);
//        intent.putExtra(Constant.ITEM_VIEWTYPE,viewType);
//        itemId = intent.getStringExtra(Constant.ITEM_ID);
//        commentsUrl = intent.getStringExtra(Constant.ITEM_COMMENTSURL);
//        viewType = intent.getIntExtra(Constant.ITEM_VIEWTYPE,0);
//        title = intent.getStringExtra(Constant.ITEM_TITLE);
        webUrl = intent.getStringExtra(Constant.ITEM_WEBURL);


        setupViews();

        showContent();

    }



    private void setupViews() {
//        scrollView = (ScrollView) findViewById(R.id.news_scrollView);
//        newsContentView = (NewsContentView) findViewById(R.id.newscontentView);
        webView = (WebView) findViewById(R.id.news_webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());


    }

    private void showContent() {

            webView.loadUrl(webUrl);

//        //专题、视频用webView展示
//        if(viewType==2||viewType==3||title.equals(Constant.Top_TITLE)){
//            scrollView.setVisibility(View.GONE);
//            newsContentView.setVisibility(View.GONE);
//            webView.loadUrl(commentsUrl);
//        }
//        //单图、多图
//        else{
//            //用自定义NewsContentView解析数据，再自定义布局内容
//        }


    }


    //点击backspace可返回上个页面，而不是退出(若webview只加载了一个页面)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.getVisibility() == View.VISIBLE) {
                // 按返回时，看网页是否能返回
                if (webView.canGoBack()) {
                    webView.goBack();
                    //返回true webview自己处理
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
