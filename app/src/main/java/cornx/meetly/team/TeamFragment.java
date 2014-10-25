package cornx.meetly.team;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.R;
import cornx.meetly.app.MeetlyApplication;
import dagger.ObjectGraph;

/**
 * Created by Mateusz on 2014-10-25.
 */
public class TeamFragment extends Fragment {
    private ListView listView;
    private TextView textView;
    private MemberListAdapter memberListAdapter;
    private long teamId;

    @Inject
    MemberProvider memberProvider;
    @Inject
    Bus mBus;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamId = getArguments().getLong(TeamActivity.TEAM_ID);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        mBus.register(this);
        memberListAdapter = new MemberListAdapter(getActivity());
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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        memberProvider.loadMembers(teamId);
    }

    @Subscribe
    public void onMembersLoader(MembersLoadEvent event) {
        memberListAdapter.setMembers(event.getMemberList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_details, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_members_details_memberslist);
        textView = (TextView) view.findViewById(R.id.fragment_members_details_description);
        listView.setAdapter(memberListAdapter);
        return view;
    }


}
