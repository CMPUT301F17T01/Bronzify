package cmput301f17t01.bronzify.models;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitType {
    private User user;
    private String name;
    private Date dateCreated;
    private ArrayList<HabitEvent> habitEvents;
    private int[] dateOfWeek;

    public HabitType(String name) {
        this.name = name;
        this.dateCreated = new Date();
    }


    public void addHabitEvent(HabitEvent event) {
        if (habitEvents.contains(event)) {
        } else {
            habitEvents.add(event);
        }
    }

    public void removeHabitEvent(HabitEvent event) {
        habitEvents.remove(event);
    }




    // Getters and Setters below

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents) {
        this.habitEvents = habitEvents;
    }
}
