package com.itheima.googleplay.http;


import android.text.TextUtils;
import android.util.Log;

import com.itheima.googleplay.util.LogUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class HttpHelper {
    private static HttpHelper helper = new HttpHelper();
    private HttpUtils httpUtils;
    private HttpHelper(){
        httpUtils = new HttpUtils();
        httpUtils.configHttpCacheSize(0);
    }
    public static HttpHelper create(){
        return  helper;
    }

    public void get(final String url, final HttpHelperCallBack callBack){
        String cache = CacheManager.create().getCache(url);
        if(TextUtils.isEmpty(cache)){
            getNetData(url, callBack);
        }else{
            //取得缓存
            callBack.onSucess(cache);
        }

    }

    private void getNetData(final String url, final HttpHelperCallBack callBack) {
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                CacheManager.create().saveCache(url,result);
                callBack.onSucess(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callBack.onFailure(e);
            }
        }
        );
    }

    public interface HttpHelperCallBack{
        public void onSucess(String result);
        public void onFailure(Exception e);
    }
}
