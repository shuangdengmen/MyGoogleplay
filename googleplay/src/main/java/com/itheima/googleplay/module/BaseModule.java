package com.itheima.googleplay.module;

import android.view.View;
import android.widget.ScrollView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.global.MyApp;

import butterknife.ButterKnife;

public abstract class BaseModule<T> {
    public View viewModule;
    public BaseModule() {
        viewModule = View.inflate(MyApp.context,getLayoutId(), null);
        ButterKnife.inject(this,viewModule);
    }

    protected abstract int getLayoutId();

    public View getViewModule(){
        return viewModule;
    }
    public abstract void loadData(T t);

}
