package cornx.meetly.team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 25/10/2014.
 */
public class TeamProviderDummy implements TeamProvider {
    @Override
    public List<Team> getTeams() {
        List<Team> t = new ArrayList<>();
        t.add(new Team("A"));
        t.add(new Team("B"));
        t.add(new Team("C"));
        return t;
    }
}
