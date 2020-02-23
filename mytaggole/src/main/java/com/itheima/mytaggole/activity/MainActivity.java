package com.itheima.mytaggole.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.itheima.mytaggole.view.HisToggleView;
import com.itheima.util.LogUtil;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    View myview = null;
    private float downX;
    private float downY;
    private float moveX=0;
    private float moveY=0;
    private float dx=0;
    private float dy=0;
    private LinearLayout llLinearLayout;
    private Random random;
    private HisToggleView hisToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        myview = findViewById(R.id.my_view);
        myview.setOnTouchListener(this);
        llLinearLayout = (LinearLayout) findViewById(R.id.ll_linearlayout);
        hisToggleView = (HisToggleView) findViewById(R.id.hisToggleView);
        Button btnButton = (Button) findViewById(R.id.btn_button);
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,MySlidingMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            case (MotionEvent.ACTION_MOVE):
                moveX = event.getRawX();
                moveY = event.getRawY();

                dx = moveX-downX;
                dy = moveY-downY;
                break;
            case (MotionEvent.ACTION_DOWN):

                downX = event.getRawX();
                downY = event.getRawY();

                break;
            case (MotionEvent.ACTION_UP):
                random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                llLinearLayout.setBackgroundColor(Color.rgb(red,green,blue));
                //llLinearLayout.setBackgroundDrawable(myview.getBackground());

                break;
            default:
                break;
        }

        downX = downX+dx;
        downY= downY+dy;
        int left = (int) (myview.getLeft()+dx);
        int top = (int) (myview.getTop()+dy);
        int right=left+myview.getWidth();
        int bottom = top+myview.getHeight();

        LogUtil.d("moveX:"+moveX+",moveY:"+moveY);
        LogUtil.d("downX:"+downX+",downY:"+downY);
        LogUtil.d("dx:"+dx+",dy:"+dy);
        LogUtil.d("left:"+left+",top:"+top);
        myview.layout(left,top,right,bottom);
        return true;
    }
}
