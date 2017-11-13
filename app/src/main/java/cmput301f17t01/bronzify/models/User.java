package cmput301f17t01.bronzify.models;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import cmput301f17t01.bronzify.exceptions.UserDoesNotExistException;
import cmput301f17t01.bronzify.exceptions.UserException;
import cmput301f17t01.bronzify.exceptions.UserExistsException;

/**
 * Created by kdehaan on 19/10/17.
 */

public class User {

    private String userID;

    private Date dateCreated;
    private Date lastUpdated;
    private Date lastInfluenced;

    private ArrayList<HabitType> habitTypes = new ArrayList<HabitType>();
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
     * Converting a date to a string
     *
     * @return
     */
    public String toString() {
        return userID + dateCreated.toString();
    }

    /**
     * Creation of a request to follow
     *
     * @param otherUserID
     */
    public void requestFollow(String otherUserID) {
        ElasticSearch elastic = new ElasticSearch();
        User remoteUser = elastic.getUser(otherUserID);
        remoteUser.addPendingFollowRequest(this.userID);
        remoteUser.setLastInfluenced(new Date());
        elastic.postUser(remoteUser);
    }

    /**
     * Method for accepting a follow request
     *
     * @param otherUserID
     */
    public void acceptFollow(String otherUserID) {
        ElasticSearch elastic = new ElasticSearch();
        User remoteUser = elastic.getUser(otherUserID);
        remoteUser.addFollowing(this.userID);
        remoteUser.setLastInfluenced(new Date());
        elastic.postUser(remoteUser);
        this.removePendingFollowRequest(otherUserID);
        update();
    }

    /**
     * Method to update the elastic search data
     *
     */
    public void update() {
        ElasticSearch elastic = new ElasticSearch();
        User newestUser = elastic.update(this);

        this.setLastUpdated(newestUser.getLastUpdated());
        this.following = newestUser.getFollowing();
        this.pendingFollowRequests = newestUser.getPendingFollowRequests();
        this.habitTypes = newestUser.getHabitTypes();
    }
//
//    public void acceptFollower(String otherUserID) throws UserDoesNotExistException {
//        //TODO: elasticsearch accept follow
//    }
//
//
////    public void register() throws UserExistsException {
////        //TODO: elasticsearch registration
////    }
//
//    public void unRegister() throws UserDoesNotExistException {
//        //TODO: elasticsearch unregistration
//    }
//
//
//    public User getRemote() throws UserException {
//        //TODO: elastic search get user object based on ID
//        return new TestUser("REMOTE", "PASSWORD");
//    }
//
//    public void setRemote() {
//        //TODO: elasticsearch set remote user object
//    }


//    public void update() throws UserException {
//        //TODO: elasticsearch get remote timestamp
//        User remote = getRemote();
//        if (this.lastUpdated.after(remote.getLastUpdated())) {
//            this.lastUpdated = new Date();
//            this.setRemote();
//        } else {
//            this.copyRemote(remote);
//        }
//        //TODO: remote last updated = new Date()
//    }

    /**
     * Method for adding a new habit type
     *
     * @param habitType
     */
    public void addHabitType(HabitType habitType) {
        if (habitTypes.contains(habitType)) {
        } else {
            habitTypes.add(habitType);
            this.lastUpdated = new Date();
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
    }

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
        pendingFollowRequests.remove(userID); //might not work
    }

    /**
     * Method that adds to the list of pending follow requests
     *
     * @param userID
     */
    public void addPendingFollowRequest(String userID) {
        pendingFollowRequests.add(userID);
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
        this.lastUpdated = new Date();
    }

}
