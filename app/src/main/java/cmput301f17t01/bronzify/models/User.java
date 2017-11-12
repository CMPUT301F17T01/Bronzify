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
//    private String passwordHash;
    private Date dateCreated;
    private Date lastUpdated;
    private ArrayList<HabitType> habitTypes;
    private ArrayList<User> following;
    private ArrayList<User> pendingFollowRequests;

    public User(String userID) /* throws UserExistsException */{
        this.userID = userID;
        this.dateCreated = new Date();
//        this.passwordHash = hashPassword(password);
//        this.register();
    }


    public String toString() {
        return userID + dateCreated.toString();
    }

//    public String hashPassword(String password) {
//        try {
//
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(password.getBytes(Charset.forName("UTF-8")));
//            String hashedPassword = new String(hash, Charset.forName("UTF-8"));
//            return hashedPassword;
//        } catch (NoSuchAlgorithmException e) {
//            return password; //TODO: this is really bad
//        }
//
//    }


//    public void requestFollow(String otherUserID) throws UserDoesNotExistException {
//        //TODO: elasticsearch request follow
//    }
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

    public void copyRemote(User remote) {
        //TODO: copy remote user object
        this.lastUpdated = remote.getLastUpdated();
        this.following = remote.getFollowing();
        this.pendingFollowRequests = remote.getPendingFollowRequests();
        this.habitTypes = remote.getHabitTypes();

    }


    public void addHabitType(HabitType habitType) {
        if (habitTypes.contains(habitType)) {
        } else {
            habitTypes.add(habitType);
            this.lastUpdated = new Date();
        }
    }



    // Lasciate ogni speranza, voi ch'entrate: Here be getters and setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

//    public String getPasswordHash() {
//        return passwordHash;
//    }

//    public void setPasswordHash(String passwordHash) {
//        this.passwordHash = passwordHash;
//    }

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

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
        this.lastUpdated = new Date();
    }

    public ArrayList<User> getPendingFollowRequests() {
        return pendingFollowRequests;
    }

    public void setPendingFollowRequests(ArrayList<User> pendingFollowRequests) {
        this.pendingFollowRequests = pendingFollowRequests;
        this.lastUpdated = new Date();
    }
}
