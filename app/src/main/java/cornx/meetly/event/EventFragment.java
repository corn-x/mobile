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

import cornx.meetly.R;
import cornx.meetly.app.MeetlyApplication;
import dagger.ObjectGraph;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements View.OnClickListener {

    @Inject
    EventProvider eventProvider;
    @Inject
    Bus bus;

    private long id;
    Button button;
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
        id = getArguments().getLong(Event.tid, -1l);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        bus.register(this);
        eventProvider.loadEvent(id);
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
        button = (Button) view.findViewById(R.id.button_addcal);
        button.setOnClickListener(this);
    }

    @Subscribe
    public void onEventLoad(Event event) {
        getActivity().setTitle(event.getName());
        textView.setText(event.toString());
    }

    @Override
    public void onClick(View v) {
        //textView.setText(""+id);
    }
}
