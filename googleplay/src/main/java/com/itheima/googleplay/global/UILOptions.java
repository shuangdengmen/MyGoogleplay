package com.itheima.googleplay.global;

import com.itheima.googleplay.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class UILOptions {
    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_default)
            .showImageForEmptyUri(R.mipmap.ic_default)//设置url为空显示哪个图
            .showImageOnFail(R.mipmap.ic_default)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)//会识别图片的方向信息
            .displayer(new FadeInBitmapDisplayer(2000)).build();//配置渐渐显示的动画
    //        .displayer(new RoundedBitmapDisplayer(36)).build();//配置圆角的

}
