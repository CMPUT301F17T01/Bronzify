package cmput301f17t01.bronzify.fragments;

import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import cmput301f17t01.bronzify.models.HabitType;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class HabitTypeDetailFragment {

    private Button editHabitType;
    private Button deleteHabitType;
    private EditText reason;
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
}
