package com.itheima.test_myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OkHttpActivity extends Activity {

    @InjectView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.inject(this);


    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked(View view) {

        new Thread (){
            @Override
            public void run() {
                request();
                super.run();
            }
        }.start();
//        request();
    }

    private void request() {
        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormEncodingBuilder().addEncoded();
        Request request = new Request.Builder().url("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100").build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();

            if(response.isSuccessful()){
                String body = response.body().string();
                Log.i("mymytest", "onViewClicked: "+body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
