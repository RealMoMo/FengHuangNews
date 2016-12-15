package momo.com.week10_project.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import momo.com.week10_project.R;
import momo.com.week10_project.fragment.AccountFragment;
import momo.com.week10_project.fragment.NewsFragment;
import momo.com.week10_project.fragment.ReadFragment;
import momo.com.week10_project.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private String[] tabTexts ={
            "新闻", "视频", "发现", "我",
    };

    private int[] tabImgIds ={
            R.drawable.selector_main_news, R.drawable.selector_main_video, R.drawable.selector_main_read, R.drawable.selector_main_account,
    };


    private Class[] fragments ={
            NewsFragment.class, VideoFragment.class, ReadFragment.class, AccountFragment.class,
    };

    private FragmentTabHost tabHost;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();




    }


    private void setupView() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //设置tabHost
        /*
        * 参数1：context
        *
        * 参数2：FragmentManager
        *
        * 参数3：要显示的Fragment的容器id
        *
        * */
        tabHost.setup(this,getSupportFragmentManager(),R.id.main_fragmentLayout);

        //初始化inflater
        inflater = LayoutInflater.from(this);

        //给tabHost添加Tab
        for(int i=0;i<tabTexts.length;i++){
            //创建新的Tab  参数作用：可通过该参数找到tabItem
            TabHost.TabSpec tabItem = tabHost.newTabSpec(i + "");
            //给tabItem设置内容view
            tabItem.setIndicator(getTabItemView(i));
            //tabItem添加到tabHost中
            /*
            * 参数1：tab标签
            *
            * 参数2：tab内容的Fragment类
            *
            * 参数3：Bundle    可以传值到Fragment
            *
            * */
            tabHost.addTab(tabItem,fragments[i],null);
            //tabHost去边线
            tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }



    /**
     * 加载底部导航的四个Tab
     *
     * @param index
     * @return
     */
    private View getTabItemView(int index){


        View view = inflater.inflate(R.layout.main_tabhost_item_layout,null);
        //找到控件
        ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
        //给控件设置相应内容
        iv.setImageResource(tabImgIds[index]);
        tv.setText(tabTexts[index]);

        return  view;
    }


    //按两次backspace退出应用

    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis()-exitTime>2000){
                Toast.makeText(this,"再点击一次退出",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else{
                finish();
                //正常退出--0
                System.exit(0);
            }
            //return true 该事件自己处理，不向外分发该事件
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
