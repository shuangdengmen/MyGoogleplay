package com.men.mymvp.model;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

public class MyModel extends AsyncTask<Integer,Void,String> {

private  CallBack callBack;

int i=0;
    public MyModel(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(Integer... integer) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i=i+5;
            }
        },3000);

        return i+"";
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        callBack.onFinished(str);
    }

    public interface CallBack{
        public void onFinished(String result);
    }
}
