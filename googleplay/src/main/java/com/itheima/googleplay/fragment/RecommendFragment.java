package com.itheima.googleplay.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.itheima.googleplay.R;
import com.itheima.googleplay.http.HttpHelper;
import com.itheima.googleplay.http.Url;
import com.itheima.googleplay.util.ColorUtil;
import com.itheima.googleplay.util.DimenUtil;
import com.itheima.googleplay.util.GsonUtil;
import com.itheima.googleplay.util.ToastUtil;
import com.itheima.googleplay.view.randomlayout.StellarMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment {


    private StellarMap stellarMap;
    private ArrayList<String> strs;

    @Override
    public View getSucessView() {
        stellarMap = new StellarMap(getContext());
        int pading = DimenUtil.getDimens(R.dimen.dp15);
        stellarMap.setInnerPadding(pading,pading,pading,pading);

        return stellarMap;
    }

    @Override
    public void loadData() {
        HttpHelper.create().get(Url.RECOMMEND_PREFIX, new HttpHelper.HttpHelperCallBack() {
            @Override
            public void onSucess(String result) {
                stateLayout.showSucessView();
                strs = (ArrayList<String>) GsonUtil.parseJsonToList(result, new TypeToken<List<String>>() {
                }.getType());
                stellarMap.setAdapter(new MyAdapter());
                stellarMap.setGroup(1,true);
                stellarMap.setRegularity(4,4);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    private  class MyAdapter implements StellarMap.Adapter{

        @Override
        public int getGroupCount() {
            return strs.size()/getCount(0);
        }

        @Override
        public int getCount(int group) {
            return 10;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            final TextView textView = new TextView(getContext());
            int myPosition = position+group*getCount(group);
            textView.setText(strs.get(myPosition));

            Random random = new Random();
            textView.setTextSize(random.nextInt(15)+12);
            textView.setTextColor(ColorUtil.getColorBeatiful());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(textView.getText().toString());
                }
            });
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return (group+1)%getGroupCount();
        }
    }
}
