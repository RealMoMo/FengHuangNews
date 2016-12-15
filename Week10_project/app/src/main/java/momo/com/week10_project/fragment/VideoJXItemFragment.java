package momo.com.week10_project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import momo.com.week10_project.R;
import momo.com.week10_project.adapter.VideoItemAdapter;
import momo.com.week10_project.entity.VideoEntity;
import momo.com.week10_project.news_interface.VideoInterface;
import momo.com.week10_project.utils.ManagerApi;
import momo.com.week10_project.utils.TimeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 视频精选
 */
public class VideoJXItemFragment extends Fragment implements AbsListView.OnScrollListener {


    private int page=1;
    //标识：是否初次进入，针对刷新头
    private boolean flag = true;
    private boolean isAddMore = false;

    private PtrClassicFrameLayout refresh;
    private ListView lv;

    public VideoItemAdapter adapter;
    //所有视频内容的集合
    private List<VideoEntity.ItemEntity> totalList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initData();
    }

    private void initData() {
        totalList = new ArrayList<>();

        adapter = new VideoItemAdapter(getContext(),totalList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.video_item,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews(view);
    }

    private void setupViews(View view) {
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.video_item_refresh);
        lv = (ListView) view.findViewById(R.id.video_item_lv);
        lv.setAdapter(adapter);


        lv.setOnScrollListener(this);

        refresh.setLastUpdateTimeRelateObject(this);
        if(flag==true){
            refresh.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int width = refresh.getWidth();
                    if(width>0){
                        refresh.autoRefresh(true);
                        refresh.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }

        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                flag =false;
                page = 1;
                //获取数据
                getVideoData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, lv, header);
            }
        });
    }

    private void getVideoData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ManagerApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VideoInterface videoInterface = retrofit.create(VideoInterface.class);
        Call<List<VideoEntity>> call = videoInterface.getVideoEntity(page);
        call.enqueue(new Callback<List<VideoEntity>>() {
            @Override
            public void onResponse(Call<List<VideoEntity>> call, Response<List<VideoEntity>> response) {
                List<VideoEntity.ItemEntity> entityList = response.body().get(0).getItem();
                if(page==1){
                    totalList.clear();
                }
                //调整播放次数格式(大于1万的才调整)
                for (VideoEntity.ItemEntity i : entityList) {
                    String playTime = TimeUtils.getPlayTime(i.getPlayTime());
                    if(playTime!=null){
                        i.setPlayTime(playTime);
                    }
                }
                totalList.addAll(entityList);
                adapter.notifyDataSetChanged();


                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<List<VideoEntity>> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }



    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(isAddMore&& scrollState == SCROLL_STATE_IDLE){
            page++;
            getVideoData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem+ visibleItemCount == totalItemCount){
            isAddMore =true;
        }else{
            isAddMore = false;
        }
    }



}
