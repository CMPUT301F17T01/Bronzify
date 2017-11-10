package cmput301f17t01.bronzify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
// import android.support.design.widget.TabItem;

import java.util.ArrayList;

import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.NavigationController;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class HabitHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // private TabItem feedTab;
    // private TabItem mapTab;
    private ToggleButton toggleFilter;
    private ArrayList<HabitEvent> habitEvents;
    private EditText searchBar;
    private Button sideBar;

    //protected void onClickFeed() {}

    //protected void onClickMap() {}

    // getters and setters
    public ToggleButton getToggleFilter() {
        return toggleFilter;
    }

    public void setToggleFilter(ToggleButton toggleFilter) {
        this.toggleFilter = toggleFilter;
    }

    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents) {
        this.habitEvents = habitEvents;
    }

    public EditText getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(EditText searchBar) {
        this.searchBar = searchBar;
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
        setContentView(R.layout.activity_habit_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        Activity currentActivity = HabitHistoryActivity.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
