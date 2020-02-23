package com.itheima.googleplay.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.itheima.googleplay.R;

public class DrawableUtil {
    public static   GradientDrawable   getGenarateDawable(int radius){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(ColorUtil.getColorBeatiful());
        drawable.setCornerRadius(radius);
        return drawable;
    }

    public static  Drawable getStateListSelector(Drawable normal,Drawable press ){
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed},press);
        drawable.addState(new int[]{},normal);
        drawable.setEnterFadeDuration(500);
        drawable.setExitFadeDuration(500);
        return drawable;
    }

}
