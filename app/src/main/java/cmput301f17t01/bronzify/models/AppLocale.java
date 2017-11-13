package cmput301f17t01.bronzify.models;

/**
 * Created by kdehaan on 11/11/17.
 */

public class AppLocale {
    private static final AppLocale ourInstance = new AppLocale();
    private User lastUser;
    private User loggedInUser;


    public static AppLocale getInstance() {
        return ourInstance;
    }

    private AppLocale() {
    }

    public User getLastUser() {
        return lastUser;
    }


    public User getUser() {
        return loggedInUser;
    }

    public void setUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.lastUser = loggedInUser;
    }
}
