package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Random;

/**
 * Created by kdehaan on 19/10/17.
 */

public class UserTest extends TestCase {

    private User user;

    @Test
    public void testSetUp() throws UserExistsException {
        user = new User("USERID", "PASSWORD");
        assertEquals(user.getUserID(), "USERID");
        assertEquals(user.getPasswordHash(), user.hashPassword("USERID"));
        //TODO: elasticsearch get user to confirm existence
    }


    @Test
    public void testRegisterUserAlreadyExists() throws UserExistsException {
        try {
            User duplicateUser = new User("USERID", "NOTPASSWORD");
            assertTrue(Boolean.FALSE);
        } catch (UserExistsException e) {
            assertEquals(e.getMessage(), "User already exists");
        }
    }

    /* //not sure whether to implement this
    @Test
    public void testHashPassword() {
        String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while (builder.length() < 18) { // arbitrary string length
            int index = (int) (rnd.nextFloat() * randChars.length());
            builder.append(randChars.charAt(index));
        }
        String testPassword = builder.toString();

    }
    */
    @Test
    public void testRequestFollower() {

    }

    @Test
    public void testAcceptFollower() {

    }

    @Test
    public void testUpdate() {


    }

    @Test
    public void testDisplayMessage() {

    }

    @Test
    public void testTearDown() {

    }

}
