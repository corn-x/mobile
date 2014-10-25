package cornx.meetly.events;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.app.MeetlyApplication;
import cornx.meetly.team.MemberProvider;
import cornx.meetly.team.MembersLoadEvent;
import dagger.ObjectGraph;


/**
 * Created by Mateusz on 2014-10-25.
 */
public class EventsFragment extends ListFragment {

    private ListView listView;
    private EventsListAdapter eventsListAdapter;

    @Inject
    EventsProvider eventsProvider;
    @Inject
    Bus mBus;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        mBus.register(this);
        eventsListAdapter = new EventsListAdapter(getActivity());
    }


    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        mBus.unregister(this);
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getListView();
        listView.setAdapter(eventsListAdapter);
    }

    @Subscribe
    public void onEventsLoader(EventsLoadEvent event) {
        eventsListAdapter.setEvents(event.getEventList());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventsProvider.loadEvents();
        setListShown(true);
    }
}
