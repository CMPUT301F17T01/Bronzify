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



    public void addHabitType(HabitType habitType) {
        if (habitTypes.contains(habitType)) {
        } else {
            habitTypes.add(habitType);
            this.lastUpdated = new Date();
        }
    }



    // Lasciate ogni speranza, voi ch'entrate: Here be getters and setters


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

    public ArrayList<HabitType> getHabitTypes() {
        return habitTypes;
    }

    public void setHabitTypes(ArrayList<HabitType> habitTypes) {
        this.habitTypes = habitTypes;
        this.lastUpdated = new Date();
    }

}
