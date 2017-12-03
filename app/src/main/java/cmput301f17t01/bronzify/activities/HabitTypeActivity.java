package cmput301f17t01.bronzify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.ArrayList;

import cmput301f17t01.bronzify.adapters.ViewPagerAdapter;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.models.HabitType;

/**
 * Created by owenm_000 on 11/1/2017.
 */

public class HabitTypeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Details", "Events"};

    private HabitType selectedHabit;

    /**
     * Called on the creation of the Habit Type Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_type);

        int pos = getIntent().getExtras().getInt("SELECTED_HABIT");
        Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ListFragment fragment = new ListFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        viewPager = findViewById(R.id.view_pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Create number of tabs
        tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            /**
             * Changes the viewPager when the tab is selected.
             *
             * @param tab
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            /**
             * Called when the tab is unselected
             *
             * @param tab
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            /**
             * Called when the tab is reselected
             *
             * @param tab
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * Called when the back button is pressed
     *
     */
    @Override
    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Creates the navigation drawer
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    /**
     * Creates the settings bar in the top corner
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Opens up the navigation bar
     *
     * @param item
     * @return
     */
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Activity currentActivity = HabitTypeActivity.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
