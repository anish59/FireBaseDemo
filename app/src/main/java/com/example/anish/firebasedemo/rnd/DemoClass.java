package com.example.anish.firebasedemo.rnd;

import android.content.Context;

/**
 * Created by anish on 04-08-2017.
 */

public class DemoClass {
    Context context;
    OnTouchListener onTouchListener;

    public DemoClass(Context context, OnTouchListener onTouchListener) {
        this.context = context;
        this.onTouchListener = onTouchListener;
    }

    public interface OnTouchListener {
        void onTouch();
    }
}
