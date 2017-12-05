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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyHabitAdapter;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by jblazusi on 2017-12-02.
 */
public class LeaderBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Called on the creation of the My Home Activity
     *
     * @param savedInstanceState
     */
    private RecyclerView recyclerView;
    private final AppLocale appLocale = AppLocale.getInstance();
    private final List<HabitType> types = new ArrayList<>();
    private final ElasticSearch es = new ElasticSearch();
    final User user = AppLocale.getInstance().getUser();
//    private ArrayList<User> topUsers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ListFragment fragment = new ListFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        getLeaderboard();

//        topUsers = ; //This is an arraylist of users ordered by score


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Username in NavBar
        User currentUser = AppLocale.getInstance().getUser();
        View hView = navigationView.getHeaderView(0);
        TextView usernameNav = hView.findViewById(R.id.userNameNav);
        usernameNav.setText(currentUser.getUserID());

        // Picture in NavBar
        ImageView userPicNav = hView.findViewById(R.id.userPicNav);
        userPicNav.setImageBitmap(currentUser.getImage());
        ImageView circularImageView = hView.findViewById(R.id.circleView);
        if (appLocale.getUser().getImage() != null) {
            circularImageView.setImageBitmap(appLocale.getUser().getImage());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MyHabitAdapter habitAdapter = new MyHabitAdapter(this, types);
        recyclerView = findViewById(R.id.myLeaderboardRecycler);
        recyclerView.setAdapter(habitAdapter);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * Called when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
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
        if (!(id == R.id.LeaderBoard)) {
            Activity currentActivity = LeaderBoardActivity.this;
            Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
            startActivity(newActivity);
            finish();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getLeaderboard() {

        ArrayList<User> users = es.findHighScore();
        for (User user : users) {
            types.addAll(user.getHabitTypes());
        }
        // TODO: FINISH THIS
        // Easiest way would be to just add a completion rate to Users
        // BUT idk if i'm allowed to touch User model soooooooo.....
    }

//    private ArrayList<User> getUsers(){
//        // Here we would have a query on ES to return all users
//        // currently returning a filler array
//        ArrayList<User> allUsers = new ArrayList<User>();
//        allUsers.add(user);
//        return  allUsers;
//    }
}
