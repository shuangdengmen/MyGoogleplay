package com.men.myzxing.application;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriPermission;
import android.support.annotation.NonNull;

import java.util.List;

public class HtscApplication extends Application {
public static    Context content;
    private static ContentResolver contentResolver;

    @Override
    public void onCreate() {
        super.onCreate();
         content = this;
    }

}
