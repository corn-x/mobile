package cornx.meetly.newevent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import cornx.meetly.R;

/**
 * @author Aleksander Ciepiela
 */
public class NewEventActivity extends Activity {

    public static final String TEAM_ID = "teamId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        if (savedInstanceState == null) {
            final long id = getIntent().getLongExtra(TEAM_ID, -1L);
            Bundle bundle = new Bundle();
            bundle.putLong(TEAM_ID, id);
            Fragment fragment = new NewEventFragment();
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }
}
