package com.itheima.googleplay.util;

import android.util.Log;

/**
 * Created by lxj on 2016/11/14.
 * Log管理类
 */

public class LogUtil {
    private static final String TAG = "LogUtil";

    //是否是开发调试环境，当项目上线的时候，将该变量置为false即可
    private static boolean isDebug = true;

    /**
     * 打印d级别的log
     * @param tag
     * @param msg
     */
    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    /**
     * 打印e级别的log
     * @param tag
     * @param msg
     */
    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }

    /**
     * 打印d级别的log，不需要传tag
     * @param msg
     */
    public static void d(String msg){
        if(isDebug){
            Log.d(TAG,msg);
        }
    }

    /**
     * 打印e级别的log，不需要传tag
     * @param msg
     */
    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }


}
