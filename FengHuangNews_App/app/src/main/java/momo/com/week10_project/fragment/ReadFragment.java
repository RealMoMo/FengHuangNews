package momo.com.week10_project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
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
import momo.com.week10_project.entity.ReadContentEntity;
import momo.com.week10_project.news_interface.ReadInterface;
import momo.com.week10_project.utils.LogUtils;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 发现fragment  ---未实现listview item点击事件。 此点击事件的数据要自己解析,接口返回的url访问无效
 */
public class ReadFragment extends Fragment implements AbsListView.OnScrollListener {

    private final String ACTION_UP = "up";
    private final String ACTION_DOWN = "down";
    //默认up
    private String action = ACTION_DOWN;
    //标识：是否初次进入，针对刷新头
    private boolean flag = true;
    //标识：是否加载更多
    private boolean isAddMore;

    private PtrClassicFrameLayout refresh;
    private ListView lv;

    private List<ReadContentEntity.ItemEntity> totalList;
    private List<ReadContentEntity.ItemEntity> tempList;

    private AbstractBaseAdapter<ReadContentEntity.ItemEntity> adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {

        totalList = new ArrayList<>();
        tempList = new ArrayList<>();

        adapter = new AbstractBaseAdapter<ReadContentEntity.ItemEntity>(getActivity(), totalList,
                R.layout.read_content_layout1, R.layout.read_content_layout2, R.layout.read_content_layout3) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                ReadContentEntity.ItemEntity itemEntity = totalList.get(position);
                int viewType = itemEntity.getViewType();
                switch (viewType){
                    //单图
                    case 0:{
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.read_lv1_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.read_lv1_title);
                        tv_title.setText(itemEntity.getTitle());
                        //分类
                        TextView tv_recommendChannel = (TextView) holder.findViewById(R.id.read_lv1_recommendChannel);
                        tv_recommendChannel.setText(itemEntity.getRecommendChannel()==null?"":itemEntity.getRecommendChannel().getName());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.read_lv1_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }break;
                    //多图
                    case 1:{
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.read_lv2_title);
                        tv_title.setText(itemEntity.getTitle());
                        //图片
                        ImageView iv1 = (ImageView) holder.findViewById(R.id.read_lv2_iv1);
                        ImageView iv2 = (ImageView) holder.findViewById(R.id.read_lv2_iv2);
                        ImageView iv3 = (ImageView) holder.findViewById(R.id.read_lv2_iv3);
                        List<String> images = itemEntity.getStyle().getImages();
                        Glide.with(getActivity()).load(images.get(0)).into(iv1);
                        Glide.with(getActivity()).load(images.get(1)).into(iv2);
                        Glide.with(getActivity()).load(images.get(2)).into(iv3);
                        //分类
                        TextView tv_recommendChannel = (TextView) holder.findViewById(R.id.read_lv2_recommendChannel);
                        tv_recommendChannel.setText(itemEntity.getRecommendChannel().getName());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.read_lv2_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }break;
                    //纯文字
                    case 2:{
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.read_lv3_title);
                        tv_title.setText(itemEntity.getTitle());
                        //分类
                        TextView tv_recommendChannel = (TextView) holder.findViewById(R.id.read_lv3_recommendChannel);
                        tv_recommendChannel.setText(itemEntity.getRecommendChannel().getName());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.read_lv3_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }break;
                }


            }

            @Override
            public int getItemViewType(int position) {
                return totalList.get(position).getViewType();
            }
        };


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    private void setupViews(View view) {
        lv = (ListView) view.findViewById(R.id.read_lv);
        lv.setAdapter(adapter);
        lv.setOnScrollListener(this);

        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.read_refresh);
        refresh.setLastUpdateTimeRelateObject(this);
        if (flag == true) {
            refresh.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int width = refresh.getWidth();
                    if (width > 0) {
                        refresh.autoRefresh(true);
                        //api>=16
                        refresh.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
        }

        //刷新头的监听
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                flag = false;
                //获取数据
                getReadContentData();

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, lv, header);
            }
        });

    }

    private void getReadContentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ManagerApi.FIND_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReadInterface readInterface = retrofit.create(ReadInterface.class);
        Call<ReadContentEntity> call = readInterface.getReadContent(action);
        call.enqueue(new Callback<ReadContentEntity>() {
            @Override
            public void onResponse(Call<ReadContentEntity> call, Response<ReadContentEntity> response) {
                List<ReadContentEntity.ItemEntity> itemEntities = response.body().getItem();

                tempList.clear();
                for (ReadContentEntity.ItemEntity i : itemEntities) {
                    //纯文字
                    if (i.getThumbnail() == null || i.getThumbnail().equals("")) {
                        i.setViewType(2);
                    }
                    //多图
                    else if (i.getStyle() != null&&i.getStyle().getImages()!=null) {
                        i.setViewType(1);
                    }
                    //单图
                    else if (i.getThumbnail() != null &&i.getStyle() != null&&i.getStyle().getTag() != null) {
                        i.setViewType(0);
                    }
                }

                tempList.addAll(itemEntities);
                if (action == ACTION_UP) {
                    totalList.addAll(tempList);
                } else if (action == ACTION_DOWN) {
                    tempList.addAll(totalList);
                    totalList.clear();
                    totalList.addAll(tempList);
                }

                adapter.notifyDataSetChanged();

                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<ReadContentEntity> call, Throwable t) {
                LogUtils.MyLog("onFailure");
                refresh.refreshComplete();
            }
        });


    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore && scrollState == SCROLL_STATE_IDLE) {
            action = ACTION_UP;
            getReadContentData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isAddMore = true;
        } else {
            isAddMore = false;
        }
    }
}
