package com.itheima.googleplay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.adapter.MyAdapter;
import com.itheima.googleplay.adapter.SubjectAdapter;
import com.itheima.googleplay.bean.Subject;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends PtrListFragment<Subject> {


    @Override
    protected void parseDataAndShowData(String result) {
        ArrayList<Subject> subjects =(ArrayList<Subject>) GsonUtil.parseJsonToList(result, new TypeToken<List<Subject>>() {
        }.getType());
        if(subjects!=null){
            list.addAll(subjects);
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected String getUrl() {
        return Url.SUBJECT_PREFIX+list.size();
    }

    @Override
    public MyAdapter<Subject> getAdapter() {
        return new SubjectAdapter(list);
    }

}
