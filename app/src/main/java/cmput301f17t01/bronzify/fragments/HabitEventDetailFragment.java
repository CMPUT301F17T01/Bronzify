package cmput301f17t01.bronzify.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.controllers.ContextController;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/*
 * Created by jblazusi on 2017-11-01.
 */

public class HabitEventDetailFragment extends Fragment {

    private HabitType habitType;
    private HabitEvent habitEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.habit_event_tab_detail, container, false);

        final User user = AppLocale.getInstance().getUser();
        final ArrayList<HabitType> habitTypes = user.getHabitTypes();
        habitEvent = AppLocale.getInstance().getEvent();

        for(HabitType habit: habitTypes){
            if(habit.getName().equals(habitEvent.getHabitType())){
                habitType = habit;
            }
        }

        Date currentDate = getZeroTimeDate(new Date());
        Date goalDate = getZeroTimeDate(habitEvent.getGoalDate());

        final EditText etHabitName = rootView.findViewById(R.id.textHabitName);
        final EditText etHabitComment = rootView.findViewById(R.id.textHabitComment);

        final Button btnGoalDate = rootView.findViewById(R.id.buttonGoalDate);

        final Button btnEdit = rootView.findViewById(R.id.buttonEdit);
        final Button btnDelete = rootView.findViewById(R.id.buttonDelete);
        final Button btnReset = rootView.findViewById(R.id.buttonReset);

        final Button btnDone = rootView.findViewById(R.id.buttonCompleted);
        final Button btnNotDone = rootView.findViewById(R.id.buttonFailed);

        etHabitName.setText(habitEvent.getHabitType());
        etHabitComment.setText(habitEvent.getComment());
        btnGoalDate.setText(habitEvent.goalDateToString());

        btnEdit.setText("Edit");

        // For complete and incomplete buttons
        if(goalDate.compareTo(currentDate) == 0){
            btnDone.setVisibility(View.VISIBLE);
            btnNotDone.setVisibility(View.VISIBLE);
        } else if (goalDate.compareTo(currentDate) < 0 && habitEvent.getCompleted() == null){
            habitType.incrementNumUncompleted(1);
            habitEvent.setCompleted(false);
            Toast.makeText(getActivity(), "Event has passed. Automatically setting as incomplete.", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnEdit.getText() == "Save") {
                    // Save changes
                    String newComment = etHabitComment.getText().toString();

                    // Update comment if new
                    if (!habitEvent.getComment().equals(newComment)) {
                        habitEvent.setComment(newComment);
                        ContextController contextController = new ContextController(getActivity().getApplicationContext());
                        contextController.updateUser(user);
                    }

                    btnEdit.setText("Edit");
                    btnDelete.setEnabled(false);
                    btnReset.setEnabled(false);
                    btnDelete.setVisibility(View.GONE);
                    btnReset.setVisibility(View.GONE);

                    etHabitComment.setEnabled(false);

                } else {
                    // Enable editing
                    btnEdit.setText("Save");
                    btnDelete.setEnabled(true);
                    btnReset.setEnabled(true);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnReset.setVisibility(View.VISIBLE);

                    etHabitComment.setEnabled(true);
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etHabitComment.setText(habitEvent.getComment());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Habit Event")
                        .setMessage("Are you sure you want to delete this habit event?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                habitType.removeEvent(habitEvent);
                                Toast.makeText(getActivity(), "Event deleted.", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }})
                        .setNegativeButton("No", null).show();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitType.incrementNumCompleted(1);
                habitEvent.setCompleted(true);
                Toast.makeText(getActivity(), "Event marked as completed", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        btnNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitType.incrementNumUncompleted(1);
                habitEvent.setCompleted(false);
                Toast.makeText(getActivity(), "Event marked as incomplete", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    // Set time to 00:00:00
    public static Date getZeroTimeDate(Date date) {
        Date res = date;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
