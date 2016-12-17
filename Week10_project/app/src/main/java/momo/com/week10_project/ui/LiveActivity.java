package momo.com.week10_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import momo.com.week10_project.R;
import momo.com.week10_project.adapter.AbstractBaseAdapter;
import momo.com.week10_project.entity.LiveChannelEntity;
import momo.com.week10_project.entity.LiveContentEntity;
import momo.com.week10_project.news_interface.LiveInterface;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.ManagerApi;
import momo.com.week10_project.widget.LiveChannelIconView;
import momo.com.week10_project.widget.LiveChannelNameView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 直播界面listview的activity
 */
public class LiveActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    //非体育直播的类型
    private final String NORMAL_LIVE="text_live";
    //是体育直播的类型
    private final String SPORT_LIVE="sports_live";

    private PtrClassicFrameLayout refresh;
    //直播电视台的icon布局的view(怪我没写数据保存，只好这么玩)
    private LiveChannelIconView liveChanneIconlView;
    //直播电视台的name布局的view
    private LiveChannelNameView liveChannelNameView;
    private ListView lv;
    private ImageView iv_back;

    private int page=1;
    private boolean isAddMore;

    //需要其item信息
    private List<LiveContentEntity.ItemEntity> itemList;
    //adapter放item信息
    private AbstractBaseAdapter<LiveContentEntity.ItemEntity> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        //初始化控件
        setupViews();
        //获取电视频道信息
        getLiveChannelData();
        //初始化list集合和adapter
        initAdapter();
        //获取直播内容信息
        getLiveContentData();
        //listview item点击事件
        lv.setOnItemClickListener(this);
        //listview 滚动监听
        lv.setOnScrollListener(this);
    }




    private void setupViews() {
        liveChanneIconlView = new LiveChannelIconView(this);
        liveChannelNameView = new LiveChannelNameView(this);
        refresh = (PtrClassicFrameLayout) findViewById(R.id.refresh);
        lv = (ListView) findViewById(R.id.live_lv);
        iv_back = (ImageView) findViewById(R.id.activity_live_iv_back);
        iv_back.setOnClickListener(this);


        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1;
                getLiveContentData();
            }

            //解决Listview与下拉刷新的冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, lv, header);
            }
        });
        lv.addHeaderView(liveChanneIconlView,null,false);
        lv.addHeaderView(liveChannelNameView,null,false);
    }

    private void initAdapter() {

        itemList = new ArrayList<>();

        adapter = new AbstractBaseAdapter<LiveContentEntity.ItemEntity>(this,itemList,R.layout.live_lv_layout1,R.layout.live_lv_layout2) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                LiveContentEntity.ItemEntity itemEntity = itemList.get(position);

                String itemType = itemEntity.getType();
                int type = itemType.equals(NORMAL_LIVE)?0:1;
//                LogUtils.MyLog("type:"+type);
//                int type = getItemViewType(position);
                //普通直播布局
                if(type ==0){
                    //图片
                    ImageView iv = (ImageView) holder.findViewById(R.id.live_lv1_iv);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(LiveActivity.this).load(itemEntity.getThumbnail()).into(iv);
                    //标题
                    TextView tv_title = (TextView) holder.findViewById(R.id.live_lv1_title);
                    tv_title.setText(itemEntity.getTitle());
                    //人数
                    TextView tv_num = (TextView) holder.findViewById(R.id.live_lv1_partakeNum);
                    tv_num.setText(itemEntity.getLiveExt().getPartakeNum()+"人参数");
                    //直播时间
                    TextView tv_starttime = (TextView) holder.findViewById(R.id.live_lv1_time);
                    tv_starttime.setText(itemEntity.getLiveExt().getStartTime());

                }
                //体育赛事布局
                else if(type ==1){
                    //图片
                    ImageView iv_left = (ImageView) holder.findViewById(R.id.live_lv2_ivleft);
                    ImageView iv_right = (ImageView) holder.findViewById(R.id.live_lv2_ivright);
                    Glide.with(LiveActivity.this).load(itemEntity.getSportsLiveExt().getLeftLogo()).into(iv_left);
                    Glide.with(LiveActivity.this).load(itemEntity.getSportsLiveExt().getRightLogo()).into(iv_right);
                    //标题
                    TextView tv_title = (TextView) holder.findViewById(R.id.live_lv2_title);
                    tv_title.setText(itemEntity.getTitle());
                    //比赛时间
                    TextView tv_time = (TextView) holder.findViewById(R.id.live_lv2_starttime);
                    tv_time.setText(itemEntity.getStartTimeStr());
                    //队名
                    TextView tv_left = (TextView) holder.findViewById(R.id.live_lv2_tvleft);
                    TextView tv_right = (TextView) holder.findViewById(R.id.live_lv2_tvright);
                    tv_left.setText(itemEntity.getSportsLiveExt().getLeftName());
                    tv_right.setText(itemEntity.getSportsLiveExt().getRightName());
                    //比分
                    TextView tv_score = (TextView) holder.findViewById(R.id.live_lv2_score);
                    tv_score.setText(itemEntity.getSportsLiveExt().getLeftScore()+"-"+itemEntity.getSportsLiveExt().getRightScore());

                }
            }


            @Override
            public int getItemViewType(int position) {

                String type = itemList.get(position).getType();
                switch (type){
                    case NORMAL_LIVE:return 0;
                    case SPORT_LIVE:return 1;
                }
                return 0;
            }
        };

        lv.setAdapter(adapter);
    }

    private void getLiveChannelData() {


        //获取电视台的信息
        Retrofit retrofit1 = new Retrofit.Builder()
                            .baseUrl(ManagerApi.BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        LiveInterface liveInterface = retrofit1.create(LiveInterface.class);
        Call<LiveChannelEntity> callChannel = liveInterface.getLiveChannel();
        callChannel.enqueue(new Callback<LiveChannelEntity>() {
            @Override
            public void onResponse(Call<LiveChannelEntity> call, Response<LiveChannelEntity> response) {
                LiveChannelEntity entity = response.body();

                //并在里面实现点击事件
                liveChanneIconlView.setData(entity);
                liveChannelNameView.setData(entity);

            }

            @Override
            public void onFailure(Call<LiveChannelEntity> call, Throwable t) {

            }
        });




    }


    private void getLiveContentData() {
        //获取直播内容信息
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(ManagerApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LiveInterface liveContent = retrofit2.create(LiveInterface.class);
        Call<List<LiveContentEntity>> call = liveContent.getLiveContent(page);
        call.enqueue(new Callback<List<LiveContentEntity>>() {
            @Override
            public void onResponse(Call<List<LiveContentEntity>> call, Response<List<LiveContentEntity>> response) {
                List<LiveContentEntity> entity = response.body();
                //直播全部信息
                LiveContentEntity contentEntity = entity.get(0);
                //entity.get(1)   ---是直播预告的轮播信息

                //直播的item信息
                List<LiveContentEntity.ItemEntity> item = contentEntity.getItem();
                //下拉刷新，清空数据
                if (page==1){
                    itemList.clear();

                }

                itemList.addAll(item);


                adapter.notifyDataSetChanged();

                page = contentEntity.getCurrentPage();

                //结束刷新
                refresh.refreshComplete();

            }

            @Override
            public void onFailure(Call<List<LiveContentEntity>> call, Throwable t) {
                //结束刷新
                refresh.refreshComplete();
            }
        });

    }


    //返回键的点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.activity_live_iv_back:
            {
                finish();
            }break;
        }


    }

    //点击listview_item
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String weburl = itemList.get(position-lv.getHeaderViewsCount()).getLink().getWeburl();
        Intent intent = new Intent(this,Live_lvContent_Activity.class);
        intent.putExtra(Constant.LIVE_LVCONTENT_URL,weburl);
        startActivity(intent);

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==SCROLL_STATE_IDLE&&isAddMore){
            page++;
            getLiveContentData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(firstVisibleItem+visibleItemCount==totalItemCount){
                isAddMore =true;
            }else{
                isAddMore = false;
            }
    }
}
