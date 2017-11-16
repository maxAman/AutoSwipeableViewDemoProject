package com.mobikul.customswipeableviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aman.gupta on 13/11/17.
 */

public class MobikulAutoScrollableView extends RelativeLayout {
    private Context mContext;
    public MyCustomViewPager myCustomViewPager;
    public LinearLayout linearLayout;
    private View[] dotList;
    private DetailOnPageChangeListener listener;
    private int seconds = 1;
    private boolean autoSrcoll = false;
    private RemindTask swipeAtInterval;
    private Timer timer;
    private int page;

    public MobikulAutoScrollableView(Context context) {
        super(context);
        initializeCustomView(context);
    }

    public MobikulAutoScrollableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeCustomView(context);
        initViewPager(attrs);
    }

    public MobikulAutoScrollableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeCustomView(context);
        initViewPager(attrs);
    }

    private void initializeCustomView(Context context) {
        mContext = context;
        myCustomViewPager = new MyCustomViewPager(mContext);
        myCustomViewPager.setId(View.generateViewId());
        linearLayout = new LinearLayout(mContext);
        this.addView(myCustomViewPager);
    }

    void initViewPager(AttributeSet attrs) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MobikulAutoScrollableView,
                0, 0);
        try {
            autoSrcoll = a.getBoolean(R.styleable.MobikulAutoScrollableView_autoSrcoll, false);
            seconds = a.getInt(R.styleable.MobikulAutoScrollableView_pageSwitchTime, 1);
        } finally {
            a.recycle();
        }
    }

    // set the Page Switch Interval timeing in seconds.
    public void setPageSwitchTime(int seconds) {
        this.seconds = seconds;
    }

    // get the Page Switch Interval timeing in seconds.
    public int getPageSwitchTime() {
        return seconds;
    }

    // for enabling the auto scrolling in CustomAutoScrollableViewPager
    // TODO: 9/11/17 setAutoSrcollEnable method should be call after setting the Adapter Always .
    public void setAutoSrcollEnable(boolean isAutoScrollEnable) {
        this.autoSrcoll = isAutoScrollEnable;
        if (autoSrcoll) {
            pageSwitcher();
        }
    }

    // method for auto switching
    private void pageSwitcher() {
        if (myCustomViewPager.getAdapter() != null) {
            swipeAtInterval = new RemindTask(myCustomViewPager.getAdapter().getCount());
            timer = new Timer();
            timer.scheduleAtFixedRate(swipeAtInterval, 0, seconds * 1000);
        }
    }

    // for getting auto Scrolling is Enabled or not
    public boolean isAutoSrcollEnable() {
        return autoSrcoll;
    }

    // this is a thread class by which the CustomAutoScrollableViewPager can perform the auto scrolling behavior
    class RemindTask extends TimerTask {
        int noOfBanners;

        RemindTask(int noOfBanners) {
            this.noOfBanners = noOfBanners;
        }

        @Override
        public void run() {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (page > noOfBanners)
                        timer.cancel();
                    else if (page == noOfBanners - 1) {
                        myCustomViewPager.setCurrentItem(0);
                        page = 0;
                    } else
                        myCustomViewPager.setCurrentItem(++page);
                }
            });
        }
    }

    /**
     * Set a PagerAdapter that will supply views for this pager as needed.
     *
     * @param pagerAdapter Adapter to use
     */
    public void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter != null) {
            myCustomViewPager.setAdapter(pagerAdapter);
        }
    }

    public MyCustomViewPager getMyCustomViewPager() {
        return myCustomViewPager;
    }

    public void setEnabledPageCounter() {
        dotList = new ImageView[myCustomViewPager.getAdapter().getCount()];
        for (int i = 0; i < myCustomViewPager.getAdapter().getCount(); i++) {
            ImageView dotImage = new ImageView(mContext);
            dotList[i] = dotImage;
            if (i == 0)
                dotList[i].setBackgroundResource(R.drawable.selecteditem_dot);
            else
                dotList[i].setBackgroundResource(R.drawable.nonselecteditem_dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(10, 0, 10, 0);
            linearLayout.addView(dotImage, params);
        }
        this.addView(linearLayout);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        listener = new DetailOnPageChangeListener();
        myCustomViewPager.addOnPageChangeListener(listener);
    }

    public class DetailOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            for (int i = 0; i < myCustomViewPager.getAdapter().getCount(); i++) {
                if (i == position)
                    dotList[i].setBackgroundResource(R.drawable.selecteditem_dot);
                else
                    dotList[i].setBackgroundResource(R.drawable.nonselecteditem_dot);
            }
        }

        public int getCurrentPage() {
            return currentPage;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float Offset, int positionOffsetPixels) {
            if (Offset > 0.5f) {
            }
        }
    }
}