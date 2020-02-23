package com.itheima.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

public class ImageScaleAdapter extends  PagerBaseAdapter {

    public ImageScaleAdapter(ArrayList<String> imagelist) {
        this.urls=imagelist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView imageView = new PhotoView(container.getContext());
        ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+urls.get(position),imageView, UILOptions.options);
        container.addView(imageView);
        return imageView;
    }


}
