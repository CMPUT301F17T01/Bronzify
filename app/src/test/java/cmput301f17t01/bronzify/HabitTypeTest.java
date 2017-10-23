package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kdehaan on 19/10/17.
 */

public class HabitTypeTest extends TestCase {

    private HabitType type;
    private Date today;
    private Date tomorrow;
    private Date nextWeek;
    private Date yesterday;

    @Test
    public void testSetUp() {
        type = new HabitType("Testing Type");
        assertTrue(type.getName().equals("Testing Type"));

        Calendar c = Calendar.getInstance();

        today = new Date();

        tomorrow = new Date();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();

        nextWeek = new Date();
        c.setTime(nextWeek);
        c.add(Calendar.DATE, 7);
        nextWeek = c.getTime();

        yesterday = new Date();
        c.setTime(yesterday);
        c.add(Calendar.DATE, -1);
        yesterday = c.getTime();
    }

    @Test
    public void testAddHabitEvent() {
        HabitEvent event = new HabitEvent(type, tomorrow);
        assertFalse(type.getHabitEvents().contains(event));
        type.addHabitEvent(event);
        assertTrue(type.getHabitEvents().contains(event));
        type.removeHabitEvent(event);
    }

    @Test
    public void testRemoveHabitEvent() {
        HabitEvent event = new HabitEvent(type, tomorrow);
        type.addHabitEvent(event);
        assertTrue(type.getHabitEvents().contains(event));
        type.removeHabitEvent(event);
        assertFalse(type.getHabitEvents().contains(event));
    }

}