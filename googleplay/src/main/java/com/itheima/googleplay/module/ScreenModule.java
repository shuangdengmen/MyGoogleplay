package com.itheima.googleplay.module;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.activity.DetailActivity;
import com.itheima.googleplay.activity.ImageScaleActivity;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.DimenUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

public class ScreenModule extends BaseModule<AppInfo> {
    @InjectView(R.id.ll_image)
    LinearLayout llImage;
    private int imageWidth;
    private int imageHeight;
    private int imagemargin;

    private DetailActivity detailActivity;

    public void setActivity(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_detail_screen;
    }


    @Override
    public void loadData(AppInfo appInfo) {
        imageWidth = DimenUtil.getDimens(R.dimen.dp90);
        imageHeight = DimenUtil.getDimens(R.dimen.dp150);
        imagemargin = DimenUtil.getDimens(R.dimen.dp12);
        final ArrayList<String> screens = appInfo.screen;
        for (int i = 0; i < screens.size(); i++) {
            ImageView imageView = new ImageView(MyApp.context);
            ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + screens.get(i), imageView, UILOptions.options);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageWidth, imageHeight);
            layoutParams.leftMargin = (i == 0 ? 0 : imagemargin);
            imageView.setLayoutParams(layoutParams);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(detailActivity, ImageScaleActivity.class);
                    intent.putStringArrayListExtra("imagelist",screens);
                    intent.putExtra("index", finalI);
                    detailActivity.startActivity(intent);
                }
            });
            llImage.addView(imageView);
        }
    }

}
