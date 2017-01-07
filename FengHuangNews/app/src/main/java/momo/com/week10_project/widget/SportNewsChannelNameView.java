package momo.com.week10_project.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import momo.com.week10_project.entity.NewsSportEntity;

/**
 * 新闻体育界面——体育类型name布局
 */
public class SportNewsChannelNameView extends LinearLayout {



    public SportNewsChannelNameView(Context context) {
        super(context);
        init();
    }

    public SportNewsChannelNameView(Context context, AttributeSet attrs) {
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

        //文字
        for (int i = 0; i < len; i++) {
            final NewsSportEntity.ItemEntity itemEntity  = entity.get(i);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight=1f;
            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setText(itemEntity.getTitle());
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(params);
            addView(tv);

            //点击事件(暂不实现)
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //url返回是json数据，要自己再自定义布局
                    String url = itemEntity.getId();
                    //webUrl 点击没有显示网页
                    String webUrl = itemEntity.getLink().getWeburl();

                }
            });

        }

    }
}
