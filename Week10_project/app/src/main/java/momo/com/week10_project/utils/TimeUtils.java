package momo.com.week10_project.utils;


import android.text.format.Time;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class TimeUtils {

    /**
     * 获取视频时长：x分x秒
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


}
