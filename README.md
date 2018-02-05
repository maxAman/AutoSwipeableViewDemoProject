# MobikulAutoScrollView

This is a demo project to show the MobikulAutoSrcollView library uses. MobikulAutoScrollView is a simple library to achieve some basic functionality which we always need when we use the Viewpager like,

## Features

* Auto Scrolling the item,
* Show bullets or page counter,
* Manage SwitchTime between pages and so on.


## How to use?

#### Import the library,

1. Download the **MobikulAutoScrollableView-release**,
2. Right click on your Project -> Select **New** -> Select **Module** -> Select **Import .JAR/.AAR package** option,
3. Select **MobikulAutoScrollableView-release.aar** and click **finish**,
4. Now add following code to your app level gradle for importing this library to your project,
``` java
   dependencies {
         compile project(':MobikulAutoScrollableView-release')
   }
```
5. Voila! You have seccessfully import the library to your Project.

## Implementation

Simply use the Below layout,

#### XML
``` xml
   <com.mobikul.customswipeableviewpager.MobikulAutoScrollableView
         android:id="@+id/my_pager_layout"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         app:pageSwitchTime="3"/>
```
#### Activity/Fragments
``` java
  CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, res);
  mobikulAutoScrollableView.setAdapter(mCustomPagerAdapter);
```
##### For show bullets,
```java
        mobikulAutoScrollableView.showBullets();
 ```

##### For set page switch time programmeticaly,
```java
        mobikulAutoScrollableView.setPageSwitchTime(3);
```
##### Enable autoscrolling,
```java
        mobikulAutoScrollableView.setAutoSrcollEnable(false);
```
##### Enable autoMeasureEnabled,
```java
         mobikulAutoScrollableView.getMyCustomViewPager().autoMeasureEnabled(true);
```
## Licence

MIT License

Copyright (c) 2018 Webkul

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

