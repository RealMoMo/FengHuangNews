package momo.com.week10_project.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.entity.VideoEntity;
import momo.com.week10_project.utils.TimeUtils;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class VideoItemAdapter extends BaseAdapter {



    private List<VideoEntity.ItemEntity> list;
    private Context context;
    private LayoutInflater inflater;

    private  MediaPlayer player;
    private  int currentPostion = -1;

    public VideoItemAdapter(Context context, List<VideoEntity.ItemEntity> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);

        initMediaPlayer();

    }

    private void initMediaPlayer() {
        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(currentPostion!=-1) {
                    mp.start();
                }else{
                    mp.reset();
                }
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.video_content_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.video_item_title);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.video_item_duration);
            viewHolder.tv_read = (TextView) convertView.findViewById(R.id.video_item_read);
            viewHolder.tv_comments = (TextView) convertView.findViewById(R.id.video_item_comments);

            viewHolder.iv_thumb = (ImageView) convertView.findViewById(R.id.video_item_iv);
            viewHolder.iv_play = (ImageView) convertView.findViewById(R.id.video_item_play_iv);


            viewHolder.surfaceView = (SurfaceView) convertView.findViewById(R.id.video_item_surfaceview);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tv_title.setText(list.get(position).getTitle());
        viewHolder.tv_duration.setText(TimeUtils.getVideoTime(list.get(position).getDuration()));
        viewHolder.tv_read.setText(list.get(position).getPlayTime());
        viewHolder.tv_comments.setText(list.get(position).getCommentsall());

        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.iv_thumb);


        Object tag = viewHolder.iv_play.getTag();
        if (tag != null) {
            Integer tag1 = (Integer) tag;
            if (tag1 == currentPostion && tag1 != position) {
                player.stop();
                currentPostion = -1;
            }
        }

        viewHolder.iv_play.setTag(position);
        if (position == currentPostion) {
            viewHolder.iv_play.setVisibility(View.INVISIBLE);
            viewHolder.iv_thumb.setVisibility(View.INVISIBLE);
            viewHolder.tv_duration.setVisibility(View.INVISIBLE);
            viewHolder.tv_title.setVisibility(View.INVISIBLE);
            viewHolder.surfaceView.setVisibility(View.VISIBLE);
            player.reset();
            try {
                player.setDisplay(viewHolder.surfaceView.getHolder());
                player.setDataSource(list.get(position).getVideo_url());
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            viewHolder.iv_play.setVisibility(View.VISIBLE);
            viewHolder.iv_thumb.setVisibility(View.VISIBLE);
            viewHolder.tv_duration.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setVisibility(View.VISIBLE);
            viewHolder.surfaceView.setVisibility(View.INVISIBLE);
        }

        viewHolder.iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer tag = (Integer) v.getTag();
                currentPostion = tag;
                if (player != null && player.isPlaying()) {
                    player.stop();
                }
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    class ViewHolder {
        TextView tv_title, tv_duration, tv_read, tv_comments;
        ImageView iv_thumb, iv_play;
        SurfaceView surfaceView;


    }


    public   MediaPlayer getMediaPlayer() {
        if (player != null) {
            return player;
        }

        return null;
    }

    public  void setCurrentPlayerPosition(int postion) {
        currentPostion = postion;
    }
}
