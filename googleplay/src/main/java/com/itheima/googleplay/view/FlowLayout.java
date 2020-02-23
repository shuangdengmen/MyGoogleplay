package com.itheima.googleplay.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FlowLayout extends ViewGroup {
    private ArrayList<Line> lines = new ArrayList<Line>();
    private boolean isHasRemain = false;

    public void setHasRemain(boolean hasRemain) {
        isHasRemain = hasRemain;
    }

    public void setHorizontalSpec(int horizontalSpec) {
        this.horizontalSpec = horizontalSpec;
    }

    public void setVerticalSpec(int verticalSpec) {
        this.verticalSpec = verticalSpec;
    }

    public int horizontalSpec =15;
    //纵向的padding
    public int verticalSpec=15;

    public FlowLayout(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int padingleft =getPaddingLeft();
        int paddingTop = getPaddingTop();
        for(int i = 0 ;i<lines.size();i++){
            Line line = lines.get(i);
            int paddingWidth=0;
            int remain=0;
            int permain=0;
            if(!isHasRemain){
                paddingWidth=getPaddingWidth();
                remain = paddingWidth-line.lineWidth;
                permain = remain/line.listView.size() ;
            }

            if(i>0){
                paddingTop +=lines.get(i-1).lineHeight+verticalSpec;
            }

            for (int j = 0; j <line.listView.size() ; j++) {

                View view = line.listView.get(j);

                if(!isHasRemain){
                    view.setPadding(view.getPaddingLeft()+permain/2,getPaddingTop(),view.getPaddingRight()+permain/2,getPaddingBottom());
                    view.measure(0,0);
                }
                if(j==0){
                    view.layout(padingleft,paddingTop,padingleft+view.getMeasuredWidth(),paddingTop+view.getMeasuredHeight());
                }else{
                    View left = line.listView.get(j-1);
                    view.layout(left.getRight()+horizontalSpec,left.getTop(),left.getRight()+horizontalSpec+view.getMeasuredWidth(),left.getBottom());
                }
            }
        }
    }

    private int getPaddingWidth() {
        return getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Line line = new Line();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int noPaddingWidth = width -getPaddingLeft()-getPaddingRight();
        for (int i = 0; i <getChildCount() ; i++) {
            View child = getChildAt(i);
            child.measure(0,0);
            if(line.listView.size()==0){
                line.addView(child);
            }else if(line.lineWidth+child.getMeasuredHeight()+horizontalSpec>noPaddingWidth){
                lines.add(line);

                line = new Line();
                line.addView(child);
            }else {
                line.addView(child);
            }
            if(i==(getChildCount()-1)){
                lines.add(line);
            }
        }

        int height=getPaddingTop()+getPaddingBottom();
        for(Line l : lines){
            height+=l.lineHeight;
        }
        height+=(lines.size()-1)*verticalSpec;

         setMeasuredDimension(width,height);
    }

    class Line{
        ArrayList<View> listView = new ArrayList<View>();
        private int lineWidth;
        private  int lineHeight;
        public void addView(View view){

            listView.add(view);
            if(listView.size()==1){
                lineWidth=view.getMeasuredWidth();
            }else {
                lineWidth+=view.getMeasuredWidth()+ horizontalSpec;
            }
            lineHeight=view.getMeasuredHeight();
        }
    }
}
