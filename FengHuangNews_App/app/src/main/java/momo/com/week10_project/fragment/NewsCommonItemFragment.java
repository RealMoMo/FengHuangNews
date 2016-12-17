package momo.com.week10_project.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import momo.com.week10_project.R;
import momo.com.week10_project.adapter.AbstractBaseAdapter;
import momo.com.week10_project.widget.BannerView;

/**
 * 相同数据类型共用的fragment模版
 */
public class NewsCommonItemFragment extends Fragment{


    //当前时间的月日
    private String currentData;
    //
    private int page =1;
    //标识：是否初次进入，针对刷新头
    private boolean flag = true;
    //标识：是否加载更多
    private boolean isAddMore = false;
    private PtrClassicFrameLayout refresh;
    private ListView lv;
    //新闻轮播View
    private BannerView<?> bannerView;
    private AbstractBaseAdapter<?> adapter;
    //所有的itemlist
    private List<?> totalList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化集合、adapter、ListView的headview
        initData();
    }

    private void initData() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        
    }
}
