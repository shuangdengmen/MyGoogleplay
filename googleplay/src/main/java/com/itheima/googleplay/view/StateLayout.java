package com.itheima.googleplay.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.itheima.googleplay.R;

public class StateLayout extends FrameLayout {
    private View sucessView;
    private View loadingView;
    private View errorView;
    private OnReloadListener onReloadListener;
    public StateLayout(@NonNull Context context) {
        this(context,null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        addView(loadingView);
        errorView = View.inflate(getContext(), R.layout.page_error, null);
         Button button= (Button) errorView.findViewById(R.id.btn_reload);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onReloadListener.onReload();
            }
        });
        addView(errorView);

        hideAll();
    }

    public void bindSuccessView(View view){
        this.sucessView=view;
        if(sucessView!=null){

            sucessView.setVisibility(View.INVISIBLE);
            addView(sucessView);
        }
    }

    public void hideAll(){
        if(sucessView !=null){
            sucessView.setVisibility(View.INVISIBLE);
        }
        loadingView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
    }

    public void showSucessView(){
        hideAll();
        if(sucessView!=null){
            sucessView.setVisibility(View.VISIBLE);
        }
    }
    public void showErrorView(){
        hideAll();
        errorView.setVisibility(View.VISIBLE);
    }

    public void showLoadingView(){
        hideAll();
        loadingView.setVisibility(View.VISIBLE);
    }

    public void setOnReloadListener(OnReloadListener onReloadListener){
        this.onReloadListener =onReloadListener;
    }
    public interface OnReloadListener{
        public void onReload();
    }
}
