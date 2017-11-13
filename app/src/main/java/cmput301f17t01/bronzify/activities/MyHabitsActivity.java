package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.NavigationController;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class MyHabitsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Intent intent;
    private ArrayList<HabitType> eventList;
    private ArrayList<DayOfWeek> schedule;
    private Date time;
    private Button sideBar;

    public Fragment detailsFragment;
    public Fragment eventsFragment;

    // getters and setters
    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public ArrayList<HabitType> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<HabitType> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<DayOfWeek> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<DayOfWeek> schedule) {
        this.schedule = schedule;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Button getSideBar() {
        return sideBar;
    }

    public void setSideBar(Button sideBar) {
        this.sideBar = sideBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.createNewHabit);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MyHabitsActivity.this, "Test", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyHabitsActivity.this, CreateNewHabitType.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Activity currentActivity = MyHabitsActivity.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
