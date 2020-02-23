package com.itheima.mytaggole.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class MySlidingMenu extends ViewGroup {
    public MySlidingMenu(Context context) {
        this(context,null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
