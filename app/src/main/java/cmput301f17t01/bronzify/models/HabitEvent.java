package cmput301f17t01.bronzify.models;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitEvent implements Comparable{
    private String habitType;
    private String comment; // Max 20 Char
    private Date goalDate; // Date for habit event to happen
    private Boolean completed;
    private Bitmap image;
    private Location location;
    private String userID;

    // Constructor
    public HabitEvent(Date goalDate, String habitType){
        this.habitType = habitType;
        this.comment = ""; // Default comment is blank
        this.goalDate = goalDate;
        this.completed = null;
        this.image = null;
        this.location = null;
        try {
            this.userID = AppLocale.getInstance().getUser().getUserID();
        } catch (NullPointerException e) {
        }
    }

    // Getters and Setters
    // User
    /**
     * Return user that created current habit type
     *
     * @return User
     */
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Goal Date
    /**
     * Get goal date for habit event
     *
     * @return Goal date for habit event
     */
    public Date getGoalDate() {
        return goalDate;
    }

    /**
     * Set new goal date for habit event
     *
     * @param goalDate New goal date for habit event
     */
    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    // Completed Date

    // Comment
    /**
     * Get habit comment
     *
     * @return Habit comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set new habit comment
     *
     * @param comment New habit comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Completed
    /**
     * See if habit event have been completed after goal date
     * true = completed
     * false = uncompleted
     *
     * @return Boolean value representing completion
     */
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // Image
    /**
     * Get image of habit event
     *
     * @return Image
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     * Set image for habit event
     *
     * @param image Image for habit event
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }

    // Location
    /**
     * Get location of habit event
     *
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set location for habit event
     *
     * @param location Location of habit event
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Convert Goal Date to String
     * Format: "Sun, Jan 01, 2017"
     *
     * @return String representation of date
     */
    public String goalDateToString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        return simpleDateFormat.format(goalDate);
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    public int compareTo(HabitEvent another) {
        return another.getGoalDate().compareTo(goalDate);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return ((HabitEvent)o).getGoalDate().compareTo(goalDate);
    }
}
