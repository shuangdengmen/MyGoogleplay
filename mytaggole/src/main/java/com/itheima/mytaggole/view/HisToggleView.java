package com.itheima.mytaggole.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.itheima.util.LogUtil;

public class HisToggleView extends View {
    public static String Namespace="http://schemas.android.com/apk/res-auto";

    public HisToggleView(Context context) {
        this(context, null);
    }

    public HisToggleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HisToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        boolean isToggle =attrs.getAttributeBooleanValue(Namespace,"isToggle",false);
        LogUtil.d("isToggle:"+isToggle);
    }

}
