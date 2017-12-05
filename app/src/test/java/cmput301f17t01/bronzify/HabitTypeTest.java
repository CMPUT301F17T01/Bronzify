package cmput301f17t01.bronzify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import cmput301f17t01.bronzify.adapters.HabitTypeAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/*
 * Created by kdehaan on 13/11/17.
 */

public class HabitTypeTest extends TestCase {
    Boolean[] daysOfWeek = {false, true, false, false, true, false, false,};

    @Test
    public void testSetUp() {
        AppLocale appLocale = AppLocale.getInstance();
        User user = new User("userID");
        appLocale.setUser(user);
    }

    @Test
    public void testJson() {

        Gson gsonType = new GsonBuilder().registerTypeAdapter(HabitType.class,
                new HabitTypeAdapter()).create();
        HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);
        String json = gsonType.toJson(type);
        HabitType type2 = gsonType.fromJson(json, HabitType.class);
        assertTrue((type.getDateToStart().getTime() - type2.getDateToStart().getTime()) < 1000);
        assertEquals(Arrays.toString(type.getDaysOfWeek()), Arrays.toString(type2.getDaysOfWeek()));
        assertEquals(type.getHabitEvents().size(), type2.getHabitEvents().size()); // relying on habiteventtest to confirm this
        assertEquals(type.getName(), type2.getName());
        assertEquals(type.getNumCompleted(), type2.getNumCompleted());
        assertEquals(type.getReason(), type2.getReason());
        assertEquals(type.getUserID(), type2.getUserID());
        assertEquals(type.getNumUncompleted(), type2.getNumUncompleted());


    }

    @Test
    public void testGenerateNewEvents() {
        HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);
        ArrayList<HabitEvent> events = type.getHabitEvents();
        Date datetoStart = type.getDateToStart();
        type.setHabitEvents(new ArrayList<HabitEvent>());
        assertNotSame(events, type.getHabitEvents());
        type.generateNewEvents(datetoStart);
        assertEquals(events.size(), type.getHabitEvents().size());
    }

    @Test
    public void testGetCompletionRatio() {
        HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);
        type.setNumCompleted(0);
        type.setNumUncompleted(0);
        assertEquals(100, type.getCompletionRatio());
        Random rand = new Random();
        Integer completed = rand.nextInt((100 - 1) + 1) + 1;
        Integer uncompleted = rand.nextInt((100 - 1) + 1) + 1;
        type.setNumCompleted(completed);
        type.setNumUncompleted(uncompleted);
        int ratio = completed / (completed + uncompleted);
        assertEquals(ratio, type.getCompletionRatio());
    }

}
