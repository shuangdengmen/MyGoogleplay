package com.itheima.googleplay.fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.R;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.ColorUtil;
import com.itheima.googleplay.util.DrawableUtil;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.LogUtil;
import com.itheima.googleplay.util.ToastUtil;
import com.itheima.googleplay.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment {


    private FlowLayout flowLayout;
    private ScrollView scrollView;
    private int dp6;
    private int dp9;
    private int dp15;
    @Override
    public View getSucessView() {
        dp6 = (int)getResources().getDimension(R.dimen.dp6);
        dp9 = (int) getResources().getDimension(R.dimen.dp9);
        dp15 = (int) getResources().getDimension(R.dimen.dp15);
        scrollView = new ScrollView(getContext());
        flowLayout = new FlowLayout(getContext());
        flowLayout.setPadding(dp15,dp15,dp15,dp15);
        flowLayout.setHorizontalSpec(dp15);
        flowLayout.setVerticalSpec(dp15);
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
        public void loadData() {
        LogUtil.d("hoturl:"+Url.HOT_PREFIX);
        HttpHelper.create().get(Url.HOT_PREFIX, new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {
                stateLayout.showSucessView();
                ArrayList<String> list = (ArrayList<String>) GsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
                }.getType());
                for (int i=0 ;i<list.size();i++){
                    final TextView textView = new TextView(getContext());
                    textView.setText(list.get(i));
                    textView.setTextSize(16);
                    textView.setTextColor(Color.WHITE);
                    textView.setBackgroundColor(ColorUtil.getColorBeatiful());
                    textView.setPadding(dp6,dp9,dp6,dp9);

                    Drawable normal = DrawableUtil.getGenarateDawable(dp15);
                    Drawable pressed = DrawableUtil.getGenarateDawable(dp15);
                    Drawable background = DrawableUtil.getStateListSelector(normal,pressed);
                    textView.setBackgroundDrawable(background);
                    flowLayout.setHorizontalSpec(dp15);
                    flowLayout.setVerticalSpec(dp15);
                    flowLayout.addView(textView);

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtil.showToast(textView.getText().toString());
                        }
                    });
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        }

}
