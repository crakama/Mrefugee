package com.crakama.refugee;

import android.app.Service;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        ServiceListFragment.OnGridItemFragInteractionListener,DialogInterface.OnClickListener,
        HomeTabFrag.OnHomeTabFragListener,DashBoardFrag.OnDashBoardFragListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private ClassPagerAdapter adapter;

    ArrayList<String> camps = new ArrayList<String>();

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
        initInstances();


    }

    @Override
    public void itemClicked(int pos, long id) {

        switch (pos) {
            case 0:
                //do
                Toast.makeText(this, "GridView Item NO: ", Toast.LENGTH_LONG).show();
                break;
            case 1:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ServiceDetailsFragment camplistfragment = new ServiceDetailsFragment();
                ft.replace(R.id.contentl, camplistfragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                break;

            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            case 6:
                //
                break;
        }



        //switch case statement
        //INITIALIZE DB ADAPTER
//        final DBAdapter sqlDBAdapter = new DBAdapter(this);
//
//        //RETRIEVE CAMP NAMES FROM DB
//        camps.clear();
//        sqlDBAdapter.openDB();
//        Cursor c = sqlDBAdapter.getAllCampDetails();
//        while(c.moveToNext()){
//            // 1 is column index in the table
//            String campname = c.getString(1);
//            camps.add(campname);
//        }
//        sqlDBAdapter.close();

        /** SHOW DIALOGUE*/
//        if(this!= null){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            int campNum = camps.size();
//            String[] campnames = new String[campNum];
//            for(int cm =0;cm <campNum; cm++){
//                campnames[cm]= camps.get(cm);
//            }
//            //set items
//            builder.setItems(campnames, this);
//        }else{
//            throw new RuntimeException(" CONTEXT IS NULL,");
//        }


    }


    @Override
    public void onClick(DialogInterface dialogInterface, int position) {
        Toast.makeText(this,camps.get(position), Toast.LENGTH_SHORT).show();
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
            switch (position) {
                case 0:
                    return HomeTabFrag.newInstance(position);

                case 1:

                    return ServiceListFragment.newInstance(position);

                case 2:
                    return DashBoardFrag.newInstance(position);

                default:
                    return null;
            }
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

    /** CODE FOR NAVIGATION DRAWER */

    private void initInstances() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcome, R.string.welcome);
        drawerLayout.addDrawerListener(drawerToggle);
        //Initializing NavigationView
        navigation = (NavigationView) findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        //Do some thing here
                        break;
                    case R.id.navigation_item_2:
                        //Do some thing here
                        break;
                    case R.id.navigation_item_3:
                        //Do some thing here
                        break;
                    case R.id.navigation_item_4:
                        //Do some thing here
                        break;

                }
                return false;
            }
        });

    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

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
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }





}// Ends Main Class
