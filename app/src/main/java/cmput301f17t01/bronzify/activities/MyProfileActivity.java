package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.ImageAdapter;
import cmput301f17t01.bronzify.adapters.recyclers.FollowAdapter;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.controllers.ProfileController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class MyProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rv;
    private ArrayList<String> userList;
    private String name;
    private AppLocale appLocale;
    private ProfileController controller;
    private User currentUser;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PIXELS = 100;
    private ImageView profilePic;

    /**
     * Called on the creation of My Profile Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        currentUser = AppLocale.getInstance().getUser();

        rv = findViewById(R.id.recyclerView);
        userList = new ArrayList<String>();
        AppLocale appLocale = AppLocale.getInstance();
        final ProfileController controller = new ProfileController();
        createTestList();

        rv = findViewById(R.id.followReqRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        FollowAdapter fa = new FollowAdapter(this, userList);
        rv.setAdapter(fa);

        name = appLocale.getUser().getUserID();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Username in NavBar
        View hView = navigationView.getHeaderView(0);
        TextView usernameNav = hView.findViewById(R.id.userNameNav);
        usernameNav.setText(currentUser.getUserID());

        // Picture in NavBar
        ImageView userPicNav = hView.findViewById(R.id.userPicNav);
        userPicNav.setImageBitmap(currentUser.getImage());
        ImageView circularImageViewNav = hView.findViewById(R.id.circleView);
        if (appLocale.getUser().getImage() != null) {
            circularImageViewNav.setImageBitmap(appLocale.getUser().getImage());
        }

        TextView profileName = findViewById(R.id.profileName);
        profileName.setText(name);
        Button followButton = findViewById(R.id.followButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        profilePic = findViewById(R.id.profileImage);
        ImageView circularImageView = findViewById(R.id.circleView);
        if (appLocale.getUser().getImage() != null) {
            circularImageView.setImageBitmap(appLocale.getUser().getImage());
        }

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });

        //FOLLOW BUTTON
        followButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("Follow", "clicked");
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(MyProfileActivity.this);
                adBuilder.setMessage("Please enter a user ID");
                final EditText input = new EditText(MyProfileActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                adBuilder.setView(input);

                adBuilder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        controller.requestFollow(input.getText().toString());
                    }
                });
                adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                adBuilder.create().show();
            }
        });

        //DELETE BUTTON
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("Delete", "clicked");
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(MyProfileActivity.this);
                adBuilder.setMessage("Are you sure you want to delete your account?");

                adBuilder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        controller.deleteAccount();
                        ContextController contextController = new ContextController(getApplicationContext());
                        contextController.saveInFile(AppLocale.getInstance().getLocalUsers());
                        Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                adBuilder.setNegativeButton(R.string.dialog_return, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                adBuilder.create().show();


            }
        });

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

        if (!(id == R.id.MyProfile)) {
            Activity currentActivity = MyProfileActivity.this;
            Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
            startActivity(newActivity);
            finish();
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This is used to compress the photo and set it into a circle
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");

                Log.d("PHOTO", "Size in KB before compression: " + bmp.getByteCount() / 1000);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Log.d("PHOTO", "Size in KB after compression: " + byteArray.length / 1000);

                AppLocale appLocale = AppLocale.getInstance();
                Bitmap circularBitmap = ImageAdapter.getRoundedCornerBitmap(bmp, PIXELS);

                appLocale.getUser().setImage(circularBitmap);

                ContextController contextController = new ContextController(getApplicationContext());
                contextController.updateUser(appLocale.getUser());
                profilePic.setImageBitmap(circularBitmap);
            }
        }
    }

    /**
     * Creates a list of follow requests for each user in My Profile activity
     */
    private void createTestList() {
        userList = currentUser.getPendingFollowRequests();
    }

}
