package com.itheima.googleplay.anim;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class HeightAnim {
    ValueAnimator valueAnimator=null;
    private OnScrollViewListener onScrollViewListener;

    public HeightAnim(int start, int end , final View target) {
        valueAnimator = ValueAnimator.ofInt(start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue=(int)animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height=animatedValue;
                target.setLayoutParams(layoutParams);
                if (onScrollViewListener!=null){
                    onScrollViewListener.onScrollView(animatedValue);
                }
            }
        });
    }
    public void start(int duration){
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public void setOnScrollViewListener(OnScrollViewListener onScrollViewListener){
        this.onScrollViewListener=onScrollViewListener;
    }

    public interface OnScrollViewListener{
      public abstract void  onScrollView(int animatedValue);
    }
}
