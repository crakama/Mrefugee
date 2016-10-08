package com.crakama.refugee.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.crakama.refugee.Auth.LoginActivity;
import com.crakama.refugee.Auth.RegisterActivity;
import com.crakama.refugee.Auth.ResetPasswordActivity;
import com.crakama.refugee.DadaabCamp;
import com.crakama.refugee.Fragments.NoticeBoardFrag;
import com.crakama.refugee.Fragments.LiveNewsFrag;
import com.crakama.refugee.R;
import com.crakama.refugee.Fragments.ServiceListFragment;

public class MainActivity extends AppCompatActivity implements
        ServiceListFragment.OnGridItemFragInteractionListener,
        LiveNewsFrag.OnHomeTabFragListener, NoticeBoardFrag.OnDashBoardFragListener {


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

        /** INITIALIZE TABS*/
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

        /** CALL    METHOD */
        initInstances();


    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void itemClicked(int pos, long id) {

        switch (pos) {
            case 0:
                //do
                Toast.makeText(this, "GridView Item NO: ", Toast.LENGTH_LONG).show();
                break;
            case 1:
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
                builderSingle.setIcon(R.drawable.ic_listcamps);
                builderSingle.setTitle("Select One Name:-");
                //Create a String array of the course names
                String[] names = new String[DadaabCamp.camps.length];
                for (int i = 0; i < names.length; i++) {
                    names[i] = DadaabCamp.camps[i].getName();
                }

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
                builderSingle.setAdapter(
                        arrayAdapter,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                        MainActivity.this);
                                builderInner.setMessage(strName);
                                builderInner.setTitle("Your Selected Item is");
                                builderInner.setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                builderInner.show();
                            }
                        });
                builderSingle.show();
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
    }

    /**
     * CODE FOR NAVIGATION DRAWER
     */

    private void initInstances() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.welcome, R.string.welcome);
        drawerLayout.addDrawerListener(drawerToggle);


 //       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//
//        if(islogin)
//        {
//            navigationView.getMenu().clear();
//            navigationView.inflateMenu(R.menu.navigation_with_login);
//        } else
//        {
//            navigationView.getMenu().clear();
//            navigationView.inflateMenu(R.menu.navigation_with_logout);
//        }



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
                            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            break;
                        case R.id.navigation_item_5:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_6:
                            //Do some thing here
                            Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                            startActivity(regIntent);
                            break;
                        case R.id.navigation_item_3:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_4:
                            //Do some thing here
                            break;
                        case R.id.navigation_item_7:
                            //Do some thing here
                            Intent resetIntent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                            startActivity(resetIntent);
                            break;
                        case R.id.nv_updatenoticeboard:
                            //Do some thing here
                            Intent noticeBoardIntent = new Intent(MainActivity.this, UpdateNoticeBoard.class);
                            startActivity(noticeBoardIntent);
                            break;
                        case R.id.navigation_item_8:
                            //Do some thing here
                            Intent newsIntent = new Intent(MainActivity.this, UpdateNews.class);
                            startActivity(newsIntent);
                            break;

                    }
                    return false;
                }
            });//end oclick listener



//        if (islogin) {
//            navigation.getMenu().clear();
//            navigation.inflateMenu(R.menu.navigation_with_login);
//            navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(MenuItem menuItem) {
//                    int id = menuItem.getItemId();
//                    switch (id) {
//                        case R.id.navigation_item_1:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_5:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_3:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_4:
//                            //Do some thing here
//                            break;
//
//                    }
//                    return false;
//                }
//            });//end oclick listener
//        } else {
//            navigation.getMenu().clear();
//            navigation.inflateMenu(R.menu.navigation_with_logout);
//            navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(MenuItem menuItem) {
//                    int id = menuItem.getItemId();
//                    switch (id) {
//                        case R.id.navigation_item_1:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_5:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_3:
//                            //Do some thing here
//                            break;
//                        case R.id.navigation_item_4:
//                            //Do some thing here
//                            break;
//
//                    }
//                    return false;
//                }
//            });//end oclick listener
//        }

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
        } else if (id == R.id.action_addcamp) {
            Intent addcampIntent = new Intent(MainActivity.this, UpdateNews.class);
            startActivity(addcampIntent);
            return true;

        }else if(id == R.id.action_followup){
            startActivity(new Intent(this, IssueFollowUp.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    public class ClassPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Home", "Agency Services", "Camp Notice Board"};

        //Constructor class
        public ClassPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //get card fragment to display the cards(items) when different tabs are swipped
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return LiveNewsFrag.newInstance(position);

                case 1:

                    return ServiceListFragment.newInstance(position);

                case 2:
                    return NoticeBoardFrag.newInstance(position);

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


}// Ends Main Class
