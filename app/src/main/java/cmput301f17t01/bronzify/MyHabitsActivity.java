package cmput301f17t01.bronzify;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.widget.Button;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by owenm_000 on 11/1/2017.
 */

public class MyHabitsActivity extends Activity {
    private Intent intent;
    private ArrayList<HabitType> eventList;
    private ArrayList<DayOfWeek> schedule;
    private Date time;
    private Button sideBar;

    public Fragment detailsFragment;
    public Fragment eventsFragment;

    @Override
    public void onCreate(){
        //TO BE IMPLEMENTED
        return;
    }

    // getters and setters
    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public ArrayList<HabitType> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<HabitType> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<DayOfWeek> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<DayOfWeek> schedule) {
        this.schedule = schedule;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Button getSideBar() {
        return sideBar;
    }

    public void setSideBar(Button sideBar) {
        this.sideBar = sideBar;
    }
}
