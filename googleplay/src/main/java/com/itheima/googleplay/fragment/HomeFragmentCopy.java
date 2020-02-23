package com.itheima.googleplay.fragment;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.adapter.PageHeaderAdapter;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.bean.Home;
import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.LogUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentCopy extends BaseFragment {


    private HomeAdapter homeAdapter;
    private ArrayList<AppInfo> homeList = new ArrayList<AppInfo>();
    private PullToRefreshListView ptrView;
    private ListView listView;
    private ViewPager viewPager;
   private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,2000);
            return true;
        }
    });

    ;
    public HomeFragmentCopy() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(0,2000);
        LogUtil.d("onStart..");
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(0);
        LogUtil.d("onStop..");

    }

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
        homeAdapter = new HomeAdapter(homeList);
        listView.setAdapter(homeAdapter);
        //添加viewpager
        addListHeadView();
        return ptrView;
    }

    private void addListHeadView() {
        View view = View.inflate(getContext(),R.layout.headview_home,null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_viewpager);
        listView.addHeaderView(view);
    }

    @Override
    public void loadData() {
        if(ptrView.getMode()== PullToRefreshBase.Mode.PULL_FROM_START){
            homeList.clear();
            LogUtil.d(" 下拉:homeList.size"+homeList.size());
        }
        HttpHelper.create().get(Url.HOME_PREFIX+homeList.size(), new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {

                stateLayout.showSucessView();
                LogUtil.d(result);
                Home home = GsonUtil.parseJsonToBean(result, Home.class);
                if (home!=null){
                    homeList.addAll(home.list);
                    homeAdapter.notifyDataSetChanged();
                    if(home.picture!=null){
                        viewPager.setAdapter(new PageHeaderAdapter( home.picture));
                    }
                }

                ptrView.onRefreshComplete();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
