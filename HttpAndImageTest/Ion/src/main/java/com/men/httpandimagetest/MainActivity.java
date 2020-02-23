package com.men.httpandimagetest;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.future.ResponseFuture;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_button)
    Button btnButton;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.ll_linearLayout)
    LinearLayout llLinearLayout;
    @InjectView(R.id.my_image)
    ImageView myImage;
    private ResponseFuture<HttpGetBean> as;
    private Retrofit retrofit;
    private MyRetrofitService myRetrofitService;
    private Call<HttpGetBean> call;
    private MyRetrofitService myRetrofitService1;
    private File file;
    private Call<ResponseBody> call1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        final RxPermissions rxPermission = new RxPermissions(MainActivity.this); // where this is an Activity or Fragment instance
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = rxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //申请的权限全部允许
                    Toast.makeText(getApplicationContext(), "允许了权限!", Toast.LENGTH_SHORT).show();
                    //调用状态布局
                    retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).build();
                    initView();
                } else {
                    //只要有一个权限被拒绝，就会执行
                    Toast.makeText(getApplicationContext(), "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
        compositeDisposable.add(disposable);
    }


    private void initView() {
        Picasso.with(MainActivity.this).load(Constants.IMAGES[0])
                .centerCrop()
                .resize(120, 120)
                .placeholder(R.mipmap.ic_launcher)
                .into(myImage);
        String dir = Environment.getExternalStorageDirectory() + File.separator + "mypic" + File.separator
                + "gugong.jpg";
        Log.d(dir, "initView: dir" + dir);
        file = new File(dir);
    }

    @OnClick(R.id.btn_button)
    public void onClick(View view) {
//        ionGet();
//        retrofitGet();
        retrofitUpload();
    }

    private void retrofitUpload() {
        myRetrofitService1 = retrofit.create(MyRetrofitService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", "gugong.jpg", requestBody);
        call1 = myRetrofitService1.upload(part);
        call1.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("enter", "onResponse: enterl.onResponse...");
                    tvText.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("enter", "onResponse: enterl.onFailure...");
                t.printStackTrace();
            }
        });
    }

    private void retrofitGet() {

        myRetrofitService = retrofit.create(MyRetrofitService.class);
        call = myRetrofitService.hello2("xx");
        call.enqueue(new Callback<HttpGetBean>() {
            @Override
            public void onResponse(Call<HttpGetBean> call, Response<HttpGetBean> response) {
                try {
                    tvText.setText(response.body().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HttpGetBean> call, Throwable t) {

            }
        });

    }

    private void ionGet() {
        as = Ion.with(this).load(Api.HELLO).as(HttpGetBean.class);
        as.setCallback(new FutureCallback<HttpGetBean>() {
            @Override
            public void onCompleted(Exception e, HttpGetBean result) {
                tvText.setText(result.toString());
            }
        });
    }
}
