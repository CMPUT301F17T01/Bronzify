package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyFollowersAdapter;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class MyFollowersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText searchBar;
    private Button searchID;
    private Button searchName;
    private RecyclerView recyclerView;

    /**
     * On creation of the MyFollowersActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_followers);

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
        View hView =  navigationView.getHeaderView(0);
        TextView usernameNav = hView.findViewById(R.id.userNameNav);
        usernameNav.setText(currentUser.getUserID());


        // Picture in NavBar
        ImageView userPicNav = hView.findViewById(R.id.userPicNav);
        userPicNav.setImageBitmap(currentUser.getImage());
        ImageView circularImageView = hView.findViewById(R.id.circleView);
        AppLocale appLocale = new AppLocale();
        if (appLocale.getUser().getImage() != null) {
            circularImageView.setImageBitmap(appLocale.getUser().getImage());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.myFollowerRecycler);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // TODO: user.getFollowedBy() isn't working

         AppLocale appLocale = AppLocale.getInstance();
        ArrayList<String> followers;
        followers = appLocale.getUser().getFollowedBy();
        


        MyFollowersAdapter followersAdapter = new MyFollowersAdapter(this,followers);
        recyclerView.setAdapter(followersAdapter);
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
        if(!(id == R.id.MyFollowers)) {
            Activity currentActivity = MyFollowersActivity.this;
            Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
            startActivity(newActivity);
            finish();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
