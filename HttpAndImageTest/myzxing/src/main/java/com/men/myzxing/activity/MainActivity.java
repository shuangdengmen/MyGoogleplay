package com.men.myzxing.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.BusUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.men.myzxing.R;
import com.men.myzxing.util.CommonUtil;
import com.men.myzxing.util.EncodingUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.UnsupportedEncodingException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private ImageView imageView;
    private Bitmap qrCode;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RxPermissions rxPermission = new RxPermissions(MainActivity.this); // where this is an Activity or Fragment instance
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = rxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE

            /*  <uses-permission android:name="android.permission.VIBRATE"/>
  <uses-permission android:name="android.permission.FLASHLIGHT"/>
  <uses-permission android:name="android.permission.READ_CONTACTS"/>*/
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //申请的权限全部允许
                    Toast.makeText(getApplicationContext(), "允许了权限!", Toast.LENGTH_SHORT).show();
                    //调用状态布局

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
        imageView = findViewById(R.id.iv_image);
        qrCode = EncodingUtils.createQRCode("ABC", 400);

        CommonUtil.saveBitmap2file(qrCode, this);

        imageView.setBackground(new BitmapDrawable(qrCode));
        Button button = findViewById(R.id.findQR_btn);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(getApplicationContext(),ViewFindViewActivity.class);
//        startActivityForResult(intent,0);

        new IntentIntegrator(this)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                //.setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                .setCaptureActivity(ViewFindViewActivity.class)//自定义扫码界面
                .initiateScan();// 初始化扫码
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==ViewFindViewActivity.RESULT_CODE_PICK_IMAGE){
            result = data.getStringExtra("result");
            try {
                String newResult=new String(result.getBytes(),"utf-8");
                Log.d("onActivityResult","result:"+newResult);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }
}
