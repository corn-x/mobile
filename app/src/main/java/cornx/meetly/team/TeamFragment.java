package cornx.meetly.team;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.R;
import cornx.meetly.addmember.AddMemberActivity;
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
        this.setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.team_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.member_add) {
            Intent intent = new Intent(getActivity(), AddMemberActivity.class);
            intent.putExtra(TeamActivity.TEAM_ID, teamId);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Subscribe
    public void onMembersLoader(MembersLoadEvent event) {
        memberListAdapter.setMembers(event.getMemberList());
        Log.d("onMEmberLoad", event.getMemberList().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_details, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_members_details_memberslist);
        listView.setAdapter(memberListAdapter);
        return view;
    }


}
