package momo.com.week10_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 自定义抽象万能 BaseAdapter
 * 可传入任意数据源、布局
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter{
    //数据源
    List<T> data;
    //LayoutInflater
    LayoutInflater inflater;
    //布局资源
    int[] layoutId;

    //构造方法
    public AbstractBaseAdapter(Context context,  List<T> data ,int ...layoutId) {
        this.layoutId = layoutId;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //重写getViewTypeCount(因为可能传入的布局有多个)
    //返回Listview布局种类个数，即布局资源id数组的长度
    @Override
    public int getViewTypeCount() {
        return layoutId.length;
    }

    //抽象绑定数据的方法
    public abstract void bindData(int position, ViewHolder holder);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //得到当前数据布局类型
        int layoutType = getItemViewType(position);
        if (convertView == null) {
            //根据类型加载不同的布局
            convertView = inflater.inflate(layoutId[layoutType], parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        bindData(position, holder);


        return convertView;
    }

    public static class ViewHolder {
        //保存的控件：是需要设置值的控件
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }


        //向子类提供一个方法，返回需要设置值的控件
        public View findViewById(int viewId) {
            //根据viewid，找到对应的控件
            return view.findViewById(viewId);
        }

    }

}
