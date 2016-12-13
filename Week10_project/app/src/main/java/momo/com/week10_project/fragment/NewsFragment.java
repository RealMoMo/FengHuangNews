package momo.com.week10_project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.adapter.NewsViewPagerAdapter;
import momo.com.week10_project.ui.LiveActivity;
import momo.com.week10_project.ui.SearchActivity;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
//fragment切换时，重走onDestroyView oncreateView onViewCreate
public class NewsFragment extends Fragment implements View.OnClickListener {

    private String[] titles={
            "头条","体育","热点","娱乐","财经","凤凰","科技","社会"
    };

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    private NewsViewPagerAdapter adapter;

    private ImageView iv_live,iv_search;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initNewsItemFragment();
        adapter = new NewsViewPagerAdapter(getContext(),getChildFragmentManager(),fragmentList,titles);

    }

    private void initNewsItemFragment() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Fragment fragment;
            if(i%2==0) {
                fragment = new NewsItemFragment();
            }else{
                fragment = new NewsSportItemFragment();
            }
            fragmentList.add(fragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //初始化控件
    private void setupViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.news_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
        iv_live = (ImageView) view.findViewById(R.id.news_live);
        iv_search = (ImageView) view.findViewById(R.id.news_search);

        iv_live.setOnClickListener(this);
        iv_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.news_live:{
                //直播界面
                Intent intent = new Intent(getActivity(), LiveActivity.class);
                startActivity(intent);
            }break;
            //搜索界面
            case R.id.news_search:{
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }break;
        }

    }
}
