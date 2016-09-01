package com.crakama.refugee;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private ClassPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ClassPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
                        getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
        tabs.setIndicatorColor(Color.parseColor("#3f51B5"));


    }


    public class ClassPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Home", "Agency Services","Camp Notice Board"};
        //Constructor class
        public ClassPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //get card fragment to display the cards(items) when different tabs are swipped
        @Override
        public Fragment getItem(int position) {
            return CardsFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
          return TITLES[position];
         }


    }//End ClassPagerAdapter class









}// Ends Main Class
