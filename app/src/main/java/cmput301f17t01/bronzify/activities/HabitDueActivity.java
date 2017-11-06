package cmput301f17t01.bronzify.activities;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import cmput301f17t01.bronzify.HabitEvent;
import cmput301f17t01.bronzify.R;

/**
 * Created by jblazusi on 2017-11-01.
 */
public class HabitDueActivity extends Activity {

    private HabitEvent currentHabitEvent;
    private Button eventCompleted;
    private Button eventMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_due);
    }

}
