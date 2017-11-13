package cmput301f17t01.bronzify.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.controllers.ProfileController;
import cmput301f17t01.bronzify.fragments.ListFragment;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class MyProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private String name;
    private String userID;
    private Image picture;
    private Button editProfile;
    private Button sideBar;
    private AppLocale appLocale = AppLocale.getInstance();
    private ProfileController controller = new ProfileController();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public void displayUserInfo() {
        return;
    }

    public void editProfile() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = appLocale.getUser().getUserID();


        setContentView(R.layout.activity_my_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "pendingFollows");

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ListFragment fragment = new ListFragment();
            fragment.setArguments(bundle);
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView profileName = (TextView) findViewById(R.id.profileName);
        profileName.setText(name);

        Button followButton = (Button) findViewById(R.id.followButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        followButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                controller.requestFollow();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                controller.deleteAccount();
                Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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
        Activity currentActivity = MyProfileActivity.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
