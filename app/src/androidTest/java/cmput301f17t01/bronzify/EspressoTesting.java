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
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ThrowOnExtraProperties;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Calendar;
import java.util.Date;

import cmput301f17t01.bronzify.activities.LoginActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by noahkryzanowski on 2017-12-03.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class EspressoTesting {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    /**
     * This test case will create the users with the name "test_user_001", "test_user_001"
     * and "follow_user_001". It is commented out, because the users are already created.
     * It can easily be run to create the users in a single step.
     *
     */
    @Test
    public void test0CreateUsers() {
        //Create user "test_user_001"
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.enter_id)).perform(clearText());

        //Create user "follow_user_001"
        onView(withId(R.id.enter_id)).perform(typeText("follow_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("follow_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.enter_id)).perform(clearText());

        //Create user "follow_user_002"
        onView(withId(R.id.enter_id)).perform(typeText("follow_user_002"));
        onView(withId(R.id.enter_id)).check(matches(withText("follow_user_002")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
    }

    /**
     * This test case will create a new habit type, with the name Running. It will
     * log in and log out, and ensure that the data entered is correct
     *
     */
    @Test
    public void test1CreateNewHabitType() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        //Create a new habit
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

        //Logout
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));
    }

    /**
     * This test case will edit the habit type that was created in test1. It will change the name
     * of the habit from "Running" to "Skating", as well as removing Wednesday from the recurring
     * days every week.
     *
     */
    @Test
    public void test2EditHabitType() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        //Click on the correct habit in the list
        onView(withId(R.id.habitTypeRow)).check(matches(withText(containsString("Running"))));
        onView(withId(R.id.habitTypeRow)).perform(click());

        //Edit the parameters of the habit
        onView(withId(R.id.buttonEdit)).perform(click());

        //onData(withId(R.id.textHabitName)).atPosition(0).perform(click());
        onView(withId(R.id.textHabitName)).perform(replaceText("Skating"));

        //onView(withText("Running")).perform(click());
        //onView(withText("Running")).perform(replaceText("Skating"));

        onView(withId(R.id.buttonEdit)).perform(closeSoftKeyboard());

        onView(withId(R.id.buttonWednesday)).perform(click());

        onView(withId(R.id.buttonEdit)).perform(click());

        //Logout
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));
    }

    /**
     * This test case will delete the habit type that was created in test2. The habit event skating
     * will no longer exist after it has been run.
     *
     */
    @Test
    public void test3DeleteHabitType() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        //Click on the correct habit in the list
        onView(withId(R.id.habitTypeRow)).check(matches(withText(containsString("Skating"))));
        onView(withId(R.id.habitTypeRow)).perform(click());

        //Delete the habit
        onView(withId(R.id.buttonEdit)).perform(click());
        onView(withId(R.id.buttonDelete)).perform(click());

        //Logout
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));
    }

    /**
     * This test case will log in as "follow_user_001" and will follow the user "test_user_001".
     * Then "test_user_001" will log into their account to accept the follow request.
     *
     */
    @Test
    public void test4FollowUser() {
        //Login with the first user
        onView(withId(R.id.enter_id)).perform(typeText("follow_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("follow_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyProfile));

        onView(withId(R.id.followButton)).perform(click());
        onView(allOf(withClassName(endsWith("EditText")))).perform(typeText("followed_user_002"));
        onView(withText("OK")).perform(click());

        //Logout from the first users account
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));

        //Login with the second user
        //onView(withId(R.id.enter_id)).perform(typeText("followed_user_002"));
        //onView(withId(R.id.enter_id)).check(matches(withText("followed_user_002")));
        onView(withId(R.id.enter_id)).perform(typeText("followed_user_002"));
        onView(withId(R.id.enter_id)).check(matches(withText("followed_user_002")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyProfile));

        //onData(allOf(withText("Tommy"), hasSibling(withText("Tommy")))).atPosition(0).perform(click());
        //onData(allOf(withId(R.id.acceptFollow), hasSibling(withText("Tommy")))).atPosition(0).perform(click());
        //onData(allOf(withId(R.id.acceptFollow), hasSibling(withText("1")), hasSibling(withId(R.id.followReqRecycler)))).perform(click());
        onView(withId(R.id.acceptFollow)).perform(click());
        //onData(withText("Tommy")).perform(click());
        //onView(withId(R.id.habitTypeRow)).perform(click());
    }

    /**
     * This test case will add two new events to the user "test_user_002" and demonstrate that
     * all the events that will occur today will display on the MyHome tab. The first event will
     * not be completed and the second event will be completed.
     *
     */
    @Test
    public void test5Today() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_002"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_002")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHabits));

        //Create the first habit
        onView(withId(R.id.createNewHabit)).perform(click());
        onView(withId(R.id.textHabitName)).perform(typeText("Rowing"));
        onView(withId(R.id.textHabitName)).check(matches(withText("Rowing")));
        onView(withId(R.id.textHabitReason)).perform(typeText("Get fit"));
        onView(withId(R.id.textHabitReason)).check(matches(withText("Get fit")));
        onView(withId(R.id.buttonSelectDate)).perform(click());
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 12, day));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.buttonMonday)).perform(click());
        onView(withId(R.id.buttonCreate)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonCreate)).perform(click());

        //Create the second habit
        onView(withId(R.id.createNewHabit)).perform(click());
        onView(withId(R.id.textHabitName)).perform(typeText("Swimming"));
        onView(withId(R.id.textHabitName)).check(matches(withText("Swimming")));
        onView(withId(R.id.textHabitReason)).perform(typeText("Too cold outside"));
        onView(withId(R.id.textHabitReason)).check(matches(withText("Too cold outside")));
        onView(withId(R.id.buttonSelectDate)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2017, 12, day));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.buttonMonday)).perform(click());
        onView(withId(R.id.buttonCreate)).perform(closeSoftKeyboard());
        onView(withId(R.id.buttonCreate)).perform(click());

        //Change to MyHome
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHome));

        //Logout
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));
    }

    /**
     * This test case will show all of the past events that the user has had, completed or not.
     *
     */
    @Test
    public void test6HabitHistory() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_001"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_001")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyHistory));

        //Logout
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.LogOut));
    }

    /**
     * This test case will add and delete a user, validating that the user account is
     * successfully deleted.
     *
     */
    @Test
    public void test7AddDeleteUser() {
        //Login
        onView(withId(R.id.enter_id)).perform(typeText("test_user_003"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_003")));
        onView(withId(R.id.register_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.MyProfile));

        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.enter_id)).perform(typeText("test_user_003"));
        onView(withId(R.id.enter_id)).check(matches(withText("test_user_003")));
        onView(withId(R.id.login_button)).perform(closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        onView(withClassName(endsWith("TextView"))).check(matches(withText("Invalid User ID")));
    }
}
