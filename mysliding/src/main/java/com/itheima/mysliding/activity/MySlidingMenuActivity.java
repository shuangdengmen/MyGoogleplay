package com.itheima.mysliding.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.itheima.mysliding.R;
import com.itheima.mysliding.view.MySldingMenu;


public class MySlidingMenuActivity extends AppCompatActivity {

    private MySldingMenu mySldingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu);
        initView();
    }

    public void showtext(View view){
        //TODO:
    }
    private void initView() {
        mySldingMenu = new MySldingMenu(MySlidingMenuActivity.this);
        View mainView =  View.inflate(getApplicationContext(),R.layout.main,null);
        View menu =  View.inflate(getApplicationContext(),R.layout.menu,null);
        mySldingMenu.addView(mainView);
        mySldingMenu.addView(menu);
    }
}
