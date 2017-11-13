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


    public User(String userID) {
        this.userID = userID;
        this.dateCreated = new Date();
        this.lastInfluenced = new Date();
        this.lastUpdated = new Date();
    }


    public String toString() {
        return userID + dateCreated.toString();
    }

    public void requestFollow(String otherUserID) {
        ElasticSearch elastic = new ElasticSearch();
        User remoteUser = elastic.getUser(otherUserID);
        remoteUser.addPendingFollowRequest(this.userID);
        remoteUser.setLastInfluenced(new Date());
        elastic.postUser(remoteUser);
    }

    public void acceptFollow(String otherUserID) {
        ElasticSearch elastic = new ElasticSearch();
        User remoteUser = elastic.getUser(otherUserID);
        remoteUser.addFollowing(this.userID);
        remoteUser.setLastInfluenced(new Date());
        elastic.postUser(remoteUser);
        this.removePendingFollowRequest(otherUserID);
        update();
    }

    public void update() {
        ElasticSearch elastic = new ElasticSearch();
        User newestUser = elastic.update(this);

        this.setLastUpdated(newestUser.getLastUpdated());
        this.following = newestUser.getFollowing();
        this.pendingFollowRequests = newestUser.getPendingFollowRequests();
        this.habitTypes = newestUser.getHabitTypes();
        AppLocale.getInstance().setUser(this);
    }

    // Lasciate ogni speranza, voi ch'entrate: Here be getters and setters

    // Habit Type
    public void addHabitType(HabitType habitType) {
        habitTypes.add(habitType);
        this.lastUpdated = new Date();
    }
    public ArrayList<HabitType> getHabitTypes() {
        return habitTypes;
    }

    public Date getLastInfluenced() {
        return lastInfluenced;
    }

    public void setLastInfluenced(Date lastInfluenced) {
        this.lastInfluenced = lastInfluenced;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void addFollowing(String userID) {
        following.add(userID);
    }

    public void removeFollowing(String userID) {
        following.remove(userID); // might not work
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public ArrayList<String> getPendingFollowRequests() {
        return pendingFollowRequests;
    }

    public void removePendingFollowRequest(String userID) {
        pendingFollowRequests.remove(userID); //might not work
    }

    public void addPendingFollowRequest(String userID) {
        pendingFollowRequests.add(userID);
    }

    public void setPendingFollowRequests(ArrayList<String> pendingFollowRequests) {
        this.pendingFollowRequests = pendingFollowRequests;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
