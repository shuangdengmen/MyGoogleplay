package com.itheima.mysliding.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.itheima.mysliding.util.LogUtil;

public class MySldingMenu extends ViewGroup {

    private View mainView;
    private View menu;
    private int menuWidth;
    private float downX;
    private float downY;
    private float instanceX;
    private float instanceY;
    private Scroller   scroller = new Scroller(getContext());;
    private float upX;
    private float lastInstanceX;
    public MySldingMenu(Context context) {
        this(context, null);
    }

    public MySldingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySldingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        menu = getChildAt(0);
        mainView = getChildAt(1);
        mainView.measure(widthMeasureSpec, heightMeasureSpec);
        menu.measure(240, 0);
        menuWidth = menu.getLayoutParams().width;
        LogUtil.d("menuWidth:=" + menuWidth + ",mainWidth:" + mainView.getMeasuredWidth());
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mainView.layout(0, 0, mainView.getMeasuredWidth(), mainView.getMeasuredHeight());
        menu.layout(-menuWidth, 0, 0, menu.getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                downX = event.getX();
                downY = event.getY();
                break;
            case (MotionEvent.ACTION_MOVE):
                instanceX =  lastInstanceX+event.getX() - downX;
                instanceY = event.getY() - downY;
                //控制滑动的范围
                if (instanceX < 0) {
                    instanceX = 0;
                }else if(instanceX>=menuWidth) {
                    instanceX=menuWidth;
                }
                break;
            case (MotionEvent.ACTION_UP):
          /*      lastInstanceX = instanceX;
                if (instanceX>0){
                    myScrollTo(menuWidth);
                }else {
                    myScrollTo(0);
                }
*/
                if (instanceX < menuWidth / 2) {
                    //myScrollTo(0);
                    lastInstanceX = 0;
                } else {
                    //myScrollTo(menuwidth);
                    lastInstanceX = menuWidth;
                }

                scrollToSlow(instanceX,lastInstanceX);
                break;
            default:
                break;
        }
        return true;
    }

    private void myScrollTo(int width) {
        scrollTo(-width, 0);
    }

    private void scrollToSlow(float startX, float endX) {

        int dx = (int) (endX - startX);
        scroller.startScroll((int)startX, 0, dx, 0, 3000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            int currX = scroller.getCurrX();
            myScrollTo(currX);
            invalidate();
        }
        super.computeScroll();
    }
}
