package com.itheima.test_myapplication;


import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @InjectView(R.id.tv_clipTextView)
    TextView tvClipTextView;
    @InjectView(R.id.tv_vibrant)
    TextView tvVibrant;
    @InjectView(R.id.iv_palette)
    ImageView ivPalette;
    @InjectView(R.id.tv_animator)
    TextView tvAnimator;
    @InjectView(R.id.btn_okhttp)
    Button btnOkhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        tvClipTextView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        // tvClipTextView.setClipToOutline(true);
        BitmapDrawable ivDra = (BitmapDrawable) ivPalette.getBackground();

        Palette.generateAsync(ivDra.getBitmap(), new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        tvVibrant.setBackgroundColor(palette.getVibrantColor(Color.BLACK));

                    }
                }
        );


    }


    @OnClick({R.id.tv_animator,R.id.btn_okhttp})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_animator:
                Animator animator = ViewAnimationUtils.createCircularReveal(tvAnimator, tvAnimator.getWidth() / 2, tvAnimator.getHeight() / 2, tvAnimator.getWidth(), 0);
//        animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(3000);
                animator.start();
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.btn_okhttp:
                startActivity(new Intent(MainActivity.this,OkHttpActivity.class));
                break;
        }

    }


}
