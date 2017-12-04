package cmput301f17t01.bronzify.models;

import android.graphics.Bitmap;
import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitEvent {
    private Date goalDate; // Date for habit event to happen
    private String habitType;
    private String comment; // Max 20 Char
    private Boolean completed;
    private Bitmap image; // Change?
    private Location location; // Unsure of location type
    private String userID;

    // Constructor
    public HabitEvent(Date goalDate, String habitType){
        this.goalDate = goalDate;
        this.habitType = habitType;
        this.comment = ""; // Default comment is blank
        this.completed = null;
        this.image = null;
        this.location = null;
        this.userID = AppLocale.getInstance().getUser().getUserID();
    }

    // Getters and Setters
    /**
     * Return user that created current habit event
     *
     * @return User
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Set user that create current habit event
     *
     * @param userID username
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

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
        return  simpleDateFormat.format(goalDate);
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    public void setCompleted(Boolean completed)
    {
        this.completed = completed;
    }
}
