package cmput301f17t01.bronzify.controllers;

import android.util.Log;

import java.util.ArrayList;

import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 12/11/17.
 */

public class ListController implements Controller {
    private final AppLocale appLocale = AppLocale.getInstance();
    private final String type;

    public ListController(String type) {
        this.type = type;
    }

    /**
     * Used to get the objects in the list fragments
     *
     * @return
     */
    public ArrayList<?> getObjects() {
        if (type.equals("pendingFollows")) {
            return appLocale.getUser().getPendingFollowRequests();
        } else if (type.equals("HabitEvents")) { //TODO: this
            return appLocale.getUser().getHabitTypes();
        } else if (type.equals("HabitTypes")) { //TODO: this
            return appLocale.getUser().getHabitTypes();
        } else if (type.equals("HabitHistory")) { //TODO: Change to habit events
            return appLocale.getUser().getHabitTypes();
        }
        return new ArrayList<>();
    }

    /**
     * Called when one of the objects is clicked, allowing you to accept a follow request
     *
     * @param clicked
     */
    public void onClick(Object clicked) {
        if (type.equals("pendingFollows")) {
            User user = AppLocale.getInstance().getUser();
            new ElasticSearch().acceptFollow(user, clicked.toString());
            Log.i("User", "Accepted");
        }

    }
}
