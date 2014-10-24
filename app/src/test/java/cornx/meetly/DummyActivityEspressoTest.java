package cornx.meetly;

import android.test.ActivityInstrumentationTestCase2;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Aleksander Ciepiela on 01/10/14.
 */
public class DummyActivityEspressoTest extends ActivityInstrumentationTestCase2<MyActivity> {


    public DummyActivityEspressoTest() {
        super(MyActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testClickingButtonShouldUpdateItsText() throws Exception {


        onView(withId(R.id.button)).perform(click())
                .check(matches(withText("clicked")));


    }
}
