package com.camping.seoul.seoulcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import fragment.NearByFragment;
import fragment.NearByFragment2;
import weather.WeatherInfo;
import weather.WeatherInfo2;

public class Main_activity  extends AppCompatActivity {
    FrameLayout frameLayout;
    Button button_main,button_petspital;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mainpage);


        final ViewPager pager = (ViewPager) findViewById(R.id.weatherPager);
        pager.setOffscreenPageLimit(3); //프래그먼트를 미리 담아두기

        Main_activity.CampingPagerAdapter fragment = new Main_activity.CampingPagerAdapter(getSupportFragmentManager());

        /* Fragment 추가 */
        WeatherInfo fragment1 = new WeatherInfo();
        fragment.addItem(fragment1);

        WeatherInfo2 fragment2 = new WeatherInfo2();
        fragment.addItem(fragment2);
        pager.setAdapter(fragment);

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);

        replace_fragment(new NearByFragment2());

        button_main = findViewById(R.id.btn_main);
        button_petspital = findViewById(R.id.btn_petspital);
        layout = findViewById(R.id.layout_search);

        /*button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace_fragment(new NearByFragment());
                layout.setVisibility(View.GONE);
            }
        });
        button_petspital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace_fragment(new NearByFragment2());
                layout.setVisibility(View.VISIBLE);
            }
        });*/
    }




    public void replace_fragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

    class CampingPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public CampingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int i) {
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}