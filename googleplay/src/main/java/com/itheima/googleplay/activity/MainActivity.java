package com.itheima.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.gxz.PagerSlidingTabStrip;
import com.itheima.googleplay.R;
import com.itheima.googleplay.adapter.MainAdapter;
import com.itheima.googleplay.fragment.AppFragment;
import com.itheima.googleplay.fragment.CategoryFragment;
import com.itheima.googleplay.fragment.GameFragment;
import com.itheima.googleplay.fragment.HomeFragment;
import com.itheima.googleplay.fragment.HotFragment;
import com.itheima.googleplay.fragment.RecommendFragment;
import com.itheima.googleplay.fragment.SubjectFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.dl_layout)
    DrawerLayout dlLayout;
    @InjectView(R.id.psts_slidingtab)
    PagerSlidingTabStrip pstsSlidingtab;
    @InjectView(R.id.vp_viewpager)
    ViewPager vpViewpager;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        addActionBar();
        initData();
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new AppFragment());
        fragments.add(new GameFragment());
        fragments.add(new SubjectFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new HotFragment());
        String[] titles = getResources().getStringArray(R.array.tab_names);
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(),fragments,titles);
        vpViewpager.setAdapter(mainAdapter);
        pstsSlidingtab.setViewPager(vpViewpager);
    }

    private void addActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, dlLayout, 0, 0);
        drawerToggle.syncState();
        dlLayout.addDrawerListener(drawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
