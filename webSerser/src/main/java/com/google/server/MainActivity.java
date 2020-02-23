package com.google.server;

import java.io.File;
import java.io.FileInputStream;

import org.mortbay.ijetty.log.AndroidLog;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
	private Button mBtStart, mBtStop, mBtSetting;
	//	public static final File JETTY_DIR;
//	public static final String WEBAPP_DIR = "webapps";
//	public static final String ETC_DIR = "etc";
//	public static final String CONTEXTS_DIR = "contexts";
//
	static {
		// 不使用jetty的XML解析验证
		System.setProperty("org.eclipse.jetty.xml.XmlParser.Validating", "false");
		// 使用android日志类
		System.setProperty("org.eclipse.jetty.util.log.class", "org.mortbay.ijetty.AndroidLog");
		org.eclipse.jetty.util.log.Log.setLog(new AndroidLog());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mBtStart = (Button) findViewById(R.id.bt_start);
		mBtStop = (Button) findViewById(R.id.bt_stop);
		mBtSetting = (Button) findViewById(R.id.bt_setting);
		mBtStart.setOnClickListener(this);
		mBtStop.setOnClickListener(this);
		mBtSetting.setOnClickListener(this);
		final RxPermissions rxPermission = new RxPermissions(MainActivity.this); // where this is an Activity or Fragment instance
		rxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.CALL_PHONE,
				Manifest.permission.INTERNET
		).subscribe(new Consumer<Boolean>() {
			@Override
			public void accept(Boolean aBoolean) throws Exception {
				if (aBoolean){
					//申请的权限全部允许
					Toast.makeText(MainActivity.this, "允许了权限!", Toast.LENGTH_SHORT).show();
					tryOpenFile();
				}else{
					//只要有一个权限被拒绝，就会执行
					Toast.makeText(MainActivity.this, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
				}
			}
		});
		//Permission denied

	}

	private void tryOpenFile() {
		try {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "WebInfos/app/homelist0";
			Log.d("doGet", "myDoget path: " + path);

			File file = new File(path);
			long length = file.length();
			FileInputStream stream = new FileInputStream(file);
			int count = -1;
			byte[] buffer = new byte[1024];
			Log.d("doGet", "myDoget path: " + stream);
			while ((count = stream.read(buffer)) != -1) {
				Log.d("mySerser", "onCreate: " + buffer.toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_start:
				onStartClick();
				break;
			case R.id.bt_stop:
				onStopClick();
				break;
			case R.id.bt_setting:
				onSettingClick();
				break;
		}
	}

	public void onStartClick() {
		Intent intent = new Intent(this, WebService.class);
		startService(intent);
	}

	public void onStopClick() {
		Intent intent = new Intent(this, WebService.class);
		stopService(intent);
	}

	public void onSettingClick() {

	}
}
