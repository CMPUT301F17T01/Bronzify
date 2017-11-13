package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import cmput301f17t01.bronzify.exceptions.UserDoesNotExistException;
import cmput301f17t01.bronzify.exceptions.UserException;
import cmput301f17t01.bronzify.exceptions.UserExistsException;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;


/**
 * Created by kdehaan on 19/10/17.
 */

public class UserTest extends TestCase {

    private User user;

//    @Test
//    public void testSetUp() throws UserExistsException {
//        user = new User("USERID", "PASSWORD");
//        assertEquals(user.getUserID(), "USERID");
//        assertEquals(user.getPasswordHash(), user.hashPassword("USERID"));
//        //TODO: elasticsearch get user to confirm existence
//    }
//
//
//    @Test
//    public void testRegisterUserAlreadyExists() {
//        try {
//            User duplicateUser = new User("USERID", "NOTPASSWORD");
//            assertTrue(Boolean.FALSE);
//        } catch (UserExistsException e) {
//            assertEquals(e.getMessage(), "User already exists");
//        }
//    }
//
//
//    /* possibly better as a controller method */
//    @Test
//    public void testRequestFollow() throws UserException {
//        User otherUser = new User("OTHERUSER", "PASSWORD");
//        user.requestFollow(otherUser.getUserID());
//        //Probably need to wait for elasticsearch to update
//        assertTrue(otherUser.getPendingFollowRequests().contains(user));
//        otherUser.unRegister();
//    }
//
//    @Test
//    public void testAcceptFollower() throws UserException {
//        User otherUser = new User("OTHERUSER", "PASSWORD");
//        otherUser.requestFollow(user.getUserID());
//        //Probably need to wait for elasticsearch to update
//        assertTrue(user.getPendingFollowRequests().contains(otherUser));
//        user.acceptFollower(otherUser.getUserID());
//        //Wait for elasticsearch
//        assertTrue(otherUser.getFollowing().contains(user));
//
//        otherUser.unRegister();
//
//    }
//
//
//    public void testAddHabitType() {
//        HabitType habitType = new HabitType("Test habit");
//        user.setHabitTypes(null);
//        assertNull(user.getHabitTypes());
//        user.addHabitType(habitType);
//        assertTrue(user.getHabitTypes().contains(habitType));
//    }
//
//    /* possibly better as a controller method */
//    @Test
//    public void testUpdate() throws UserException {
//        HabitType habitType = new HabitType("Test habit");
//        user.setHabitTypes(null);
//        user.update();
//        User remote = user.getRemote();
//        assertNull(remote.getHabitTypes());
//        user.addHabitType(habitType);
//        remote = user.getRemote();
//        assertTrue(remote.getHabitTypes().contains(habitType));
//    }
//
//
//    @Test
//    public void testTearDown() throws UserDoesNotExistException {
//        user.unRegister();
//    }

}
