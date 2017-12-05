package cmput301f17t01.bronzify;

import android.location.Location;

import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import cmput301f17t01.bronzify.adapters.UserAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;


/*
 * Created by kdehaan on 19/10/17.
 */

public class UserTest extends TestCase {

    final Date created = new Date();
    private final User user = new User("userID");

    final Boolean[] daysOfWeek = {false, true, false, true, false, false, false,};
    final HabitType habitType = new HabitType("name" , "reason", new Date(), daysOfWeek);

    @Test
    public void testSetup() {
        AppLocale appLocale = AppLocale.getInstance();
        appLocale.setUser(user);
//        habitType = new HabitType("name" , "reason", new Date(), daysOfWeek);
    }

    @Test
    public void testJson() {
        User user0 = new User("testID");
        try {
            user0.addHabitType(habitType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> followRequests = new ArrayList<>();
//        followRequests.add("someone");
        user0.setPendingFollowRequests(followRequests);
        ArrayList<String> following = new ArrayList<>();
        following.add("best person");
        following.add("someone else");
        user0.setFollowing(following);
        user0.setScore(0.9);

        Gson gsonUser = new GsonBuilder().registerTypeAdapter(User.class,
                new UserAdapter()).create();

        String json = gsonUser.toJson(user0);
        User user2 = gsonUser.fromJson(json, User.class);
        assertTrue((user0.getDateCreated().getTime() - user2.getDateCreated().getTime()) < 1000);
        assertTrue((user0.getLastInfluenced().getTime() - user2.getLastInfluenced().getTime()) < 1000);
        assertTrue((user0.getLastUpdated().getTime() - user2.getLastUpdated().getTime()) < 1000);
        assertEquals(user0.getHabitTypes().size(), user2.getHabitTypes().size()); // relying on habiteventtest to confirm this
        assertEquals(user0.getUserID(), user2.getUserID());
        assertEquals(user0.getFollowing(), user2.getFollowing());
        assertEquals(user0.getPendingFollowRequests(), user2.getPendingFollowRequests());
    }

    @Test
    public void testToString() {
        assertEquals("userID", user.toString());
    }

    @Test
    public void testAddHabitType() {

        Date updated = user.getLastUpdated();

        try {
            user.addHabitType(habitType);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

//        assertTrue(user.getLastUpdated().after(updated));
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

//        assertTrue(user.getLastInfluenced().after(updated));

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

//        assertTrue(user.getLastInfluenced().after(updated));

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

//        assertTrue(user.getLastInfluenced().after(updated));
    }


    @Test
    public void testGetHabitTypes() {

        ArrayList<HabitType> habitTypes = new ArrayList<>();
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        habitTypes.add(habitType);
        user.setHabitTypes(habitTypes);

        assertEquals(3, user.getHabitTypes().size());
//        assertTrue(user.getHabitTypes().contains(habitType));


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

    @Test
    public void testRemoveHabitType() {
        try {
            user.addHabitType(habitType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(user.getHabitTypes().contains(habitType));
        user.removeHabitType(habitType);
        assertFalse(user.getHabitTypes().contains(habitType));
    }



    @Test
    public void testGetType() {
        habitType.setName("wow");
        try {
            user.addHabitType(habitType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HabitType dupType = user.getType(habitType.getName());
        assertEquals(habitType, dupType);
    }

    @Test
    public void testIsHabitUsed() {
        assertFalse(user.isHabitUsed(habitType.getName()));
        try {
            user.addHabitType(habitType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(user.isHabitUsed(habitType.getName()));
    }

    @Test
    public void testAddFollowedBy() {
        assertFalse(user.getFollowedBy().contains("billy"));
        user.addFollowedBy("billy");
        assertTrue(user.getFollowedBy().contains("billy"));
    }


}
