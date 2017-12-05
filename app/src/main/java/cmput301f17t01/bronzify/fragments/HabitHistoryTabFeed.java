package cmput301f17t01.bronzify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyEventAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

;

/**
 * Created by omcleod on 2017-12-03.
 */

public class HabitHistoryTabFeed extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "HabitHistoryTabFeed";
    private List<HabitEvent> events = new ArrayList<HabitEvent>();
    private MyEventAdapter adapter;
    public HabitHistoryTabFeed() {
            fillEventList();
        }


        @Override
        /**
         * Called on the creation of a habit history tab fragment
         *
         */
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.habit_history_tab_feed, container, false);
            RecyclerView rv = rootView.findViewById(R.id.myHistoryRecycler);
            rv.setHasFixedSize(true);
            setHasOptionsMenu(true);
            adapter = new MyEventAdapter(getContext(),events);
            rv.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                    llm.getOrientation());
            rv.addItemDecoration(dividerItemDecoration);


            return rootView;
        }

    /**
     * Populates the event list depending on what the user selected for repeatability
     *
     */
    private void fillEventList(){
        User user = AppLocale.getInstance().getUser();
        ArrayList<HabitType> habitTypes = user.getHabitTypes();
        events.clear();
        for(HabitType type: habitTypes){
            ArrayList<HabitEvent> habitEvents = type.getHabitEvents();
            for(HabitEvent event: habitEvents){
//                Date eventDate = getZeroTimeDate(event.getGoalDate());
//                Date currentDate = getZeroTimeDate(new Date());
//                int dateDiff = eventDate.compareTo(currentDate);
//                if(dateDiff < 0){
//                    events.add(event);
//                }
                if(event.getCompleted() != null){
                    events.add(event);
                }
            }
        }
    }

    /**
     * Gets the zeroth time stamp to set the time to 00:00:00
     *
     * @param date
     * @return
     */
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
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    /**
     * Creates the navigation drawer
     *
     * @param menu
     * @return
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.search_drawer, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s = s.toLowerCase();
        ArrayList<HabitEvent> hes = new ArrayList<HabitEvent>();
        for (HabitEvent event : events){
            String eventName = event.getHabitType().toLowerCase();
            if(eventName.contains(s)){
                hes.add(event);
            }
        }

        adapter.setFilter(hes);

        return true;
    }
}