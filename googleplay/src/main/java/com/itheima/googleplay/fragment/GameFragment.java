package com.itheima.googleplay.fragment;


import android.support.v4.app.Fragment;

import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.adapter.MyAdapter;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends PtrListFragment {

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
        return Url.GAME_PREFIX+list.size();
    }

    @Override
    public MyAdapter getAdapter() {
        return new HomeAdapter(list);
    }
}
