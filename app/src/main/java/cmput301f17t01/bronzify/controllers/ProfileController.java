package cmput301f17t01.bronzify.controllers;

import android.app.AlertDialog;
import android.util.Log;

import cmput301f17t01.bronzify.activities.MyProfileActivity;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.Controller;
import cmput301f17t01.bronzify.models.ElasticSearch;

/**
 * Created by kdehaan on 12/11/17.
 */

public class ProfileController implements Controller {
    ElasticSearch elastic = new ElasticSearch();
    AppLocale appLocale = AppLocale.getInstance();

    /**
     * Deletes the account of a user, when a button is clicked
     *
     */
    public void deleteAccount() {
        elastic.deleteUser(appLocale.getUser().getUserID());
    }

    /**
     * Requests to follow a user based on their ID.
     *
     * @param userID
     */
    public void requestFollow(String userID) {
        Log.i("Follow", "Requested");
        appLocale.getUser().requestFollow(userID);
    }

}
