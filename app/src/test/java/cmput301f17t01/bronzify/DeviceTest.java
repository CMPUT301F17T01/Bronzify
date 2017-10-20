package cmput301f17t01.bronzify;


import junit.framework.TestCase;
import org.junit.Test;


/**
 * Created by kdehaan on 19/10/17.
 */

public class DeviceTest extends TestCase {
    private Device device;

    @Test
    public void testSetUp() {
        device = new Device();
        assertNull(device.getLastUser());
        assertNull(device.getLoggedInUser());
    }


    @Test
    public void testAddUserNewUser() throws UserException {
        User user = new User("TESTUSER", "PASSWORD");
        device.addUser(user);
        assertTrue(device.getUsers().contains(user));
        user.unRegister();
    }

    @Test // Java does not attempt to detect duplicates, so this is valuable
    public void testAddNewUserAlreadyExists() throws UserException {
        User user = new User("TESTUSER", "PASSWORD");
        device.addUser(user);
        try {
            device.addUser(user);
            assertTrue(Boolean.FALSE);
        } catch (UserExistsException e){
            assertEquals(e.getMessage(), "User already exists");
        }
        user.unRegister();
    }

    @Test
    public void testLogInCorrectPassword() throws UserException {
        User user = new User("TESTUSER", "PASSWORD");
        device.logIn(user, "PASSWORD");
        assertEquals(user, device.getLoggedInUser());
        device.logOut();
        user.unRegister();
    }

    @Test
    public void testLogInIncorrectPassword() throws UserException {
        User user = new User("TESTUSER", "PASSWORD");
        device.logIn(user, "NOTPASSWORD");
        if (user.equals(device.getLoggedInUser())) {
            assertTrue(Boolean.FALSE);
        }
        device.logOut();
        user.unRegister();
    }

    @Test
    public void testLogInNonExistentAccount() {
        User user = null;
        try {
            device.logIn(user, "PASSWORD");
            assertTrue(Boolean.FALSE);
        } catch (UserDoesNotExistException e) {
            assertEquals(e.getMessage(), "User does not exist");
        }
    }

    @Test
    public void testLogOut() throws UserException {
        User user = new User("TESTUSER", "PASSWORD");
        device.logIn(user, "PASSWORD");
        device.logOut();
        assertNull(device.getLoggedInUser());
        user.unRegister();
    }


    @Test
    public void testTearDown() {
        //TODO: ???
    }



}