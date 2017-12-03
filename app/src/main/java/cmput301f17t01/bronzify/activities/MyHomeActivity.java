package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyEventAdapter;
import cmput301f17t01.bronzify.adapters.recyclers.MyHabitAdapter;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class MyHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AppLocale appLocale = AppLocale.getInstance();

    private List<HabitEvent> events = new ArrayList<HabitEvent>();

    /**
     * Called on the creation of the My Home Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ListFragment fragment = new ListFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.myEventRecycler);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillEventList();
        MyEventAdapter myEventAdapter = new MyEventAdapter(this, events);
        recyclerView.setAdapter(myEventAdapter);
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
        if(!(id == R.id.MyHome)) {
            Activity currentActivity = MyHomeActivity.this;
            Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
            startActivity(newActivity);
            finish();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillEventList(){
        User user = AppLocale.getInstance().getUser();
        ArrayList<HabitType> habitTypes = user.getHabitTypes();
        events.clear();
        for(HabitType type: habitTypes){
            ArrayList<HabitEvent> habitEvents = type.getHabitEvents();
            for(HabitEvent event: habitEvents){
                Date eventDate = getZeroTimeDate(event.getGoalDate());
                Date currentDate = getZeroTimeDate(new Date());
                int dateDiff = eventDate.compareTo(currentDate);
                if(dateDiff == 0){
                    events.add(event);
                }
            }
        }
    }

    // Set time to 00:00:00
    public static Date getZeroTimeDate(Date date) {
        Date res = date;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
