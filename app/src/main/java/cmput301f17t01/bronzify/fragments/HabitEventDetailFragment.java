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
import cmput301f17t01.bronzify.models.HabitEvent;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class HabitEventDetailFragment extends Fragment {

    private Button editHabitEvent;
    private Button deleteButton;
    private EditText comment;

    public void updateEvent(){
        return;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.habit_event_tab_detail, container, false);

        // EditText in Activity
        final EditText eHabitName = rootView.findViewById(R.id.textHabitName);
        final EditText eHabitComment = rootView.findViewById(R.id.textHabitComment);

        // Buttons in Activity
        final Button btnSelectDate = rootView.findViewById(R.id.buttonGoalDate);
        final Button btnClear = rootView.findViewById(R.id.buttonClear);
        final Button btnCreate = rootView.findViewById(R.id.buttonCreate);

        eHabitName.setText("");          //Retrieve data
        eHabitComment.setText("");   //Retrieve data
        btnSelectDate.setText(""); //Retrieve data
        btnSelectDate.setEnabled(false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
