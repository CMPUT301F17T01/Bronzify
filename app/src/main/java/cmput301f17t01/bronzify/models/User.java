package cmput301f17t01.bronzify.models;


import android.graphics.Bitmap;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by kdehaan on 19/10/17.
 */

public class User {

    private String userID;
    private Date dateCreated;
    private Date lastUpdated;
    private Date lastInfluenced;
    private GoogleMap location;
    private Double score;
    private Bitmap image;

    private ArrayList<HabitType> habitTypes = new ArrayList<HabitType>();
//    private ArrayList<String> habitTypes = new ArrayList<String>();
    private ArrayList<String> following = new ArrayList<String>();
    private ArrayList<String> followedBy = new ArrayList<String>();
    private ArrayList<String> pendingFollowRequests = new ArrayList<String>();

    /**
     * Creation of a user
     *
     * @param userID
     */
    public User(String userID) {
        this.userID = userID;
        this.dateCreated = new Date();
        this.lastInfluenced = new Date();
        this.lastUpdated = new Date();
        this.score = 0.0;
    }

    /**
     * Convert to a string
     *
     * @return
     */
    public String toString() {
        return userID;
    }



    /**
     * Add a habit type
     * 
     * @param habitType
     * @return
     */
    public void addHabitType(HabitType habitType) throws Exception {
        if (getType(habitType.getName()) == null) {
            habitTypes.add(habitType);
            this.lastUpdated = new Date();
        } else {
            throw new Exception("Habit type exists");
        }
    }


    /**
     * Method returning the last influence piece of data while you are offline. To be used
     * differently than the last updated. (OFFLINE)
     *
     * @return
     */
    public Date getLastInfluenced() {
        return lastInfluenced;
    }

    /**
     * Method setting the last influenced data
     *
     * @param lastInfluenced
     */
    public void setLastInfluenced(Date lastInfluenced) {
        this.lastInfluenced = lastInfluenced;
    }

    /**
     * Returns a list of Strings of who a user is following
     *
     * @return
     */
    public ArrayList<String> getFollowing() {
        return following;
    }

    /**
     * Method that adds a new follower to the list of followers
     *
     * @param userID
     */
    public void addFollowing(String userID) {
        following.add(userID);
        setLastInfluenced(new Date());
    } //TODO: investigate passing the user directly

    /**
     * Removes a follower
     *
     * @param userID
     */
    public void removeFollowing(String userID) {
        following.remove(userID); // might not work
    }

    /**
     * Method that sets a user following (after they have accepted the follower request)
     *
     * @param following
     */
    public void setFollowing(ArrayList<String> following) {
        this.following = following;

    }

    /**
     * Sets the pending follow requests to a list (for offline capabilities)
     *
     * @param pendingFollowRequests
     */
    public void setPendingFollowRequests(ArrayList<String> pendingFollowRequests) {
        this.pendingFollowRequests = pendingFollowRequests;
    }

    /**
     * Method that retrieves all of the pending follow requests
     *
     * @return
     */
    public ArrayList<String> getPendingFollowRequests() {
        return pendingFollowRequests;
    }

    /**
     * Method that removes pending follow requests
     *
     * @param userID
     */
    public void removePendingFollowRequest(String userID) {
        pendingFollowRequests.remove(userID); 
        this.lastInfluenced = new Date();
    }

    /**
     * Method that adds to the list of pending follow requests
     *
     * @param userID
     */
    public void addPendingFollowRequest(String userID) {
        pendingFollowRequests.add(userID);
        this.lastInfluenced = new Date();
    }



    /**
     * Method that returns the userID of the logged in user
     *
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the userID of the logged in user
     *
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Method that returns the date created (to be used with habit events or types)
     *
     * @return
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Method that returns when the last update was
     *
     * @return
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Method that sets the last update
     *
     * @param lastUpdated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    /**
     * Method that returns a list of HabitTypes
     *
     * @return
     */
    public ArrayList<HabitType> getHabitTypes() {
        return habitTypes;
    }

    /**
     * Method that sets a ArrayList of HabitTypes with dates
     *
     * @param habitTypes
     */
    public void setHabitTypes(ArrayList<HabitType> habitTypes) {
        this.habitTypes = habitTypes;
    }

    /**
     * Removes a habit type
     */
    public void removeHabitType(HabitType deletedHabit) {
        if(habitTypes.contains(deletedHabit)){
            habitTypes.remove(deletedHabit);
        }
    } //TODO: test

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public GoogleMap getLocation() {
        return location;
    }

    public void setLocation(GoogleMap location) {
        this.location = location;
    }

//    public void updateEvent(HabitEvent event) {
//        HabitType type = getType(event.getHabitType());
//        HabitEvent oldEvent = type.getEvent(event.getGoalDate());
//        type.updateEvent(oldEvent, event);
//        updateType(type);
//    }

//    public void updateType(HabitType type) {
//        HabitType oldType = getType(type.getName());
//        if (habitTypes.contains(oldType)) {
//            habitTypes.remove(oldType);
//            habitTypes.add(type);
//        }
//    }

//    public HabitEvent getEvent(Date eventGoal, String typeName) {
//        HabitType type = getType(typeName);
//        Iterator<HabitEvent> itr = type.getHabitEvents().iterator();
//        while (itr.hasNext()) {
//            HabitEvent next = itr.next();
//            if (next.getGoalDate().getTime() - eventGoal.getTime() < 1000) {
//                return next;
//            }
//        }
//        return null;
//    }

    public HabitType getType(String typeName) {
        Iterator<HabitType> itr = habitTypes.iterator();
        while (itr.hasNext()) {
            HabitType next = itr.next();
            if (next.getName().equals(typeName)) {
                return next;
            }
        }
        return null;
    }
    public Boolean isHabitUsed(String newHabit){
        for(HabitType habit: habitTypes){
            if(habit.getName().equals(newHabit)){
                return true;
            }
        }
        return false;
    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ArrayList<String> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(ArrayList<String> followedBy) {
        this.followedBy = followedBy;
    }

    public void addFollowedBy(String otherUserID) {
        if (!followedBy.contains(otherUserID)){
            followedBy.add(otherUserID);
        }
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
