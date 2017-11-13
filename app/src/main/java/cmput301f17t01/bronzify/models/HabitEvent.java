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

    private Date goalDate; // Date for habit event to happen
    private Date completedDate;
    private String comment; // Max 20 Char
    private Boolean completed;
    private Bitmap image; // Change?
    private GoogleMap location; // Unsure of location type

    // Constructor
    public HabitEvent(Date goalDate){
        this.user = AppLocale.getInstance().getUser();
        this.goalDate = goalDate;
        this.comment = ""; // Default comment is blank
        this.completed = false;
        this.image = null;
        this.location = null;
    }

    // Getters and Setters
    // User
    public User getUser() {
        return user;
    }

    // Goal Date
    public Date getGoalDate() {
        return goalDate;
    }
    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }

    // Completed Date
    public Date getCompletedDate(){
        return completedDate;
    }

    // Comment
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Completed
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
        this.completedDate = new Date();
    }

    // Image
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }

    // Location
    public GoogleMap getLocation() {
        return location;
    }
    public void setLocation(GoogleMap location) {
        this.location = location;
    }

    // Convert Date to String ("Sun, Jan 01, 2017")
    public String dateToString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");
        String strGoalDate = simpleDateFormat.format(goalDate);
        return strGoalDate;
    }
}
