package com.itheima.googleplay.adapter;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class MyAdapter<T> extends BaseAdapter {
    ArrayList<T> list = new ArrayList<T>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object holder;
        if (convertView == null) {
            View view = View.inflate(parent.getContext(), getItemLayoutId(position), null);
            convertView = view;
            holder= getHolder(convertView,position);
            convertView.setTag(holder);
        } else {
            holder = convertView.getTag();
        }
        T t = (T) list.get(position);
        bindViewHolder(t,holder,position);
        animateConvertView(convertView);
        return convertView;
    }

    private void animateConvertView(View convertView) {
        convertView.setScaleX(0.5f);
        convertView.setScaleY(0.5f);
        ViewCompat.animate(convertView).scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(1500).start();
    }

    protected abstract void bindViewHolder(T t, Object holder, int position);


    public abstract int getItemLayoutId(int position);

    protected abstract Object getHolder(View convertView, int position);
    


}
