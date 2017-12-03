package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.controllers.NavigationController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

public class CreateNewHabitTypeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Date date;
    private Boolean[] daysOfWeek = {false, false, false, false, false, false, false,};

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // EditText in Activity
        final EditText etHabitName = findViewById(R.id.textHabitName);
        final EditText etHabitReason = findViewById(R.id.textHabitReason);

        // Buttons in Activity
        final Button btnSelectDate = findViewById(R.id.buttonSelectDate);
        final Button btnMonday = findViewById(R.id.buttonMonday);
        final Button btnTuesday = findViewById(R.id.buttonTuesday);
        final Button btnWednesday = findViewById(R.id.buttonWednesday);
        final Button btnThursday = findViewById(R.id.buttonThursday);
        final Button btnFriday = findViewById(R.id.buttonFriday);
        final Button btnSaturday = findViewById(R.id.buttonSaturday);
        final Button btnSunday = findViewById(R.id.buttonSunday);
        final Button btnClear = findViewById(R.id.buttonClear);
        final Button btnCreate = findViewById(R.id.buttonCreate);

        /**
         * Set date dialog listener
         *
         * Will change select date button's text based on selected date
         */
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                cal.set(Calendar.MONTH, month);

                String strMonth = month_date.format(cal.getTime());

                String strDate = strMonth + "/" + day + "/" + year;
                btnSelectDate.setText(strDate);

                date = new GregorianCalendar(year, month, day).getTime();
            }
        };

        /**
         * Select date button listener
         *
         * Opens a new select date dialog
         */
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateNewHabitTypeActivity.this,
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

        // Day of Week Buttons ("Flip-Flop")
        // Sunday
        btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[0]) {
                    // Active
                    // Deactivate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[0] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[0] = true;
                }
            }
        });

        // Monday
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[1]) {
                    // Active
                    // Deactivate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[1] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[1] = true;
                }
            }
        });

        // Tuesday
        btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[2]) {
                    // Active
                    // Deactivate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[2] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[2] = true;
                }
            }
        });

        // Wednesday
        btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[3]) {
                    // Active
                    // Deactivate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[3] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[3] = true;
                }
            }
        });

        // Thursday
        btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[4]) {
                    // Active
                    // Deactivate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[4] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[4] = true;
                }
            }
        });

        // Friday
        btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[5]) {
                    // Active
                    // Deactivate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[5] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[5] = true;
                }
            }
        });

        // Saturday
        btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daysOfWeek[6]) {
                    // Active
                    // Deactivate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    daysOfWeek[6] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    daysOfWeek[6] = true;
                }
            }
        });

        // Clear Button
        // Clear all fields
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Clear days of week array
                for (int i = 0; i < 7; ++i) {
                    daysOfWeek[i] = false;
                }

                // Clear starting date
                date = null;

                // Reset button colours
                btnMonday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnTuesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnWednesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnThursday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnFriday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnSaturday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                btnSunday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));

                // Reset select date button text
                btnSelectDate.setText("SELECT A DATE");

                // Clear edit texts
                etHabitName.setText("");
                etHabitReason.setText("");

                // Focus on habit name
                etHabitName.requestFocus();
            }
        });

        // Create Habit Button
        // Create a new habit type
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String habitName = etHabitName.getText().toString();
                String habitReason = etHabitReason.getText().toString();

                Boolean validName = true;
                Boolean validReason = true;
                Boolean validDate = true;
                Boolean validDaysOfWeek = false;

                // Check if all fields are valid
                if (habitName.equals("")) {
                    validName = false;
                }
                if (habitReason.equals("")) {
                    validReason = false;
                }
                if (date == null){
                    validDate = false;
                }

                // Check if user has selected at least one day of the week
                for (int i = 0; i < 7; ++i) {
                    if (daysOfWeek[i]) {
                        validDaysOfWeek = true;
                        break;
                    }
                }

                // Create Habit Type and initial Habit Events (31 Days after starting date)
                // Only if all fields are filled in
                if (validName && validReason && validDate && validDaysOfWeek) {
                    HabitType newHabit = new HabitType(habitName, habitReason, date, daysOfWeek);
                    newHabit.generateNewEvents(newHabit.getDateToStart());

                    // Add new habit type to logged in user
                    User currentUser = AppLocale.getInstance().getUser();
                    try {
                        currentUser.addHabitType(newHabit);
                    } catch (Exception e) {
                        //todo: type already exists
                        e.printStackTrace();
                    }
                    ContextController contextController = new ContextController(getApplicationContext());
                    contextController.updateUser(currentUser);
                    
                    /*ElasticSearch elastic = new ElasticSearch();
                    currentUser = elastic.update(currentUser);
                    AppLocale appLocale = AppLocale.getInstance();
//                    appLocale.setContext(getApplicationContext());
                    appLocale.setUser(currentUser);*/
//                    contextController.saveInFile(appLocale.getLocalUsers());

                    // Fill Habit Event List Fragment
                    // Need to change fillList() Code
                    newHabit.fillList();

                    // Go back
                    finish();
                } else {
                    // Missing a field
                    Toast.makeText(CreateNewHabitTypeActivity.this, "Cannot create habit, make sure all fields are filled in.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Called when the back button is pressed on the phone
     *
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
        Activity currentActivity = CreateNewHabitTypeActivity.this;
        Intent newActivity = NavigationController.navigationSelect(id, currentActivity);
        startActivity(newActivity);
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
