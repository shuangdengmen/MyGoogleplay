package com.itheima.googleplay.module;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.anim.HeightAnim;
import com.itheima.googleplay.bean.AppInfo;

import butterknife.InjectView;

public class DetailDesModule extends BaseModule<AppInfo> implements View.OnClickListener {
    @InjectView(R.id.tv_des)
    TextView tvDes;
    @InjectView(R.id.tv_author)
    TextView tvAuthor;
    @InjectView(R.id.iv_des_arrow)
    ImageView ivDesArrow;

    private  int minDesHeight;
    private int maxDesHeigh;
    private  boolean isOpen=false;
    private  boolean isAnimateEnd = true;
    private ScrollView scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_detail_des;
    }

    public  void setScrollView(ScrollView scrollView){
        this.scrollView=scrollView;
    }

    @Override
    public void loadData(AppInfo appInfo) {
        tvDes.setText(appInfo.des);
        tvAuthor.setText(appInfo.author);
        viewModule.setOnClickListener(this);

        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                maxDesHeigh= tvDes.getHeight();
                getMinHeight();
            }


        });
    }
    private void getMinHeight() {
        tvDes.setMaxLines(5);
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvDes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                minDesHeight= tvDes.getHeight();
                tvDes.setMaxLines(Integer.MAX_VALUE);
                ViewGroup.LayoutParams layoutParams = tvDes.getLayoutParams();
                layoutParams.height=minDesHeight;
                tvDes.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(!isAnimateEnd){
            return;
        }

        HeightAnim heightAnim = null;
        if(isOpen){
            heightAnim = new HeightAnim(maxDesHeigh, minDesHeight, tvDes);
            heightAnim.start(600);
        }else {
            heightAnim = new HeightAnim(minDesHeight, maxDesHeigh, tvDes);
            heightAnim.start(600);
            heightAnim.setOnScrollViewListener(new HeightAnim.OnScrollViewListener() {
                @Override
                public void onScrollView(int animatedValue) {
                    scrollView.scrollBy(0,animatedValue);
                }
            });
        }

        ViewCompat.animate(ivDesArrow)
                  .setListener(new ViewPropertyAnimatorListenerAdapter(){
                      @Override
                      public void onAnimationStart(View view) {
                          isAnimateEnd=false;
                      }

                      @Override
                      public void onAnimationEnd(View view) {
                          isAnimateEnd=true;
                      }
                  })
                  .rotationBy(180f)
                  .setDuration(600)
                  .start();
        isOpen=!isOpen;

    }
}
