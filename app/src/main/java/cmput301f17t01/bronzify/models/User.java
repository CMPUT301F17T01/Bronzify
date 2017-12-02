package cmput301f17t01.bronzify.models;


import java.util.ArrayList;
import java.util.Date;


/**
 * Created by kdehaan on 19/10/17.
 */

public class User {

    private String userID;

    private Date dateCreated;
    private Date lastUpdated;
    private Date lastInfluenced;

//    private ArrayList<HabitType> habitTypes = new ArrayList<HabitType>();
    private ArrayList<String> habitTypes = new ArrayList<String>();
    private ArrayList<String> following = new ArrayList<String>();
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
        if (habitTypes.contains(habitType.getName())) {
            habitTypes.add(habitType.getName());
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
        this.lastInfluenced = new Date();

    }

    /**
     * Sets the pending follow requests to a list (for offline capabilities)
     *
     * @param pendingFollowRequests
     */
    public void setPendingFollowRequests(ArrayList<String> pendingFollowRequests) {
        this.pendingFollowRequests = pendingFollowRequests;
        this.setLastInfluenced(new Date());
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
    public ArrayList<String> getHabitTypes() {
        return habitTypes;
    }

    /**
     * Method that sets a ArrayList of HabitTypes with dates
     *
     * @param habitTypes
     */
    public void setHabitTypes(ArrayList<String> habitTypes) {
        this.habitTypes = habitTypes;
        this.lastUpdated = new Date();
    }

    /**
     * Removes a habit type
     */
    public void removeHabitType(String habitTypeName) {
        if (habitTypes.contains(habitTypeName)) {
            habitTypes.remove(habitTypeName);
        }
    } //TODO: test

}
