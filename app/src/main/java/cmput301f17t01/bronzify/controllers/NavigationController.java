package cmput301f17t01.bronzify.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.FollowUserActivity;
import cmput301f17t01.bronzify.activities.HabitHistoryActivity;
import cmput301f17t01.bronzify.activities.MyHabitsActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;
import cmput301f17t01.bronzify.activities.MyMapActivity;
import cmput301f17t01.bronzify.activities.MyProfileActivity;

/**
 * Created by jblazusi on 2017-11-07.
 */

public class NavigationController {

    static public Intent navigationSelect(int id, Activity currentActivity) {
        if (id == R.id.MyHome) {
            Intent navIntent = new Intent(currentActivity, MyHomeActivity.class);
            return navIntent;
        } else if (id == R.id.MyFeed) {
            //UPDATE THE TARGET ACTIVITY, WE DO NOT HAVE A FEED ACTIVITY
            Intent navIntent = new Intent(currentActivity, HabitHistoryActivity.class);
            return navIntent;
        } else if (id == R.id.MyHabits) {
            //UPDATE THE TARGET ACTIVITY, WE DO NOT HAVE A FEED ACTIVITY
            Intent navIntent = new Intent(currentActivity, MyHabitsActivity.class);
            return navIntent;
        } else if (id == R.id.MyHistory) {
            Intent navIntent = new Intent(currentActivity, HabitHistoryActivity.class);
            return navIntent;
        } else if (id == R.id.MyMap) {
            Intent navIntent = new Intent(currentActivity, MyMapActivity.class);
            return navIntent;
        } else if (id == R.id.MyFollowers) {
            Intent navIntent = new Intent(currentActivity, FollowUserActivity.class);
            return navIntent;
        } else if (id == R.id.MyProfile) {
            Intent navIntent = new Intent(currentActivity, MyProfileActivity.class);
            return navIntent;
        }
        //EXCEPTION HANDLING HERE, FOR WHEN ID IS NULL OR INCORRECT!
        return null;
    }
}
