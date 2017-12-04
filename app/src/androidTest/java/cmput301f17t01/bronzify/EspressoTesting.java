package cmput301f17t01.bronzify;

import android.drm.DrmStore;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cmput301f17t01.bronzify.activities.LoginActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by noahkryzanowski on 2017-12-03.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTesting {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    /*@Test
    public void CreateNewHabitEvent() {
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        onView(withId(R.id.createNewHabit)).perform(click());

        onView(withId(R.id.textHabitName)).perform(typeText("Running"));
        onView(withId(R.id.textHabitName)).check(matches(withText("Running")));
        onView(withId(R.id.textHabitReason)).perform(typeText("Get fit"));
        onView(withId(R.id.textHabitReason)).check(matches(withText("Get fit")));

        onView(withId(R.id.buttonSelectDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 12, 5));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.buttonMonday)).perform(click());
        onView(withId(R.id.buttonWednesday)).perform(click());
        onView(withId(R.id.buttonFriday)).perform(click());

        onView(withId(R.id.buttonCreate)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonCreate)).perform(click());
    }*/

    @Test
    public void EditHabitType() {
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        /*onData(hasToString(startsWith("Running")))
                .inAdapterView(withId(R.id.)).atPosition(0)
                .perform(click());*/

        onView(withId(R.id.habitTypeRow))
                .check(matches(withText(containsString("Running"))));
    }
}
