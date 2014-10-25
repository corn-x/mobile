package cornx.meetly.team;

import java.util.List;

/**
 * Created by Dawid on 25/10/2014.
 */
public interface TeamProvider {
    public List<Team> getTeams();

    public Team getTeam(long teamId);
}
