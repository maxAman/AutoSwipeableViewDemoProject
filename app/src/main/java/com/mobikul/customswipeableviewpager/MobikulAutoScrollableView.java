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
    public MyCustomViewPager mobikulAutoScrollableViewPager;
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
        mobikulAutoScrollableViewPager = new MyCustomViewPager(mContext);
        linearLayout = new LinearLayout(mContext);
        this.addView(mobikulAutoScrollableViewPager);
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
        if (mobikulAutoScrollableViewPager.getAdapter() != null) {
            swipeAtInterval = new RemindTask(mobikulAutoScrollableViewPager.getAdapter().getCount());
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
                        mobikulAutoScrollableViewPager.setCurrentItem(0);
                        page = 0;
                    } else
                        mobikulAutoScrollableViewPager.setCurrentItem(++page);
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
            mobikulAutoScrollableViewPager.setAdapter(pagerAdapter);
        }
    }

    public MyCustomViewPager getMobikulAutoScrollableViewPager() {
        return mobikulAutoScrollableViewPager;
    }

    public void setEnabledPageCounter() {
        dotList = new ImageView[mobikulAutoScrollableViewPager.getAdapter().getCount()];
        for (int i = 0; i < mobikulAutoScrollableViewPager.getAdapter().getCount(); i++) {
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
        mobikulAutoScrollableViewPager.addOnPageChangeListener(listener);
//        mobikulAutoScrollableViewPager.setOnPageChangeListener(new CircularViewPagerHandler(mobikulAutoScrollableViewPager));
    }

    public class DetailOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int currentPage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            for (int i = 0; i < mobikulAutoScrollableViewPager.getAdapter().getCount(); i++) {
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

//    public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {
//        private ViewPager   mViewPager;
//        private int         mCurrentPosition;
//        private int         mScrollState;
//
//        public CircularViewPagerHandler(final ViewPager viewPager) {
//            mViewPager = viewPager;
//        }
//
//        @Override
//        public void onPageSelected(final int position) {
//            mCurrentPosition = position;
//        }
//
//        @Override
//        public void onPageScrollStateChanged(final int state) {
//            handleScrollState(state);
//            mScrollState = state;
//        }
//
//        private void handleScrollState(final int state) {
//            if (state == ViewPager.SCROLL_STATE_IDLE) {
//                setNextItemIfNeeded();
//            }
//        }
//
//        private void setNextItemIfNeeded() {
//            if (!isScrollStateSettling()) {
//                handleSetNextItem();
//            }
//        }
//
//        private boolean isScrollStateSettling() {
//            return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
//        }
//
//        private void handleSetNextItem() {
//            final int lastPosition = mViewPager.getAdapter().getCount() - 1;
//            if(mCurrentPosition == 0) {
//                mViewPager.setCurrentItem(lastPosition, false);
//            } else if(mCurrentPosition == lastPosition) {
//                mViewPager.setCurrentItem(0, false);
//            }
//        }
//
//        @Override
//        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
//        }
//    }

}