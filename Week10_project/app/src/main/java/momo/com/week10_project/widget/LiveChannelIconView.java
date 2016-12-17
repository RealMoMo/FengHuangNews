package momo.com.week10_project.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import momo.com.week10_project.entity.LiveChannelEntity;
import momo.com.week10_project.ui.Live_lvContent_Activity;
import momo.com.week10_project.utils.Constant;

/**
 * 直播界面——电视台icon布局
 */
public class LiveChannelIconView extends LinearLayout {



    public LiveChannelIconView(Context context) {
        super(context);
        init();
    }

    public LiveChannelIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);

    }


    public void setData(LiveChannelEntity entity){

        if(entity==null){
            return;
        }


        final List<LiveChannelEntity.LiveInfoEntity> liveInfo = entity.getLiveInfo();
        int len = liveInfo.size();
        //图片
        for (int i = 0; i < len; i++) {
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight=1f;
            params.setMargins(0,20,0,0);
            ImageView iv = new ImageView(getContext());
            iv.setLayoutParams(params);
            //图片
           Glide.with(getContext()).load(liveInfo.get(i).getBigIconURL()).into(iv);
            addView(iv);
            final int index = i;
            //点击事件
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //暂时跳到Live_lvContent_Activity
                    Intent intent = new Intent(getContext(), Live_lvContent_Activity.class);
                    intent.putExtra(Constant.LIVE_LVCONTENT_URL,liveInfo.get(index).getMUrl());
                    getContext().startActivity(intent);
                }
            });
        }





    }
}
