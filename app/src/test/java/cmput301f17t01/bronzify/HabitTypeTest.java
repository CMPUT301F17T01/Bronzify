package cmput301f17t01.bronzify;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by kdehaan on 19/10/17.
 */

public class HabitTypeTest extends TestCase {

    private HabitType type;

    @Test
    public void testSetUp() {
        type = new HabitType("Testing Type");
        assertTrue(type.getName().equals("Testing Type"));
    }

    @Test
    public void testAddHabitEvent() {
        event = new HabitEvent()
    }
}