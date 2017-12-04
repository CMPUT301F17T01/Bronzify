package cmput301f17t01.bronzify;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Display;
import android.view.Gravity;
import android.widget.EditText;

import com.robotium.solo.Solo;

import cmput301f17t01.bronzify.activities.LoginActivity;
import cmput301f17t01.bronzify.activities.MyHabitsActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by noahkryzanowski on 2017-12-03.
 */

public class NewHabitTypeTest extends ActivityInstrumentationTestCase2<LoginActivity>{
        private Solo solo;

        public NewHabitTypeTest() {
            super(cmput301f17t01.bronzify.activities.LoginActivity.class);
        }

        /**
         * Runs at the beginning of the test
         *
         * @throws Exception
         */
        public void setUp() throws Exception {
            //super.setUp();
            solo = new Solo(getInstrumentation(), getActivity());
        }

        /*public void testStart() throws Exception {
            Activity activity = getActivity();
        }*/


        public void testCorrectLogin() {

            solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

            solo.enterText((EditText) solo.getView(R.id.enter_id), "test_user_001");
            solo.clickOnView(solo.getView(R.id.login_button));

            assertTrue(solo.waitForText("Login Successful"));

            solo.assertCurrentActivity("Wrong Activity", MyHomeActivity.class);

            solo.clickOnView(solo.getView(R.id.login_button));


            //solo.clickOnView(solo.getView(R.id.drawer_layout);

            //solo.clickOnView(solo.getView(R.id.drawer_layout));

            // OPEN NAV BAR HERE
            // CHANGE ACTIVITY HERE

            solo.assertCurrentActivity("Wrong Activity", MyHabitsActivity.class);

        }

        /*public void testNavBarSwitch() {
            solo.assertCurrentActivity("Wrong Activity", MyHomeActivity.class);

            solo.clickOnView(solo.getView(R.id.drawer_layout));
        }*/

        /**
         * Runs at the end of the tests
         *
         * @throws Exception
         */
        @Override
        public void tearDown() throws Exception{
            //super.tearDown();
            solo.finishOpenedActivities();
        }
}
