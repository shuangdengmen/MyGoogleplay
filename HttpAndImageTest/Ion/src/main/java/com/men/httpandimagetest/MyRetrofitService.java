package com.men.httpandimagetest;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MyRetrofitService {

    @GET("get")
    Call<ResponseBody> hello(@Query("name") String name) ;

    @GET("get")
    Call<HttpGetBean> hello2(@Query("name") String name) ;

    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part MultipartBody.Part part);
}
