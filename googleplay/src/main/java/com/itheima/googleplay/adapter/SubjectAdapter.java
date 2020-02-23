package com.itheima.googleplay.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.Subject;
import com.itheima.googleplay.global.UILOptions;
import com.itheima.googleplay.http.Url;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubjectAdapter extends MyAdapter<Subject> {

    public SubjectAdapter(ArrayList<Subject> list) {
        this.list=list;
    }

    @Override
    protected void bindViewHolder(Subject subject, Object holder, int position) {
        SubjectHolder subjectHolder = (SubjectHolder) holder;
        ImageLoader.getInstance().displayImage(Url.IMAGE_PREFIX+subject.url,subjectHolder.ivImage, UILOptions.options);
        subjectHolder.ivTitle.setText(subject.des);
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.subject_layout;
    }

    @Override
    protected Object getHolder(View convertView, int position) {
        return new SubjectHolder(convertView);
    }


    static
    class SubjectHolder {
        @InjectView(R.id.iv_image)
        ImageView ivImage;
        @InjectView(R.id.iv_title)
        TextView ivTitle;

        SubjectHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
