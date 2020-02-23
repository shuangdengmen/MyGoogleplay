package com.itheima.test_myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecyclerViewActivity extends Activity {

    @InjectView(R.id.rv_RecyclerView1)
    RecyclerView rvRecyclerView1;
    View myView;
    @InjectView(R.id.srl_swipe1)
    SwipeRefreshLayout srlSwipe1;
    @InjectView(R.id.t_toolbar)
    Toolbar tToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.inject(this);
        setActionBar(tToolbar);

        srlSwipe1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlSwipe1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlSwipe1.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        srlSwipe1.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.white, android.R.color.darker_gray);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecyclerView1.setLayoutManager(linearLayoutManager);
        rvRecyclerView1.setAdapter(new MyListAdapter());

    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListHolder> {


        @Override
        public MyListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.activity_recycler_item, null);

            return new MyListHolder(view);
        }

        @Override
        public void onBindViewHolder(MyListHolder holder, int position) {
            holder.setData(position);
        }

        @Override
        public int getItemCount() {
            return 1000;
        }

        private class MyListHolder extends RecyclerView.ViewHolder {
            private final TextView tvTextItem;
            private final ImageView ivIconItem;

            public MyListHolder(View itemView) {
                super(itemView);
                myView = itemView;
                tvTextItem = (TextView) myView.findViewById(R.id.tv_text_item);
                ivIconItem = (ImageView) myView.findViewById(R.id.iv_icon_item);
            }

            private void setData(int position) {
                tvTextItem.setText("你好");
                ivIconItem.setImageResource(R.drawable.ic_launcher_foreground);

            }
        }

    }

}
