package com.itheima.googleplay.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.SubCategory;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.LogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoryAdapter extends MyAdapter<Object> {
    private final  int TITLE = 0;
    private final  int SUBCATEGORY = 1;


    public CategoryAdapter(ArrayList<Object> list) {
        this.list=list;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = list.get(position);
        if (obj instanceof String) {
            return TITLE;
        } else {
            return SUBCATEGORY;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    protected void bindViewHolder(Object o, Object holder, int position) {
        int itemType = getItemViewType(position);
        LogUtil.d("itemType:"+itemType);
        LogUtil.d("positon:"+position);
        switch (itemType) {
            case TITLE:
                TitleHolder titleHolder= (TitleHolder) holder;
                String title= (String) o;
                titleHolder.tvTitle.setText(title);
                break;
            case SUBCATEGORY:
                SubHolder subjectHolder= (SubHolder) holder;
                SubCategory subcategory = (SubCategory) o;
                subjectHolder.tvName1.setText(subcategory.name1);
                ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+subcategory.url1,subjectHolder.ivImage1, UILOptions.options);
                ViewGroup image2Parent = (ViewGroup) subjectHolder.ivImage2.getParent();
                if(!TextUtils.isEmpty(subcategory.name2)&&!TextUtils.isEmpty(subcategory.url2)){
                    subjectHolder.tvName2.setText(subcategory.name1);
                    ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+subcategory.url2,subjectHolder.ivImage2, UILOptions.options);
                }else {
                    image2Parent.setVisibility(View.INVISIBLE);
                }
                ViewGroup image3Parent = (ViewGroup) subjectHolder.ivImage3.getParent();

                if(!TextUtils.isEmpty(subcategory.name3)&&!TextUtils.isEmpty(subcategory.url3)){
                    subjectHolder.tvName3.setText(subcategory.name1);
                    ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+subcategory.url3,subjectHolder.ivImage3, UILOptions.options);
                }else {
                    image3Parent.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemLayoutId(int position) {
        int itemType = getItemViewType(position);

        switch (itemType) {
            case TITLE:
                return R.layout.adapter_category_title;
            case SUBCATEGORY:
                return R.layout.adapter_category_sub;
        }

        return 0;
    }

    @Override
    protected Object getHolder(View convertView, int position) {
        int itemType = getItemViewType(position);

        switch (itemType) {
            case TITLE:
                return new TitleHolder(convertView);
            case SUBCATEGORY:
                return new SubHolder(convertView);
        }
        return null;
    }


    static
    class TitleHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        TitleHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static
    class SubHolder {
        @InjectView(R.id.iv_image1)
        ImageView ivImage1;
        @InjectView(R.id.tv_name1)
        TextView tvName1;
        @InjectView(R.id.iv_image2)
        ImageView ivImage2;
        @InjectView(R.id.tv_name2)
        TextView tvName2;
        @InjectView(R.id.iv_image3)
        ImageView ivImage3;
        @InjectView(R.id.tv_name3)
        TextView tvName3;

        SubHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
