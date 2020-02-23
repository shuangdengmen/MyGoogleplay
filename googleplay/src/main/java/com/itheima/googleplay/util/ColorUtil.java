package com.itheima.googleplay.util;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {
    public static int getColorBeatiful(){
        Random random = new Random();
        int red = random.nextInt(200);
        int green = random.nextInt(200);
        int blue = random.nextInt(200);
        return Color.rgb(red,green,blue);
    }
}
