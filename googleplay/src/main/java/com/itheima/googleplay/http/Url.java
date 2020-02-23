package com.itheima.googleplay.http;

public interface Url {
    String HOST_SERVER="http://127.0.0.1:8090/";
    String IMAGE_PREFIX=HOST_SERVER+"image?name=";
    String HOME_PREFIX=HOST_SERVER+"home?index=";
    String APP_PREFIX=HOST_SERVER+"app?index=";
    String GAME_PREFIX=HOST_SERVER+"game?index=";
    String SUBJECT_PREFIX=HOST_SERVER+"subject?index=";
    String  RECOMMEND_PREFIX=HOST_SERVER+"recommend";
    String CATEGORY_PREFIX = HOST_SERVER+"category?index=";
    String HOT_PREFIX = HOST_SERVER+"hot";
    String DETAIL =HOST_SERVER+"detail?packageName=%s" ;
    String DOWNLOAD=HOST_SERVER+"download?name=%s";
    String BREAKDOWNLOAD=HOST_SERVER+"download?name=%s&range=%d";




}
