package com.itheima.googleplay.adapter;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeAdapter extends MyAdapter<AppInfo> {

    private HomeHolder homeHolder;

    public HomeAdapter(ArrayList<AppInfo> list) {
        this.list = list;
    }

    @Override
    protected void bindViewHolder(AppInfo appInfo, Object holder, int position) {
        homeHolder= (HomeHolder) holder;
        homeHolder.tvName.setText(appInfo.name);
        homeHolder.rbStar.setRating(appInfo.stars);
        homeHolder.tvSize.setText(Formatter.formatFileSize(MyApp.context,appInfo.size));
        homeHolder.tvDesc.setText(appInfo.des);


        ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+appInfo.iconUrl, homeHolder.ivImage , UILOptions.options);
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.home_adapter;
    }

    @Override
    protected Object getHolder(View convertView, int position) {
        return new HomeHolder(convertView);
    }


    static
    class HomeHolder {
        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.rb_star)
        RatingBar rbStar;
        @InjectView(R.id.tv_size)
        TextView tvSize;
        @InjectView(R.id.tv_desc)
        TextView tvDesc;

        HomeHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
