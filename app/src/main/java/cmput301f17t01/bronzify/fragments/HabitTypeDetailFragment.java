package cmput301f17t01.bronzify.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyEventAdapter;
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
        User user = AppLocale.getInstance().getUser();
        HabitType habitType = user.getHabitTypes().get(pos);

        EditText etHabitName = rootView.findViewById(R.id.textHabitName);
        EditText etHabitReason = rootView.findViewById(R.id.textHabitReason);

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

        etHabitName.setText(habitType.getName());
        etHabitName.setEnabled(false);

        etHabitReason.setText(habitType.getReason());
        etHabitReason.setEnabled(false);

        Date dateToStart = habitType.getDateToStart();
        Format formatter = new SimpleDateFormat("MMMM/dd/yyyy");
        String strDate = formatter.format(dateToStart);
        btnSelectDate.setText(strDate);
        btnSelectDate.setEnabled(false);

        final Button[] buttonArray = {btnSunday, btnMonday, btnTuesday, btnWednesday
                , btnThursday, btnFriday, btnSaturday};

        Boolean[] dayOfWeeks = habitType.getDaysOfWeek();

        for (int i = 0; i < 7; ++i) {
            if (dayOfWeeks[i]) {
                buttonArray[i].setBackgroundColor(getResources().getColor(R.color.Amber));
            }
            buttonArray[i].setEnabled(false);
        }

        btnDelete.setEnabled(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEdit.getText() == "Save") {
                    btnEdit.setText("Edit");
                    for(Button button: buttonArray){
                        button.setEnabled(false);
                    }
                } else {
                    btnEdit.setText("Save");
                    for(Button button: buttonArray){
                        button.setEnabled(true);
                    }
                }
            }
        });

        // Day of Week Buttons ("Flip-Flop")
        // Sunday
        btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[0]) {
                    // Active
                    // Deactivate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[0] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnSunday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[0] = true;
                }
            }
        });

        // Monday
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[1]) {
                    // Active
                    // Deactivate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[1] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnMonday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[1] = true;
                }
            }
        });

        // Tuesday
        btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[2]) {
                    // Active
                    // Deactivate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[2] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnTuesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[2] = true;
                }
            }
        });

        // Wednesday
        btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[3]) {
                    // Active
                    // Deactivate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[3] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnWednesday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[3] = true;
                }
            }
        });

        // Thursday
        btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[4]) {
                    // Active
                    // Deactivate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[4] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnThursday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[4] = true;
                }
            }
        });

        // Friday
        btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[5]) {
                    // Active
                    // Deactivate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[5] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnFriday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[5] = true;
                }
            }
        });

        // Saturday
        btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayOfWeeks[6]) {
                    // Active
                    // Deactivate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.lighterAmber));
                    dayOfWeeks[6] = false;
                } else {
                    // Un-active
                    // Activate Button
                    btnSaturday.setBackgroundColor(getResources().getColor(R.color.Amber));
                    dayOfWeeks[6] = true;
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
