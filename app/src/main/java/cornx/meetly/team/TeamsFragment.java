package cornx.meetly.team;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import cornx.meetly.app.MeetlyApplication;
import dagger.ObjectGraph;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "TeamsFragment";

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
        listView.setAdapter(teamListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamProvider.loadTeams();
    }


    @Subscribe
    public void onTeamsListLoaded(TeamsLoadEvent teams) {
        Log.d(TAG, "onTeamsListLoaded");
        teamListAdapter.setTeams(teams.getTeamList());
        setListShown(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), TeamActivity.class));
    }
}
