package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.NavigationController;

public class CreateNewHabitType extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private int[] daysOfWeek = {0, 0, 0, 0, 0, 0, 0};

    /**
     * Called on the creation of Create New Habit Type Activity
     *
     * TODO: Rename to "..Activity"?
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_habit_type);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Buttons in Activity
        final Button selectDate = findViewById(R.id.buttonSelectDate);
        final Button btnMonday = findViewById(R.id.buttonMonday);
        final Button btnTuesday = findViewById(R.id.buttonTuesday);
        final Button btnWednesday = findViewById(R.id.buttonWednesday);
        final Button btnThursday = findViewById(R.id.buttonThursday);
        final Button btnFriday = findViewById(R.id.buttonFriday);
        final Button btnSaturday = findViewById(R.id.buttonSaturday);
        final Button btnSunday = findViewById(R.id.buttonSunday);

        /**
         * Set date dialog listener
         *
         * Will change select date button's text based on selected date
         */
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                cal.set(Calendar.MONTH, month);

                String strMonth = month_date.format(cal.getTime());

                String date = strMonth + "/" + day + "/" + year;
                selectDate.setText(date);
            }
        };

        /**
         * Select date button listener
         *
         * Opens a new select date dialog
         */
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateNewHabitType.this,
                        dateSetListener,
                        year, month, day);

                Date today = new Date();
                cal.setTime(today);
                long minDate = cal.getTime().getTime();

                dialog.getDatePicker().setMinDate(minDate);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        // Monday
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[0] == 1){
                    // Active
                    // Deactivate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[0] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[0] = 1;
                }
            }
        });

        // Tuesday
        btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[1] == 1){
                    // Active
                    // Deactivate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[1] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[1] = 1;
                }
            }
        });

        // Wednesday
        btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[2] == 1){
                    // Active
                    // Deactivate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[2] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[2] = 1;
                }
            }
        });

        // Thursday
        btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[3] == 1){
                    // Active
                    // Deactivate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[3] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[3] = 1;
                }
            }
        });

        // Friday
        btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[4] == 1){
                    // Active
                    // Deactivate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[4] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[4] = 1;
                }
            }
        });

        // Saturday
        btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[5] == 1){
                    // Active
                    // Deactivate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[5] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[5] = 1;
                }
            }
        });

        // Sunday
        btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(daysOfWeek[6] == 1){
                    // Active
                    // Deactivate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[6] = 0;
                } else {
                    // Un-active
                    // Activate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[6] = 1;
                }
            }
        });
    }

    /**
     * Called when the back button is pressed on the phone
     *
     */
    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        Activity currentActivity = CreateNewHabitType.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
