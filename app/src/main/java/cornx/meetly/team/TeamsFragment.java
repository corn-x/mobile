package cornx.meetly.team;


import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends ListFragment {

    private ListView listView;
    private TeamListAdapter teamListAdapter;
    private TeamProvider teamProvider;

    public TeamsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamListAdapter = new TeamListAdapter(getActivity());
        teamProvider = new TeamProviderDummy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getListView();
        listView.setAdapter(teamListAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        teamListAdapter.setTeams(teamProvider.getTeams());
        setListShown(true);
    }
}
