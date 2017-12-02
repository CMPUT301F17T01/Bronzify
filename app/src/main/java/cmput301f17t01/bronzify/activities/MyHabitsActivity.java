package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.ViewPagerAdapter;
import cmput301f17t01.bronzify.adapters.recyclers.MyHabitAdapter;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class MyHabitsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private String name;
    private AppLocale appLocale = AppLocale.getInstance();
    private ViewPager viewPager;
    private List<HabitType> types;

    /**
     * Called on the creation of the My Habits Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        name = appLocale.getUser().getUserID();
        viewPager = findViewById(R.id.view_pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        types =(List) new ArrayList<HabitType>();
        createTypesList();
        rv = (RecyclerView) findViewById(R.id.myHabitRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        MyHabitAdapter fa = new MyHabitAdapter(this,types);
        rv.setAdapter(fa);

        FloatingActionButton fab = findViewById(R.id.createNewHabit);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MyHabitsActivity.this, CreateNewHabitTypeActivity.class);
                startActivity(intent);
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
        if(!(id == R.id.MyHabits)) {
            Activity currentActivity = MyHabitsActivity.this;
            Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
            startActivity(newActivity);
            finish();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createTypesList(){
        Boolean[] array = new Boolean[7];
        Arrays.fill(array, Boolean.FALSE);
        types.add(new HabitType("Running","Cuz",new Date(),array ));
        types.add(new HabitType("Jogging","Cuz",new Date(),array ));
        types.add(new HabitType("Eating","Cuz",new Date(),array ));
        types.add(new HabitType("Sleeping","Cuz",new Date(),array ));
        types.add(new HabitType("Crying","Cuz",new Date(),array ));
        types.add(new HabitType("Singing","Cuz",new Date(),array ));
        types.add(new HabitType("Running","Cuz",new Date(),array ));
        types.add(new HabitType("Jogging","Cuz",new Date(),array ));
        types.add(new HabitType("Eating","Cuz",new Date(),array ));
        types.add(new HabitType("Sleeping","Cuz",new Date(),array ));
        types.add(new HabitType("Crying","Cuz",new Date(),array ));
        types.add(new HabitType("Singing","Cuz",new Date(),array ));
        types.add(new HabitType("Running","Cuz",new Date(),array ));
        types.add(new HabitType("Jogging","Cuz",new Date(),array ));
        types.add(new HabitType("Eating","Cuz",new Date(),array ));
        types.add(new HabitType("Sleeping","Cuz",new Date(),array ));
        types.add(new HabitType("Crying","Cuz",new Date(),array ));
        types.add(new HabitType("Singing","Cuz",new Date(),array ));


    }
}
