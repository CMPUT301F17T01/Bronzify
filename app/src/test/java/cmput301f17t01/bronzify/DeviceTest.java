package cmput301f17t01.bronzify;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;
import org.junit.Test;


/**
 * Created by kdehaan on 19/10/17.
 */

public class DeviceTest extends TestCase{
    public Device device;

    @Test
    public void testSetUp() {
        device = new Device();
        //TODO: check construction values
    }


    @Test
    public void testRegisterUserNewUser() throws UserExistsException {
        User user = new User("TESTUSER", "PASSWORD");
        device.registerUser(user);
        //TODO: elasticsearch get user to confirm existence
        user.delete();
    }

    @Test
    public void testRegisterUserAlreadyExists() throws UserExistsException {
        User user = new User("TESTUSER", "PASSWORD");
        device.registerUser(user);
        try {
            device.registerUser(user);
            assertTrue(Boolean.FALSE);
        } catch (UserExistsException e) {
            assertEquals(e.getMessage(), "User already exists");
        }
        user.delete();
    }

    @Test
    public void testAddUserNewUser() {
        User user = new User("TESTUSER", "PASSWORD");
        device.addUser(user);
        assertTrue(device.getUsers().contains(user));
        user.delete();
    }

    @Test
    public void testAddNewUserAlreadyExists() {
        User user = new User("TESTUSER", "PASSWORD");
        device.addUser(user);
        try {
            device.addUser(user);
            assertTrue(Boolean.FALSE);
        } catch (Exception e){
            assertEquals(e.getMessage(), "User in list");
        }
        user.delete();
    }

    @Test
    public void testLogInCorrectPassword() {

    }

    @Test
    public void testLogInIncorrectPassword() {

    }

    @Test
    public void testLogInNonExistentAccount() {

    }

    @Test
    public void testTearDown() {

    }



}