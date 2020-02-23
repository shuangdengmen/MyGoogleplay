package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.MyAdapter;
import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.util.LogUtil;

import java.util.ArrayList;

public abstract class PtrListFragment<T> extends BaseFragment implements AdapterView.OnItemClickListener {
    public PullToRefreshListView ptrView;
    public ListView listView;
    public ArrayList<T> list = new ArrayList<T>();
    public MyAdapter<T> baseAdapter;
    @Override
    public View getSucessView() {
        ptrView = (PullToRefreshListView)View.inflate(MyApp.context, R.layout.pulltoreflesh_layout, null);
        ptrView.setMode(PullToRefreshBase.Mode.BOTH);
        ptrView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();
            }
        });
        listView = ptrView.getRefreshableView();
        listView.setDividerHeight(0);
        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(this);
        baseAdapter =getAdapter();
        listView.setAdapter(baseAdapter);
        addListHeadView();
        return ptrView;
    }

    protected void addListHeadView() {
    }


    @Override
    public void loadData() {
        if(ptrView.getMode()== PullToRefreshBase.Mode.PULL_FROM_START){
            list.clear();
        }
        HttpHelper.create().get(getUrl(), new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {

                stateLayout.showSucessView();
                LogUtil.d(result);
                parseDataAndShowData(result);
                ptrView.onRefreshComplete();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    protected abstract void parseDataAndShowData(String result);
    protected abstract String getUrl();
    public abstract   MyAdapter<T> getAdapter() ;

}
