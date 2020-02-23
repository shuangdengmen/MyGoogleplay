package com.men.myzxing.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.Result;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.men.myzxing.async.ImageScanningTask;
import com.men.myzxing.R;

public class ViewFindViewActivity extends AppCompatActivity  implements  DecoratedBarcodeView.TorchListener {

    public static final int OPENIMG_RQUESTCODE = 1111;
    public  static final int RESULT_CODE_PICK_IMAGE = 22222;
    private DecoratedBarcodeView decoratedBarcodeView;
    private CaptureManager captureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_find_view);

        decoratedBarcodeView=this.findViewById(R.id.decoratedBarcodeView);
        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, decoratedBarcodeView);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
        decoratedBarcodeView.setTorchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                selectPhoto();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void selectPhoto() {
        Intent innerIntent =new Intent(Intent.ACTION_PICK);
        innerIntent.setType("image/*");
        startActivityForResult(innerIntent,OPENIMG_RQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==OPENIMG_RQUESTCODE){
            Toast.makeText(getApplicationContext(),"uri:"+data.getData(),Toast.LENGTH_SHORT).show();
            Uri uri = data.getData();
            ImageScanningTask scanningTask = new ImageScanningTask(uri,
                    new ImageScanningTask.ImageScanningCallback() {
                        @Override
                        public void onFinishScanning(Result result) {
                            Log.d("ssss","onFinishScanning:"+result);
                            if (result != null) {
                                Intent intent = new Intent();
                                intent.putExtra("result", result.getText());
                                setResult(RESULT_CODE_PICK_IMAGE, intent);
                                finish();
                            } else {
                                // 识别失败
                            }
                        }
                    });
            scanningTask.execute();
        }
    }

    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }
}
