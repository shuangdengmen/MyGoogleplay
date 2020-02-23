package com.itheima.googleplay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.adapter.MyAdapter;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends PtrListFragment<AppInfo> {
    @Override
    protected void parseDataAndShowData(String result) {
        ArrayList<AppInfo>  appInfos = (ArrayList<AppInfo>) GsonUtil.parseJsonToList(result, new TypeToken<List<AppInfo>>() {
        }.getType());
        list.addAll(appInfos);
        if(appInfos!=null){
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected String getUrl() {
        return Url.APP_PREFIX+list.size();
    }

    @Override
    public MyAdapter<AppInfo> getAdapter() {
        return new HomeAdapter(list);
    }

}
