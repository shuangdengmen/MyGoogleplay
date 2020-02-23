package com.itheima.googleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.AppInfo;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.module.DetailDesModule;
import com.itheima.googleplay.module.DetailDownloadModule;
import com.itheima.googleplay.module.DetailInfoModule;
import com.itheima.googleplay.module.SafeInfoModule;
import com.itheima.googleplay.module.ScreenModule;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.view.StateLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lxj on 2016/11/19.
 */
public class DetailActivity extends AppCompatActivity {

    @InjectView(R.id.ll_container)
    LinearLayout llContainer;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;
    @InjectView(R.id.fl_download)
    FrameLayout flDownload;
    private String packageName;
    private StateLayout stateLayout;
    private DetailInfoModule detailInfoModule;
    private SafeInfoModule safeInfoModule;
    private ScreenModule screenModule;
    private DetailDesModule detailDesModule;
    private DetailDownloadModule detailDownloadModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.获取intent的数据
        packageName = getIntent().getStringExtra("packageName");

        //setActionbar
        setActionBar();

        //2.创建StateLayout
        stateLayout = new StateLayout(this);
        //将stateLayout作为Activity的View
        setContentView(stateLayout);

        //设置成功的View
        stateLayout.bindSuccessView(getSuccessView());
        //先显示loadingView
        stateLayout.showLoadingView();

        //3.请求数据
        loadData();
    }

    /**
     * 获取成功的VIEW
     */
    public View getSuccessView() {
        View view = View.inflate(this, R.layout.activity_detail, null);
        ButterKnife.inject(this, view);
        detailInfoModule = new DetailInfoModule();
        llContainer.addView(detailInfoModule.getViewModule());
        safeInfoModule = new SafeInfoModule();
        llContainer.addView(safeInfoModule.getViewModule());
        screenModule = new ScreenModule();
        screenModule.setActivity(DetailActivity.this);
        llContainer.addView(screenModule.getViewModule());
        detailDesModule = new DetailDesModule();
        detailDesModule.setScrollView(scrollView);
        llContainer.addView(detailDesModule.getViewModule());

        detailDownloadModule = new DetailDownloadModule();
        flDownload.addView(detailDownloadModule.getViewModule());
        return view;
    }

    private AppInfo appInfo;

    /**
     * 加载数据
     */
    private void loadData() {
        String url = String.format(Url.DETAIL, packageName);
        HttpHelper.create().get(url, new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {
                stateLayout.showSucessView();
                appInfo = GsonUtil.parseJsonToBean(result, AppInfo.class);
                if (appInfo != null) {
                    //更新UI
                    updateUI();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    /**
     * 更新UI的代码
     */
    private void updateUI() {
        detailInfoModule.loadData(appInfo);
        safeInfoModule.loadData(appInfo);
        screenModule.loadData(appInfo);
        detailDesModule.loadData(appInfo);
        detailDownloadModule.loadData(appInfo);
    }

    /**
     * 设置ActionBar
     */
    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_detail));

        //显示home按钮
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
