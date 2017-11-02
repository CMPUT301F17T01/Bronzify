package cmput301f17t01.bronzify;

import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jblazusi on 2017-11-01.
 */

public class HabitListFragment {

    private ListView habitList;

    private ArrayList<HabitEvent> Arrayadapter;

    public ListView getHabitList() {
        return habitList;
    }

    public void setHabitList(ListView habitList) {
        this.habitList = habitList;
    }

    private void updateHabits(String input, String filterType){
        return;
    }

    private void displayHabitList(){
        return;
    }
}
