package com.itheima.googleplay.util;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.itheima.googleplay.global.MyApp;

import java.io.File;

public class ApkUtils {

	/**
	 * 安装apk
	 * @param apkFilePath apk所在路径
	 */
	public static void install(String apkFilePath){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//指定apk文件路径,安装apk
		File file = new File(apkFilePath);
		Uri uri =  FileProvider.getUriForFile(MyApp.context, MyApp.context.getPackageName() + ".provider", file);
		//intent.setDataAndType(Uri.parse("file://"+apkFilePath),"application/vnd.android.package-archive");
		intent.setDataAndType(uri,"application/vnd.android.package-archive");
		//intent.setData(uri);
		MyApp.context.startActivity(intent);
	}
}
