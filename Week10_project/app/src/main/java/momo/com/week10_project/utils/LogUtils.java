package momo.com.week10_project.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class LogUtils {


    public static void MyLog(String content){
        Log.d("Realmo",content);
    }

    public static void MyLog2(String regex,String content){
        Log.d(regex,content);
    }
}
