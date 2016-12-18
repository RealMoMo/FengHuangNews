package momo.com.week10_project.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import momo.com.week10_project.entity.NewsSportEntity;

/**
 * 新闻体育界面——体育类型icon布局
 */
public class SportNewsChannelIconView extends LinearLayout {



    public SportNewsChannelIconView(Context context) {
        super(context);
        init();
    }

    public SportNewsChannelIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);

    }


    public void setData(List<NewsSportEntity.ItemEntity> entity){

        if(entity==null){
            return;
        }

        int len = entity.size();

        //图片
        for (int i = 0; i < len; i++) {
            final NewsSportEntity.ItemEntity itemEntity  = entity.get(i);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight=1f;
            params.setMargins(0,20,0,0);
            ImageView iv = new ImageView(getContext());
            iv.setLayoutParams(params);
            //图片
           Glide.with(getContext()).load(itemEntity.getThumbnail()).into(iv);
            addView(iv);

            //点击事件(暂不实现)
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //url返回是json数据，要自己再自定义布局
//                    String url = itemEntity.getId();
                    //webUrl 点击没有显示网页
//                    String webUrl = itemEntity.getLink().getWeburl();

                }
            });
        }

    }
}
