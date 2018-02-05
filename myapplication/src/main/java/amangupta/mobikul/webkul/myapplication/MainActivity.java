package amangupta.mobikul.webkul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobikul.customswipeableviewpager.MobikulAutoScrollableView;

public class MainActivity extends AppCompatActivity {

    private MobikulAutoScrollableView mobikulAutoScrollableView;
    private CustomPagerAdapter mCustomPagerAdapter;
    int res[] = {R.drawable.res1, R.drawable.res2, R.drawable.res3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomPagerAdapter = new CustomPagerAdapter(this, res);
        mobikulAutoScrollableView = findViewById(R.id.my_pager_layout);
//        mobikulAutoScrollableView.setBackgroundColor(getColor(R.color.colorAccent));

        mobikulAutoScrollableView.setAdapter(mCustomPagerAdapter);
        mobikulAutoScrollableView.showBullets();
        mobikulAutoScrollableView.setPageSwitchTime(3);
//        mobikulAutoScrollableView.getMyCustomViewPager();
        mobikulAutoScrollableView.setAutoSrcollEnable(false);
        mobikulAutoScrollableView.getMyCustomViewPager().autoMeasureEnabled(true);
    }
}