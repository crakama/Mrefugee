package com.crakama.refugee.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.crakama.refugee.UpdateDatabase.UpdateNews;
import com.crakama.refugee.UpdateDatabase.UpdateNoticeBoard;
import com.crakama.refugee.UpdateDatabase.UpdateTownInfo;
import com.crakama.refugee.Auth.LoginActivity;
import com.crakama.refugee.Fragments.LiveNewsFrag;
import com.crakama.refugee.Fragments.NoticeBoardFrag;
import com.crakama.refugee.Fragments.TabsFragment;
import com.crakama.refugee.R;

public class MainActivity extends AppCompatActivity implements
        LiveNewsFrag.OnHomeTabFragListener, NoticeBoardFrag.OnDashBoardFragListener{


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
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.img_helpdesk);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://drc.dk/about-drc"));
                startActivity(browserIntent);
            }
        });
        ImageButton imageButton2 = (ImageButton)findViewById(R.id.img_helpdesk2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://drc.dk/about-drc"));
                startActivity(browserIntent2);
            }
        });

        ImageButton imageButton3 = (ImageButton)findViewById(R.id.img_helpdesk3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent3 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://drc.dk/about-drc"));
                startActivity(browserIntent3);
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
        if(id == R.id.home){
            onBackPressed();
            return true;

        }else if(id == R.id.action_jobsearch){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://drc.dk/about-drc/vacancies"));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
      drawerToggle.syncState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "mfragment", tabsFragment);
    }


}// Ends Main Class