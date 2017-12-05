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
    public void testGetLocalUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        appLocale.setLocalUsers(users);
        User newUser = appLocale.getLocalUser("user");
        assertEquals(user, newUser);
    }

    @Test
    public void testRemoveLocalUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        appLocale.setLocalUsers(users);
        assertTrue(appLocale.getLocalUsers().contains(user));
        appLocale.removeLocalUser(user);
        assertFalse(appLocale.getLocalUsers().contains(user));
    }

    @Test
    public void testSaveUser() {
        User newUser = new User("newUser");
        assertFalse(appLocale.getLocalUsers().contains(newUser));
        appLocale.addLocalUser(newUser);
        assertTrue(appLocale.getLocalUsers().contains(newUser));
        assertNotSame(newUser, appLocale.getUser());

    }

    @Test
    public void testSetUser() {
        User newUser = new User("newUser");
        assertFalse(appLocale.getLocalUsers().contains(newUser));
        appLocale.setUser(newUser);
        assertTrue(appLocale.getLocalUsers().contains(newUser));
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
    public void testGetLocalUsers() {
        User newUser = new User("newUser");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(newUser);
        appLocale.setLocalUsers(users);
        assertTrue(appLocale.getLocalUsers().contains(user));
        assertTrue(appLocale.getLocalUsers().contains(newUser));
    }

    @Test
    public void testSetLocalUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        assertFalse(appLocale.getLocalUsers().contains(user));
        appLocale.setLocalUsers(users);
        assertTrue(appLocale.getLocalUsers().contains(user));
    }


}
