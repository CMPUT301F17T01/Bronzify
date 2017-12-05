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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
                break;
            }
        }

        Date goalDate = habitEvent.getGoalDate();

        final TextView tvMarked = rootView.findViewById(R.id.labelMarked);

        final EditText etHabitName = rootView.findViewById(R.id.textHabitName);
        final EditText etHabitComment = rootView.findViewById(R.id.textHabitComment);
        final EditText etMarked = rootView.findViewById(R.id.etMarked);

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
        etMarked.setText("Not Set/Due");

        tvMarked.setVisibility(View.VISIBLE);
        etMarked.setVisibility(View.VISIBLE);

        if (!habitType.getUserID().equals(user.getUserID())){
            btnEdit.setVisibility(View.GONE);
        }

        Date currentDate  = getZeroTimeDate(new Date());
        if(habitEvent.getCompleted() == null){
            if(goalDate.compareTo(currentDate) == 0){
                btnDone.setVisibility(View.VISIBLE);
                btnNotDone.setVisibility(View.VISIBLE);
            } else if (goalDate.compareTo(currentDate) < 0){
                habitType.incrementNumUncompleted(1);
                Toast.makeText(getActivity(), "Event has passed. Automatically setting as incomplete.", Toast.LENGTH_SHORT).show();
                habitEvent.setCompleted(false);
                ContextController contextController = new ContextController(getActivity().getApplicationContext());
                contextController.updateUser(user);
            }
        } else if (habitEvent.getCompleted()){
            etMarked.setText("Completed");
        } else {
            etMarked.setText("Incomplete");
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
                Toast.makeText(getActivity(), "Activity set as completed", Toast.LENGTH_SHORT).show();
                etMarked.setText("Completed");
                ContextController contextController = new ContextController(getActivity().getApplicationContext());
                contextController.updateUser(user);
                btnDone.setVisibility(View.GONE);
                btnNotDone.setVisibility(View.GONE);
            }
        });

        btnNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitType.incrementNumUncompleted(1);
                habitEvent.setCompleted(false);
                Toast.makeText(getActivity(), "Activity set as incomplete", Toast.LENGTH_SHORT).show();
                etMarked.setText("Incompleted");
                ContextController contextController = new ContextController(getActivity().getApplicationContext());
                contextController.updateUser(user);
                btnDone.setVisibility(View.GONE);
                btnNotDone.setVisibility(View.GONE);
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
