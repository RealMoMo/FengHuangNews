package momo.com.week10_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import momo.com.week10_project.R;
import momo.com.week10_project.adapter.AbstractBaseAdapter;
import momo.com.week10_project.entity.NewsTopEntity;
import momo.com.week10_project.news_interface.NewsInterface;
import momo.com.week10_project.ui.NewsTopContent_Activity;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.ManagerApi;
import momo.com.week10_project.utils.TimeUtils;
import momo.com.week10_project.widget.BannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class NewsItemFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    //当前时间的月日
    private String currentData;
    //标识：初次进入default，上拉加载更多up，下拉刷新down
    private String action = "default";
    //标识：是否初次进入，针对刷新头
    private boolean flag = true;
    //标识：是否加载更多
    private boolean isAddMore = false;
    private PtrClassicFrameLayout refresh;
    private ListView lv;
    //新闻轮播View
    private BannerView<NewsTopEntity.ItemEntity> bannerView;
    private AbstractBaseAdapter<NewsTopEntity.ItemEntity> adapter;

    //所有的itemlist
    private List<NewsTopEntity.ItemEntity> totalList;
    //放专题的itemlist
    private List<NewsTopEntity.ItemEntity> topList;
    //只放普通新闻的itemlist
    private List<NewsTopEntity.ItemEntity> itemList;
    //放新闻轮播的itemlist
    private List<NewsTopEntity.ItemEntity> bannerList;


    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);


        initData();




    }


    //初始化数据源及adapter
    private void initData() {
        totalList = new ArrayList<>();
        itemList = new ArrayList<>();
        topList = new ArrayList<>();
        bannerList = new ArrayList<>();

        adapter = new AbstractBaseAdapter<NewsTopEntity.ItemEntity>(getActivity(), totalList,
                R.layout.news_content_layout1, R.layout.news_content_layout2,
                R.layout.news_content_layout3, R.layout.news_content_layout4) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                NewsTopEntity.ItemEntity itemEntity = totalList.get(position);
                int type = totalList.get(position).getViewType();
                switch (type) {
                    //单图布局或不置顶的专题
                    case 0: {
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv1_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv1_title);
                        tv_title.setText(itemEntity.getTitle());
                        //来源与时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv1_source);
                        if (itemEntity.getSource()!=null) {
                            tv_source.setText(itemEntity.getSource() + "  " + getUpdateTime(itemEntity.getUpdateTime()));
                        }else{
                            //不置顶的专题
                            itemEntity.setTitle(Constant.Top_TITLE);
                            tv_source.setText(itemEntity.getTitle());
                        }
                    }
                    break;
                    //多图布局
                    case 1: {
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv2_title);
                        tv_title.setText(itemEntity.getTitle());
                        //图片
                        List<String> imagesUrl = itemEntity.getStyle().getImages();
                        ImageView iv1 = (ImageView) holder.findViewById(R.id.news_lv2_iv1);
                        ImageView iv2 = (ImageView) holder.findViewById(R.id.news_lv2_iv2);
                        ImageView iv3 = (ImageView) holder.findViewById(R.id.news_lv2_iv3);
                        Glide.with(getActivity()).load(imagesUrl.get(0)).into(iv1);
                        Glide.with(getActivity()).load(imagesUrl.get(1)).into(iv2);
                        Glide.with(getActivity()).load(imagesUrl.get(2)).into(iv3);
                        //来源与时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv2_source);
                        tv_source.setText(itemEntity.getSource() + "  " + getUpdateTime(itemEntity.getUpdateTime()));
                    }
                    break;
                    //专题布局(置顶)
                    case 2: {
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv3_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv3_title);
                        tv_title.setText(itemEntity.getTitle());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.news_lv3_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }
                    break;
                    //视频布局
                    case 3: {
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv4_title);
                        tv_title.setText(itemEntity.getTitle());
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv4_iv_thumb);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //视频时长
                        TextView tv_videoTime = (TextView) holder.findViewById(R.id.news_lv4_tv_videotime);
                        tv_videoTime.setText(TimeUtils.getVideoTime(itemEntity.getPhvideo().getLength()));
                        //来源
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv4_source);
                        tv_source.setText(itemEntity.getPhvideo().getChannelName());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.news_lv4_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }
                }
            }

            @Override
            public int getItemViewType(int position) {
                int tmpType = totalList.get(position).getViewType();


                return tmpType;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_item, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
        setupViews(view);
    }

    private void setupViews(View view) {
        lv = (ListView) view.findViewById(R.id.newstop_lv);
        lv.setAdapter(adapter);
        //还是首次加载数据，viewpager切换回来，广告保留(若用回来的bannerView切换不流畅，所以在ondestoryView将bannerView=null)
        if(bannerList.size()>0&& action.equals("default")){

            bannerView = new BannerView<NewsTopEntity.ItemEntity>(getActivity(),bannerList) {
                @Override
                public void bindData(Banner banner, List<NewsTopEntity.ItemEntity> list) {

                    List<String> imgUrls = new ArrayList<String>();
                    List<String> titles = new ArrayList<String>();

                    for (NewsTopEntity.ItemEntity i : list) {
                        imgUrls.add(i.getThumbnail());
                        titles.add(i.getTitle());
                    }

                    //设置banner的图片集合及标题集合
                    banner.setImages(imgUrls);
                    banner.setBannerTitles(titles);
                    //banner配置完成，开始轮播
                    banner.start();
                }
            };
            lv.addHeaderView(bannerView,null,false);

        }
        //listview item点击事件
        lv.setOnItemClickListener(this);
        //listview 滚动监听
        lv.setOnScrollListener(this);

        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.news_refresh);
        refresh.setLastUpdateTimeRelateObject(this);
        //初次进来，自动刷新
        if(flag ==true) {

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
                //判断是首次还是人为的下拉刷新
                action = flag == true ? "default" : "down";
                flag = false;
                //获取数据
                getNewsData();

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, lv, header);
            }
        });

    }


    private void getNewsData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ManagerApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsInterface newsInterface = retrofit.create(NewsInterface.class);

        Call<List<NewsTopEntity>> call = newsInterface.getTopEntity(action);
        call.enqueue(new Callback<List<NewsTopEntity>>() {
            @Override
            public void onResponse(Call<List<NewsTopEntity>> call, Response<List<NewsTopEntity>> response) {
                //非首次加载数据，移除广告
                if(bannerView!=null&& !action.equals("default")){
                    lv.removeHeaderView(bannerView);
                    bannerView =null;
                }
                List<NewsTopEntity> entity = response.body();
                for (int i = 0; i < entity.size(); i++) {
                    switch (entity.get(i).getType()) {
                        //普通新闻
                        case "list": {
                            //人为下拉刷新
                            if (action.equals("down")) {
                                itemList.clear();
                                //去掉专题
                                    if(totalList.size()>0) {
                                        if (totalList.get(0).getViewType() == 2) {
                                            totalList.remove(0);
                                        }
                                    }

                            }
                            for (int j = 0; j < entity.get(i).getItem().size(); j++) {
                                //单图
                                if (entity.get(i).getItem().get(j).getStyle() == null && entity.get(i).getItem().get(j).getPhvideo() == null) {
                                    entity.get(i).getItem().get(j).setViewType(0);
                                }
                                //多图
                                else if (entity.get(i).getItem().get(j).getStyle() != null) {
                                    entity.get(i).getItem().get(j).setViewType(1);
                                }
                                //视频
                                else {
                                    entity.get(i).getItem().get(j).setViewType(3);
                                }
                            }
                            //下拉刷新，最新数据放到
                            if (action.equals("down")) {
                                itemList.addAll(entity.get(i).getItem());
                                itemList.addAll(totalList);
                            } else {
                                itemList.addAll(entity.get(i).getItem());
                            }
                        }
                        break;
                        //轮播新闻
                        case "focus": {
                            bannerList.addAll(entity.get(i).getItem());
                            bannerView = new BannerView<NewsTopEntity.ItemEntity>(getActivity(),bannerList) {
                                @Override
                                public void bindData(Banner banner, List<NewsTopEntity.ItemEntity> list) {

                                    List<String> imgUrls = new ArrayList<String>();
                                    List<String> titles = new ArrayList<String>();

                                    for (NewsTopEntity.ItemEntity i : list){
                                        imgUrls.add(i.getThumbnail());
                                        titles.add(i.getTitle());
                                    }

                                    //设置banner的图片集合及标题集合
                                    banner.setImages(imgUrls);
                                    banner.setBannerTitles(titles);
                                    //banner配置完成，开始轮播
                                    banner.start();
                                }
                            };

                            lv.addHeaderView(bannerView,null,false);


                        }
                        break;
                        //专题新闻
                        case "top": {
                            for (int j = 0; j < entity.get(i).getItem().size(); j++) {
                                entity.get(i).getItem().get(j).setViewType(2);
                            }
                            topList.addAll(entity.get(i).getItem());

                        }
                        break;
                    }
                }

                //获取当前时间
                currentData = null;
                currentData = TimeUtils.getCurrentTime();


                totalList.clear();
                if (action.equals("default")) {
                    totalList.addAll(topList);
                }
                totalList.addAll(itemList);
                adapter.notifyDataSetChanged();

                refresh.refreshComplete();


            }

            @Override
            public void onFailure(Call<List<NewsTopEntity>> call, Throwable t) {
                refresh.refreshComplete();
            }
        });
    }

    //listview item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String itemId = totalList.get(position-lv.getHeaderViewsCount()).getId();
//        String commentsUrl = totalList.get(position-lv.getHeaderViewsCount()).getCommentsUrl();
//        int viewType = totalList.get(position-lv.getHeaderViewsCount()).getViewType();
//        String title = totalList.get(position-lv.getHeaderViewsCount()).getTitle();
        String webUrl = totalList.get(position-lv.getHeaderViewsCount()).getLink().getWeburl();
        Intent intent = new Intent(getActivity(),NewsTopContent_Activity.class);
//        intent.putExtra(Constant.ITEM_ID,itemId);
//        intent.putExtra(Constant.ITEM_COMMENTSURL,commentsUrl);
//        intent.putExtra(Constant.ITEM_VIEWTYPE,viewType);
//        intent.putExtra(Constant.ITEM_TITLE,title);
        intent.putExtra(Constant.ITEM_WEBURL,webUrl);
        startActivity(intent);
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


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore && scrollState == SCROLL_STATE_IDLE) {
            action = "up";
            getNewsData();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(bannerView!=null) {
            lv.removeHeaderView(bannerView);
            bannerView = null;
        }
    }


}
