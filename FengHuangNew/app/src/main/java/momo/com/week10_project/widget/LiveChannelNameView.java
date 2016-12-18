package momo.com.week10_project.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import momo.com.week10_project.entity.LiveChannelEntity;
import momo.com.week10_project.ui.Live_lvContent_Activity;
import momo.com.week10_project.utils.Constant;

/**
 * 直播界面——电视台name布局
 */
public class LiveChannelNameView extends LinearLayout {



    public LiveChannelNameView(Context context) {
        super(context);
        init();
    }

    public LiveChannelNameView(Context context, AttributeSet attrs) {
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
        //文字
        for (int i = 0; i < len; i++) {
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight=1f;
            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setText(liveInfo.get(i).getCName());
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(params);
            addView(tv);

            final int index = i;
            //点击事件
            tv.setOnClickListener(new OnClickListener() {
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
