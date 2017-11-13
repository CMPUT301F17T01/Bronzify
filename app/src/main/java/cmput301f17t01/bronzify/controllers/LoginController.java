package cmput301f17t01.bronzify.controllers;

import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 11/11/17.
 */

public class LoginController implements Controller {
    AppLocale appLocale = AppLocale.getInstance();
    ElasticSearch elastic = new ElasticSearch();

    /**
     * Checks if the user is logged in or not
     *
     * @param userID
     * @return
     */
    public User checkLogin(String userID) {
        User user = elastic.getUser(userID);
        if (user != null) {
            appLocale.saveUser(user);
        }
        return user;
    }

    /**
     * If the user is not already logged in, it will log them in based on the button click and
     * userID provided
     *
     * @param user
     */
    public void loginUser(User user) {
        appLocale.setUser(user);
    }

    /**
     * If the user is not already logged in, it will register them in based on the button click
     * and userID provided
     *
     * @param userID
     */
    public void registerUser(String userID) {
        User user = new User(userID);
        elastic.postUser(user);
    }
}
