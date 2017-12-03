package cmput301f17t01.bronzify.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitType {
    private static final int MAX_DAYS_AHEAD = 31;

    private String userID;
    private String name;
    private String reason;
    private Date dateToStart;
    private Boolean[] daysOfWeek; // Sunday = 0, ... , Saturday = 6

    private ArrayList<HabitEvent> habitEvents = new ArrayList<>();
    private int numCompleted;
    private int numUncompleted;

    // Constructor
    public HabitType(String name, String reason, Date dateToStart, Boolean[] daysOfWeek) {
        try {
            userID = AppLocale.getInstance().getUser().getUserID();
        } catch (NullPointerException e) {
        }
        this.name = name;
        this.reason = reason;
        this.dateToStart = dateToStart;
        this.daysOfWeek = daysOfWeek;
        this.numCompleted = 0;
        this.numUncompleted = 0;
        generateNewEvents(dateToStart);
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
            if(daysOfWeek[dayOfWeek]){
                HabitEvent newHabitEvent = new HabitEvent(date, name);
                habitEvents.add(newHabitEvent);
            }

            // Increment Date
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
        }
    }

    // Setters and Getters
    // User
    /**
     * Return user that created current habit type
     *
     * @return User
     */
    public String getUserID() {
        return userID;
    }

    // Habit Name
    /**
     * Get habit type name
     *
     * @return Habit type name
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name for habit type
     *
     * @param name New name for habit type
     */
    public void setName(String name) {
        this.name = name;
    }

    // Habit Reason
    /**
     * Get habit type reason
     *
     * @return Habit type reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set a new reason for habit type
     *
     * @param reason New reason for habit type
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    // Date to Start
    /**
     * Get date to start habit events
     *
     * @return Date to start habit events
     */
    public Date getDateToStart() {
        return dateToStart;
    }

    /**
     * Set new date to start for habit event
     *
     * @param dateToStart New date to start
     */
    public void setDateToStart(Date dateToStart) {
        this.dateToStart = dateToStart;
    }

    // Days of Week to Repeat
    /**
     * Get days of week to repeat
     * Represented as an array of integers
     * Index: 0 = Sunday
     * Index: 1 = Monday
     * Index: 2 = Tuesday
     * Index: 3 = Wednesday
     * Index: 4 = Thursday
     * Index: 5 = Friday
     * Index: 6 = Saturday
     *
     * Value: false = Don't repeat
     * Value: true = Repeat
     *
     * @return Array of integers that represents days of week to repeat
     */
    public Boolean[] getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Set new days of week to repeat
     *
     * @param daysOfWeek New days of week to repeat
     */
    public void setDaysOfWeek(Boolean[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    // List of Habit Events
    /**
     * Get the whole list of habit events
     *
     * @return ArrayList of habit events
     */
    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }

    // Number of Completed Habit Events
    /**
     * Get number of completed habit events
     *
     * @return Number of completed habit events
     */
    public int getNumCompleted() {
        return numCompleted;
    }

    /**
     * Increment number of completed habit events by changeValue
     *
     * @param changeValue Amount to increment number of completed habit event by
     */
    public void incrementNumCompleted(int changeValue) {
        this.numCompleted += changeValue;
    }

    // Number of Uncompleted Habit Events
    /**
     * Get number of uncompleted habit events
     *
     * @return Number of uncompleted habit events
     */
    public int getNumUncompleted() {
        return numUncompleted;
    }

    /**
     * Increment number of uncompleted habit event by changeValue
     *
     * @param changeValue Amount to increment number of uncompleted habit events by
     */
    public void incrementNumUncompleted(int changeValue) {
        this.numUncompleted = changeValue;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents) {
        this.habitEvents = habitEvents;
    }

    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    public void setNumUncompleted(int numUncompleted) {
        this.numUncompleted = numUncompleted;
    }

    public int getCompletionRatio(){
        if(numUncompleted==0 && numCompleted == 0 ){return 100;}
        return numCompleted/(numCompleted+numUncompleted);
    }
}
