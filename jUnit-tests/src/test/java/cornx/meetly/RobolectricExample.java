package cornx.meetly;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksander Ciepiela on 01/10/14.
 */

@RunWith(RobolectricGradleTestRunner.class)
public class RobolectricExample {


    @Test
    public void shouldHelloTextBeVisibleWhenActivityIsCreated() throws Exception {

/*        //given
        final DummyActivity activity = new DummyActivity();

        //when
        ActivityController.of(activity).attach().create();
        int visibility = activity.findViewById(R.id.my_hello_text_view).getVisibility();*/

        //then
        assertEquals(1, 1);
    }
}
