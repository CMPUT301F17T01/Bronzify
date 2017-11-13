package cmput301f17t01.bronzify.controllers;

import android.util.Log;

import cmput301f17t01.bronzify.models.AppLocale;

/**
 * Created by kdehaan on 12/11/17.
 */

public class ProfileController implements Controller {
    ElasticSearch elastic = new ElasticSearch();
    AppLocale appLocale = AppLocale.getInstance();

    public void deleteAccount() {
        elastic.deleteUser(appLocale.getUser().getUserID());
    }

    public void requestFollow(String userID) {
        Log.i("Follow", "Requested");
        elastic.requestFollow(appLocale.getUser(), userID);
    }

}
