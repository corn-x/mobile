package cornx.meetly.events;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.R;
import cornx.meetly.app.MeetlyApplication;
import cornx.meetly.event.EventActivity;
import dagger.ObjectGraph;


/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public static final String TEAM_ID = "teamID";

    private ListView listView;
    private EventsListAdapter eventsListAdapter;
    @Inject
    EventsProvider eventsProvider;
    @Inject
    Bus bus;
    private long teamID;

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        bus.unregister(this);
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        bus.register(this);
        teamID = getArguments() == null ? -1L : getArguments().getLong(TEAM_ID, -1l);
        eventsListAdapter = new EventsListAdapter(getActivity());
        if (teamID != -1) setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (teamID != -1) inflater.inflate(R.menu.teamevents, menu);
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
        if (teamID != -1) {
            eventsProvider.loadEvents(teamID);
        } else {
            eventsProvider.loadAllEventsForUser();
        }
    }

    @Subscribe
    public void onEventsLoadFinished(EventsLoadEvent eventsLoadEvent) {
        eventsListAdapter.setEvents(eventsLoadEvent.getEventList());
        setListShown(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), EventActivity.class));
    }
}
