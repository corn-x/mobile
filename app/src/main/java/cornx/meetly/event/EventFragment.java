package cornx.meetly.event;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cornx.meetly.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private EventProvider eventProvider;
    private Event event;
    private long id;
    public static final String eventID = "current_event";
    private TextView textView;

    public EventFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //id = getArguments().getLong(eventID);
        id = 3;
        eventProvider = new EventProviderDummy();
        event = eventProvider.getEvent(id);
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
        textView.setText(event.getDescription());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //what about name? should be in activity header
    }
}
