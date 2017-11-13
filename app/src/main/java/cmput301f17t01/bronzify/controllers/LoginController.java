package cmput301f17t01.bronzify.controllers;

import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.Controller;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 11/11/17.
 */

public class LoginController implements Controller {
    AppLocale appLocale = AppLocale.getInstance();
    ElasticSearch elastic = new ElasticSearch();

    public User checkLogin(String userID) {
        User user = elastic.getUser(userID);
        if (user != null) {
            appLocale.saveUser(user);
        }
        return user;
    }

    public void loginUser(User user) {
        appLocale.setUser(user);
    }

    public void registerUser(String userID) {
        User user = new User(userID);
        elastic.postUser(user);
    }
}
