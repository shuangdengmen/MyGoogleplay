package com.itheima.googleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.ImageScaleAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ImageScaleActivity extends AppCompatActivity {
    @InjectView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagescale);
        ButterKnife.inject(this);
        ArrayList<String> imagelist = getIntent().getStringArrayListExtra("imagelist");
        int index = getIntent().getIntExtra("index", 0);
        ImageScaleAdapter imageScaleAdapter = new ImageScaleAdapter(imagelist);
        vpViewpager.setAdapter(imageScaleAdapter);
        vpViewpager.setCurrentItem(index);
    }
}
