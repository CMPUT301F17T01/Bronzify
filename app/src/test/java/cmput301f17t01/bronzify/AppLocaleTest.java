package cmput301f17t01.bronzify;


import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.User;

/**
 * Created by kdehaan on 13/11/17.
 */

public class AppLocaleTest extends TestCase {
    AppLocale appLocale = AppLocale.getInstance();
    User user = new User("user");

    @Test
    public void testSetUp() {
        AppLocale appLocale = AppLocale.getInstance();
        assertEquals(AppLocale.class, appLocale.getClass());
    }

    @Test
    public void testGetLastUser() { //will be used for login persistence
        appLocale.setUser(user);
        assertEquals(user, appLocale.getLastUser());
    }

    @Test
    public void testGetUser() {
        appLocale.setUser(user);
        assertEquals(user, appLocale.getUser());
    }

    @Test
    public void testGetSavedUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        appLocale.setSavedUsers(users);
        User newUser = appLocale.getSavedUser("user");
        assertEquals(user, newUser);
    }

    @Test
    public void testRemoveSavedUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        appLocale.setSavedUsers(users);
        assertTrue(appLocale.getSavedUsers().contains(user));
        appLocale.removeSavedUser(user);
        assertFalse(appLocale.getSavedUsers().contains(user));
    }

    @Test
    public void testSaveUser() {
        User newUser = new User("newUser");
        assertFalse(appLocale.getSavedUsers().contains(newUser));
        appLocale.saveUser(newUser);
        assertTrue(appLocale.getSavedUsers().contains(newUser));
        assertNotSame(newUser, appLocale.getUser());

    }

    @Test
    public void testSetUser() {
        User newUser = new User("newUser");
        assertFalse(appLocale.getSavedUsers().contains(newUser));
        appLocale.setUser(newUser);
        assertTrue(appLocale.getSavedUsers().contains(newUser));
        assertEquals(newUser, appLocale.getUser());

    }

    @Test
    public void testLogoutUser() {
        appLocale.setUser(user);
        assertEquals(user, appLocale.getUser());
        assertEquals(user, appLocale.getLastUser());
        appLocale.logoutUser();
        assertNotSame(user, appLocale.getUser());
        assertEquals(user, appLocale.getLastUser());
    }

    @Test
    public void testLoadFromFile() {
        //TODO: implement when method is implemented
    }

    @Test
    public void testSaveInFile() {
        //TODO: implement when method is implemented
    }

    @Test
    public void testGetSavedUsers() {
        User newUser = new User("newUser");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(newUser);
        appLocale.setSavedUsers(users);
        assertTrue(appLocale.getSavedUsers().contains(user));
        assertTrue(appLocale.getSavedUsers().contains(newUser));
    }

    @Test
    public void testSetSavedUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        assertFalse(appLocale.getSavedUsers().contains(user));
        appLocale.setSavedUsers(users);
        assertTrue(appLocale.getSavedUsers().contains(user));
    }


}
