package cornx.meetly.team;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 25/10/2014.
 */
public class TeamProviderDummy implements TeamProvider {

    private static final String TAG = "TeamProviderDummy";

    Bus bus;

    public TeamProviderDummy(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void loadTeams() {
        List<Team> t = new ArrayList<>();
        t.add(new Team("A"));
        t.add(new Team("B"));
        t.add(new Team("C"));
        bus.post(new TeamsLoadEvent(t));
    }

    @Override
    public Team getTeam(long teamId) {
        Team t = new Team("A team");
        t.setDescription("a description");
        return t;
    }
}
