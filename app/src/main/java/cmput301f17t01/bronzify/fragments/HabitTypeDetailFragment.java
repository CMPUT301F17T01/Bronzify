package cmput301f17t01.bronzify.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.models.HabitType;

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

    public void deleteHabit(HabitType habit){
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
        final View rootView = inflater.inflate(R.layout.habit_event_tab_picture, container, false);

        // EditText in Activity
        final EditText etHabitName = rootView.findViewById(R.id.textHabitName);
        final EditText etHabitReason = rootView.findViewById(R.id.textHabitReason);

        // Buttons in Activity
        final Button btnSelectDate = rootView.findViewById(R.id.buttonSelectDate);
        final Button btnMonday = rootView.findViewById(R.id.buttonMonday);
        final Button btnTuesday = rootView.findViewById(R.id.buttonTuesday);
        final Button btnWednesday = rootView.findViewById(R.id.buttonWednesday);
        final Button btnThursday = rootView.findViewById(R.id.buttonThursday);
        final Button btnFriday = rootView.findViewById(R.id.buttonFriday);
        final Button btnSaturday = rootView.findViewById(R.id.buttonSaturday);
        final Button btnSunday = rootView.findViewById(R.id.buttonSunday);
        final Button btnClear = rootView.findViewById(R.id.buttonClear);
        final Button btnCreate = rootView.findViewById(R.id.buttonCreate);

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

        return inflater.inflate(R.layout.habit_type_tab_detail, container, false);
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
