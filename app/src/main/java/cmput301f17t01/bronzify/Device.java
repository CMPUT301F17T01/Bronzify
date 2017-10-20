package cmput301f17t01.bronzify;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kdehaan on 19/10/17.
 */

public class Device {
    private Date dateAdded;
    //private ArrayList<User> users;
    private User lastUser;
    private User loggedInUser;

    public Device() {
        this.dateAdded = new Date();
        this.lastUser = null;
        this.loggedInUser = null;
    }

    /*
    public void addUser(User user) throws UserExistsException {
        if (this.users.contains(user)) {
            throw new UserExistsException();
        }
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
    */

    public void logIn(User user, String password) throws UserDoesNotExistException {
        if (user.getPasswordHash().equals(user.hashPassword(password))) {
            this.lastUser = user;
            this.loggedInUser = user;
        } else {
            //TODO: incorrect password handling
        }
    }

    public void logOut() {
        this.loggedInUser = null;
    }

    public User getUser(String userID) throws UserDoesNotExistException {
        //TODO: elasticsearch return user by ID
    }


    // Lasciate ogne speranza, voi ch'intrate: Here be getters and setters


    public Date getDateAdded() {
        return dateAdded;
    }

    /*
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    } */

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
