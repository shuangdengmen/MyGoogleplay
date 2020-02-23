package com.itheima.googleplay.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.itheima.googleplay.util.LogUtil;
import com.itheima.googleplay.view.StateLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public abstract class BaseFragment extends Fragment implements StateLayout.OnReloadListener{

    public StateLayout stateLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RxPermissions rxPermission = new RxPermissions(BaseFragment.this); // where this is an Activity or Fragment instance
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable disposable = rxPermission.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //申请的权限全部允许
                    Toast.makeText(getContext(), "允许了权限!", Toast.LENGTH_SHORT).show();
                    //调用状态布局
                    executeStateLayout();
                } else {
                    //只要有一个权限被拒绝，就会执行
                    Toast.makeText(getContext(), "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
        });
        compositeDisposable.add(disposable);
        return stateLayout;
    }

    private void executeStateLayout() {
        if (stateLayout == null) {
            stateLayout = new StateLayout(getContext());
            stateLayout.setOnReloadListener(BaseFragment.this);
            stateLayout.bindSuccessView(getSucessView());
            stateLayout.showLoadingView();
            loadData();
        }
    }

    @Override
    public void onReload() {
        LogUtil.d("调用了onReLoad...");
        loadData();
    }

    abstract public View getSucessView() ;

    abstract public void loadData();

}
