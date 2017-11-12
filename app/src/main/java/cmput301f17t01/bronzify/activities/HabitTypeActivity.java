package cmput301f17t01.bronzify.activities;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import java.time.DayOfWeek;
import java.util.ArrayList;

import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.R;

/**
 * Created by owenm_000 on 11/1/2017.
 */

public class HabitTypeActivity extends Activity {

    // private TabItem detailsTab;
    // private TabItem evenList;
    private ArrayList<HabitEvent> eventList;
    private ArrayList<DayOfWeek> schedule;
    private Button sideBar;


    // getters and setters
    public ArrayList<HabitEvent> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<HabitEvent> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<DayOfWeek> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<DayOfWeek> schedule) {
        this.schedule = schedule;
    }

    public Button getSideBar() {
        return sideBar;
    }

    public void setSideBar(Button sideBar) {
        this.sideBar = sideBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_type);
    }

}
