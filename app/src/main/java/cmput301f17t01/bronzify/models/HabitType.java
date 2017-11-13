package cmput301f17t01.bronzify.models;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitType {
    private static final int MAX_DAYS_AHEAD = 31;

    private User user;
    private String name;
    private String reason;
    private Date dateToStart;
    private int[] daysOfWeek; // Sunday = 0, ... , Saturday = 6

    private ArrayList<HabitEvent> habitEvents;
    private int numCompleted;
    private int numUncompleted;

    // Constructor
    public HabitType(String name, String reason, Date dateToStart, int[] daysOfWeek) {
        user = AppLocale.getInstance().getUser();
        this.name = name;
        this.reason = reason;
        this.dateToStart = dateToStart;
        this.daysOfWeek = daysOfWeek;
        this.numCompleted = 0;
        this.numUncompleted = 0;
    }

    // Generate MAX_DAYS_AHEAD worth of habit events
    public void generateNewEvents(Date date) {
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < MAX_DAYS_AHEAD; ++i) {
            calendar.setTime(date);

            // Get day of week for each date
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            dayOfWeek -= 1;

            // If day of week is a repeat day of week
            // Create a new habit event
            if(daysOfWeek[dayOfWeek] == 1){
                HabitEvent newHabitEvent = new HabitEvent(date);
                habitEvents.add(newHabitEvent);
            }

            // Increment Date
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
        }
    }

    // Setters and Getters
    // User
    public User getUser() {
        return user;
    }

    // Habit Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Habit Reason
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    // Date to Start
    public Date getDateToStart() {
        return dateToStart;
    }
    public void setDateToStart(Date dateToStart) {
        this.dateToStart = dateToStart;
    }

    // Days of Week to Repeat
    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }
    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    // List of Habit Events
    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }
    public void setHabitEvents(ArrayList<HabitEvent> habitEvents) {
        this.habitEvents = habitEvents;
    }

    // Number of Completed Habit Events
    public int getNumCompleted() {
        return numCompleted;
    }
    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    // Number of Uncompleted Habit Events
    public int getNumUncompleted() {
        return numUncompleted;
    }
    public void setNumUncompleted(int numUncompleted) {
        this.numUncompleted = numUncompleted;
    }
}
