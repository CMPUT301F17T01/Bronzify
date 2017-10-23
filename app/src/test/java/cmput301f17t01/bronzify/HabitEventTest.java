package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kdehaan on 19/10/17.
 */

public class HabitEventTest extends TestCase {

    private HabitType type = new HabitType("Test Type");
    private Date today;
    private Date tomorrow;
    private Date nextWeek;
    private Date yesterday;

    @Test
    public void testSetUp() {

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
    public void testCheckPriority() {
        HabitEvent event = new HabitEvent(type, nextWeek);
        event.checkPriority();
        assertFalse(event.getPriority());
        event.setTime(tomorrow);
        assertTrue(event.getPriority());
        event.setTime(yesterday);
        assertFalse(event.getPriority());
    }

    @Test
    public void testCheckComplete() {
        HabitEvent event = new HabitEvent(type, nextWeek);
        event.checkComplete();
        assertFalse(event.getCompleted());
        event.setTime(yesterday);
        assertTrue(event.getCompleted());
    }


}