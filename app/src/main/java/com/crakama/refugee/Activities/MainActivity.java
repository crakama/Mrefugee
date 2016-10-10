package com.crakama.refugee.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crakama.refugee.Auth.LoginActivity;
import com.crakama.refugee.Auth.RegisterActivity;
import com.crakama.refugee.Auth.ResetPasswordActivity;
import com.crakama.refugee.Fragments.LiveNewsFrag;
import com.crakama.refugee.Fragments.NoticeBoardFrag;
import com.crakama.refugee.Fragments.RepatriationChildFrag;
import com.crakama.refugee.Fragments.RepatriationRootFrag;
import com.crakama.refugee.Fragments.TabsFragment;
import com.crakama.refugee.R;

public class MainActivity extends AppCompatActivity implements
        RepatriationRootFrag.OnRepartButtonClickedListener,
        LiveNewsFrag.OnHomeTabFragListener, NoticeBoardFrag.OnDashBoardFragListener ,
        RepatriationChildFrag.RepatriationChildFragListener{


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    private TabsFragment tabsFragment;
    Fragment mfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Check whether the activity is using the layout version with
        // the container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                //return;
                //Restore the fragment's instance
                mfragment = getSupportFragmentManager().getFragment(savedInstanceState, "mfragment");
            }

            // Create an instance of TabsFragment
            tabsFragment = new TabsFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            tabsFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, tabsFragment).commit();
            initInstances();
        }else {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of TabsFragment
            tabsFragment = new TabsFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            tabsFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'container' FrameLayout
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, tabsFragment).commit();
              initInstances();
        }



    }

    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    /**
     * Only Activity has this special callback method
     * Fragment doesn't have any onBackPressed callback
     *
     * Logic:
     * Each time when the back button is pressed, this Activity will propagate the call to the
     * container Fragment and that Fragment will propagate the call to its each tab Fragment
     * those Fragments will propagate this method call to their child Fragments and
     * eventually all the propagated calls will get back to this initial method
     *
     * If the container Fragment or any of its Tab Fragments and/or Tab child Fragments couldn't
     * handle the onBackPressed propagated call then this Activity will handle the callback itself
     */
    @Override
    public void onBackPressed() {

        if (!tabsFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();

        } else {
            // tabsFragment handled the back pressed task
            // do not call super
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void itemClicked(int pos, long id) {
        /*
        *  USAGES: LiveNewsFrag & NoticeBoardFrag
         */
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
//        drawerToggle.syncState();
    }



    @Override
    public void onRepartBtnClick(int position) {
        // The user selected the headline of an article from the HeadlinesFragment


        // Capture the RepatriationChildFrag fragment from the activity layout


        View fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            // If article frag is available, we're in two-pane layout...

            /*
            *
            * TO DO: Differenciate display for tablets and phones
             */
            RepatriationChildFrag repatriationChildFrag = new RepatriationChildFrag();
            // Call a method in the ArticleFragment to update its content
            //repatriationChildFrag.updateArticleView(position);
            Bundle args = new Bundle();
            args.putInt(RepatriationChildFrag.ARG_POSITION, position);
            repatriationChildFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, repatriationChildFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            RepatriationChildFrag repatriationChildFrag1 = new RepatriationChildFrag();
            Bundle args = new Bundle();
            args.putInt(RepatriationChildFrag.ARG_POSITION, position);
            repatriationChildFrag1.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.container, repatriationChildFrag1);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }



    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        onCreate(savedInstanceState);
//
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "mfragment", tabsFragment);
    }


}// Ends Main Class
