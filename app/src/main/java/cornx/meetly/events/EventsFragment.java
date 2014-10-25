package cornx.meetly.events;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cornx.meetly.event.EventActivity;


/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private EventsListAdapter eventsListAdapter;
    private EventsProvider eventsProvider;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventsListAdapter = new EventsListAdapter(getActivity());
        eventsProvider = new EventsProviderDummy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getListView();
        listView.setAdapter(eventsListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventsListAdapter.setEvents(eventsProvider.getEvents());
        setListShown(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), EventActivity.class));
    }
}
