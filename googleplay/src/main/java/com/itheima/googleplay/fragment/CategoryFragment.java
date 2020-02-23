package com.itheima.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.CategoryAdapter;
import com.itheima.googleplay.bean.Category;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.LogUtil;
import com.itheima.googleplay.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {


    private ListView listView;
    private ArrayList<Object> list= new ArrayList<Object>();

    @Override
    public View getSucessView() {
        listView = (ListView) View.inflate(getContext(), R.layout.listview,null);
        return listView;
    }

    @Override
    public void loadData() {

        LogUtil.d("category.loadData..."+Url.CATEGORY_PREFIX+list.size());
        HttpHelper.create().get(Url.CATEGORY_PREFIX+list.size(), new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {
                stateLayout.showSucessView();
                ArrayList<Category> categories = (ArrayList<Category>) GsonUtil.parseJsonToList(result, new TypeToken<List<Category>>() {
                }.getType());
                for (Category category:categories){
                    list.add(category.title);
                    list.addAll(category.infos);
                }

                CategoryAdapter categoryAdapter = new CategoryAdapter(list);
                listView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
