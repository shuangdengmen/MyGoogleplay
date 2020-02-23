package com.men.mymvp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.men.mymvp.presenter.MyPresenter;
import com.men.mymvp.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private Handler handler;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.startMyPresenter();
    }

    @Override
    public void updateUI(String result) {
        textView = findViewById(R.id.tv_textView);
        textView.setText(result);
    }
}
