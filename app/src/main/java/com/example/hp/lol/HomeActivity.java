package com.example.hp.lol;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;


public class HomeActivity extends FragmentActivity {


    public static HomeActivity instance = null;

    private ViewPager mTabPager;
    private ImageView mTabImg;
    private ImageView mTab1, mTab2, mTab3, mTab4;
    private int zero = 0;
    private int currIndex = 0;
    private int one;
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display = false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;
    private ArrayList<Fragment> fragmentList;
    private ImageButton toReleaseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//
        instance = this;

        mTabPager = (ViewPager) findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mTab1 = (ImageView) findViewById(R.id.img_a);
        mTab2 = (ImageView) findViewById(R.id.img_b);
        mTab3 = (ImageView) findViewById(R.id.img_c);
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));

        Display currDisplay = getWindowManager().getDefaultDisplay();// 获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();

        fragmentList = new ArrayList<Fragment>();
        Fragment f1 = new HallFragment();
        Fragment f2 = new FriendFragment();
        Fragment f3 = new PersonFragment();
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);

        mTabPager.setAdapter(new MyfragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mTabPager.setCurrentItem(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    }
}
