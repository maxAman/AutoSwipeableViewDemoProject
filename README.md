# MobikulAutoScrollView

This is a demo project to show the MobikulAutoSrcollView library uses. MobikulAutoScrollView is a simple library to achieve some basic functionality which we always need when we use the Viewpager like,

## Features

* Auto Scrolling the item,
* Show bullets or page counter,
* Manage SwitchTime between pages and so on.


## How we use

simply use the Below layout,
```xml
   <com.mobikul.customswipeableviewpager.MobikulAutoScrollableView
         android:id="@+id/my_pager_layout"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         app:pageSwitchTime="3"/>
```

```Activity

  CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, res);

#### like all viewpager add the adapter,
        mobikulAutoScrollableView.setAdapter(mCustomPagerAdapter);

#### like all viewpager add the adapter,
BottomNavigationItem item1 = new BottomNavigationItem("menu1", R.drawable.ic_bag);

BottomNavigationItem item2 = new BottomNavigationItem("menu2", R.drawable.ic_vector_home);

 bottomNavigationItems.add(item1);
 bottomNavigationItems.add(item2);

 bottomNavigation.addItems(bottomNavigationItems);

#### Add Notification Badge

bottomNavigation.setNotification("notification no", int item posiition);

bottomNavigation.setNotificationBackgroundColor(Color.RED);
bottomNavigation.setNotificationTextColor(Color.WHITE);