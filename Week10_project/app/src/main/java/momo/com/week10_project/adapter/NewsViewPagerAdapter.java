package momo.com.week10_project.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
public class NewsViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Context context;
    private List<Fragment> list;

    public NewsViewPagerAdapter(Context context,FragmentManager fm, List<Fragment> list, String[] titles ) {
        super(fm);
        this.context = context;
        this.list = list;
        this.titles= titles;

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
