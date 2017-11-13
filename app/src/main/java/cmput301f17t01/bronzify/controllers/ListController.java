package cmput301f17t01.bronzify.controllers;

import java.util.ArrayList;

import cmput301f17t01.bronzify.models.AppLocale;

/**
 * Created by kdehaan on 12/11/17.
 */

public class ListController {
    private AppLocale appLocale = AppLocale.getInstance();

    public ArrayList<?> getObjects(String type) {
        if (type.equals("pendingFollows")) {
            return appLocale.getUser().getPendingFollowRequests();

        } else if (type.equals("HabitEvents")) { //TODO: this
            return appLocale.getUser().getHabitTypes();

        } else if (type.equals("HabitTypes")) { //TODO: this
            return appLocale.getUser().getHabitTypes();
        }
        return new ArrayList<>();
    }
}
