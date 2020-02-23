package com.men.mymvp.presenter;

import android.os.Handler;
import android.os.Message;

import com.men.mymvp.model.MyModel;
import com.men.mymvp.view.IView;

public class MyPresenter {

    private IView iView;

    public MyPresenter(IView iView) {
        this.iView = iView;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
                iView.updateUI( (String) msg.obj);
                handler.sendEmptyMessage(0);
            return true;
        }
    });
    public void startMyPresenter() {
        MyModel myModel = new MyModel(new MyModel.CallBack() {
            @Override
            public void onFinished(String result) {
                Message message =Message.obtain(handler);
                message.obj=result;
                 handler.sendMessageDelayed(message,3000);
            }
        });
        myModel.execute();
    }
}
