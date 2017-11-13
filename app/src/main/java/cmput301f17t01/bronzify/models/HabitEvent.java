package cmput301f17t01.bronzify.models;

import android.graphics.Bitmap;

import com.google.android.gms.maps.GoogleMap;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by noahkryzanowski on 2017-10-20.
 */

public class HabitEvent {
    private User user;
    private String habitType;
    private Date goalDate; // Date for habit event to happen
    private Date completedDate;
    private String comment; // Max 20 Char
    private Boolean completed;
    private Bitmap image; // Change?
    private GoogleMap location; // Unsure of location type

    // Constructor
    public HabitEvent(String habitType, Date goalDate){
        this.user = AppLocale.getInstance().getUser();
        this.habitType = habitType;
        this.goalDate = goalDate;
        this.comment = ""; // Default comment is blank
        this.completed = false;
        this.image = null;
        this.location = null;
    }

    // Getters and Setters
    // User
    /**
     * Return user that created current habit type
     *
     * @return User
     */
    public User getUser() {
        return user;
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
    /**
     * Get completed date for habit event
     * Used to sort in history
     *
     * @return Completion date
     */
    public Date getCompletedDate(){
        return completedDate;
    }

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

    /**
     * Set completion status
     * Update completion date
     *
     * @param completed Completion status
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
        this.completedDate = new Date();
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
    public GoogleMap getLocation() {
        return location;
    }

    /**
     * Set location for habit event
     *
     * @param location Location of habit event
     */
    public void setLocation(GoogleMap location) {
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
        String strGoalDate = simpleDateFormat.format(goalDate);
        return strGoalDate;
    }

    /**
     * Convert Completed Date to String
     * Format: "Sun, Jan 01, 2017"
     *
     * @return String representation of date
     */
    public String completedDateToString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        String strCompDate= simpleDateFormat.format(completedDate);
        return strCompDate;
    }
}
