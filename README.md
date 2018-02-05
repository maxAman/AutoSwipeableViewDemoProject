# MobikulAutoScrollView

This is a demo project to show the MobikulAutoSrcollView library uses. MobikulAutoScrollView is a simple library to achieve some basic functionality which we always need when we use the Viewpager like,

## Features

* Auto Scrolling the item,
* Show bullets or page counter,
* Manage SwitchTime between pages and so on.


## How we use

simply use the Below layout,
xml
```
   <com.mobikul.customswipeableviewpager.MobikulAutoScrollableView
         android:id="@+id/my_pager_layout"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         app:pageSwitchTime="3"/>
```
Activity
```
  CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, res);
```
#### like all viewpager add the adapter,
```
        mobikulAutoScrollableView.setAdapter(mCustomPagerAdapter);
```
#### for show bullets,
```
        mobikulAutoScrollableView.showBullets();
 ```

#### for set page switch time programmeticaly,
```
        mobikulAutoScrollableView.setPageSwitchTime(3);
```
#### enable autoscrolling ,
```
        mobikulAutoScrollableView.setAutoSrcollEnable(false);
```
#### enable autoMeasureEnabled ,
```        mobikulAutoScrollableView.getMyCustomViewPager().autoMeasureEnabled(true);
```
```
