package cmput301f17t01.bronzify;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

import cmput301f17t01.bronzify.adapters.HabitEventAdapter;
import cmput301f17t01.bronzify.adapters.HabitTypeAdapter;
import cmput301f17t01.bronzify.models.AppLocale;
import cmput301f17t01.bronzify.models.HabitEvent;
import cmput301f17t01.bronzify.models.HabitType;
import cmput301f17t01.bronzify.models.User;

/**
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
        assertTrue(Boolean.TRUE);
        HabitType type = new HabitType("name" , "reason", new Date(), daysOfWeek);
        Gson gsonType = new GsonBuilder().registerTypeAdapter(HabitType.class,
                new HabitTypeAdapter()).create();

//        String json = gsonType.toJson(type);
//        HabitType type2 = gsonType.fromJson(json, HabitType.class);


    }
}
