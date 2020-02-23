package com.itheima.googleplay.http;

import android.os.Environment;

import com.itheima.googleplay.global.MyApp;
import com.itheima.googleplay.util.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;

public class CacheManager {

   public static final String CACHE_DIR = Environment.getExternalStorageDirectory()+ File.separator+
            MyApp.context.getPackageName()+File.separator+
           "cache";


    private static CacheManager  instance = new CacheManager();
    private static long DURATION_TIME= 1000*60*60*2;


    private CacheManager(){
         File dir = new File(CACHE_DIR);
        if(!dir.exists()){
            dir.mkdirs();
        }


    }
    public static CacheManager create(){
        return instance;
    }

    public void saveCache(String url ,String json){
        try {
            File file = new File(CACHE_DIR, URLEncoder.encode(url));
            if(!file.exists()){
                file.createNewFile();

            }
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCache(String url){


        File cacheFile = new File(CACHE_DIR,URLEncoder.encode(url));
        StringBuilder builder = new StringBuilder();
        try {
            if (cacheFile.exists()) {

                if(validate(cacheFile)){
                    FileReader fileReader = new FileReader(cacheFile);
                    BufferedReader br = new BufferedReader(fileReader);
                    String line = null;
                    while ((line= br.readLine())!=null ){
                        builder.append(line);
                    }
                    br.close();
                }else{
                    cacheFile.delete();
                    return  null;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    private boolean validate(File cachaFile) {
        long duration = System.currentTimeMillis() - cachaFile.lastModified();
        return duration<=DURATION_TIME;
    }
}
