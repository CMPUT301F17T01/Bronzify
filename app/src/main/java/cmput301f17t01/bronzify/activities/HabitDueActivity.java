package cmput301f17t01.bronzify.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.models.HabitEvent;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class HabitDueActivity extends Activity {

    private HabitEvent currentHabitEvent;
    private Button eventCompleted;
    private Button eventMissed;

    /**
     * Used on the creation of the Habit Due Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_due);
    }

}
