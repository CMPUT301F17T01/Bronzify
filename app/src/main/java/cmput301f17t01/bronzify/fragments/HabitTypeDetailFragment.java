package cmput301f17t01.bronzify.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.CreateNewHabitTypeActivity;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class HabitTypeDetailFragment extends Fragment {

    private Date time;
    //- statistics: Graphs

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void deleteHabit(HabitType habit) {
        return;
    }

    private Date dateToStart;
    private Boolean[] daysOfWeek;

    /**
     * Creates the fragment habit type tab detail
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.habit_type_tab_detail,
                container, false);

        int pos = getActivity().getIntent().getExtras().getInt("SELECTED_HABIT");
        final User user = AppLocale.getInstance().getUser();
        final HabitType habitType = user.getHabitTypes().get(pos);

        final EditText etHabitName = rootView.findViewById(R.id.textHabitName);
        final EditText etHabitReason = rootView.findViewById(R.id.textHabitReason);

        final Button btnSelectDate = rootView.findViewById(R.id.buttonSelectDate);
        final Button btnMonday = rootView.findViewById(R.id.buttonMonday);
        final Button btnTuesday = rootView.findViewById(R.id.buttonTuesday);
        final Button btnWednesday = rootView.findViewById(R.id.buttonWednesday);
        final Button btnThursday = rootView.findViewById(R.id.buttonThursday);
        final Button btnFriday = rootView.findViewById(R.id.buttonFriday);
        final Button btnSaturday = rootView.findViewById(R.id.buttonSaturday);
        final Button btnSunday = rootView.findViewById(R.id.buttonSunday);
        final Button btnEdit = rootView.findViewById(R.id.buttonEdit);
        final Button btnDelete = rootView.findViewById(R.id.buttonDelete);
        final Button btnReset = rootView.findViewById(R.id.btnReset);

        etHabitName.setText(habitType.getName());
        etHabitName.setEnabled(false);

        etHabitReason.setText(habitType.getReason());
        etHabitReason.setEnabled(false);

        dateToStart = habitType.getDateToStart();
        Format formatter = new SimpleDateFormat("MMMM/dd/yyyy");
        String strDate = formatter.format(dateToStart);
        btnSelectDate.setText(strDate);
        btnSelectDate.setEnabled(false);

        final Button[] buttonArray = {btnSunday, btnMonday, btnTuesday, btnWednesday
                , btnThursday, btnFriday, btnSaturday};

        daysOfWeek = habitType.getDaysOfWeek();

        for (int i = 0; i < 7; ++i) {
            if (daysOfWeek[i]) {
                buttonArray[i].setBackgroundColor(getResources().getColor(R.color.Amber));
            }
            buttonArray[i].setEnabled(false);
        }

        btnDelete.setEnabled(false);
        btnReset.setEnabled(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEdit.getText() == "Save") {
                    String habitName = etHabitName.getText().toString();
                    String habitReason = etHabitReason.getText().toString();

                    Boolean validName = true;
                    Boolean validReason = true;
                    Boolean validDaysOfWeek = false;

                    // Check if all fields are valid
                    if (habitName.equals("")) {
                        validName = false;
                    }
                    if (habitReason.equals("")) {
                        validReason = false;
                    }
                    // Check if user has selected at least one day of the week
                    for (int i = 0; i < 7; ++i) {
                        if (daysOfWeek[i]) {
                            validDaysOfWeek = true;
                            break;
                        }
                    }

                    if (validName && validReason && validDaysOfWeek) {
                        habitType.setName(habitName); // Update name, even if same
                        habitType.setReason(habitReason); // Update reason, even if same
                        habitType.setDateToStart(dateToStart); // Update date, even if same
                        habitType.setDaysOfWeek(daysOfWeek); // Update days of week, even if same
                        // TODO: NEED TO UPDATE LIST OF EVENTS, NEED TO CHECK DUPLICATE HABIT NAME

                        // Update user
                        ContextController contextController = new ContextController(getActivity().getApplicationContext());
                        contextController.updateUser(user);

                        btnEdit.setText("Edit");
                        etHabitName.setEnabled(false);
                        etHabitReason.setEnabled(false);
                        btnSelectDate.setEnabled(false);
                        for(Button button: buttonArray){
                            button.setEnabled(false);
                        }
                        btnDelete.setEnabled(false);
                        btnReset.setEnabled(false);

                    } else {
                        // Missing a field
                        Toast.makeText(getActivity(), "Cannot update habit, make sure all fields are filled in.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    btnEdit.setText("Save");
                    etHabitName.setEnabled(true);
                    etHabitReason.setEnabled(true);
                    btnSelectDate.setEnabled(true);
                    for(Button button: buttonArray){
                        button.setEnabled(true);
                    }
                    btnDelete.setEnabled(true);
                    btnReset.setEnabled(true);
                }
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

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                cal.set(Calendar.MONTH, month);

                String strMonth = month_date.format(cal.getTime());

                String strDate = strMonth + "/" + day + "/" + year;
                btnSelectDate.setText(strDate);

                dateToStart = new GregorianCalendar(year, month, day).getTime();
            }
        };

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
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

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etHabitName.setText(habitType.getName());
                etHabitReason.setText(habitType.getReason());

                Date dateToStart = habitType.getDateToStart();
                Format formatter = new SimpleDateFormat("MMMM/dd/yyyy");
                String strDate = formatter.format(dateToStart);
                btnSelectDate.setText(strDate);

                Boolean daysOfWeek = habitType.getDaysOfWeek();
                for(int i = 0; i < 7; ++i){

                }
            }
        });

        return rootView;
    }

    /**
     * Called when the view is created.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
