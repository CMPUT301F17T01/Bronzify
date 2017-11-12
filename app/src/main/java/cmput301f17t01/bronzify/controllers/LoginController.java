package cmput301f17t01.bronzify.controllers;

import cmput301f17t01.bronzify.models.ElasticSearch;
import cmput301f17t01.bronzify.models.Locale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 11/11/17.
 */

public class LoginController {
    Locale locale = Locale.getInstance();
    ElasticSearch elastic = new ElasticSearch();

    public User checkLogin(String userID) {
        User user = elastic.getUser(userID);
        return user;
    }

    public void loginUser(User user) {
        locale.setUser(user);
    }

    public void registerUser(String userID) {

    }
}
