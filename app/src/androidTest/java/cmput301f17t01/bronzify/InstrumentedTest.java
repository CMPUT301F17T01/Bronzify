package cmput301f17t01.bronzify;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import cmput301f17t01.bronzify.activities.LoginActivity;
import cmput301f17t01.bronzify.activities.MyHabitsActivity;
import cmput301f17t01.bronzify.activities.MyHomeActivity;

/**
 * Created by Ken on 13-Nov-2017.
 */

public class InstrumentedTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private Solo solo;

    public InstrumentedTest() {
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

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testFalseLogin() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

        solo.enterText((EditText) solo.getView(R.id.enter_id), "AAAAAAAAAAA");
        solo.clickOnView(solo.getView(R.id.login_button));

        assertTrue(solo.waitForText("Invalid User ID"));
    }

    public void testCorrectLogin() {
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);

        solo.enterText((EditText) solo.getView(R.id.enter_id), "s");
        solo.clickOnView(solo.getView(R.id.login_button));

        assertTrue(solo.waitForText("Login Successful"));

        solo.assertCurrentActivity("Wrong Activity", MyHomeActivity.class);

        // OPEN NAV BAR HERE
        // CHANGE ACTIVITY HERE

        solo.assertCurrentActivity("Wrong Activity", MyHabitsActivity.class);
    }

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


//    public void testClickTweetList(){
//        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
//        solo.clickOnButton("Clear");
//
//        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
//        solo.clickOnButton("Save");
//        solo.waitForText("Test Tweet!");
//
//        solo.clickInList(0);
//        solo.assertCurrentActivity("Wrong Activity!", EditTweetActivity.class);
//
//        solo.waitForText("Test Tweet!");
//        assertTrue(solo.searchText("Test Tweet!"));
//    }
//

//}
