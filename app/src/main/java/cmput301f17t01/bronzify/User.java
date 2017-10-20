package cmput301f17t01.bronzify;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kdehaan on 19/10/17.
 */

public class User {
    private String userID;
    private String passwordHash;
    private Date dateCreated;
    private Date lastUpdated;
    private ArrayList<HabitType> habitTypes;
    private ArrayList<User> following;
    private ArrayList<User> pendingFollowRequests;

    public User(String userID, String password) {
        this.userID = userID;
        this.passwordHash = hashPassword(password);
    }

    public String hashPassword(String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(Charset.forName("UTF-8")));
            String hashedPassword = new String(hash, Charset.forName("UTF-8"));
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            return password; //TODO: this is really bad
        }

    }

    public void register() throws UserExistsException {
        //TODO: elasticsearch registration
    }


    // Lasciate ogne speranza, voi ch'intrate: Here be getters and setters

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public ArrayList<User> getPendingFollowRequests() {
        return pendingFollowRequests;
    }

    public void setPendingFollowRequests(ArrayList<User> pendingFollowRequests) {
        this.pendingFollowRequests = pendingFollowRequests;
    }
}
