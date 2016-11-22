package com.crakama.refugee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by User on 10/12/2016.
 */

public class OnRepChildItemClickListener implements RecyclerView.OnItemTouchListener{

    private OnItemClickListener onItemClickListener;
    GestureDetector gestureDetector;

    public OnRepChildItemClickListener(Context c, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        gestureDetector = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public  boolean onSingleTapUp(MotionEvent e){
                return  true;
            }
        });
    }
    public interface OnItemClickListener{
        public void  onItemClick(View view, int i);
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)) {
            onItemClickListener.onItemClick(child, rv.getChildLayoutPosition(child));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}
