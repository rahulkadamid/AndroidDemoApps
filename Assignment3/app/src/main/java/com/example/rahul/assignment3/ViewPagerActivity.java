package com.example.rahul.assignment3;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Locale;

public class ViewPagerActivity extends AppCompatActivity {
    MyFragmentStatePagerAdapter mypagerAdapter;
    ViewPager mviewPager;
    MovieData movieData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        movieData = new MovieData();
        mypagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), movieData.getSize());
        mviewPager = (ViewPager)findViewById(R.id.activity_view_pager);
        mviewPager.setAdapter(mypagerAdapter);
        mviewPager.setCurrentItem(3);
        customizeViewPager();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);
    }
    private void customizeViewPager()
    {

        mviewPager.setPageTransformer(false, new ViewPager.PageTransformer()
        {
          @Override
          public void transformPage(View page, float position)
          {
              final float normalised_position = Math.abs(Math.abs(position)-1);
              page.setScaleX(normalised_position/ 2+0.5f);
              page.setScaleY(normalised_position/ 2+0.5f);
              page.setRotationY(position * -30);

          }
        });

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i2)
            {
            }
            @Override
            public void onPageSelected(int i)
            {

            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
    public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        int count;
        public MyFragmentStatePagerAdapter(FragmentManager fm, int size) {
            super(fm);
            count = size;
        }
        @Override
        public Fragment getItem(int position) {

            return MovieFragment.newInstance(movieData.getItem(position));
        }
        @Override
        public int getCount() { return count;}
        @Override
        public CharSequence getPageTitle(int position)
        {
            Locale l = Locale.getDefault();
            HashMap<String, ?> movie = (HashMap<String, ?>)movieData.getItem(position);
            String name = (String)movie.get("name");
            return name.toUpperCase(l);
        }
    }
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        int count;
        public MyFragmentPagerAdapter(FragmentManager fm, int size)
        {
            super(fm);
            count = size;
        }

        @Override
        public Fragment getItem(int position)
        {
            return MovieFragment.newInstance(movieData.getItem(position));
        }

        @Override
        public int getCount() { return count;}

        @Override
        public CharSequence getPageTitle(int position)
        {
            Locale l = Locale.getDefault();
            HashMap<String, ?> movie = (HashMap<String, ?>)movieData.getItem(position);
            String name = (String)movie.get("name");
            return name.toUpperCase(l);
        }

    }
}

