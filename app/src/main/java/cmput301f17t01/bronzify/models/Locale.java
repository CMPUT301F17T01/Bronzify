package cmput301f17t01.bronzify.models;

/**
 * Created by kdehaan on 11/11/17.
 */

public class Locale {
    private static final Locale ourInstance = new Locale();
    private User lastUser;
    private User loggedInUser;

    public static Locale getInstance() {
        return ourInstance;
    }

    private Locale() {
    }

    public User getLastUser() {
        return lastUser;
    }


    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.lastUser = loggedInUser;
    }
}
