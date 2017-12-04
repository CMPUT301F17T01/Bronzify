package cmput301f17t01.bronzify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Date;
import cmput301f17t01.bronzify.adapters.HabitEventAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.User;

/*
 * Created by kdehaan on 13/11/17.
 */

public class HabitEventTest extends TestCase {
    Boolean[] daysOfWeek = {false, false, false, false, false, false, false,};
//    private HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);





    @Test
    public void testSetUp() {
        AppLocale appLocale = AppLocale.getInstance();
        User user = new User("userID");
        appLocale.setUser(user);
    }

    @Test
    public void testJson() {
        HabitEvent event = new HabitEvent(new Date(), "typename");
        event.markCompleted();
        event.setComment("nice comment");
        Gson gsonEvent = new GsonBuilder().registerTypeAdapter(HabitEvent.class,
                new HabitEventAdapter()).create();

        String json = gsonEvent.toJson(event);
        HabitEvent event2 = gsonEvent.fromJson(json, HabitEvent.class);
        assertEquals(event.getUserID(), event2.getUserID());
        assertEquals(event.getComment(), event2.getComment());
        assertEquals(event.getCompleted(), event2.getCompleted());
        assertTrue((event.getCompletedDate().getTime() - event2.getCompletedDate().getTime()) < 1000);
        assertTrue((event.getGoalDate().getTime() - event2.getGoalDate().getTime()) < 1000);
        assertEquals(event.getImage(), event2.getImage());
        assertEquals(event.getLocation(), event2.getLocation());
        assertEquals(event.getHabitType(), event2.getHabitType());

    }





}

