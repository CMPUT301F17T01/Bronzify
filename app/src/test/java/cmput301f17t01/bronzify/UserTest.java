package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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
    public void testRegisterUserAlreadyExists() {
        try {
            User duplicateUser = new User("USERID", "NOTPASSWORD");
            assertTrue(Boolean.FALSE);
        } catch (UserExistsException e) {
            assertEquals(e.getMessage(), "User already exists");
        }
    }

    @Test
    public void testRequestFollow() throws UserException {
        User otherUser = new User("OTHERUSER", "PASSWORD");
        user.requestFollow(otherUser.getUserID());
        //Probably need to wait for elasticsearch to update
        assertTrue(otherUser.getPendingFollowRequests().contains(user));
        otherUser.unRegister();
    }

    @Test
    public void testAcceptFollower() throws UserException {
        User otherUser = new User("OTHERUSER", "PASSWORD");
        otherUser.requestFollow(user.getUserID());
        //Probably need to wait for elasticsearch to update
        assertTrue(user.getPendingFollowRequests().contains(otherUser));
        user.acceptFollower(otherUser.getUserID());
        //Wait for elasticsearch
        assertTrue(otherUser.getFollowing().contains(user));

        otherUser.unRegister();

    }

    public void testAddHabitType() {
        HabitType habitType = new HabitType();
        user.setHabitTypes(null);
        assertNull(user.getHabitTypes());
        user.addHabitType(habitType);
        assertTrue(user.getHabitTypes().contains(habitType));
    }

    @Test
    public void testUpdate() throws UserException {
        HabitType habitType = new HabitType();
        user.setHabitTypes(null);
        user.update();
        User remote = user.getRemote();
        assertNull(remote.getHabitTypes());
        user.addHabitType(habitType);
        remote = user.getRemote();
        assertTrue(remote.getHabitTypes().contains(habitType));
    }

    @Test
    public void testTearDown() throws UserDoesNotExistException {
        user.unRegister();
    }

}
