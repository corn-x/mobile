package cornx.meetly.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import cornx.meetly.R;

public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        if (savedInstanceState == null) {
            EventFragment eventFragment = new EventFragment();
            long id = getIntent().getLongExtra(Event.tid, -1l);
            Bundle bundle = new Bundle();
            bundle.putLong(Event.tid, id);
            eventFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, eventFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
    }


}
