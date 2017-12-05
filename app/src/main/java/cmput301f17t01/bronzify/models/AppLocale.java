package cmput301f17t01.bronzify.models;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by kdehaan on 11/11/17.
 */

public class AppLocale {
    private static final AppLocale ourInstance = new AppLocale();
    private User lastUser;
    private User user;
    private HabitEvent event;
    private HabitType type;

    private ArrayList<User> localUsers = new ArrayList<>();

    /**
     * Gets the instances of the app locale
     *
     * @return ourInstance
     */
    public static AppLocale getInstance() {
        return ourInstance;
    }

    /**
     * Gets the last user that was accessing the app locale
     *
     * @return lastUser
     */
    public User getLastUser() {
        return lastUser;
    }


    /**
     * Method that retrieves the logged in user
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the local user, this is used for offline capabilities
     *
     * @param userID
     * @return
     */
    public User getLocalUser(String userID) {
        if (localUsers == null) {
            return null;
        }
        Iterator<User> itr = localUsers.iterator();

        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(userID)) {
                return next;
            }
        }
        return null;
    }

    /**
     * Deletes the local user based on the user
     *
     * @param deleteUser
     */
    public void removeLocalUser(User deleteUser) {
        Iterator<User> itr = localUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(deleteUser.getUserID())) {
                localUsers.remove(next);
                return;
            }
        }
    }

    /**
     * Deletes the local user based on their userID in a string format
     *
     * @param userID
     */
    public void removeLocalUser(String userID) {
        Iterator<User> itr = localUsers.iterator();
        while (itr.hasNext()) {
            User next = itr.next();
            if (next.getUserID().equals(userID)) {
                localUsers.remove(next);
                return;
            }
        }
    }

    /**
     * Adds a new local user if there already isn't one
     *
     * @param newUser
     */
    public void addLocalUser(User newUser) {
        if (!localUsers.contains(newUser)) {
            localUsers.add(newUser);
        }

    }

    /**
     * Method that sets a user as the logged in user
     * <p>
     * newUser @param
     */
    public void setUser(User newUser) {
        this.user = newUser;
        this.lastUser = newUser;
        addLocalUser(newUser);
    }

    /**
     * Logs out the user by setting the current user to null
     */
    public void logoutUser() {
        user = null;
    }

    /**
     * Returns an array list of local users
     *
     * @return
     */
    public ArrayList<User> getLocalUsers() {
        return localUsers;
    }

    /**
     * Sets the local users to an array list of local users
     *
     * @param localUsers
     */
    public void setLocalUsers(ArrayList<User> localUsers) {
        this.localUsers = localUsers;
    }

    /**
     * Returns the habit event
     *
     * @return
     */
    public HabitEvent getEvent() {
        return event;
    }

    /**
     * Sets the habit events
     *
     * @param event
     */
    public void setEvent(HabitEvent event) {
        this.event = event;
    }

    /**
     * Returns the habit type
     *
     * @return
     */
    public HabitType getType() {
        return type;
    }

    /**
     * Sets the habit type
     *
     * @param type
     */
    public void setType(HabitType type) {
        this.type = type;
    }
}
