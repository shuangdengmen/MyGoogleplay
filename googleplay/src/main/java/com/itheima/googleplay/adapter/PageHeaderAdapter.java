package com.itheima.googleplay.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class PageHeaderAdapter extends PagerBaseAdapter {

    public PageHeaderAdapter(ArrayList<String> urls) {
        this.urls=urls;
    }

    @Override
    public int getCount() {
        return urls.size()*10000;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext()) ;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+urls.get(position%urls.size()),imageView, UILOptions.options);
        container.addView(imageView);
        return imageView;
    }


}
