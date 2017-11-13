package cmput301f17t01.bronzify.controllers;

import android.util.Log;

import java.util.ArrayList;

import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.Controller;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 12/11/17.
 */

public class ListController implements Controller {
    private AppLocale appLocale = AppLocale.getInstance();
    private String type;

    public ListController(String type) {
        this.type = type;
    }

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

    public void onClick(Object clicked) {
        if (type.equals("pendingFollows")) {
            User user = AppLocale.getInstance().getUser();
            new ElasticSearch().acceptFollow(user, clicked.toString());
            Log.i("User", "Accepted");
        }

    }
}
