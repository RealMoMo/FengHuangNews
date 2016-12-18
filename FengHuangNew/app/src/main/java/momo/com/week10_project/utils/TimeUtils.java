package momo.com.week10_project.utils;


import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class TimeUtils {

    /**
     * 获取视频时长：x分x秒:格式x'x'' 或 x''
     * @param sourceTime
     * @return
     */
    public static String getVideoTime(String sourceTime){
        int time = Integer.parseInt(sourceTime);
        int min = time/60;
        int second = time-60*min;
        if(min == 0){
            return sourceTime+"''";
        }else{
            return min+"'"+(second==0?"":second+"''");
        }

    }

    /**
     * 获取当前的月日
     * @return
     */
    public static String getCurrentTime(){
        Time time = new Time("GMT+8");
        time.setToNow();
        int month = time.month+1;
        int monthDay = time.monthDay;

        return month+"/"+monthDay;
    }


    /**
     * 获取视频的播放量
     * @param playtime
     * @return
     */
    public static String getPlayTime(String playtime){
        int time = Integer.parseInt(playtime);
        int a = time/10000;
        int b = (time-10000*a)/1000;
        if(a == 0){
            return null;
        }else{
            return a+"."+b+"万";
        }

    }


    /**
     * 获取视频播放时间  len单位：毫秒
     *
     * @param len
     * @return
     */
    public static String getVideoPlayTime(long len){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(len);
        String time = dateFormat.format(date);

        return time;
    }




}
