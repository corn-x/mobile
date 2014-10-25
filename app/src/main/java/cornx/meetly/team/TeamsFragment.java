package cornx.meetly.team;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.R;
import cornx.meetly.addteam.AddTeamActivity;
import cornx.meetly.app.MeetlyApplication;
import dagger.ObjectGraph;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "TeamsFragment";
    public static final String ACTION_REFRESH_TEAMS = "refreshTeams";
    private BroadcastReceiver receiver;

    private ListView listView;
    private TeamListAdapter teamListAdapter;
    @Inject
    TeamProvider teamProvider;
    @Inject
    Bus mBus;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectGraph objectGraph = ((MeetlyApplication) getActivity().getApplication()).getObjectGraph();
        objectGraph.inject(this);
        mBus.register(this);
        teamListAdapter = new TeamListAdapter(getActivity());
        setHasOptionsMenu(true);
        receiver = new TeamsRefreshListener();
        final IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_REFRESH_TEAMS);
        getActivity().registerReceiver(receiver, filter);
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
    public void onDestroy() {
        getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getListView();
        listView.setAdapter(teamListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamProvider.loadTeams();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.teams, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.team_add) {
            startActivity(new Intent(getActivity(), AddTeamActivity.class));
            return true;
        }
        return false;
    }

    @Subscribe
    public void onTeamsListLoaded(TeamsLoadEvent teams) {
        teamListAdapter.setTeams(teams.getTeamList());
        setListShown(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), TeamActivity.class);
        intent.putExtra(TeamActivity.TEAM_ID, id);
        intent.putExtra(TeamActivity.TEAM_NAME, teamListAdapter.getItem(position).getName());
        startActivity(intent);
    }

    private class TeamsRefreshListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) return;
            if (ACTION_REFRESH_TEAMS.equals(intent.getAction())) {
                teamProvider.loadTeams();
            }
        }
    }
}
