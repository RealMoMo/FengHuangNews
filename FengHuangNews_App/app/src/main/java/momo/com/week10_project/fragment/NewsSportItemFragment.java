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
import momo.com.week10_project.entity.NewsSportEntity;
import momo.com.week10_project.news_interface.NewsInterface;
import momo.com.week10_project.ui.NewsTopContent_Activity;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.ManagerApi;
import momo.com.week10_project.utils.TimeUtils;
import momo.com.week10_project.widget.BannerView;
import momo.com.week10_project.widget.SportNewsChannelIconView;
import momo.com.week10_project.widget.SportNewsChannelNameView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 新闻--体育fragment
 */
public class NewsSportItemFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    //当前时间的月日
    private String currentData;
    //标识：已加载的页数
    private int page = 1;
    //标识：是否初次进入，针对刷新头
    private boolean flag = true;
    //标识：是否加载更多
    private boolean isAddMore = false;
    private PtrClassicFrameLayout refresh;
    private ListView lv;
    //新闻轮播View
    private BannerView<NewsSportEntity.ItemEntity> bannerView;
    //体育项目的iconView
    private SportNewsChannelIconView iconView;
    //体育项目的nameView
    private SportNewsChannelNameView nameView;
    //adapter
    private AbstractBaseAdapter<NewsSportEntity.ItemEntity> adapter;

    //所有的itemlist
    private List<NewsSportEntity.ItemEntity> totalList;
    //体育种类的itemlist
    private List<NewsSportEntity.ItemEntity> channelList;
    //放新闻轮播的itemlist
    private List<NewsSportEntity.ItemEntity> bannerList;


    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        initData();

        iconView = new SportNewsChannelIconView(getActivity());
        nameView = new SportNewsChannelNameView(getActivity());

    }


    //初始化数据源及adapter
    private void initData() {
        totalList = new ArrayList<>();
        channelList = new ArrayList<>();
        bannerList = new ArrayList<>();

        adapter = new AbstractBaseAdapter<NewsSportEntity.ItemEntity>(getActivity(), totalList,
                R.layout.news_content_layout5, R.layout.news_content_layout6,
                R.layout.news_content_layout3,R.layout.news_content_layout5) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                NewsSportEntity.ItemEntity itemEntity = totalList.get(position);
                int type = totalList.get(position).getViewType();
                switch (type) {
                    //带来源单图布局
                    case 0: {
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv5_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv5_title);
                        tv_title.setText(itemEntity.getTitle());
                        //来源与时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv5_source);
                        tv_source.setText(itemEntity.getSource() + "  " + getUpdateTime(itemEntity.getUpdateTime()));
                        //评论
                        TextView tv_comment = (TextView) holder.findViewById(R.id.news_lv5_comments);
                        tv_comment.setText( itemEntity.getCommentsall());
                    }
                    break;
                    //多图布局
                    case 1: {
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv6_title);
                        tv_title.setText(itemEntity.getTitle());
                        //图片
                        List<String> imagesUrl = itemEntity.getStyle().getImages();
                        ImageView iv1 = (ImageView) holder.findViewById(R.id.news_lv6_iv1);
                        ImageView iv2 = (ImageView) holder.findViewById(R.id.news_lv6_iv2);
                        ImageView iv3 = (ImageView) holder.findViewById(R.id.news_lv6_iv3);
                        Glide.with(getActivity()).load(imagesUrl.get(0)).into(iv1);
                        Glide.with(getActivity()).load(imagesUrl.get(1)).into(iv2);
                        Glide.with(getActivity()).load(imagesUrl.get(2)).into(iv3);
                        //时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv6_source);
                        tv_source.setText( getUpdateTime(itemEntity.getUpdateTime()));
                        //评论
                        TextView tv_comment = (TextView) holder.findViewById(R.id.news_lv6_comments);
                        tv_comment.setText( itemEntity.getCommentsall());
                    }
                    break;
                    //专题布局
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
                    //不带来源单图布局
                    case 3: {
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv5_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv5_title);
                        tv_title.setText(itemEntity.getTitle());
                        //时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv5_source);
                        tv_source.setText(getUpdateTime(itemEntity.getUpdateTime()));
                        //评论
                        TextView tv_comment = (TextView) holder.findViewById(R.id.news_lv5_comments);
                        tv_comment.setText( itemEntity.getCommentsall());
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
        if(bannerList.size()>0){
            bannerView = new BannerView<NewsSportEntity.ItemEntity>(getActivity(),bannerList) {
                @Override
                public void bindData(Banner banner, List<NewsSportEntity.ItemEntity> list) {
                    List<String> imgUrls = new ArrayList<String>();
                    List<String> titles = new ArrayList<String>();

                    for (NewsSportEntity.ItemEntity i : list){
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
            lv.addHeaderView(iconView,null,false);
            lv.addHeaderView(nameView,null,false);
        }
        //这样直接加有bug,listview最上面留了空白  (不懂),所以把iconView,nameView放上面加
//        if(iconView!=null){
//            lv.addHeaderView(iconView,null,false);
//        }
//        if(nameView!=null){
//            lv.addHeaderView(nameView,null,false);
//        }

        //listview item点击事件
        lv.setOnItemClickListener(this);
        //listview 滚动监听
        lv.setOnScrollListener(this);

        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.news_refresh);
        refresh.setLastUpdateTimeRelateObject(this);
        //初次进来，自动刷新
        if(flag == true) {
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
                page =1;
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

        Call<List<NewsSportEntity>> call = newsInterface.getSportEntity(page);
        call.enqueue(new Callback<List<NewsSportEntity>>() {
            @Override
            public void onResponse(Call<List<NewsSportEntity>> call, Response<List<NewsSportEntity>> response) {

                List<NewsSportEntity> entity = response.body();
                for (int i = 0; i < entity.size(); i++) {
                    switch (entity.get(i).getType()) {
                        //普通新闻
                        case "list": {
                            //头部刷新，清空数据
                            if(page==1&&totalList.size()>0){
                                totalList.clear();
                            }
                            for (int j = 0; j < entity.get(i).getItem().size(); j++) {
                                //带来源的单图/多图
                                if (entity.get(i).getItem().get(j).getSource() != null) {
                                    entity.get(i).getItem().get(j).setViewType(0);
                                }
                                //多图
                                else if (entity.get(i).getItem().get(j).getStyle() != null) {
                                    entity.get(i).getItem().get(j).setViewType(1);
                                }
                                //专题
                                else if(entity.get(i).getItem().get(j).getStyleType()!=null &&entity.get(i).getItem().get(j).getStyleType().equals("tytopic")){
                                    entity.get(i).getItem().get(j).setViewType(2);
                                }
                                //单图
                                else {
                                    entity.get(i).getItem().get(j).setViewType(3);
                                }


                            }
                            totalList.addAll(entity.get(i).getItem());
                        }
                        break;
                        //轮播新闻
                        case "focus": {
                            if(bannerList.size()>0){
                                continue;
                            }
                            bannerList.addAll(entity.get(i).getItem());
                            bannerView = new BannerView<NewsSportEntity.ItemEntity>(getActivity(),bannerList) {
                                @Override
                                public void bindData(Banner banner, List<NewsSportEntity.ItemEntity> list) {
                                    List<String> imgUrls = new ArrayList<String>();
                                    List<String> titles = new ArrayList<String>();

                                    for (NewsSportEntity.ItemEntity i : list){
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
                        //体育项目
                        case "tytopic": {
                            if(channelList.size()>0){
                                continue;
                            }

                            channelList.addAll(entity.get(i).getItem());
                            iconView.setData(channelList);
                            nameView.setData(channelList);

                            lv.addHeaderView(iconView,null,false);
                            lv.addHeaderView(nameView,null,false);

                        }
                        break;
                    }
                }

                //获取当前时间
                currentData = null;
                currentData = TimeUtils.getCurrentTime();


                adapter.notifyDataSetChanged();

                refresh.refreshComplete();


        }

            @Override
            public void onFailure(Call<List<NewsSportEntity>> call, Throwable t) {
                refresh.refreshComplete();

            }
        });

    }

    //listview item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String weburl = totalList.get(position-lv.getHeaderViewsCount()).getLink().getWeburl();
        Intent intent = new Intent(getActivity(),NewsTopContent_Activity.class);
        intent.putExtra(Constant.ITEM_WEBURL,weburl);
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
            page++;
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
        lv.removeHeaderView(iconView);
        lv.removeHeaderView(nameView);
    }


}
