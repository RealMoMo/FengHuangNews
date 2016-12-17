package momo.com.week10_project.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.adapter.AbstractBaseAdapter;
import momo.com.week10_project.entity.SearchEntity;
import momo.com.week10_project.news_interface.SearchInterface;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.ManagerApi;
import momo.com.week10_project.utils.TimeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 搜索Activity  数据类型返回有4种：纯文字、单图、多图、视频。
 * 主要应用的是：ListView多布局
 * 小功能:根据edittext是否有内容，改变其他控件的状态。
 */
public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, TextWatcher {

    private final String SEARCH= "搜索";
    private final String CANCEL = "取消";

    private int page =1;
    //editext的检索内容的长度
    private int searchTextLen;
    private boolean isAddMore = false;
    //检索的关键字
    private String keyWord;
    //当前时间的月日
    private String currentData;

    //控件
    private EditText et_search;
    private ImageView iv_clear,iv_loading,iv_loadingmore;
    private TextView tv_search;
    private ListView lv;

    private AbstractBaseAdapter<SearchEntity.DataEntity> adapter;

    //handler发runnable
    private Handler handler = new Handler();

    //根据edittext的文本，控制某些控件的是否可见
    private  Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(searchTextLen>0) {
                tv_search.setText(SEARCH);
                iv_clear.setVisibility(View.VISIBLE);
            }else{
                tv_search.setText(CANCEL);
                iv_clear.setVisibility(View.INVISIBLE);
            }
        }
    };

    private List<SearchEntity.DataEntity> totalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initData();

        setupViews();

    }

    private void initData() {

        totalList = new ArrayList<>();
        adapter = new AbstractBaseAdapter<SearchEntity.DataEntity>(this,totalList,
                R.layout.search_content_layout1,R.layout.search_content_layout2,
                R.layout.search_content_layout4,R.layout.search_content_layout3) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                SearchEntity.DataEntity dataEntity = totalList.get(position);
                int viewType = dataEntity.getViewType();
                switch (viewType){
                    //单图
                    case 0:{
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.search_lv1_iv);
                        Glide.with(SearchActivity.this).load(dataEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.search_lv1_title);
                        tv_title.setText(dataEntity.getTitle());
                        //来源与时间
                        TextView iv_source = (TextView) holder.findViewById(R.id.search_lv1_source);
                        iv_source.setText(dataEntity.getSource()+"  "+getUpdateTime(dataEntity.getUpdateTime()));
                    }break;
                    //多图
                    case 1:{
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.search_lv2_title);
                        tv_title.setText(dataEntity.getTitle());
                        //图片
                        List<String> imagesUrl = dataEntity.getStyle().getImages();
                        ImageView iv1 = (ImageView) holder.findViewById(R.id.search_lv2_iv1);
                        ImageView iv2 = (ImageView) holder.findViewById(R.id.search_lv2_iv2);
                        ImageView iv3 = (ImageView) holder.findViewById(R.id.search_lv2_iv3);
                        Glide.with(SearchActivity.this).load(imagesUrl.get(0)).into(iv1);
                        Glide.with(SearchActivity.this).load(imagesUrl.get(1)).into(iv2);
                        Glide.with(SearchActivity.this).load(imagesUrl.get(2)).into(iv3);
                        //来源与时间
                        TextView iv_source = (TextView) holder.findViewById(R.id.search_lv2_source);
                        iv_source.setText(dataEntity.getSource()+"  "+getUpdateTime(dataEntity.getUpdateTime()));
                    }break;
                    //纯文字
                    case 2:{
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.search_lv4_title);
                        tv_title.setText(dataEntity.getTitle());
                        //来源与时间
                        TextView iv_source = (TextView) holder.findViewById(R.id.search_lv4_source);
                        //有些数据没有Source数据，需加非空的判断
                        iv_source.setText(dataEntity.getSource().equals("null")?"":dataEntity.getSource()
                                +"  "+getUpdateTime(dataEntity.getUpdateTime()));
                    }break;
                    //视频
                    case 3:{
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.search_lv3_iv);
                        Glide.with(SearchActivity.this).load(dataEntity.getThumbnail()).into(iv);
                        //时长
                        TextView tv_videoLen = (TextView) holder.findViewById(R.id.search_lv3_videoLen);
                        tv_videoLen.setText(dataEntity.getPhvideo().getLength());
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.search_lv3_title);
                        tv_title.setText(dataEntity.getTitle());
                        //来源
                        TextView tv_source = (TextView) holder.findViewById(R.id.search_lv3_source);
                        tv_source.setText(dataEntity.getPhvideo().getChannelName());
                    }break;
                }

            }

            //返回布局的viewType
            @Override
            public int getItemViewType(int position) {


                return totalList.get(position).getViewType();
            }
        };
    }

    private void setupViews() {
        et_search = (EditText) findViewById(R.id.search_edittext);
        iv_clear = (ImageView) findViewById(R.id.search_clear_iv);
        iv_loading = (ImageView) findViewById(R.id.search_loading_iv);
        iv_loadingmore = (ImageView) findViewById(R.id.search_loadingmore_iv);
        tv_search = (TextView) findViewById(R.id.search_tv_cancel_search);
        lv = (ListView) findViewById(R.id.search_lv);
        //默认是取消文本
        tv_search.setText(CANCEL);

        //edittext文本变化监听
        et_search.addTextChangedListener(this);

        //搜索 or 取消 的动作
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_search.getText().equals(SEARCH)){
                    hideKeyboard(view);
                    page = 1;
                    view.setClickable(false);
                    getSearchData(keyWord=et_search.getText().toString().trim());
                }else if(tv_search.getText().equals(CANCEL)){
                    SearchActivity.this.finish();
                }
            }
        });

        iv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
            }
        });

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        lv.setOnScrollListener(this);


    }

    private void getSearchData(String  keyWord) {

        if(page==1) {
            //显示中间的loading图片
            iv_loading.setVisibility(View.VISIBLE);
            //清空之前加载的数据（若搜索内容一样，可以再加点限制。这里我就不加 =。=除非用户都那么奇葩，那就让他费点流量）
            totalList.clear();
        }else{
            //显示底部的loading图片
            iv_loadingmore.setVisibility(View.VISIBLE);
        }

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ManagerApi.SEARCH_BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        SearchInterface searchInterface = retrofit.create(SearchInterface.class);
        Call<SearchEntity> call = searchInterface.getTest(page, keyWord);
        call.enqueue(new Callback<SearchEntity>() {
            @Override
            public void onResponse(Call<SearchEntity> call, Response<SearchEntity> response) {
                SearchEntity searchContentEntity = response.body();
                //处理非视频
                List<SearchEntity.DataEntity> data = searchContentEntity.getData();
                if( data!=null && data.size()>0){
                    for (SearchEntity.DataEntity i : data){
                        //纯文字布局
                        if(i.getThumbnail().equals("")||i.getThumbnail()==null){
                            i.setViewType(2);
                        }
                        //多图布局
                        else if(i.getStyle()!=null){
                            i.setViewType(1);
                        }
                        //单图布局
                        else if(i.getStyle() == null &&i.getThumbnail()!=null){
                            i.setViewType(0);
                        }
                        i.setUpdateTime(i.getUpdateTime());
                    }

                }
                //处理视频item
                List<SearchEntity.VdataEntity> vdata = searchContentEntity.getVdata();
                if(vdata!=null&& vdata.size()>0){

                    for (SearchEntity.VdataEntity i :vdata){
                        i.setViewType(3);
                        //图片
                        String thumbnail = i.getThumbnail();
                        //标题
                        String title = i.getTitle();
                        //phvideo
                        SearchEntity.VdataEntity.PhvideoEntity phvideo = i.getPhvideo();
                        //来源
                        String channelName = phvideo.getChannelName();
                        //视频时长
                        String length = TimeUtils.getVideoTime(phvideo.getLength());
                        //commentsurl
                        String commentsUrl = i.getCommentsUrl();
                        //flag
                        String flag = i.getFlag();
                        //viewType
                        int viewType = i.getViewType();
                        //将其转换为DataEntity  统一把所有搜索数据都能用List<SearchEntity.DataEntity>集合管理。
                        SearchEntity.DataEntity temp = new SearchEntity.DataEntity();
                        temp.setThumbnail(thumbnail);
                        temp.setTitle(title);
                        SearchEntity.DataEntity.PhvideoEntity phvideoEntity = new SearchEntity.DataEntity.PhvideoEntity();
                        SearchEntity.DataEntity.LinkEntity linkEntity = new SearchEntity.DataEntity.LinkEntity();
                        phvideoEntity.setChannelName(channelName);
                        phvideoEntity.setLength(length);
                        temp.setPhvideo(phvideoEntity);
                        linkEntity.setWeburl(commentsUrl);
                        temp.setLink(linkEntity);
                        temp.setFlag(flag);
                        temp.setViewType(viewType);


                        searchContentEntity.getData().add(temp);

                    }

                }

                //获取当前时间
                currentData = null;
                currentData = TimeUtils.getCurrentTime();

                totalList.addAll(searchContentEntity.getData());
                adapter.notifyDataSetChanged();
                if(page==1) {
                    iv_loading.setVisibility(View.GONE);

                    tv_search.setText(CANCEL);
                    //搜索按钮可点击
                    tv_search.setClickable(true);
                }else{
                    iv_loadingmore.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<SearchEntity> call, Throwable t) {

            }
        });

    }

    //listview
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String weburl = totalList.get(position).getLink().getWeburl();

        Intent intent = new Intent(this,NewsTopContent_Activity.class);
        intent.putExtra(Constant.ITEM_WEBURL,weburl);
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(isAddMore&&scrollState==SCROLL_STATE_IDLE){
                page++;
                getSearchData(keyWord);
            }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(firstVisibleItem+visibleItemCount ==totalItemCount){
                isAddMore = true;
            }else{
                isAddMore =false;
            }
    }


    //edittext
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
                if(s!=null) {
                    searchTextLen = s.toString().length();
                    //通过handler调用搜索方法,延迟搜索，减少误输入搜索对控件展示与否的重复下达指令。
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, 300);
                }
    }


    private String getUpdateTime(String sourceTime) {
        if (sourceTime == null) {
            return null;
        }
        if (sourceTime.contains(currentData)) {
            //返回时分
            return sourceTime.substring(12, 16);
        } else {
            //返回月日
            return sourceTime.substring(5, 10);
        }


    }


    //隐藏键盘的方法
    private void hideKeyboard(View view) {
        view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
