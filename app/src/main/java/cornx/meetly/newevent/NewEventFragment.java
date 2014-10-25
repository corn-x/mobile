package cornx.meetly.newevent;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cornx.meetly.R;


public class NewEventFragment extends Fragment {

    private TextView name;
    private TextView desc;
    private NumberPicker hours;
    private NumberPicker minutes;
    private Long teamID;
    private Button submitButton;
    private ProgressBar progress;
    public static final String teamReq = "team_id";

    public NewEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //teamID = getArguments().getLong(teamReq);
        teamID = 3l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_event, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = (TextView) view.findViewById(R.id.event_add_name);
        name.setText("");
        desc = (TextView) view.findViewById(R.id.event_add_desc);
        hours = (NumberPicker) view.findViewById(R.id.numberPicker);
        minutes = (NumberPicker) view.findViewById(R.id.numberPicker2);
        progress = (ProgressBar) view.findViewById(R.id.event_add_progress);
        progress.setVisibility(View.INVISIBLE);
        hours.setMinValue(0);
        hours.setMaxValue(23);
        minutes.setMinValue(0);
        minutes.setMaxValue(59);
        submitButton = (Button) view.findViewById(R.id.event_add_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEvent();
            }
        });
    }

    private void submitEvent() {
        if (name.getText().length() == 0) {
            showPopup("Name cannot be empty.");
            return;
        }
        long length = hours.getValue() * 60 + minutes.getValue();
        if (length == 0) {
            showPopup("The event has to be longer than 0 minutes.");
            return;
        }
        NewEvent eventToSend = new NewEvent(name.getText().toString(), desc.getText().toString(), teamID, length);
        //send event
        progress.setVisibility(View.VISIBLE);
        progress.setProgress(50);
        //wait for result
        showPopup("Event submitted."); //replace

    }

    private void showPopup(String why) {
        Toast.makeText(getActivity(), why, Toast.LENGTH_SHORT).show();
    }
}
