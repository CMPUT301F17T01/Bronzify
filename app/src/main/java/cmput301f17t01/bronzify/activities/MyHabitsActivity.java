package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyHabitAdapter;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class MyHabitsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AppLocale appLocale = AppLocale.getInstance();

    private List<HabitType> types = new ArrayList<HabitType>();

    /**
     * Called on the creation of the My Habits Activity
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_habits);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Username in NavBar
        User currentUser = AppLocale.getInstance().getUser();
        View hView =  navigationView.getHeaderView(0);
        TextView usernameNav = hView.findViewById(R.id.userNameNav);
        usernameNav.setText(currentUser.getUserID());

        types = new ArrayList<HabitType>();
        recyclerView = findViewById(R.id.myHabitRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        final MyHabitAdapter fa = new MyHabitAdapter(this,types);
        recyclerView.setAdapter(fa);

        /*TextView test = (TextView) findViewById(R.id.habitTypeRow);
        test.setText("TESTING"); */

        FloatingActionButton fab = findViewById(R.id.createNewHabit);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MyHabitsActivity.this, CreateNewHabitTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillTypesList();
        MyHabitAdapter myHabitAdapter = new MyHabitAdapter(this, types);
        recyclerView.setAdapter(myHabitAdapter);
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


    private void fillTypesList(){
        User user = AppLocale.getInstance().getUser();
        ArrayList<HabitType> habitTypes = user.getHabitTypes();
        types.clear();
        for(HabitType type: habitTypes){
            types.add(type);
        }
    }
}
