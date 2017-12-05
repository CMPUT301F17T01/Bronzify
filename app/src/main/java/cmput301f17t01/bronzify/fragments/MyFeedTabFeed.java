package cmput301f17t01.bronzify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import cmput301f17t01.bronzify.adapters.recyclers.MyFeedAdapter;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;

;

/**
 * Created by omcleod on 2017-12-03.
 */

public class MyFeedTabFeed extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "MyFeedTabFeed";
    private List<HabitType> types  = new ArrayList<HabitType>();
    private MyFeedAdapter adapter;
    public MyFeedTabFeed() {fillTypesList();}


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.my_feed_tab_feed, container, false);
            RecyclerView rv = rootView.findViewById(R.id.myFeedRecycler);
            rv.setHasFixedSize(true);
            fillTypesList();
            setHasOptionsMenu(true);
            Log.d(TAG,types.toString());
            adapter = new MyFeedAdapter(getContext(),types);
            rv.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                    llm.getOrientation());
            rv.addItemDecoration(dividerItemDecoration);


            return rootView;
        }
    private void fillTypesList(){

        ArrayList<String> following = AppLocale.getInstance().getUser().getFollowing();
        ElasticSearch es = new ElasticSearch();
        for(String user :following){
            types.addAll(es.getUser(user).getHabitTypes());
        }
        types.addAll(es.getUser("s").getHabitTypes());
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
        ArrayList<HabitType> hes = new ArrayList<HabitType>();
        for (HabitType type : types){
            String typeName = type.getName().toLowerCase();
            if(typeName.contains(s)){
                hes.add(type);
            }
        }

        adapter.setFilter(hes);

        return true;
    }
}