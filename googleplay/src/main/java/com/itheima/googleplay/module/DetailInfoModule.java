package com.itheima.googleplay.module;

import android.support.v4.view.ViewCompat;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailInfoModule extends BaseModule<AppInfo> {

    @InjectView(R.id.iv_image)
    ImageView ivImage;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.rb_star)
    RatingBar rbStar;
    @InjectView(R.id.tv_download_num)
    TextView tvDownloadNum;
    @InjectView(R.id.tv_version)
    TextView tvVersion;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_size)
    TextView tvSize;
    @InjectView(R.id.ll_info)
    LinearLayout llInfo;

    @Override
    protected int getLayoutId() {
        return  R.layout.layout_detail_item;
    }


    @Override
    public void loadData(AppInfo appInfo){
        ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+appInfo.iconUrl,ivImage, UILOptions.options);
        tvName.setText(appInfo.name);
        rbStar.setRating(appInfo.stars);
        tvDownloadNum.setText("下载："+appInfo.downloadNum);
        tvVersion.setText("版本："+appInfo.version);
        tvDate.setText("日期："+appInfo.date);
        tvSize.setText("大小："+ Formatter.formatFileSize(MyApp.context,appInfo.size));

        //执行掉落动画
        //1.先让llInfo上去
        //添加一个布局完成的监听器
        llInfo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /**
             * 当执行完布局之后，回调该方法，因此可以在该方法中获取宽高
             */
            @Override
            public void onGlobalLayout() {
                llInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                llInfo.setTranslationY(-llInfo.getHeight());
                //再通过属性动画移动下来
                ViewCompat.animate(llInfo)
                        .translationY(0)
                        .setDuration(800)
                        .setStartDelay(400)
                        .setInterpolator(new BounceInterpolator())//像球落地一样的感觉
                        .start();
            }
        });

    }


}
