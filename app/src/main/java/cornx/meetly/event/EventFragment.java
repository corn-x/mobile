package cornx.meetly.event;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import cornx.meetly.R;
import cornx.meetly.app.MeetlyApplication;
import cornx.meetly.events.EventsLoadEvent;
import cornx.meetly.events.EventsProvider;
import dagger.ObjectGraph;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    @Inject
    EventsProvider eventsProvider;
    @Inject
    Bus bus;

    private Event event;
    private long id;
    @InjectView(R.id.button_addcal)
    Button button;
    public static final String eventReq = "current_event";
    private TextView textView;

    public EventFragment() {
        // Required empty public constructor

    }

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
        //id = getArguments().getLong(eventReq);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        bus.register(this);
        id = 3;
        eventsProvider.loadEvent(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.event_desc);
    }

    @OnClick(R.id.button_addcal)
    void addToCal() {
        textView.setText("42!");
    }

    @Subscribe
    public void onEventLoad(EventsLoadEvent eventsLoadEvent) {
        event = eventsLoadEvent.getEventList().get(0);
        getActivity().setTitle(event.getName());
        textView.setText(event.getDescription());
    }

}
