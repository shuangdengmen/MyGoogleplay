package com.itheima.googleplay.module;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.anim.HeightAnim;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.bean.SafeInfo;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.InjectView;

public class SafeInfoModule extends BaseModule<AppInfo> implements View.OnClickListener {
    @InjectView(R.id.iv_image1)
    ImageView ivImage1;
    @InjectView(R.id.iv_image2)
    ImageView ivImage2;
    @InjectView(R.id.iv_image3)
    ImageView ivImage3;
    @InjectView(R.id.iv_safe_arrow)
    ImageView ivSafeArrow;
    @InjectView(R.id.iv_icon1)
    ImageView ivIcon1;
    @InjectView(R.id.tv_des1)
    TextView tvDes1;
    @InjectView(R.id.iv_icon2)
    ImageView ivIcon2;
    @InjectView(R.id.tv_des2)
    TextView tvDes2;
    @InjectView(R.id.iv_icon3)
    ImageView ivIcon3;
    @InjectView(R.id.tv_des3)
    TextView tvDes3;
    @InjectView(R.id.ll_safe)
    LinearLayout llSafe;
    private int height;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_safe_info;
    }

    @Override
    public void loadData(AppInfo appInfo) {
        ArrayList<SafeInfo> safelist = appInfo.safe;

        if (safelist != null && safelist.size() != 0) {
            SafeInfo safe1 = safelist.get(0);
            tvDes1.setText(safe1.safeDes);
            ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe1.safeDesUrl, ivIcon1, UILOptions.options);
            ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe1.safeUrl, ivImage1, UILOptions.options);

            if (safelist.size() > 1) {
                SafeInfo safe2 = safelist.get(1);
                tvDes1.setText(safe2.safeDes);
                ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe2.safeDesUrl, ivIcon2, UILOptions.options);
                ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe2.safeUrl, ivImage2, UILOptions.options);
            } else {
                ViewGroup viewGroup2 = (ViewGroup) tvDes2.getParent();
                viewGroup2.setVisibility(View.GONE);
            }

            if (safelist.size() > 2) {
                SafeInfo safe3 = safelist.get(2);
                tvDes1.setText(safe3.safeDes);
                ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe3.safeDesUrl, ivIcon3, UILOptions.options);
                ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX + safe3.safeUrl, ivImage3, UILOptions.options);
            } else {
                ViewGroup viewGroup3 = (ViewGroup) tvDes3.getParent();
                viewGroup3.setVisibility(View.GONE);
            }

        } else {
            ViewGroup viewGroup = (ViewGroup) tvDes1.getParent();
            viewGroup.setVisibility(View.GONE);
            ViewGroup viewGroup2 = (ViewGroup) tvDes2.getParent();
            viewGroup2.setVisibility(View.GONE);
            ViewGroup viewGroup3 = (ViewGroup) tvDes3.getParent();
            viewGroup3.setVisibility(View.GONE);
        }

        viewModule.setOnClickListener(this);
        llSafe.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llSafe.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = llSafe.getHeight();
                ViewGroup.LayoutParams layoutParams = llSafe.getLayoutParams();
                layoutParams.height = 0;
                llSafe.setLayoutParams(layoutParams);

            }
        });
    }

    private boolean isOpen = false;
    private boolean isRotaing = false;

    @Override
    public void onClick(View v) {
        if (isRotaing) {
            return;
        }

        HeightAnim heightAnim = null;
        if (isOpen) {
            heightAnim = new HeightAnim(height, 0, llSafe);
        } else {
            heightAnim = new HeightAnim(0, height, llSafe);
        }
        heightAnim.start(500);

        ViewCompat.animate(ivSafeArrow)
                .rotationBy(180f)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationStart(View view) {
                                     isRotaing = true;
                                 }

                                 @Override
                                 public void onAnimationEnd(View view) {
                                     isRotaing = false;
                                 }
                             }
                )
                .setDuration(500)
                .start();
        isOpen = !isOpen;
    }
}
