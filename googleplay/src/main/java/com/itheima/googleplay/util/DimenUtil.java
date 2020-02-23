package com.itheima.googleplay.util;

import com.itheima.googleplay.global.MyApp;

/**
 * Created by lxj on 2016/11/14.
 */

public class DimenUtil {
    /**
     * 获取dimes.xml中定义的dp值，并会自动转为像素
     * @param resId
     * @return
     */
    public static int getDimens(int resId){
        return MyApp.context.getResources().getDimensionPixelSize(resId);
    }
}
