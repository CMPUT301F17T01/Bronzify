package cmput301f17t01.bronzify.controllers;

import android.app.Activity;
import android.content.Intent;

import cmput301f17t01.bronzify.R;
import cmput301f17t01.bronzify.activities.FollowUserActivity;
import cmput301f17t01.bronzify.activities.HabitHistoryActivity;
import cmput301f17t01.bronzify.activities.LoginActivity;
import cmput301f17t01.bronzify.activities.MyHabitsActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;
import cmput301f17t01.bronzify.activities.MyMapActivity;
import cmput301f17t01.bronzify.activities.MyProfileActivity;
import cmput301f17t01.bronzify.models.AppLocale;

/**
 * Created by jblazusi on 2017-11-07.
 */

public class NavigationController implements Controller {

    /**
     * Allows you to jump to a new activity based on which is selected from the navigation bar
     *
     * @param id
     * @param currentActivity
     * @return
     */
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
        } else if (id == R.id.LogOut) {
            Intent navIntent = new Intent(currentActivity, LoginActivity.class);
            AppLocale.getInstance().logoutUser();
            return navIntent;
        }
        //EXCEPTION HANDLING HERE, FOR WHEN ID IS NULL OR INCORRECT!
        return null;
    }
}
