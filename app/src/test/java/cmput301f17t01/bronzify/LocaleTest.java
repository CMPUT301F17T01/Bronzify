package cmput301f17t01.bronzify;


import junit.framework.TestCase;
import org.junit.Test;

import cmput301f17t01.bronzify.models.Locale;


/**
 * Created by kdehaan on 19/10/17.
 */

public class LocaleTest extends TestCase {
    private Locale locale;

    @Test
    public void testSetUp() {
        locale = Locale.getInstance();
        assertNull(locale.getLastUser());
        assertNull(locale.getLoggedInUser());
    }


//    @Test
//    public void testLogInCorrectPassword() throws UserException {
//        locale.logOut();
//        TestUser user = new TestUser("TESTUSER", "PASSWORD");
//        locale.logIn(user, "PASSWORD");
//        assertEquals(user, locale.getLoggedInUser());
//        locale.logOut();
//        user.unRegister();
//    }
//
//    @Test
//    public void testLogInIncorrectPassword() throws UserException {
//        locale.logOut();
//        TestUser user = new TestUser("TESTUSER", "PASSWORD");
//        locale.logIn(user, "NOTPASSWORD");
//        if (user.equals(locale.getLoggedInUser())) {
//            assertTrue(Boolean.FALSE);
//        }
//        locale.logOut();
//        user.unRegister();
//    }
//
//    @Test
//    public void testLogInNonExistentAccount() {
//        TestUser user = null;
//        try {
//            locale.logIn(user, "PASSWORD");
//            assertTrue(Boolean.FALSE);
//        } catch (UserDoesNotExistException e) {
//            assertEquals(e.getMessage(), "User does not exist");
//        }
//        locale.logOut();
//    }
//
//    @Test
//    public void testLogOut() throws UserException {
//        locale.logOut();
//        TestUser user = new TestUser("TESTUSER", "PASSWORD");
//        locale.logIn(user, "PASSWORD");
//        locale.logOut();
//        assertNull(locale.getLoggedInUser());
//        user.unRegister();
//    }


    @Test
    public void testTearDown() {
        //TODO: ???
    }



}