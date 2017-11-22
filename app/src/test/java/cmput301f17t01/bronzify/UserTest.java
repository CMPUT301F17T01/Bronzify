package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t01.bronzify.exceptions.UserDoesNotExistException;
import cmput301f17t01.bronzify.exceptions.UserException;
import cmput301f17t01.bronzify.exceptions.UserExistsException;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;


/**
 * Created by kdehaan on 19/10/17.
 */

public class UserTest extends TestCase {

    Date created = new Date();
    private User user = new User("userID");

    Boolean[] days = new Boolean[7];
    HabitType habitType = new HabitType("type", "because", new Date(), days);

    @Test
    public void testToString() {
        assertEquals("userID", user.toString());
    }

    @Test
    public void testAddHabitType() {

        Date updated = user.getLastUpdated();

        user.addHabitType(habitType);
        assertTrue(user.getHabitTypes().contains(habitType));

        assertTrue(user.getLastUpdated().after(updated));
    }

    @Test
    public void testSetHabitTypes() {
        Date updated = user.getLastUpdated();

        ArrayList<HabitType> habitTypes = new ArrayList<>();
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        user.setHabitTypes(habitTypes);
        assertEquals(3, user.getHabitTypes().size());

        assertTrue(user.getLastUpdated().after(updated));
    }

    @Test
    public void testSetFollowing() {
        Date updated = user.getLastInfluenced();

        ArrayList<String> following = new ArrayList<>();
        following.add("1");
        following.add("2");
        following.add("someone");
        user.setFollowing(following);
        assertTrue(user.getFollowing().contains("someone"));
        assertEquals(3, user.getFollowing().size());

        assertTrue(user.getLastInfluenced().after(updated));

    }

    @Test
    public void testSetPendingFollowRequests() {
        Date updated = user.getLastInfluenced();

        ArrayList<String> followRequests = new ArrayList<>();
        followRequests.add("1");
        followRequests.add("2");
        followRequests.add("someone");
        user.setPendingFollowRequests(followRequests);
        assertTrue(user.getPendingFollowRequests().contains("someone"));
        assertEquals(3, user.getPendingFollowRequests().size());

        assertTrue(user.getLastInfluenced().after(updated));

    }

    @Test
    public void testGetPendingFollowRequests() { //?
        Date updated = user.getLastInfluenced();

        ArrayList<String> followRequests = new ArrayList<>();
        user.setPendingFollowRequests(followRequests);
        assertFalse(user.getPendingFollowRequests().contains("someone"));
        followRequests.add("1");
        followRequests.add("2");
        followRequests.add("someone");
        user.setPendingFollowRequests(followRequests);
        assertTrue(user.getPendingFollowRequests().contains("someone"));
        assertEquals(3, user.getPendingFollowRequests().size());

        assertTrue(user.getLastInfluenced().after(updated));
    }


    @Test
    public void testGetHabitTypes() {

        ArrayList<HabitType> habitTypes = new ArrayList<>();
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        user.setHabitTypes(habitTypes);

        assertEquals(3, user.getHabitTypes().size());
        assertTrue(user.getHabitTypes().contains(habitType));


    }

    @Test
    public void testGetLastInflucenced() {
        Date date = new Date();
        user.setLastInfluenced(date);
        assertEquals(date, user.getLastInfluenced());
    }

    @Test
    public void testSetLastInfluenced() {
        Date date = new Date();
        assertNotSame(date, user.getLastInfluenced());
        user.setLastInfluenced(date);
        assertEquals(date, user.getLastInfluenced());
    }

    @Test
    public void testAddFollowing() {
        user.setFollowing(new ArrayList<String>());
        assertFalse(user.getFollowing().contains("following"));
        user.addFollowing("following");
        assertTrue(user.getFollowing().contains("following"));
    }


    @Test
    public void removePendingFollowRequest() {
        Date updated = user.getLastInfluenced();

        ArrayList<String> followRequests = new ArrayList<>();
        user.setPendingFollowRequests(followRequests);
        user.addPendingFollowRequest("request");
        assertTrue(user.getPendingFollowRequests().contains("request"));
        user.removePendingFollowRequest("request");
        assertFalse(user.getPendingFollowRequests().contains("request"));

        assertTrue(user.getLastInfluenced().after(updated));
    }

    @Test
    public void addPendingFollowRequest() {
        Date updated = user.getLastInfluenced();

        ArrayList<String> followRequests = new ArrayList<>();
        user.setPendingFollowRequests(followRequests);
        assertFalse(user.getPendingFollowRequests().contains("request"));
        user.addPendingFollowRequest("request");
        assertTrue(user.getPendingFollowRequests().contains("request"));
        assertTrue(user.getLastInfluenced().after(updated));
    }

    @Test
    public void testGetUserID() {
        assertEquals("userID", user.getUserID());
    }

    @Test
    public void testSetUserID() {
        assertNotSame("newid", user.getUserID());
        user.setUserID("newid");
        assertEquals("newid", user.getUserID());
    }

    @Test
    public void testGetDateCreated() {
        assertEquals(created, user.getDateCreated());
    }

    @Test
    public void testGetLastUpdated() {
        Date date = new Date();
        user.setLastUpdated(date);
        assertEquals(date, user.getLastUpdated());
    }

    @Test
    public void testSetLastUpdate() {
        Date date = new Date();
        assertNotSame(date, user.getLastUpdated());
        user.setLastUpdated(date);
        assertEquals(date, user.getLastUpdated());
    }


}
