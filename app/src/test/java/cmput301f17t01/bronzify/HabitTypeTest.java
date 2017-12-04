package cmput301f17t01.bronzify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Arrays;
import java.util.Date;
import cmput301f17t01.bronzify.adapters.HabitTypeAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
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
        HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);
        Gson gsonType = new GsonBuilder().registerTypeAdapter(HabitType.class,
                new HabitTypeAdapter()).create();

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
}
