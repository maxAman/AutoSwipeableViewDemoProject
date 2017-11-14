package com.mobikul.customswipeableviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aman.gupta on 7/11/17.
 */

public class MyCustomViewPager extends ViewPager {
    Context context;

    private int seconds = 1;
    private boolean autoSrcoll = false;

    public MyCustomViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public MyCustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


}