package cmput301f17t01.bronzify;

import java.util.ArrayList;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitType {
    private String name;
    private ArrayList<HabitEvent> habitEvents;
    private User user;
    private String recurrence;

    public void setName(String name) {
        this.name = name;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public String getName() {

        return name;
    }

    public User getUser() {
        return user;
    }
}
