package cmput301f17t01.bronzify.activities;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
// import android.support.design.widget.TabItem;

import java.util.ArrayList;

import cmput301f17t01.bronzify.HabitEvent;
import cmput301f17t01.bronzify.R;

/**
 * Created by owenm_000 on 11/1/2017.
 */
public class HabitHistoryActivity extends Activity {

    // private TabItem feedTab;
    // private TabItem mapTab;
    private ToggleButton toggleFilter;
    private ArrayList<HabitEvent> habitEvents;
    private EditText searchBar;
    private Button sideBar;

    //protected void onClickFeed() {}

    //protected void onClickMap() {}

    // getters and setters
    public ToggleButton getToggleFilter() {
        return toggleFilter;
    }

    public void setToggleFilter(ToggleButton toggleFilter) {
        this.toggleFilter = toggleFilter;
    }

    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents) {
        this.habitEvents = habitEvents;
    }

    public EditText getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(EditText searchBar) {
        this.searchBar = searchBar;
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
        setContentView(R.layout.activity_habit_history);
    }
}
