package momo.com.week10_project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.adapter.AbstractBaseAdapter;
import momo.com.week10_project.entity.NewsTopEntity;
import momo.com.week10_project.news_interface.NewsInterface;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.LogUtils;
import momo.com.week10_project.utils.ManagerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class NewsItemFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String title;

    private ListView lv;
    private AbstractBaseAdapter<NewsTopEntity.ItemEntity> adapter;

    //放专题和普通新闻的itemlist
    private List<NewsTopEntity.ItemEntity> totalList;
    //放普通新闻的itemlist
    private List<NewsTopEntity.ItemEntity> itemList;
    //放新闻轮播的itemlist
    private List<NewsTopEntity.ItemEntity> bannerList;



    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if(getArguments()!=null){
            title = getArguments().getString(Constant.MODULE_CONTENT);
        }

        initData();

        getNewsData();

    }



    //初始化数据源及adapter
    private void initData() {
        totalList = new ArrayList<>();
        itemList = new ArrayList<>();
        bannerList = new ArrayList<>();

        adapter = new AbstractBaseAdapter<NewsTopEntity.ItemEntity>(getActivity(),totalList,
                R.layout.news_content_layout1,R.layout.news_content_layout2,
                R.layout.news_content_layout3) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                NewsTopEntity.ItemEntity itemEntity = totalList.get(position);
                int type = 1;
                switch (type){
                    //单图布局
                    case 0:{
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv1_iv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv1_title);
                        tv_title.setText(itemEntity.getTitle());
                        //来源与时间(时间还没有优化处理)
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv1_source);
                        tv_source.setText(itemEntity.getSource()+"  "+itemEntity.getUpdateTime());
                    }break;
                    //多图布局
                    case 1:{
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
                        tv_source.setText(itemEntity.getSource()+"  "+itemEntity.getUpdateTime());
                    }break;
                    //专题布局
                    case 2:{
                        //图片
                        ImageView iv = (ImageView) holder.findViewById(R.id.news_lv3_topiv);
                        Glide.with(getActivity()).load(itemEntity.getThumbnail()).into(iv);
                        //标题
                        TextView tv_title = (TextView) holder.findViewById(R.id.news_lv3_title);
                        tv_title.setText(itemEntity.getTitle());
                        //来源与时间
                        TextView tv_source = (TextView) holder.findViewById(R.id.news_lv3_source);
                        tv_source.setText(itemEntity.getSource()+"  "+itemEntity.getUpdateTime());
                        //评论数
                        TextView tv_comments = (TextView) holder.findViewById(R.id.news_lv3_comments);
                        tv_comments.setText(itemEntity.getCommentsall());
                    }break;
                }
            }

            @Override
            public int getItemViewType(int position) {
                int tmpType = totalList.get(position).getViewType();


                return super.getItemViewType(position);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_top,container,false);
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

        //listview item点击事件
        lv.setOnItemClickListener(this);

    }


    private void getNewsData() {
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ManagerApi.BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        NewsInterface newsInterface = retrofit.create(NewsInterface.class);
        Call<List<NewsTopEntity>> call = newsInterface.getTopEntity("default");
        call.enqueue(new Callback<List<NewsTopEntity>>() {
            @Override
            public void onResponse(Call<List<NewsTopEntity>> call, Response<List<NewsTopEntity>> response) {
                List<NewsTopEntity> entity = response.body();
                for (int i = 0; i < entity.size(); i++) {
                    LogUtils.MyLog("entity:"+entity.size());
                    switch (entity.get(i).getType()){
                        //普通新闻
                        case "list":{
                            for (int j = 0; j < entity.get(i).getItem().size(); j++) {
                                entity.get(i).getItem().get(j).setViewType(0);
                            }
                            itemList.addAll(entity.get(i).getItem());
                            LogUtils.MyLog("itemList:"+itemList.size());
                        }break;
                        //轮播新闻
                        case "focus":{
                            bannerList.addAll(entity.get(i).getItem());
                            LogUtils.MyLog("bannerList:"+bannerList.size());
                        }break;
                        case "top":{
                            for (int j = 0; j < entity.get(i).getItem().size(); j++) {
                                entity.get(i).getItem().get(j).setViewType(2);
                            }
                            totalList.addAll(entity.get(i).getItem());
                            LogUtils.MyLog("totalList:"+totalList.size());
                        }break;
                    }
                }

                totalList.addAll(itemList);
                LogUtils.MyLog("totalList2:"+totalList.size());



            }

            @Override
            public void onFailure(Call<List<NewsTopEntity>> call, Throwable t) {

            }
        });
    }

    //listview item的点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
