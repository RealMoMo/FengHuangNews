package momo.com.week10_project.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import momo.com.week10_project.R;
import momo.com.week10_project.entity.VideoEntity;
import momo.com.week10_project.utils.TimeUtils;

/**
 * videoitemfragment的listview adapter
 */
public class VideoItemAdapter extends BaseAdapter {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ViewHolder holder = (ViewHolder) msg.obj;
            if(player.isPlaying()){
                holder.tv_now.setText(TimeUtils.getVideoPlayTime(player.getCurrentPosition()));
                holder.seekBar.setProgress((int) (player.getCurrentPosition()*100f/player.getDuration()));
            }
            Message message = obtainMessage();
            message.obj = holder;
            sendMessageDelayed(message,1000);
        }
    };

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

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.pause();
                mp.stop();
                mp.reset();
                currentPostion= -1;
                VideoItemAdapter.this.notifyDataSetChanged();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.video_content_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.video_item_title);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.video_item_duration);
            viewHolder.tv_read = (TextView) convertView.findViewById(R.id.video_item_read);
            viewHolder.tv_comments = (TextView) convertView.findViewById(R.id.video_item_comments);
            viewHolder.tv_now = (TextView) convertView.findViewById(R.id.video_item_nowtime);
            viewHolder.tv_end = (TextView) convertView.findViewById(R.id.video_item_endtime);

            viewHolder.iv_thumb = (ImageView) convertView.findViewById(R.id.video_item_iv);
            viewHolder.iv_play = (ImageView) convertView.findViewById(R.id.video_item_play_iv);

            viewHolder.seekBar = (SeekBar) convertView.findViewById(R.id.video_item_seekbar);

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
            viewHolder.tv_now.setVisibility(View.VISIBLE);
            viewHolder.tv_now.setText("00:00");
            viewHolder.tv_end.setVisibility(View.VISIBLE);
            Message message = handler.obtainMessage();
//            message.obj = viewHolder.tv_now;
            message.obj = viewHolder;
            handler.sendMessage(message);
            viewHolder.tv_end.setText(TimeUtils.getVideoPlayTime(Integer.parseInt(list.get(position).getDuration())*1000));
            viewHolder.seekBar.setVisibility(View.VISIBLE);
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
            viewHolder.tv_now.setText("");
            viewHolder.tv_end.setText("");
            viewHolder.seekBar.setProgress(0);
            viewHolder.tv_now.setVisibility(View.INVISIBLE);
            viewHolder.tv_end.setVisibility(View.INVISIBLE);
            viewHolder.seekBar.setVisibility(View.INVISIBLE);
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

        viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //用户拖动时，改变视频播放进度
                if(fromUser){

                        player.seekTo(progress * player.getDuration() / 100);
                        viewHolder.tv_now.setText(TimeUtils.getVideoPlayTime(player.getCurrentPosition()));

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        return convertView;
    }

    class ViewHolder {
        TextView tv_title, tv_duration, tv_read, tv_comments,tv_now,tv_end;
        ImageView iv_thumb, iv_play;
        SurfaceView surfaceView;
        SeekBar seekBar;


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
