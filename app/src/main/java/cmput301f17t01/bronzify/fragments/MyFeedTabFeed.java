package cmput301f17t01.bronzify.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.adapters.recyclers.MyHabitAdapter;
import cmput301f17t01.bronzify.controllers.ElasticSearch;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

;

/**
 * Created by omcleod on 2017-12-03.
 */

public class MyFeedTabFeed extends Fragment {

    private static final String TAG = "MyFeedTabFeed";
    private List<HabitType> types = new ArrayList<HabitType>();

        public MyFeedTabFeed() {
            fillTypeList();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.my_feed_tab_feed, container, false);

            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.myHistoryRecycler);
            rv.setHasFixedSize(true);
            MyHabitAdapter adapter = new MyHabitAdapter(getContext(),types);
            rv.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(llm);

            return rootView;
        }
    private void fillTypeList(){
        User user = AppLocale.getInstance().getUser();
        ElasticSearch es = new ElasticSearch();
        ArrayList<String> userIDs = user.getFollowing();
        ArrayList<User> users = new ArrayList<User>();

        for(String userID : userIDs ){
            User currUser = es.getUser(userID);
            if(!(currUser==null)){
                users.add(currUser);
            }
            else{
                Log.d(TAG, "fillTypeList: User" + userID + "Does not exist");
            }
        }

        types.clear();
        for(User currUser : users){
            ArrayList<HabitType> habitTypes = user.getHabitTypes();
            for(HabitType type: habitTypes){
                types.add(type);
                }
            }
        }
    }

/*
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

}*/
