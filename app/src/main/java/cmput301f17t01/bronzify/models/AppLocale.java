package cmput301f17t01.bronzify.models;

/**
 * Created by kdehaan on 11/11/17.
 */

public class AppLocale {
    private static final AppLocale ourInstance = new AppLocale();
    private User lastUser;
    private User loggedInUser;

    /**
     * Gets the instances of the app locale
     *
     * @return
     */
    public static AppLocale getInstance() {
        return ourInstance;
    }

    private AppLocale() {
    }

    /**
     * Gets the last user that was accessing the app locale
     *
     * @return
     */
    public User getLastUser() {
        return lastUser;
    }

    /**
     * Method that retrieves the logged in user
     *
     * @return
     */
    public User getUser() {
        return loggedInUser;
    }

    /**
     * Method that sets a user as the logged in user
     *
     * @param loggedInUser
     */
    public void setUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.lastUser = loggedInUser;
    }

}
