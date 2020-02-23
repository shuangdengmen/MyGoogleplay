package com.itheima.googleplay.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.activity.DetailActivity;
import com.itheima.googleplay.adapter.HomeAdapter;
import com.itheima.googleplay.adapter.MyAdapter;
import com.itheima.googleplay.adapter.PageHeaderAdapter;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.bean.Home;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends PtrListFragment<AppInfo> {



    private ViewPager viewPager;
   private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,2000);
            return true;
        }
    });

    public HomeFragment() {
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
    protected void addListHeadView() {
        View view = View.inflate(getContext(),R.layout.headview_home,null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_viewpager);
        listView.addHeaderView(view);
    }

    @Override
    protected void parseDataAndShowData(String result) {
        Home home = GsonUtil.parseJsonToBean(result, Home.class);
        if (home!=null){
            list.addAll(home.list);
            baseAdapter.notifyDataSetChanged();
            if(home.picture!=null){
                viewPager.setAdapter(new PageHeaderAdapter( home.picture));
            }
        }
    }

    @Override
    protected String getUrl() {
        return Url.HOME_PREFIX+list.size();
    }

    @Override
    public MyAdapter<AppInfo> getAdapter() {
        return new HomeAdapter(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("packageName",list.get(position-2).packageName);
        startActivity(intent);
    }
}
